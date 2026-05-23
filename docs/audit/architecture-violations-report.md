# Architecture Violations Report (Phase 2)

## High Severity

### A) Shared kernel polluted by context-specific identifiers
- Violation:
  - Shared kernel contains `EmployeeId` and `StructureId`, while organization module has its own variants.
- Impact:
  - Invalid dependency direction pressure: modules may depend on shared versions and bypass context language.
- Scalability risk:
  - Increasing semantic drift; future modules may reuse wrong ID types.

### B) Layer leakage risk: duplicate correlation filters
- Violation:
  - Correlation handling exists in both shared API and platform observability.
- Impact:
  - Competing filter registration, duplicated side-effects, unstable trace propagation.
- Scalability risk:
  - Hard-to-diagnose distributed tracing failures as modules grow.

## Medium Severity

### C) Fragmented exception mapping chain
- Violation:
  - Multiple specialized handlers (`TelemetryExceptionHandler`, `SecurityExceptionHandler`, `IntegrationExceptionHandler`) plus `GlobalExceptionHandler` and `shared/api/RestExceptionHandler` suggest overlapping boundaries.
- Impact:
  - Non-deterministic exception-to-problem-details mapping order.
- Scalability risk:
  - API contract inconsistency across endpoints.

### D) Persistence abstraction inconsistency in identityaccess
- Violation:
  - Coexistence of JPA adapter stack and `InMemoryUserRepository` in main source set without explicit bounded runtime role.
- Impact:
  - Ambiguous repository resolution path.
- Scalability risk:
  - Environment-specific defects when bean selection changes.

## Low Severity

### E) Test architecture duplication
- Violation:
  - Duplicate test classes in root `platform` and feature-specific subpackages.
- Impact:
  - Redundant maintenance and noisy test outcomes.
- Scalability risk:
  - Slower test suites and conflicting future assertions.
