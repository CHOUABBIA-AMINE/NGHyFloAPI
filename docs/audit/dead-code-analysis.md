# Dead Code Indicators (Phase 2)

## Confirmed Indicators

### 1) Duplicate tests likely redundant
- `platform/ActorSpoofingPreventionTest` vs `platform/security/authentication/ActorSpoofingPreventionTest`
- `platform/AuditChainHasherTest` vs `platform/audit/AuditChainHasherTest`
- `platform/OutboxEventTest` vs `platform/events/outbox/OutboxEventTest`

Indicator: probable redundant assertions with package drift.

## Probable Indicators (Need static usage graph)

### 2) Shared identifier value objects not demonstrably consumed cross-context
- `shared/domain/value/EmployeeId`
- `shared/domain/value/StructureId`

Indicator: may be obsolete after modularization into organization bounded context.

### 3) API response wrappers potentially overlapping
- `shared/api/EventResponse`, `ApiResponse`, `ErrorResponse`, `ValidationErrorResponse`

Indicator: if controllers standardize on a subset, remaining wrappers may be vestigial.

### 4) In-memory persistence adapter in main tree
- `modules/identityaccess/infrastructure/persistence/InMemoryUserRepository`

Indicator: if no active profile wiring references it, adapter is dead/experimental.

## Dead Module Signals
- No dedicated scheduler package beyond outbox cleanup job.
- No explicit workflow module despite workflow events in shared domain.
- Several architecture docs signal planned components not fully implemented.
