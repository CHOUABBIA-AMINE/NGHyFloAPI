# CODEX Execution Plan — NGHyFloAPI Refactoring Roadmap

## 1) Documentation inventory

### Baseline documentation (source-of-truth)
- `docs/adr/`
  - `0001-nghyflo-modular-monolith-first.md`
- `docs/api/`
  - `openapi-placeholder.yaml`

### Generated refactoring documentation
- `docs/architecture/` (architecture and repository analyses)
- `docs/audit/` (audit findings/reports)
- `docs/normalization/` (normalization standards and strategies)
- `docs/target-architecture/` (target architecture definitions)
- `docs/roadmap/` (master and phase-by-phase execution roadmap)

## 2) Baseline-vs-generated documentation classification
- Baseline docs (`docs/adr`, `docs/api`) are authoritative for architectural decisions and API contract intent.
- Generated docs are execution guidance for refactoring and normalization.
- If generated guidance conflicts with baseline ADR/API meaning, stop before behavior changes, log blocker/manual decision, and require ADR/API-aligned resolution.

## 3) Source-code inventory summary
- Application source root: `src/main/java/dz/sh/trc/nghyflo/`.
- Main top-level code areas present:
  - `bootstrap`
  - `modules` (`identityaccess`, `organization`)
  - `platform` (`audit`, `configuration`, `events`, `exception`, `observability`, `security`, `tenancy`)
  - `shared` (`api`, `application`, `domain`, `infrastructure`)
- Resource root: `src/main/resources/` (profiles + Flyway migrations).
- Tests root: `src/test/java/dz/sh/trc/nghyflo/` (architecture, bootstrap, modules, platform, shared).
- Build/runtime/config artifacts inspected: `pom.xml`, `Makefile`, `checkstyle.xml`, `docker-compose*.yml`, `.github/workflows/`.

## 4) Roadmap phase map
1. Phase 01 — Cleanup (`R01-C01`..`R01-C03`)
2. Phase 02 — Boundary Normalization (`R02-C01`..`R02-C04`)
3. Phase 03 — Architecture Normalization (`R03-C01`..`R03-C04`)
4. Phase 04 — Shared Kernel (`R04-C01`..`R04-C03`)
5. Phase 05 — Telemetry Foundation (`R05-C01`..`R05-C03`)
6. Phase 06 — Workflow Foundation (`R06-C01`..`R06-C03`)
7. Phase 07 — Security Hardening (`R07-C01`..`R07-C03`)
8. Phase 08 — Audit Normalization (`R08-C01`..`R08-C03`)
9. Phase 09 — Testing Standardization (`R09-C01`..`R09-C03`)
10. Phase 10 — Final Normalization (`R10-C01`..`R10-C03`)

## 5) Commit-by-commit execution checklist

Status scale: **Pending / Blocked / Ready / Done**.

