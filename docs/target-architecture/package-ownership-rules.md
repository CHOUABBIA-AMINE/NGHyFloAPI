# Package Ownership Rules

## Ownership Model
Each bounded context owns:
- `api` (controllers, request/response DTOs)
- `application` (commands/queries, handlers, orchestration)
- `domain` (aggregates, entities, value objects, domain services, specs)
- `infrastructure` (persistence, messaging, external adapters)

## Rules
- `modules.<context>.domain` is private to context.
- `modules.<context>.application.port.in|out` are the only stable cross-context seams.
- `modules.<context>.infrastructure` cannot be imported by other contexts.
- `platform-runtime.security` exposes policy interfaces, not module internals.
- `shared-kernel` must not include domain-specific IDs (e.g., employee/structure domain IDs).

## Enforced Naming
- `*Command`, `*Query`, `*Event`, `*Policy`, `*Specification`, `*Adapter`, `*JpaEntity`, `*Mapper`.
