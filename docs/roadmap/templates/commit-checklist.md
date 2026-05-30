# Roadmap Commit Checklist Template

Use this template for one roadmap item at a time. Copy it into the pull request
notes or into `docs/roadmap/checklists/<roadmap-item-id>.md` when the selected
roadmap item needs detailed tracking.

## Roadmap scope

- [ ] Roadmap item ID:
- [ ] Roadmap source file:
- [ ] Exactly one roadmap item is included.
- [ ] No unrelated cleanup is included.
- [ ] No new feature is introduced.
- [ ] Existing behavior is preserved unless the roadmap item explicitly requires
      a correction.

## Preconditions

- [ ] `AGENTS.md` was read before implementation.
- [ ] `docs/architecture/CODEX-EXECUTION-PLAN.md` was read.
- [ ] Baseline documentation impact was checked:
  - [ ] `docs/adr/` unchanged unless explicitly approved.
  - [ ] `docs/api/` unchanged unless explicitly approved.
- [ ] Generated roadmap/architecture guidance was compared with the real source
      tree before changes.

## Implementation scope

- [ ] Files expected to change are listed before editing.
- [ ] Files changed match the selected roadmap item.
- [ ] Application source code changes are included only when required by the
      selected item.
- [ ] Out-of-scope findings are recorded instead of being fixed opportunistically.

## Coding standards for touched files

- [ ] Mandatory class header fields are present where Java files are touched.
- [ ] JavaDoc is present where required.
- [ ] Constructor injection is used; field injection is not introduced.
- [ ] DTO Bean Validation and controller `@Valid` usage are correct when touched.
- [ ] No duplicated manual DTO validation is introduced.
- [ ] Domain models stay free of validation-framework leakage unless approved.
- [ ] Public API/controller DTO changes are reflected in API/OpenAPI docs when
      the roadmap item requires it.
- [ ] No controller-to-repository, domain-to-infrastructure, or cross-module
      entity-sharing violations are introduced.
- [ ] Package and naming consistency is preserved for touched files.

## Documentation and checklist updates

- [ ] `docs/architecture/CODEX-EXECUTION-PLAN.md` records:
  - [ ] item status
  - [ ] current step
  - [ ] next step
  - [ ] files changed
  - [ ] documentation updated
  - [ ] checklist updated
  - [ ] validation commands and result
  - [ ] remaining risks
  - [ ] follow-up items
- [ ] `docs/roadmap/checklists/<roadmap-item-id>.md` is created or updated when
      detailed tracking is required.

## Validation

- [ ] Roadmap-specific validation command:
- [ ] Validation result is recorded as pass, fail, or environment limitation.
- [ ] Any non-runnable command has a specific explanation.
- [ ] Failures caused by the current item are fixed before PR readiness.
- [ ] Failures outside the current item are recorded as follow-up risks.

## Closure

- [ ] `git status` was inspected.
- [ ] `git diff --stat` was inspected.
- [ ] `git diff` was inspected.
- [ ] PR readiness is explicitly marked Ready or Not ready.
- [ ] Recommended PR title and description match the selected roadmap item.