| Item | Source roadmap file | Goal | Item status | Files/packages likely affected | Coding-standard acceptance criteria | Documentation update required (Y/N) | Detailed checklist required (Y/N) | Validation command(s) | Risk level |
|---|---|---|---|---|---|---|---|---|---|
| R01-C01 | `docs/roadmap/phase-01-cleanup.md` | Baseline cleanup and inventory lock | Done | `docs/roadmap/inventory/*`, `docs/architecture/current-repository-tree.md` | Documentation-only change; preserve ADR/API meaning; no source-code edits | Y | Y | `mvn -q -DskipTests compile`; `rg` duplicate parity check | Low |
| R01-C02 | `docs/roadmap/phase-01-cleanup.md` | Remove redundant documentation overlap | Ready | `docs/architecture/index.md`, `docs/architecture/*.md` cross-links, possible superseded architecture summary docs | Keep baseline docs untouched in meaning; cross-link consistency; no source-code edits | Y | Y | markdown link check; `rg "TODO\|TBD" docs/architecture` | Low |
| R01-C03 | `docs/roadmap/phase-01-cleanup.md` | Establish cleanup guardrails | Pending | `docs/roadmap/templates/*`, `README.md` | Governance template aligns with roadmap sequencing; no architecture redesign | Y | N | docs lint + link validation | Low |
| R02-C01 | `docs/roadmap/phase-02-boundary-normalization.md` | Canonicalize context-owned identifiers | Pending | `modules/organization`, `shared`, `platform`, tests | Constructor injection only; no controller→repository; no domain→infrastructure; naming/package consistency | Y | Y | compile + unit tests + architecture tests | Medium |
| R02-C02 | `docs/roadmap/phase-02-boundary-normalization.md` | Normalize correlation filter ownership | Pending | `platform/observability`, `shared/api`, config wiring/tests | Single filter ownership; avoid duplicate registration; layering compliance | Y | Y | integration tests for filter order/correlation headers | Medium-High |
| R02-C03 | `docs/roadmap/phase-02-boundary-normalization.md` | Isolate non-production persistence adapters | Pending | `modules/identityaccess` persistence wiring, test/profile config | No production leakage of test adapter; package ownership clarity | Y | Y | compile + profile boot tests | Medium |
| R02-C04 | `docs/roadmap/phase-02-boundary-normalization.md` | Remove duplicated root-package tests | Pending | root/platform duplicated tests; canonical tests | Keep only canonical tests; avoid assertion loss | Y | Y | full test run | Low |
| R03-C01 | `docs/roadmap/phase-03-architecture-normalization.md` | Normalize package placement and ownership | Pending | `src/main/java/**`, architecture docs/tests | Layer/package ownership, naming consistency, no technical leakage | Y | Y | roadmap-defined compile/tests/arch checks | Medium |
| R03-C02 | `docs/roadmap/phase-03-architecture-normalization.md` | Enforce architecture dependency rules | Pending | architecture tests + dependency boundaries | No controller→repository, no domain→infrastructure, strict module boundaries | Y | Y | roadmap-defined architecture validation | Medium-High |
| R03-C03 | `docs/roadmap/phase-03-architecture-normalization.md` | Normalize exception and API error flow | Pending | exception mappers, API layer docs/tests | Consistent API error contract, no behavior drift outside scope | Y | Y | roadmap-defined compile/tests/contracts | Medium |
| R03-C04 | `docs/roadmap/phase-03-architecture-normalization.md` | Close architecture normalization phase | Pending | phase docs/tests/checklists | Phase closure evidence only, no unrelated cleanup | Y | N | roadmap-defined phase gate checks | Low |
| R04-C01 | `docs/roadmap/phase-04-shared-kernel.md` | Shared-kernel contract extraction | Pending | `shared/*`, dependent modules | Shared kernel minimalism; no cross-module entity sharing | Y | Y | roadmap-defined compile/tests/contracts | Medium |
| R04-C02 | `docs/roadmap/phase-04-shared-kernel.md` | Module dependency contraction | Pending | module interfaces/adapters/tests | Domain independence from infrastructure; package ownership | Y | Y | roadmap-defined compile/tests/arch checks | Medium |
| R04-C03 | `docs/roadmap/phase-04-shared-kernel.md` | Shared-kernel phase closure | Pending | docs/checklists/tests | Closure evidence only | Y | N | roadmap-defined phase gate checks | Low |
| R05-C01 | `docs/roadmap/phase-05-telemetry-foundation.md` | Telemetry ingestion foundation | Pending | telemetry modules/platform/events | Event ownership, layering, no cross-context leakage | Y | Y | roadmap-defined telemetry/compile/tests | Medium-High |
| R05-C02 | `docs/roadmap/phase-05-telemetry-foundation.md` | Telemetry processing normalization | Pending | telemetry app/domain/infrastructure/tests | Touched-file standards only; preserve behavior | Y | Y | roadmap-defined compile/tests | Medium |
| R05-C03 | `docs/roadmap/phase-05-telemetry-foundation.md` | Telemetry phase hardening | Pending | telemetry + platform integration/tests | Integration consistency and dependency boundaries | Y | Y | roadmap-defined integration validations | Medium-High |
| R06-C01 | `docs/roadmap/phase-06-workflow-foundation.md` | Workflow domain baseline | Pending | workflow domain/application/infrastructure | DDD boundaries and application orchestration | Y | Y | roadmap-defined compile/tests | Medium |
| R06-C02 | `docs/roadmap/phase-06-workflow-foundation.md` | Workflow adapter normalization | Pending | workflow adapters and API surfaces | Port/adapter discipline; no domain leakage | Y | Y | roadmap-defined compile/tests/contracts | Medium |
| R06-C03 | `docs/roadmap/phase-06-workflow-foundation.md` | Workflow phase closure | Pending | docs/checklists/tests | Closure evidence only | Y | N | roadmap-defined phase gate checks | Low |
| R07-C01 | `docs/roadmap/phase-07-security-hardening.md` | Security boundary hardening | Pending | authN/authZ layers, filters, tests | No policy leakage across layers; strict boundary ownership | Y | Y | roadmap-defined security tests | Medium-High |
| R07-C02 | `docs/roadmap/phase-07-security-hardening.md` | Security flow normalization | Pending | security application/domain/infrastructure | Constructor injection; contract consistency | Y | Y | roadmap-defined compile/tests | Medium |
| R07-C03 | `docs/roadmap/phase-07-security-hardening.md` | Security phase closure | Pending | docs/checklists/tests | Closure evidence only | Y | N | roadmap-defined phase gate checks | Low |
| R08-C01 | `docs/roadmap/phase-08-audit-normalization.md` | Audit model normalization | Pending | platform audit/events/outbox/tests | Domain ownership and dependency discipline | Y | Y | roadmap-defined compile/tests | Medium |
| R08-C02 | `docs/roadmap/phase-08-audit-normalization.md` | Audit adapter normalization | Pending | audit persistence/export adapters | No domain→infrastructure dependency | Y | Y | roadmap-defined compile/tests | Medium |
| R08-C03 | `docs/roadmap/phase-08-audit-normalization.md` | Audit hardening and closure | Pending | audit integrations/docs/tests | Integration consistency, no unrelated changes | Y | Y | roadmap-defined integration validations | Medium-High |
| R09-C01 | `docs/roadmap/phase-09-testing-standardization.md` | Test taxonomy normalization | Pending | test packages, naming, structure docs | Apply testing standards only in selected item scope | Y | Y | roadmap-defined test suite gates | Medium |
| R09-C02 | `docs/roadmap/phase-09-testing-standardization.md` | Contract and architecture test alignment | Pending | contract/architecture tests | Architecture/contract ownership checks | Y | Y | roadmap-defined contract+arch checks | Medium |
| R09-C03 | `docs/roadmap/phase-09-testing-standardization.md` | Testing phase closure | Pending | docs/checklists/tests | Closure evidence only | Y | N | roadmap-defined phase gate checks | Low |
| R10-C01 | `docs/roadmap/phase-10-final-normalization.md` | Final normalization pass part 1 | Pending | residual normalization docs/code/tests | No big-bang cleanup; strict item scope | Y | Y | roadmap-defined compile/tests | Medium |
| R10-C02 | `docs/roadmap/phase-10-final-normalization.md` | Final normalization pass part 2 | Pending | remaining target-architecture alignments | Touched-file standards only | Y | Y | roadmap-defined compile/tests/contracts | Medium |
| R10-C03 | `docs/roadmap/phase-10-final-normalization.md` | Final closure and documentation completion | Pending | final docs/checklists/phase closure | Final closure consistency with ADR/API | Y | N | roadmap-defined final gate validations | Medium |

