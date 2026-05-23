# Phase 09 — Testing Standardization

## R09-C01
- **Goal**: Normalize architecture and ownership tests.
- **Detailed Description**: Add/strengthen tests for package ownership, forbidden imports, and module dependency direction.
- **Files To Create**: additional architecture tests
- **Files To Update**: existing architecture test suites
- **Files To Move**: none
- **Files To Merge**: duplicate rule tests
- **Files To Delete**: obsolete/redundant architecture tests
- **Dependency Notes**: after phases 02-08
- **Validation Requirements**: architecture tests only stage
- **Architectural Impact**: Medium
- **Risk Level**: Low
- **Rollback Complexity**: Low
- **Exact Commit Message**: `test(architecture): normalize ownership and dependency enforcement suites`

## R09-C02
- **Goal**: Normalize contract test coverage.
- **Detailed Description**: Add API/event/audit/security contract tests with version and metadata assertions.
- **Files To Create**: contract test suites and fixtures
- **Files To Update**: existing integration tests
- **Files To Move**: contract tests into dedicated package
- **Files To Merge**: duplicate contract assertions
- **Files To Delete**: obsolete contract test variants
- **Dependency Notes**: after R09-C01
- **Validation Requirements**: contract + integration test runs
- **Architectural Impact**: Medium
- **Risk Level**: Low-Medium
- **Rollback Complexity**: Low
- **Exact Commit Message**: `test(contract): standardize api event audit and security contracts`

## R09-C03
- **Goal**: Normalize test taxonomy and naming.
- **Detailed Description**: Enforce package-based test taxonomy and naming rules for unit/integration/architecture/contract suites.
- **Files To Create**: `docs/testing/test-taxonomy.md`
- **Files To Update**: test class/package names
- **Files To Move**: misnamed/misplaced tests
- **Files To Merge**: equivalent tests
- **Files To Delete**: deprecated duplicates
- **Dependency Notes**: after R09-C02
- **Validation Requirements**: full test suite
- **Architectural Impact**: Low
- **Risk Level**: Low
- **Rollback Complexity**: Medium (renames)
- **Exact Commit Message**: `test: normalize taxonomy naming and placement`
