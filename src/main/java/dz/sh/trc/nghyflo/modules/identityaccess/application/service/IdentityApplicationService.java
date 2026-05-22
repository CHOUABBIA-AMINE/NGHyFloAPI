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
        User user = userRepository.findById(new UserId(command.userId())).orElseThrow();
        user.assignRole(command.roleCode());
        userRepository.save(user);
        return new UserResponse(user.id().value(), user.username().value(), user.roles());
    }

    public List<UserResponse> users() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponse(user.id().value(), user.username().value(), user.roles()))
                .toList();
    }
}