## 6) Current step
- Re-prepared the execution planning artifacts by re-validating roadmap/documentation scope and normalizing this execution plan structure.

## 7) Next step
- Execute the first ready roadmap item (`R01-C02`) in one dedicated commit after review approval.

## 8) Blockers and manual decisions
- No hard blocker for planning.
- If generated roadmap guidance conflicts with baseline ADR/API meaning, pause implementation and request explicit decision before behavior changes.
- Potential sequencing caution: compile currently fails for a pre-existing shared-vs-module identifier mismatch; this is tracked for later in-scope boundary normalization.

## 9) Out-of-scope issues to revisit later
- Pre-existing compile failure in `OrganizationApplicationService` due shared vs organization identifier type mismatch (outside planning scope; candidate for Phase 02 boundary normalization item handling).
- Any coding-standard violations discovered outside the currently selected roadmap item scope.
- Broad testing taxonomy cleanup before Phase 09.
- Final documentation closure actions before Phase 10.

## Execution tracking log
- 2026-05-23: Planning-only pass performed; roadmap and documentation inventories reviewed; no application source-code changes.
- 2026-05-23: Rebuilt execution plan matrix to include explicit item status scale, documentation/checklist requirement flags, and per-item risk/validation placeholders aligned to roadmap files.
- 2026-05-23: Closure verification for `R01-C02` (planning-preparation only) completed. Verified single-item scope (`R01-C02`) and alignment with roadmap source/checklist. Item remains `Ready` (not `Done`) because implementation and roadmap validation commands for `R01-C02` were not executed in this pass. Files changed in this verification: `docs/architecture/CODEX-EXECUTION-PLAN.md`, `docs/roadmap/checklists/r01-c02.md`. Documentation updated: same two files. Checklist updated: `docs/roadmap/checklists/r01-c02.md`. Validation run: `git status`, `git diff --stat`, `git diff`, `rg "TODO|TBD" docs/architecture`; markdown link checker command not runnable here due no project-defined markdown link-check tool/script. Remaining risks/follow-up: keep `R01-C02` open for implementation; pre-existing compile mismatch remains out-of-scope until boundary normalization phase.
