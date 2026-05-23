# Layer Dependency Analysis (Phase 2)

## Intended Layering (inferred)
- API/Controller layer
- Application services/commands/dto/mappers
- Domain model/value/repository contracts
- Infrastructure adapters (JPA, security adapters, config)
- Platform cross-cutting services

## Observed Dependency Posture

### Positive signals
- Arch tests exist:
  - `DddLayeringRulesTest`
  - `ModuleDependencyRulesTest`
  - `NoCrossModuleJpaAccessTest`
- Modules expose application service façades and ports.
- Persistence adapters are mostly isolated to infrastructure packages.

### Layer risks
1. **Shared vs Platform overlap for request correlation and exception handling**
   - Risk: unclear ownership and accidental bidirectional dependencies.
2. **Shared kernel domain specificity leakage**
   - Risk: domain layer in modules depending on shared domain types that should be module-local.
3. **Identity persistence dual path (JPA + in-memory in main tree)**
   - Risk: dependency graph variance across environments.

## Potential Cyclic Dependency Pressure Points
- `shared/api` ↔ `platform/exception` responsibilities.
- Module application services consuming platform authorization while platform handlers consume shared API contracts.

## Transaction Boundary Assessment (structural)
- Outbox and audit capabilities are present, indicating intent for transactional consistency.
- Explicit transactional boundary definitions are not visibly centralized (likely annotation-distributed).
- Risk: service-level inconsistency for multi-aggregate operations.

## Layer Conformance Rating
- API → Application: **Good**
- Application → Domain: **Good**
- Domain purity: **Moderate** (shared-kernel leakage)
- Infrastructure isolation: **Moderate-Good**
- Cross-cutting ownership clarity: **Moderate**
