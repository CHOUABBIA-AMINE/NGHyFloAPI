# CODEX Execution Plan — NGHyFloAPI Refactoring Roadmap

## 1) Documentation inventory

### Baseline documentation (source-of-truth)
- `docs/adr/`
  - `0001-nghyflo-modular-monolith-first.md`
- `docs/api/`
  - `openapi-placeholder.yaml`

### Generated refactoring documentation
- `docs/architecture/` (architecture and repo analyses)
- `docs/audit/` (audit findings/reports)
- `docs/normalization/` (normalization standards and strategies)
- `docs/target-architecture/` (target architecture definitions)
- `docs/roadmap/` (master and phase-by-phase execution roadmap)

## 2) Baseline-vs-generated documentation classification
- Baseline docs (`docs/adr`, `docs/api`) are authoritative for architectural decisions and API contract intent.
- Generated docs are execution guidance for refactoring and normalization.
- Any conflict between generated guidance and baseline docs must be recorded as blocker/manual decision before behavioral changes.

## 3) Source-code inventory summary
- Application source root: `src/main/java/dz/sh/trc/nghyflo/`.
- Main top-level areas present:
  - `bootstrap`
  - `modules` (`identityaccess`, `organization`)
  - `platform` (`audit`, `configuration`, `events`, `exception`, `observability`, `security`, `tenancy`)
  - `shared` (`api`, `application`, `domain`, `infrastructure`)
- Resource root: `src/main/resources/`.
- Tests root: `src/test/java/dz/sh/trc/nghyflo/`.
- Build/config inspected: `pom.xml`, `Makefile`, `checkstyle.xml`, `docker-compose*.yml`, `.github/workflows/`.

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

| Item | Source roadmap file | Goal | Likely affected files/packages | Coding-standard acceptance criteria | Validation command(s) | Risk | Status |
|---|---|---|---|---|---|---|---|
| R01-C01 | `docs/roadmap/phase-01-cleanup.md` | Baseline cleanup and inventory lock | `docs/roadmap/inventory/*`, `docs/architecture/current-repository-tree.md` | Doc updates must preserve ADR/API meaning; no source-code modifications | `mvn -q -DskipTests compile` + `rg` duplicate parity check | Low | Completed (2026-05-23) |
| R01-C02 | `docs/roadmap/phase-01-cleanup.md` | Remove redundant documentation overlap | `docs/architecture/*.md`, new `docs/architecture/index.md` | Keep baseline docs untouched in meaning; cross-link consistency | markdown link check; `rg "TODO\|TBD" docs/architecture` | Low | Planned |
| R01-C03 | `docs/roadmap/phase-01-cleanup.md` | Establish cleanup guardrails | `docs/roadmap/templates/*`, `README.md` | Governance template aligns with roadmap sequencing | docs lint + link validation | Low | Planned |
| R02-C01 | `docs/roadmap/phase-02-boundary-normalization.md` | Canonicalize context-owned identifiers | `modules/organization`, `shared`, `platform`, tests | Constructor injection; no controller→repository; no domain→infrastructure; naming/package consistency | compile + unit tests + architecture tests | Medium | Planned |
| R02-C02 | `docs/roadmap/phase-02-boundary-normalization.md` | Normalize correlation filter ownership | `platform/observability`, `shared/api`, config wiring/tests | Single filter ownership; avoid duplicate registration; layering compliance | integration tests for filter order/correlation headers | Medium-High | Planned |
| R02-C03 | `docs/roadmap/phase-02-boundary-normalization.md` | Isolate non-production persistence adapters | `modules/identityaccess` persistence wiring, test/profile config | No production leakage of test adapter; package ownership clarity | compile + profile boot tests | Medium | Planned |
| R02-C04 | `docs/roadmap/phase-02-boundary-normalization.md` | Remove duplicated root-package tests | root/platform duplicated tests; canonical tests | Keep only canonical tests; avoid assertion loss | full test run | Low | Planned |
| R03-C01..R10-C03 | `docs/roadmap/phase-03-architecture-normalization.md` .. `phase-10-final-normalization.md` | Execute sequentially per phase goals | architecture, module boundaries, telemetry/workflow/security/audit/testing docs+code per item | Apply AGENTS coding standards only within selected item scope; no big-bang enforcement | Use per-item validation requirement from phase files (compile/tests/contracts/arch checks) | Mixed (Low→Medium-High) | Planned |

## 6) Current step
- Completed planning-only initialization and repository/documentation inventory.
- Created initial execution control document for ordered roadmap execution.

## 7) Next step
- Execute roadmap item `R01-C02` (documentation overlap normalization) in a single dedicated commit after reviewing cross-link impact.

## 8) Files/packages likely affected by each roadmap item
- Detailed likely file ownership is captured in each phase file (`docs/roadmap/phase-*.md`) and will be copied to per-item checklist before implementation.
- Immediate next item (`R01-C02`) likely affects only:
  - `docs/architecture/index.md`
  - cross-links in `docs/architecture/*.md`

## 9) Coding-standard acceptance criteria per roadmap item
For every implementation commit (`Rxx-Cyy`), enforce for touched files only:
- Mandatory class header fields: Module, Bounded Context, Responsibility, Layer.
- JavaDoc where required.
- Constructor injection only; no field injection.
- DTO Bean Validation + `@Valid` in controllers where nested DTOs are accepted.
- No duplicated manual DTO validation in services.
- No validation-framework leakage into pure domain models unless approved.
- OpenAPI/API documentation for public controllers/exposed DTOs.
- No controller-to-repository access.
- No domain-to-infrastructure dependency.
- No cross-module entity sharing.
- Package and naming consistency.

## 10) Validation command per roadmap item
- Primary source: each `Rxx-Cyy` in `docs/roadmap/phase-*.md`.
- Validation will be executed exactly per selected item requirements (no broad unrelated runs).
- Execution log will be appended per item with command and result.

## 11) Risk level per roadmap item
- Risk level source: each phase file entry.
- Current highest-risk early items identified:
  - `R02-C02` (Medium-High)
  - `R03-C02` (Medium-High)
  - `R05-C01` and `R05-C03` (Medium-High)
  - `R07-C01` (Medium-High)
  - `R08-C03` (Medium-High)

## 12) Blockers and manual decisions
- No hard blocker for planning stage.
- Manual decision needed before any behavior-changing conflict where generated docs diverge from `docs/adr` or `docs/api`.
- If conflict appears, pause implementation and request ADR/API-aligned resolution.

## 13) Out-of-scope issues to revisit later
- Pre-existing compile failure in `OrganizationApplicationService` due shared vs organization identifier type mismatch (outside R01-C01 scope; roadmap-candidate for boundary normalization in Phase 02).
- Any coding-standard violation found outside currently selected roadmap item scope.
- Any broad test taxonomy cleanup before Phase 09.
- Any final doc closure work before Phase 10.

## Execution tracking log
- 2026-05-23: Planning-only pass completed. No application source code changes performed.
- 2026-05-23: Executed `R01-C01` (baseline inventory lock). Created inventory manifests, updated repository-tree doc, ran required validation commands; duplicate parity check succeeded while compile failed due pre-existing type mismatch in organization application service.
- 2026-05-23: Closure check for `R01-C01` completed before next item. Scope remains limited to one roadmap item with documentation-only artifacts; no unrelated cleanup/new features detected. Re-ran `mvn -q -DskipTests compile` (fails due pre-existing shared-vs-module ID type mismatch in organization application service), and confirmed working tree clean via `git status`, `git diff --stat`, `git diff`.
