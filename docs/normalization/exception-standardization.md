# Exception Handling Standardization

## Target Model
- Single global exception orchestration entrypoint with specialized mappers by category:
  - domain/business
  - security/authorization
  - integration/protocol
  - telemetry/quality

## Rules
1. One canonical ProblemDetails factory and payload schema.
2. Deterministic precedence order for handlers.
3. No duplicate fallback handlers across shared and platform layers.
4. Exception-to-HTTP mapping documented and contract-tested.

## Migration Strategy
- Consolidate overlapping handlers.
- Keep category-specific translation components, but centralize dispatch.
