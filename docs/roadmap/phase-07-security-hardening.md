# Phase 07 — Security Hardening

## R07-C01
- **Goal**: Normalize security policy pipeline boundaries.
- **Detailed Description**: Ensure deterministic RBAC → ABAC → SoD sequence with explicit decision contract.
- **Files To Create**: security decision contract docs/tests
- **Files To Update**: security evaluators, policy decision model, app integrations
- **Files To Move**: misplaced security checks from domain/controller into policy services
- **Files To Merge**: duplicated permission/approval evaluators
- **Files To Delete**: obsolete duplicated security helpers
- **Dependency Notes**: workflow normalization should precede SoD integration
- **Validation Requirements**: security policy matrix tests
- **Architectural Impact**: High
- **Risk Level**: Medium-High
- **Rollback Complexity**: Medium
- **Exact Commit Message**: `refactor(security): normalize policy pipeline and decision contracts`

## R07-C02
- **Goal**: Normalize JWT and actor resolution behavior.
- **Detailed Description**: Centralize actor resolution (tenant, delegation, impersonation flags) and align token claims mapping.
- **Files To Create**: actor-resolution contract tests
- **Files To Update**: authentication filters/resolvers/principal mapping
- **Files To Move**: actor helpers to security context
- **Files To Merge**: duplicate actor-context utilities
- **Files To Delete**: obsolete actor-resolution variants
- **Dependency Notes**: after R07-C01
- **Validation Requirements**: auth integration tests
- **Architectural Impact**: High
- **Risk Level**: Medium
- **Rollback Complexity**: Medium
- **Exact Commit Message**: `refactor(auth): centralize jwt claims and actor resolution model`

## R07-C03
- **Goal**: Normalize authorization audit emission.
- **Detailed Description**: Guarantee all deny/approve decisions emit standardized audit events.
- **Files To Create**: policy-to-audit coverage tests
- **Files To Update**: security + audit event bridge
- **Files To Move**: none
- **Files To Merge**: duplicate auth audit hooks
- **Files To Delete**: obsolete decision logging paths
- **Dependency Notes**: requires audit phase prep
- **Validation Requirements**: security+audit integration tests
- **Architectural Impact**: Medium
- **Risk Level**: Medium
- **Rollback Complexity**: Medium
- **Exact Commit Message**: `refactor(security-audit): standardize authorization decision audit emission`
