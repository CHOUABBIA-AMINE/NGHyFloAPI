# Package Relocation Strategy

## Relocation Principles
- Relocate by bounded-context ownership, not by technical convenience.
- Keep API/application/domain/infrastructure layering intact during moves.
- Use anti-corruption adapters for cross-context transitions.

## Required Relocations
1. `shared.domain.value.EmployeeId` → `modules.organization.domain.value` ownership.
2. `shared.domain.value.StructureId` → `modules.organization.domain.value` ownership.
3. `shared.api.RequestCorrelationFilter` → `platform.observability.correlation` ownership.
4. `identityaccess.infrastructure.persistence.InMemoryUserRepository` → test-support package or profile-scoped testing adapter package.

## Migration Mechanics
- Move in two-step pattern:
  1) introduce canonical import path + adapters,
  2) delete old location once reference count is zero.
- Preserve serialization and API compatibility during migration window.

## Exit Criteria Per Relocation
- No direct references to old package.
- No bean duplication or ordering conflicts.
- Architecture tests enforce new ownership.
