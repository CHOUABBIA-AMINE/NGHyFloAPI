# Missing Components Report (Phase 2)

## Module: `identityaccess`
- Missing/weak:
  - Explicit domain services for credential lifecycle policies (currently concentrated in application services).
  - Specification objects for user/role policy checks.
  - Integration adapters for external IdP/SSO federation.
  - Scheduler(s) for token/session cleanup.
  - Domain events for role assignment/session revocation publication pipeline.
  - Broader module documentation for use-case boundaries.

## Module: `organization`
- Missing/weak:
  - Repository ports appear partial (coverage/lookup only); aggregate repositories for all core aggregates are not uniformly explicit.
  - Validator/specification layer for staffing and assignment business rules.
  - Event classes for staffing/coverage lifecycle transitions.
  - Workflow handlers for assignment/coverage approval flows.
  - Dedicated audit hooks for key domain actions.

## Module: `platform`
- Missing/weak:
  - Unified exception contract governance document linking all handlers.
  - Telemetry ingestion adapters (collector-facing) beyond correlation/metrics wrappers.
  - Security audit event bridge from authorization decisions to immutable audit chain.

## Shared kernel
- Missing/weak:
  - Clear criteria document for what is allowed in shared kernel vs bounded-context ownership.
  - Anti-corruption interfaces for cross-module ID/value translation.

## Tests (cross-cutting)
- Missing/weak:
  - End-to-end tests proving controller→service→repository layering at runtime.
  - Contract tests for exception response uniformity.
  - Boundary tests for shared-kernel leak prevention (ID type crossing).
