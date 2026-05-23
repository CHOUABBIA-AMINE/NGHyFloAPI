# AGENTS.md — NGHyFloAPI Codex Instructions

## Repository Scope

You are working ONLY on:

CHOUABBIA-AMINE/NGHyFloAPI

Do not inspect, modify, copy from, or reference implementation work from:

- CHOUABBIA-AMINE/HyFloAPI
- CHOUABBIA-AMINE/HyFloWEB
- CHOUABBIA-AMINE/NGHyFlo
- any other repository

Default branch:

- main

## Primary Mission

The primary mission is to execute the generated NGHyFloAPI refactoring roadmap commit by commit.

Do not turn architecture review, coding standards enforcement, documentation cleanup, test normalization, or API cleanup into separate standalone projects.

Every Codex task must advance exactly one roadmap item unless the user explicitly asks for planning, audit, or documentation-only work.

The roadmap source is:

- docs/roadmap/master-refactoring-roadmap.md
- docs/roadmap/phase-*.md

The roadmap must be executed in order unless a blocker is documented.

## Project Context

NGHyFloAPI is the backend API for the New Generation Hydrocarbon Flow Intelligence Platform.

The backend source root is:

- src/main/java/dz/sh/trc/nghyflo/

The main code areas are:

- bootstrap
- modules
- platform
- shared

The repository documentation areas include:

- docs/adr/
- docs/api/
- docs/architecture/
- docs/audit/
- docs/normalization/
- docs/target-architecture/
- docs/roadmap/

## Documentation Classification

The following folders existed from the beginning and are baseline project documentation:

- docs/adr/
- docs/api/

The following folders were generated later for repository analysis and refactoring-roadmap generation:

- docs/architecture/
- docs/audit/
- docs/normalization/
- docs/target-architecture/
- docs/roadmap/

Do not confuse baseline documentation with generated refactoring documentation.

## Source of Truth Rules

1. Treat docs/adr/ as original architectural decision history.
2. Treat docs/api/ as original API contract documentation.
3. Treat generated folders as analysis and execution guidance.
4. Generated refactoring documents must not silently override ADRs or API contracts.
5. If generated documents conflict with baseline ADR/API documentation:
   - stop before changing behavior
   - document the conflict
   - propose the safest resolution
   - require an ADR update if architecture behavior must change

## Roadmap Execution Rules

For every implementation task:

1. Select exactly one roadmap item.
2. Implement only that roadmap item.
3. Do not batch multiple roadmap items.
4. Do not perform unrelated cleanup.
5. Do not redesign architecture.
6. Do not create new features.
7. Preserve existing behavior unless the selected roadmap item explicitly requires a correction.
8. Keep the build/test state stable.
9. Update documentation/checklists where required.
10. Report the current step and the next step.

Do not assume there is one file per phase.
Analyze docs recursively.

## Required Planning Document

Before source-code implementation begins, create or update:

- docs/architecture/CODEX-EXECUTION-PLAN.md

This file is the execution control document.

It must include:

- documentation inventory
- baseline-vs-generated documentation classification
- roadmap phase map
- commit-by-commit execution checklist
- current step
- next step
- roadmap item status
- files/packages likely affected
- coding-standard acceptance criteria per item
- validation command per item
- risk level per item
- blockers
- manual decisions required
- out-of-scope findings

## Required Execution Tracking

During roadmap execution, update:

- docs/architecture/CODEX-EXECUTION-PLAN.md

For each executed roadmap item, record:

- roadmap item ID
- source roadmap file
- current step
- next step
- files changed
- documentation updated
- checklist items completed
- validation commands run
- validation result
- remaining risks
- follow-up items

If the roadmap item requires a more detailed checklist, create or update:

- docs/roadmap/checklists/<roadmap-item-id>.md

Use lowercase file names, for example:

- docs/roadmap/checklists/r01-c01.md
- docs/roadmap/checklists/r03-c02.md

## Before Modifying Code

Before changing application source code, always:

1. Read this AGENTS.md file.
2. Read docs/adr/.
3. Read docs/api/.
4. Read generated refactoring docs:
   - docs/architecture/
   - docs/audit/
   - docs/normalization/
   - docs/target-architecture/
   - docs/roadmap/
