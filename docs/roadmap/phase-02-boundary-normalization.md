# Phase 02 — Boundary Normalization

## R02-C01
- **Goal**: Canonicalize context-owned identifiers.
- **Detailed Description**: Switch all references to organization-owned `EmployeeId`/`StructureId` and mark shared variants deprecated.
- **Files To Create**: none
- **Files To Update**: imports/usages in `modules/organization`, `shared`, `platform`, tests
- **Files To Move**: none
- **Files To Merge**: ID conversion helpers into organization domain
- **Files To Delete**: none (deletion deferred)
- **Dependency Notes**: must precede shared-kernel contraction (Phase 04)
- **Validation Requirements**: compile + unit tests + architecture tests
- **Architectural Impact**: High
- **Risk Level**: Medium
- **Rollback Complexity**: Medium
- **Exact Commit Message**: `refactor(boundary): canonicalize organization identifier ownership`

## R02-C02
- **Goal**: Normalize correlation filter ownership.
- **Detailed Description**: Route all web correlation logic to platform observability filter and disable shared duplicate registration.
- **Files To Create**: none
- **Files To Update**: `platform/observability/...`, `shared/api/...`, config wiring, tests
- **Files To Move**: possible move of shared filter into platform namespace
- **Files To Merge**: duplicate filter behaviors
- **Files To Delete**: obsolete shared filter (if zero references)
- **Dependency Notes**: after R02-C01
- **Validation Requirements**: integration tests for filter order/correlation headers
- **Architectural Impact**: High
- **Risk Level**: Medium-High
- **Rollback Complexity**: Medium
- **Exact Commit Message**: `refactor(observability): consolidate request correlation filter ownership`

## R02-C03
- **Goal**: Isolate non-production persistence adapters.
- **Detailed Description**: Move in-memory user repository to test-support/profile-scoped package and harden bean conditions.
- **Files To Create**: profile-specific config under test support
- **Files To Update**: identityaccess persistence wiring
- **Files To Move**: `InMemoryUserRepository`
- **Files To Merge**: none
- **Files To Delete**: old production-path in-memory adapter
- **Dependency Notes**: independent of R02-C02
- **Validation Requirements**: compile + profile boot tests
- **Architectural Impact**: Medium
- **Risk Level**: Medium
- **Rollback Complexity**: Medium
- **Exact Commit Message**: `refactor(identityaccess): isolate in-memory repository to test/profile scope`

## R02-C04
- **Goal**: Remove duplicated root-package tests.
- **Detailed Description**: Keep canonical feature-package tests and delete mirrored root-platform duplicates.
- **Files To Create**: none
- **Files To Update**: canonical tests if assertion merge needed
- **Files To Move**: none
- **Files To Merge**: duplicate test assertions into canonical locations
- **Files To Delete**: duplicated root `platform/*Test.java` mirrors
- **Dependency Notes**: after R02-C01..C03
- **Validation Requirements**: full test run
- **Architectural Impact**: Low
- **Risk Level**: Low
- **Rollback Complexity**: Low
- **Exact Commit Message**: `test(platform): remove duplicated root-level security/audit/outbox tests`
