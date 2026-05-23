# Duplication Report (Phase 2)

## Scope
Repository-wide audit of source and test packages under `src/main/java` and `src/test/java`.

## Confirmed Duplications

### 1) `EmployeeId` value object duplicated across shared kernel and organization context
- Files:
  - `src/main/java/dz/sh/trc/nghyflo/shared/domain/value/EmployeeId.java`
  - `src/main/java/dz/sh/trc/nghyflo/modules/organization/domain/value/EmployeeId.java`
- Overlap:
  - Both classes model employee identity and expose factory/validation semantics around string identifiers.
- Canonical implementation:
  - `modules/organization/domain/value/EmployeeId` should be canonical for organization bounded context.
- Obsolete implementation:
  - `shared/domain/value/EmployeeId` is context-leaking unless explicitly required cross-module.
- Refactor complexity:
  - **Medium** (imports, serialization contracts, and tests across modules).

### 2) `StructureId` value object duplicated across shared kernel and organization context
- Files:
  - `src/main/java/dz/sh/trc/nghyflo/shared/domain/value/StructureId.java`
  - `src/main/java/dz/sh/trc/nghyflo/modules/organization/domain/value/StructureId.java`
- Overlap:
  - Duplicate identifier concept for organization structures.
- Canonical implementation:
  - Organization-local `StructureId`.
- Obsolete implementation:
  - Shared-kernel `StructureId` unless used by multiple bounded contexts with explicit shared contract.
- Refactor complexity:
  - **Medium**.

### 3) Correlation/request-filter concern duplicated in `platform` and `shared/api`
- Files:
  - `src/main/java/dz/sh/trc/nghyflo/platform/observability/correlation/RequestCorrelationFilter.java`
  - `src/main/java/dz/sh/trc/nghyflo/shared/api/RequestCorrelationFilter.java`
- Overlap:
  - Two similarly named filters handling correlation enrichment at web boundary.
- Canonical implementation:
  - Platform observability filter (`platform/observability/correlation`) due to ownership.
- Obsolete implementation:
  - `shared/api/RequestCorrelationFilter` if not intentionally variant-scoped.
- Refactor complexity:
  - **Medium-High** (ordering in filter chain, bean naming collisions, test updates).

### 4) Actor spoofing tests duplicated in two packages
- Files:
  - `src/test/java/dz/sh/trc/nghyflo/platform/ActorSpoofingPreventionTest.java`
  - `src/test/java/dz/sh/trc/nghyflo/platform/security/authentication/ActorSpoofingPreventionTest.java`
- Overlap:
  - Same security scenario appears twice with package drift.
- Canonical implementation:
  - Security package test (`platform/security/authentication`).
- Obsolete implementation:
  - Root `platform` package duplicate.
- Refactor complexity:
  - **Low**.

### 5) Audit chain hasher tests duplicated in two packages
- Files:
  - `src/test/java/dz/sh/trc/nghyflo/platform/AuditChainHasherTest.java`
  - `src/test/java/dz/sh/trc/nghyflo/platform/audit/AuditChainHasherTest.java`
- Overlap:
  - Duplicate test class by capability and subject-under-test.
- Canonical implementation:
  - `platform/audit` package test.
- Obsolete implementation:
  - Root `platform` package duplicate.
- Refactor complexity:
  - **Low**.

### 6) Outbox event tests duplicated in two packages
- Files:
  - `src/test/java/dz/sh/trc/nghyflo/platform/OutboxEventTest.java`
  - `src/test/java/dz/sh/trc/nghyflo/platform/events/outbox/OutboxEventTest.java`
- Overlap:
  - Duplicate validations for outbox event behavior.
- Canonical implementation:
  - `platform/events/outbox` package test.
- Obsolete implementation:
  - Root `platform` package duplicate.
- Refactor complexity:
  - **Low**.

## Probable Duplications (Require usage tracing)
- `ApiResponse`, `EventResponse`, `ErrorResponse`, `ValidationErrorResponse` in `shared/api` may overlap with exception handler payload generation in `platform/exception`.
- `TelemetryExceptionHandler`, `IntegrationExceptionHandler`, `SecurityExceptionHandler`, and `GlobalExceptionHandler` likely overlap in fallback behavior.

## Summary Risk
Duplication is concentrated in:
- Cross-context value objects (domain boundary erosion).
- HTTP correlation/observability filters.
- Test suites (false confidence + maintenance drag).
