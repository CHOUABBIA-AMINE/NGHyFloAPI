/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : IdentityApplicationService
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Service
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.application.service
 *
 * @Description : Coordinates identity-access user and role application use cases.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.application.service;

import dz.sh.trc.nghyflo.modules.identityaccess.application.command.AssignRoleCommand;
import dz.sh.trc.nghyflo.modules.identityaccess.application.command.CreateUserCommand;
import dz.sh.trc.nghyflo.modules.identityaccess.application.dto.UserResponse;
import dz.sh.trc.nghyflo.modules.identityaccess.application.security.PasswordEncoderPort;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.model.User;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.repository.UserRepository;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.Username;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.time.Instant;
import java.util.List;

public class IdentityApplicationService {
    private final UserRepository userRepository;
    private final PasswordEncoderPort credentialEncoder;

    public IdentityApplicationService(UserRepository userRepository, PasswordEncoderPort credentialEncoder) {
        if (userRepository == null) {
            throw new IllegalArgumentException("userRepository is required");
        }
        if (credentialEncoder == null) {
            throw new IllegalArgumentException("credentialEncoder is required");
        }
        this.userRepository = userRepository;
        this.credentialEncoder = credentialEncoder;
    }

    public UserResponse createUser(CreateUserCommand command) {
        User user = new User(
                UserId.newId(),
                new Username(command.username()),
                credentialEncoder.encode(command.rawPassword()),
                Instant.now()
        );
        User saved = userRepository.save(user);
        return new UserResponse(saved.id().value(), saved.username().value(), saved.roles());
    }

    public UserResponse assignRole(AssignRoleCommand command) {
        User user = userRepository.findById(UserId.of(command.userId())).orElseThrow();
        user.assignRole(command.roleCode());
        User saved = userRepository.save(user);
        return new UserResponse(saved.id().value(), saved.username().value(), saved.roles());
    }

    public List<UserResponse> users() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponse(user.id().value(), user.username().value(), user.roles()))
                .toList();
    }
}
