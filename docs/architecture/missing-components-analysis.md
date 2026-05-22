# Missing Components Analysis (Phase 2)

## IdentityAccess Module
- **Missing/weak areas identified:**
  - explicit query handlers/read model segregation
  - richer domain events for auth/session lifecycle
  - stronger aggregate-level policy objects for role assignment governance
  - broader integration tests for security + persistence + session renewal flows

## Organization Module
- **Missing/weak areas identified:**
  - explicit repository abstraction for all active workflows (beyond map-backed service path)
  - clear validator/specification components for coverage and assignment constraints
  - explicit workflow handlers separating command orchestration from read-model assembly
  - domain events around employee assignment/coverage lifecycle
  - stronger end-to-end tests for staffing allocation/coverage decisions with DB-backed state

## Platform Module
- **Missing/weak areas identified:**
  - explicit consolidation of correlation abstractions (single owner)
  - clearer concrete implementations/wiring evidence for outbox dispatch/cleanup runtime
  - explicit audit integration adapters in module use-case execution paths
  - additional integration tests validating exception, security, tenancy, and observability interplay

## Shared Kernel
- **Missing/weak areas identified:**
  - ownership governance for identifiers to avoid over-shared context types
  - formal documentation of what qualifies for shared inclusion vs module-local value object

## Bootstrap / API Boundary
- **Missing/weak areas identified:**
  - mature OpenAPI contract generation/annotation coverage (currently placeholder artifact)
  - standardized API validation annotation strategy and explicit request validator policy

## Testing and Documentation
- **Missing/weak areas identified:**
  - integration test matrix for multi-module workflows and transaction boundaries
  - architecture decision addenda for identifier ownership and correlation strategy
  - deeper runbook docs for outbox operations, retry, dead-letter handling, and cleanup policy
