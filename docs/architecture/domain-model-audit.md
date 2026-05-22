# Domain Model Audit (Phase 2)

## Scope and Baseline
This audit extends the Phase 1 baseline docs in `docs/architecture/*` and drills into domain-layer artifacts across `shared`, `identityaccess`, and `organization`.

## IdentityAccess Domain Findings
- `User` and `Role` contain core invariants (null guards, role/permission assignment behavior) and are not fully anemic.
- `User` aggregate currently allows unconstrained role assignment (no existence/authorization policy at aggregate level), so consistency is delegated to upstream layers.
- `UserRepository` is minimal and correctly domain-scoped.

### Risks
- **Aggregate leakage (Medium):** role semantics are split between `User` and infra role lookup/mapping logic, which weakens aggregate ownership of role lifecycle.
- **Transactional consistency risk (Medium):** user-role association relies on infra-side role materialization (`getOrCreate` style flow), not domain policy objects.

## Organization Domain Findings
- `Employee` has basic lifecycle methods and state transitions (`suspend`, `markOnLeave`, `retire`, `assignStructure`).
- `Structure` and `StaffingCoverage` are value-centric models with simple validation and inclusion semantics.
- Coverage logic partly resides in domain (`StaffingCoverage.includes`) and partly in application service filtering.

### Risks
- **Anemia tendency (Medium):** several organization domain records are mostly data carriers with limited encapsulated policy.
- **Duplicate concept risk (High):** `StructureId` exists both in module-local and shared namespaces, creating semantic ambiguity.
- **Aggregate boundary ambiguity (Medium):** no explicit aggregate root contract for staffing/coverage workflow; lifecycle and consistency rules are spread.

## Shared Domain Findings
- Shared kernel provides base abstractions/events/exceptions/value objects; this is a strong DDD foundation.
- Broad shared value catalog may over-centralize context-specific identifiers, increasing accidental coupling.

## Domain Events & Invariants
- Domain event abstractions exist in `shared.domain.event`, but module-specific event publication behavior is not strongly surfaced in domain services.
- Invariants are mostly constructor guards and simple state checks; policy-rich invariants are limited.

## Domain-Level Violations/Indicators Summary
- Potential duplicate identity concepts (`StructureId` split namespaces).
- Invariant enforcement skewed toward constructors and application guards instead of deeper aggregate policies.
- Domain services are sparse; orchestration and policy checks concentrate in application services.
