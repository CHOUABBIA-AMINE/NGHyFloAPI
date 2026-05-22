# Module Dependency Rules (Target)

## Allowed Dependencies
- `shared-kernel` -> no module dependencies.
- `security` -> `identityaccess`, `organization`, `shared-kernel`.
- `audit` -> `shared-kernel` only (consumes events from all contexts).
- Domain contexts (`topology`, `telemetry`, `planning`, `monitoring`, `incidents`, `workflow`, `analytics`, `integration`, `identityaccess`, `organization`) -> `shared-kernel`, optionally `security` contracts.

## Forbidden Dependencies
- Any context -> another context's infrastructure package.
- API layer -> infrastructure persistence implementations.
- Domain layer -> Spring/JPA/web frameworks.
- Context-to-context direct entity reference.
- Bidirectional dependencies between bounded contexts.

## Dependency Graph
```mermaid
graph LR
  SK[shared-kernel]
  IA[identityaccess]
  ORG[organization]
  SEC[security]
  TOP[topology]
  TEL[telemetry]
  PLAN[planning]
  MON[monitoring]
  INC[incidents]
  WF[workflow]
  ANA[analytics]
  INT[integration]
  AUD[audit]

  IA --> SK
  ORG --> SK
  TOP --> SK
  TEL --> SK
  PLAN --> SK
  MON --> SK
  INC --> SK
  WF --> SK
  ANA --> SK
  INT --> SK
  AUD --> SK
  SEC --> SK

  SEC --> IA
  SEC --> ORG

  MON --> TEL
  INC --> MON
  WF --> INC
  WF --> PLAN
  ANA --> TEL
  ANA --> MON

  classDef forbidden fill:#fee,stroke:#f66,stroke-width:1px;
```

## Enforcement model
- ArchUnit rules per layer and per context.
- Build fails on forbidden package dependency.
- Dependency whitelist maintained per module in architecture tests.
