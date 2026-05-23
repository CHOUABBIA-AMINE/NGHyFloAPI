# Validation Standardization

## Validation Layers
- API input validation: bean validation annotations + request validators.
- Application validation: use-case preconditions.
- Domain validation: invariants and business rules in aggregates/specifications.

## Rules
1. No domain invariants in controllers.
2. Reusable business predicates expressed as `*Specification` in domain.
3. Validation errors normalized to common ProblemDetails structure.
4. Cross-field request checks implemented in dedicated validators, not controllers.

## Governance
- Validation catalog per context documenting:
  - syntactic rules,
  - semantic rules,
  - authorization-coupled rules.
