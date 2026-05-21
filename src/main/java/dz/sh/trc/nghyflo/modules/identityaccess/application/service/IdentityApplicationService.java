package dz.sh.trc.nghyflo.modules.identityaccess.application.service;

import dz.sh.trc.nghyflo.modules.identityaccess.application.command.AssignRoleCommand;
import dz.sh.trc.nghyflo.modules.identityaccess.application.command.CreateUserCommand;
import dz.sh.trc.nghyflo.modules.identityaccess.application.dto.UserResponse;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.model.User;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.repository.UserRepository;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.PasswordHash;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.Username;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.time.Instant;
import java.util.List;

public class IdentityApplicationService {
    private final UserRepository userRepository;

    public IdentityApplicationService(UserRepository userRepository) { this.userRepository = userRepository; }

    public UserResponse createUser(CreateUserCommand command) {
        User user = new User(UserId.newId(), new Username(command.username()), new PasswordHash(hash(command.rawPassword())), Instant.now());
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
        return userRepository.findAll().stream().map(u -> new UserResponse(u.id().value(), u.username().value(), u.roles())).toList();
    }

    private String hash(String rawPassword) { return "{bcrypt}" + Integer.toHexString(rawPassword.hashCode()); }
}
