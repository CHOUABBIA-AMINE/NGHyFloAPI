# Phase 01 — Cleanup

## R01-C01
- **Goal**: Baseline cleanup and inventory lock.
- **Detailed Description**: Freeze architecture baseline, list duplicate classes/tests, and create machine-checkable inventory manifests.
- **Files To Create**: `docs/roadmap/inventory/baseline-file-manifest.md`, `docs/roadmap/inventory/duplicate-candidate-manifest.md`
- **Files To Update**: `docs/architecture/current-repository-tree.md`
- **Files To Move**: none
- **Files To Merge**: none
- **Files To Delete**: none
- **Dependency Notes**: none
- **Validation Requirements**: `mvn -q -DskipTests compile`, `rg` duplicate list parity check
- **Architectural Impact**: Low
- **Risk Level**: Low
- **Rollback Complexity**: Low
- **Exact Commit Message**: `docs(roadmap): add baseline inventory manifests for normalization`

## R01-C02
- **Goal**: Remove redundant documentation overlap.
- **Detailed Description**: Consolidate duplicate/overlapping architecture notes into canonical docs index pages.
- **Files To Create**: `docs/architecture/index.md`
- **Files To Update**: existing `docs/architecture/*.md` cross-links
- **Files To Move**: none
- **Files To Merge**: overlapping architecture summaries
- **Files To Delete**: superseded duplicate summary docs (only after link migration)
- **Dependency Notes**: run after R01-C01
- **Validation Requirements**: markdown link check, `rg "TODO|TBD" docs/architecture`
- **Architectural Impact**: Low
- **Risk Level**: Low
- **Rollback Complexity**: Low
- **Exact Commit Message**: `docs(architecture): normalize architecture documentation index and cross-links`

## R01-C03
- **Goal**: Establish cleanup guardrails.
- **Detailed Description**: Add governance checklist templates for future PR phases.
- **Files To Create**: `docs/roadmap/templates/commit-checklist.md`
- **Files To Update**: `README.md`
- **Files To Move**: none
- **Files To Merge**: none
- **Files To Delete**: none
- **Dependency Notes**: none
- **Validation Requirements**: docs lint + link validation
- **Architectural Impact**: Low
- **Risk Level**: Low
- **Rollback Complexity**: Low
- **Exact Commit Message**: `docs(governance): add refactoring checklist templates`
