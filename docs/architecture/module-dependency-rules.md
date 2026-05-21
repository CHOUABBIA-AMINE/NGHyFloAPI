# Module Dependency Rules

## Purpose
Define enforceable dependency constraints for the NGHyFlo modular monolith.

NGHyFloAPI is intentionally implemented as a modular monolith first. Module independence must be enforced by package boundaries, architecture tests, application contracts, and outbox events before any future microservice extraction.

## Root Package

`dz.sh.trc.nghyflo`

## Layering Model

Each business module must follow:

```text
domain/
application/
infrastructure/
api/
```

## Allowed Dependency Direction

```text
api -> application -> domain
infrastructure -> application/domain
platform -> shared
modules -> shared/platform abstractions
```

## Forbidden Dependencies

- `domain` must not depend on `infrastructure`.
- `domain` must not depend on `api`.
- `domain` must not depend on Spring Framework.
- `domain` must not depend on JPA.
- `domain` must not depend on Spring Data repositories.
- `application` must not depend on `api`.
- `application` must not depend on `infrastructure`.
- `application` must not depend on Spring Data repositories.
- `api` must not depend on JPA infrastructure packages.
- Modules must not directly access another module's JPA repositories.
- Advisory modules must not depend on approval, publication, or incident-closure commands.

## Cross-Module Interaction Rules

Cross-module interaction must use one of:

1. application service contracts;
2. domain events;
3. outbox events;
4. read-model projections;
5. explicitly approved platform abstractions.

Direct repository access across bounded contexts is forbidden.

## Active ArchUnit Guardrails

The following tests now enforce the baseline rules:

- `DddLayeringRulesTest`
- `ModuleDependencyRulesTest`
- `NoCrossModuleJpaAccessTest`
- `AdvisoryModuleIsolationTest`

## Industrial Safety Rules

- No actor identity may be trusted from request bodies.
- No state-changing action should exist without audit path.
- No cross-module operational reaction should bypass outbox/event path.
- Raw telemetry must never be treated as approved operational truth.
- AI, digital twin, and hydraulic simulation modules must remain advisory-only.
- Workflow approval must enforce authenticated actor, authorization, valid transition, and segregation of duty.

## Current Limitations

- Some modules remain skeleton-only.
- Some source files still require header and formatting normalization.
- Additional architecture rules will be added as workflow, measurement, telemetry, and advisory modules become executable.
