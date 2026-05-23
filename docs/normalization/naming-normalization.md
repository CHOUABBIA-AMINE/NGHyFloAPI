# Naming Normalization

## Naming Conventions
- Commands: `*Command`
- Queries: `*Query`
- Responses/DTOs: `*Request`, `*Response`
- Mappers: `*Mapper`
- Adapters: `*Adapter`
- Specifications: `*Specification`
- Policies/Evaluators: `*Policy`, `*Evaluator`
- Persistence entities: `*JpaEntity`
- Repositories:
  - Domain ports: `*Repository`
  - Spring Data interfaces: `SpringData*Repository`

## Identifier Naming
- Domain-specific IDs remain in owning context.
- Shared-kernel IDs must be domain-agnostic (tenant, correlation, causation, event).

## Package Naming
- lowercase, context-first: `modules.<context>.<layer>...`
- avoid mixed ownership names inside shared/kernel packages.
