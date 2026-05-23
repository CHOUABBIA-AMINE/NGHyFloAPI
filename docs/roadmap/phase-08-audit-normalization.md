# Phase 08 — Audit Normalization

## R08-C01
- **Goal**: Normalize audit event schema.
- **Detailed Description**: Standardize audit envelope fields across contexts and align writer API.
- **Files To Create**: audit schema contract spec/tests
- **Files To Update**: audit model/writer/metadata classes
- **Files To Move**: audit helpers to platform audit namespace
- **Files To Merge**: duplicate metadata builders
- **Files To Delete**: obsolete audit schema variants
- **Dependency Notes**: after security hardening contracts
- **Validation Requirements**: audit schema contract tests
- **Architectural Impact**: High
- **Risk Level**: Medium
- **Rollback Complexity**: Medium
- **Exact Commit Message**: `refactor(audit): standardize audit event schema and writer contracts`

## R08-C02
- **Goal**: Normalize hash-chain and retention boundaries.
- **Detailed Description**: Separate chain integrity logic from export/retention orchestration and define immutable evidence path.
- **Files To Create**: audit integrity test suite additions
- **Files To Update**: hasher, retention policy, export service
- **Files To Move**: mixed concerns into dedicated audit subpackages
- **Files To Merge**: duplicate retention/export utilities
- **Files To Delete**: obsolete mixed audit utility classes
- **Dependency Notes**: after R08-C01
- **Validation Requirements**: audit chain + retention tests
- **Architectural Impact**: Medium
- **Risk Level**: Medium
- **Rollback Complexity**: Medium
- **Exact Commit Message**: `refactor(audit): normalize integrity chain and retention orchestration`

## R08-C03
- **Goal**: Normalize audit-outbox integration.
- **Detailed Description**: Ensure transactional coupling of domain change + outbox + audit writes where required.
- **Files To Create**: transaction boundary tests for audit/outbox coupling
- **Files To Update**: outbox dispatcher/writer transaction handling
- **Files To Move**: none
- **Files To Merge**: duplicate transaction helpers
- **Files To Delete**: obsolete non-transactional audit emission paths
- **Dependency Notes**: after R08-C02
- **Validation Requirements**: transactional integration tests
- **Architectural Impact**: High
- **Risk Level**: Medium-High
- **Rollback Complexity**: High
- **Exact Commit Message**: `refactor(audit-eventing): enforce transactional audit and outbox coupling`
