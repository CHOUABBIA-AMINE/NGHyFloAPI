# Coding Standards Target (Architecture-Aligned)

## Naming conventions
- Bounded context prefix for integration events and contracts.
- `*Command`, `*Query`, `*Handler` enforced in application layer.
- `*Port` for application ports, `*Adapter` for infrastructure adapters.
- `*JpaEntity` strictly in infrastructure persistence packages.

## DTO conventions
- API transport DTOs in `.api.rest.request/.response`.
- Application DTOs in `.application.dto` only.
- No domain entities exposed directly through API DTOs.

## Mapper conventions
- API mappers map transport DTO <-> command/query DTO.
- Persistence mappers map domain <-> entity only.
- Cross-context mapping occurs in Integration BC anti-corruption layer.

## Validation conventions
- Bean Validation annotations on transport DTOs.
- `@Valid` enforced at controller boundary.
- Domain invariants remain in value objects/aggregates.
- No duplicated ad-hoc validation helpers across DTOs.

## Exception strategy
- Layered exception taxonomy:
  - domain exceptions
  - application exceptions
  - infrastructure exceptions
  - API problem mapping
- Deterministic mapping to standardized error envelope with correlation id.

## Audit strategy
- Audit event emitted for all command-side state changes.
- Sensitive actions require decision trace (actor, scope, policy basis, correlation).
- Hash-chain evidence maintained for tamper detection.

## Logging strategy
- Structured logs (JSON) with mandatory fields:
  - correlationId
  - tenantId
  - actorId
  - module
  - useCase
  - outcome
- Log levels standardized by event criticality.
- No PII/secrets in logs.
