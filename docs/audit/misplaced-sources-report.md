# Misplaced Sources Report (Phase 2)

## 1) Domain identifiers in shared kernel that are organization-specific
- Misplaced files:
  - `shared/domain/value/EmployeeId.java`
  - `shared/domain/value/StructureId.java`
- Why misplaced:
  - These names are organization-domain specific, not generic cross-context primitives.
- Correct bounded context:
  - `modules.organization`.
- Correct package:
  - `modules/organization/domain/value`.
- Migration impact:
  - **Medium** (import and event payload contract updates).

## 2) Request correlation filter in `shared/api`
- Misplaced file:
  - `shared/api/RequestCorrelationFilter.java`
- Why misplaced:
  - Correlation is observability/platform concern, not shared API contract concern.
- Correct bounded context:
  - `platform.observability`.
- Correct package:
  - `platform/observability/correlation`.
- Migration impact:
  - **Medium-High** (filter registration ordering and integration tests).

## 3) In-memory repository inside identityaccess infrastructure without profile isolation
- Misplaced file:
  - `modules/identityaccess/infrastructure/persistence/InMemoryUserRepository.java`
- Why misplaced:
  - Test/demonstration adapter appears in production package tree; can accidentally become runtime bean.
- Correct bounded context:
  - identityaccess test support.
- Correct package:
  - `src/test/...` or `infrastructure/persistence/testing` with strict profile guards.
- Migration impact:
  - **Low-Medium**.

## 4) Bootstrap readiness probes mixed with module-level readiness semantics
- Potentially misplaced files:
  - `bootstrap/health/*ReadinessProbe.java`
- Why misplaced:
  - Mixed concerns of startup orchestration and domain-module readiness can blur ownership.
- Correct bounded context:
  - platform operations/health or dedicated runtime module.
- Correct package:
  - `platform/operations/health` (or explicit runtime health package).
- Migration impact:
  - **Medium**.
