# Current Architecture Analysis (Discovery)

## Architectural Pattern Signals
- Strong **modular monolith** layout: `modules/<bounded-context>` with internal `api/application/domain/infrastructure` layering.
- **DDD tactical patterns** present: aggregates/entities/value objects/domain events/domain exceptions.
- **Hexagonal/ports-adapters signals** in application ports plus infrastructure adapters (notably in organization and identity modules).
- **Shared kernel + platform split**: shared abstractions in `shared`, runtime cross-cutting concerns in `platform`.
- **Architecture conformance tests** enforced via ArchUnit tests under `src/test/java/.../architecture`.

## Preliminary Module Boundaries & Bounded Contexts
- `identityaccess`: identity/authz user-role-session lifecycle.
- `organization`: org structure, staffing coverage, and operational scope evaluation.
- `platform`: security, tenancy, outbox, observability, auditing, exception handling.
- `shared`: common domain/application/api/infrastructure primitives.
- `bootstrap`: startup and readiness endpoint/probes.

## Layering Rules Evidence
- Architecture tests explicitly prohibit:
  - `api -> infrastructure`
  - `application -> api`
  - `application -> infrastructure`
- Separate tests indicate module isolation and no cross-module JPA access constraints.

## Workflow & Runtime Discovery
- CI workflows run compile/test/verify on pushes/PRs.
- Runtime config profiles: default/dev/test/staging/production.
- Flyway migration track exists for platform and domain modules.

## Preliminary Risk Indicators (Discovery-Only)
- **Potential duplicated concepts**:
  - `RequestCorrelationFilter` exists in both `platform.observability.correlation` and `shared.api` namespace (possible overlap).
  - `StructureId` appears in both shared value package and organization module value package naming space (concept collision candidate).
  - Duplicate migration version prefix `V003__...` used by two files, which can be problematic depending on Flyway naming/ordering policy.
- **Misplacement indicators**:
  - Some API-ish cross-cutting classes in `shared.api` overlap with platform exception/correlation handling responsibilities.
- **Dead code indicators** (preliminary only):
  - Presence of both in-memory and JPA persistence implementations may imply environment-specific alternates; usage paths require runtime bean resolution audit.
- **Cyclic dependency indicators**:
  - No explicit cycle confirmed in discovery pass; ArchUnit isolation tests suggest intentional prevention.
