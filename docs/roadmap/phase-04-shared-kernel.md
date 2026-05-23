# Phase 04 — Shared Kernel

## R04-C01
- **Goal**: Define strict shared-kernel admissibility.
- **Detailed Description**: Introduce shared-kernel inclusion policy and banned domain-specific artifacts list.
- **Files To Create**: `docs/architecture/shared-kernel-admissibility.md`
- **Files To Update**: architecture tests for shared-kernel rules
- **Files To Move**: none
- **Files To Merge**: none
- **Files To Delete**: none
- **Dependency Notes**: follows boundary normalization
- **Validation Requirements**: docs + architecture tests
- **Architectural Impact**: Medium
- **Risk Level**: Low
- **Rollback Complexity**: Low
- **Exact Commit Message**: `docs(shared-kernel): add admissibility and ownership policy`

## R04-C02
- **Goal**: Remove shared-kernel context leaks.
- **Detailed Description**: Delete/migrate context-specific value objects from shared-kernel after references are zero.
- **Files To Create**: compatibility migration notes
- **Files To Update**: imports/usages/tests
- **Files To Move**: none (already normalized)
- **Files To Merge**: none
- **Files To Delete**: leaked shared value objects
- **Dependency Notes**: hard dependency on R02-C01
- **Validation Requirements**: full build + tests
- **Architectural Impact**: High
- **Risk Level**: Medium
- **Rollback Complexity**: Medium
- **Exact Commit Message**: `refactor(shared-kernel): remove context-specific identifier leaks`

## R04-C03
- **Goal**: Normalize shared event/value primitives.
- **Detailed Description**: Keep only cross-context primitive contracts and document ownership matrix.
- **Files To Create**: `docs/architecture/shared-primitives-matrix.md`
- **Files To Update**: shared domain event/value packages and tests
- **Files To Move**: any ambiguous primitives to owning contexts
- **Files To Merge**: duplicate primitives
- **Files To Delete**: obsolete shared primitives
- **Dependency Notes**: after R04-C02
- **Validation Requirements**: shared kernel tests
- **Architectural Impact**: Medium
- **Risk Level**: Medium
- **Rollback Complexity**: Medium
- **Exact Commit Message**: `refactor(shared): normalize shared primitives and ownership matrix`
