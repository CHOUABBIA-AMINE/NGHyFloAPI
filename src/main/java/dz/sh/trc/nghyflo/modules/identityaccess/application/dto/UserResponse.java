package dz.sh.trc.nghyflo.modules.identityaccess.application.dto;

import java.util.Set;

public record UserResponse(String id, String username, Set<String> roles) {}
