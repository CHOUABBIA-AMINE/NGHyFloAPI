# Testing Standardization

## Test Taxonomy
- Unit tests: domain invariants, mappers, validators, policies.
- Integration tests: repository adapters, controller contracts, exception mapping.
- Architecture tests: layering, module dependencies, ownership boundaries.
- Contract tests: event schema and API compatibility.

## Duplication Removal in Tests
- Keep one canonical test class per behavior in owning package.
- Remove mirrored duplicates in umbrella/root packages.

## Required Coverage Gates
1. Architecture rules must pass for every module.
2. Exception contract tests enforce normalized ProblemDetails output.
3. Security tests cover RBAC, ABAC, SoD permutations.
4. Event tests validate versioning, idempotency, and metadata completeness.

## Transaction Boundary Verification
- Tests must verify outbox write and aggregate mutation in same transaction.
- Workflow tests validate SLA timers and compensation paths.