5. Scan the actual repository structure:
   - src/main/java/dz/sh/trc/nghyflo/
   - src/main/resources/
   - src/test/java/dz/sh/trc/nghyflo/
   - pom.xml
   - Makefile
   - checkstyle.xml
   - docker-compose*.yml
   - .github/workflows/
6. Compare the selected roadmap item against the real source tree.
7. Identify conflicts, blockers, stale documentation, and unsafe sequencing.
8. Update docs/architecture/CODEX-EXECUTION-PLAN.md.
9. Stop after planning unless the user explicitly asks to implement.

## Architecture Rules

Follow the target architecture only where confirmed by the repository and generated roadmap:

- DDD
- Hexagonal architecture
- Modular monolith
- strict module boundaries
- no controller-to-repository access
- no domain-to-infrastructure dependency
- no cross-module entity sharing
- application services orchestrate use cases
- domain owns business rules
- infrastructure implements ports
- shared kernel must stay minimal

## Coding Standards as Roadmap Acceptance Criteria

Coding standards are mandatory acceptance criteria for every roadmap commit.

For each selected roadmap item, Codex must check whether the files touched by that item comply with:

- mandatory class header:
  - Module
  - Bounded Context
  - Responsibility
  - Layer
- JavaDoc where required
- constructor injection only
- no field injection
- DTO Bean Validation where applicable
- @Valid in controllers where nested DTOs are accepted
- no duplicated manual DTO validation in services
- no validation-framework leakage into pure domain models unless explicitly approved
- OpenAPI/API documentation for public controllers and exposed DTOs
- no controller-to-repository access
- no domain-to-infrastructure dependency
- no cross-module entity sharing
- package naming consistency
- service/DTO/entity naming consistency
- no technical top-level package leakage

If a touched file violates these rules, fix the violation only when it belongs to the current roadmap item.

If a violation is outside the current roadmap item, do not expand the commit scope. Record it as follow-up work in:

- docs/architecture/CODEX-EXECUTION-PLAN.md

## Enforcement Must Follow the Roadmap

Do not introduce all enforcement checks at once unless the selected roadmap item requires it.

Checkstyle, ArchUnit, validation tests, API contract tests, documentation checks, and test taxonomy must be introduced according to the generated roadmap sequence.

In particular:

- architecture ownership and dependency tests belong to the relevant architecture/testing roadmap items
- contract tests belong to the contract-testing roadmap items
- broad test taxonomy cleanup belongs to Phase 09
- final documentation closure belongs to the final normalization phase

Do not perform big-bang cleanup.
Do not perform unrelated coding-standard enforcement.

## Documentation Update Rules

Update documentation only when needed by the selected roadmap item.

Allowed documentation updates:

- docs/architecture/CODEX-EXECUTION-PLAN.md
- docs/roadmap/checklists/<roadmap-item-id>.md
- generated analysis docs when the selected roadmap item explicitly requires it
- ADRs only when the user explicitly approves an architecture decision update
- API docs only when the selected roadmap item changes or confirms an API contract

Do not delete or rewrite baseline documentation:

- docs/adr/
- docs/api/

If baseline documentation must change, document the reason and require explicit user approval.

## Codex Cloud Completion Rule

When using Codex Cloud, do not assume the user will commit manually.

After each implementation task, Codex must perform a closure verification and prepare the task for the cloud PR workflow.

The closure response must include:
- Current step
- Next step
- Roadmap item
- Files changed
- Documentation updated
- Checklist updated
- Validation commands and results
- PR readiness
- Recommended PR title
- Recommended PR description
- Remaining risks

Do not start the next roadmap item until the current item is verified and marked ready for PR review.

## Current Step / Next Step Reporting

Every Codex response must include:

- Current step
- Next step
- Roadmap item ID
- Roadmap source file
- Whether implementation was performed or planning only
- Documentation updated
- Checklist updated
- Validation run
- Remaining risks

Use this format:

```text
Current step:
- <what was done now>

Next step:
- <next roadmap item or next required action>

Roadmap item:
- <ID and title>

Documentation updated:
- <files>

Checklist updated:
- <files or none>

Validation:
- <command and result>

Remaining risks:
- <risks or none>