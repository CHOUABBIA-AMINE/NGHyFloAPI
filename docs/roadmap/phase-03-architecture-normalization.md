# Phase 03 — Architecture Normalization

## R03-C01
- **Goal**: Enforce dependency direction contracts.
- **Detailed Description**: Extend architecture tests for forbidden imports: cross-context infra access, shared-kernel domain leakage.
- **Files To Create**: new architecture test cases
- **Files To Update**: existing architecture test suite
- **Files To Move**: none
- **Files To Merge**: none
- **Files To Delete**: none
- **Dependency Notes**: requires Phase 02 completed
- **Validation Requirements**: architecture tests green
- **Architectural Impact**: High
- **Risk Level**: Medium
- **Rollback Complexity**: Low
- **Exact Commit Message**: `test(architecture): enforce boundary and dependency direction rules`

## R03-C02
- **Goal**: Normalize exception handling chain.
- **Detailed Description**: Centralize handler precedence with one canonical orchestration entrypoint and category translators.
- **Files To Create**: exception precedence documentation/test matrix
- **Files To Update**: `platform/exception/*`, `shared/api/RestExceptionHandler`
- **Files To Move**: optional shared handler relocation
- **Files To Merge**: overlapping fallback handling
- **Files To Delete**: duplicate fallback handlers
- **Dependency Notes**: after R03-C01
- **Validation Requirements**: exception mapping contract tests
- **Architectural Impact**: High
- **Risk Level**: Medium-High
- **Rollback Complexity**: Medium
- **Exact Commit Message**: `refactor(exception): centralize exception orchestration and handler precedence`

## R03-C03
- **Goal**: Normalize DTO envelope/error contracts.
- **Detailed Description**: Align external DTO metadata envelope and consolidate error response format.
- **Files To Create**: API contract matrix docs
- **Files To Update**: DTO classes/controllers/mappers/tests
- **Files To Move**: context DTO relocation if required
- **Files To Merge**: duplicate response wrappers
- **Files To Delete**: obsolete duplicate wrappers
- **Dependency Notes**: after R03-C02
- **Validation Requirements**: API contract tests + backward compatibility checks
- **Architectural Impact**: Medium
- **Risk Level**: Medium
- **Rollback Complexity**: Medium
- **Exact Commit Message**: `refactor(api): standardize dto envelopes and error contracts`

## R03-C04
- **Goal**: Normalize mapper boundaries.
- **Detailed Description**: Enforce mapper placement and remove rule logic from mapper classes.
- **Files To Create**: mapper convention tests
- **Files To Update**: API and JPA mapper packages
- **Files To Move**: mislocated mappers
- **Files To Merge**: duplicate mapper methods
- **Files To Delete**: obsolete mapper duplicates
- **Dependency Notes**: after R03-C03
- **Validation Requirements**: mapper unit tests + compile
- **Architectural Impact**: Medium
- **Risk Level**: Medium
- **Rollback Complexity**: Medium
- **Exact Commit Message**: `refactor(mapping): normalize mapper ownership and boundary responsibilities`
