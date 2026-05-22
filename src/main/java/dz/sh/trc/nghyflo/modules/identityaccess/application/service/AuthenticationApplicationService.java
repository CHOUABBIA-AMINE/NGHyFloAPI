/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AuthenticationApplicationService
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Service
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.application.service
 *
 * @Description : Coordinates login, renewal, logout, and session lifecycle use cases.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.application.service;

import dz.sh.trc.nghyflo.modules.identityaccess.application.auth.RenewalRepositoryPort;
import dz.sh.trc.nghyflo.modules.identityaccess.application.auth.SessionArtifactIssuer;
import dz.sh.trc.nghyflo.modules.identityaccess.application.auth.SessionRepositoryPort;
import dz.sh.trc.nghyflo.modules.identityaccess.application.command.LoginCommand;
import dz.sh.trc.nghyflo.modules.identityaccess.application.command.LogoutCommand;
import dz.sh.trc.nghyflo.modules.identityaccess.application.command.RefreshSessionCommand;
import dz.sh.trc.nghyflo.modules.identityaccess.application.dto.AuthSessionResponse;
import dz.sh.trc.nghyflo.modules.identityaccess.application.dto.LogoutResponse;
import dz.sh.trc.nghyflo.modules.identityaccess.application.security.PasswordEncoderPort;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.model.RefreshToken;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.model.User;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.model.UserSession;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.repository.UserRepository;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.SessionId;
import java.time.Duration;
import java.time.Instant;

public class AuthenticationApplicationService {
    private static final Duration ACCESS_TTL = Duration.ofMinutes(15);
    private static final Duration RENEWAL_TTL = Duration.ofHours(12);

    private final UserRepository users;
    private final PasswordEncoderPort credentialEncoder;
    private final SessionRepositoryPort sessions;
    private final RenewalRepositoryPort renewals;
    private final SessionArtifactIssuer issuer;

    public AuthenticationApplicationService(
            UserRepository users,
            PasswordEncoderPort credentialEncoder,
            SessionRepositoryPort sessions,
            RenewalRepositoryPort renewals,
            SessionArtifactIssuer issuer
    ) {
        this.users = users;
        this.credentialEncoder = credentialEncoder;
        this.sessions = sessions;
        this.renewals = renewals;
        this.issuer = issuer;
    }

    public AuthSessionResponse login(LoginCommand command) {
        User user = users.findByUsername(command.username())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        if (!credentialEncoder.matches(command.credential(), user.passwordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        UserSession session = sessions.save(UserSession.open(user.id()));
        return issue(user, session);
    }

    public AuthSessionResponse refresh(RefreshSessionCommand command) {
        RefreshToken renewal = renewals.findByArtifact(command.refreshToken())
                .orElseThrow(() -> new IllegalArgumentException("Invalid renewal artifact"));
        if (!renewal.usableAt(Instant.now())) {
            throw new IllegalStateException("Renewal artifact is not usable");
        }
        UserSession session = sessions.findById(renewal.sessionId())
                .orElseThrow(() -> new IllegalStateException("Session not found"));
        if (!session.active()) {
            throw new IllegalStateException("Session is not active");
        }
        User user = users.findById(session.userId())
                .orElseThrow(() -> new IllegalStateException("User not found"));
        return issue(user, session);
    }

    public LogoutResponse logout(LogoutCommand command) {
        SessionId sessionId = SessionId.of(command.sessionId());
        UserSession session = sessions.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));
        if (session.active()) {
            session.close(Instant.now());
            sessions.save(session);
        }
        return new LogoutResponse(session.id().value(), true);
    }

    private AuthSessionResponse issue(User user, UserSession session) {
        Instant accessExpiresAt = Instant.now().plus(ACCESS_TTL);
        Instant renewalExpiresAt = Instant.now().plus(RENEWAL_TTL);
        String accessArtifact = issuer.issueAccessArtifact(user, session, accessExpiresAt);
        String renewalArtifact = issuer.issueRenewalArtifact(user, session, renewalExpiresAt);
        renewals.save(new RefreshToken(issuer.hashRenewalArtifact(renewalArtifact), session.id(), renewalExpiresAt));
        return new AuthSessionResponse(
                user.id().value(),
                user.username().value(),
                session.id().value(),
                accessArtifact,
                renewalArtifact,
                accessExpiresAt,
                user.roles()
        );
    }
}
