# Naming Conventions Analysis (Discovery)

## Package Naming
- Lowercase, domain-first package namespace: `dz.sh.trc.nghyflo...`.
- Layer names are explicit and consistent: `api`, `application`, `domain`, `infrastructure`.
- Module scoping uses `modules.<context>`.

## Class Naming
- Domain model nouns: `User`, `Role`, `Employee`, `Structure`, etc.
- Value objects suffixed or semantic-typed (`...Id`, `...Code`, `PasswordHash`, etc.).
- Application commands consistently use `...Command` suffix.
- Services use `...Service` / `...ApplicationService`.
- Persistence adapters include technology hints (`Jpa...Adapter`, `SpringData...Repository`, `...JpaEntity`).
- REST layer uses `...Controller`, request DTOs `...Request`, API mappers `...ApiMapper`.

## Repository/Port Naming
- Domain repositories use `...Repository`.
- Application-side abstraction ports use `...Port`.
- Infra implementations often include `Jpa` / `SpringData` prefixes.

## Exception & Policy Naming
- Domain exception hierarchy under `shared.domain.exception` using business-meaningful names.
- Authorization decisions/evaluators use policy-centric nouns (`PolicyDecision`, `RbacEvaluator`, `AbacEvaluator`).

## Conventions Consistency Notes
- Overall naming is coherent and DDD-oriented.
- Preliminary inconsistencies/duplication indicators:
  - Similar cross-cutting filter naming in both shared and platform packages.
  - `StructureId` naming appears in both shared and organization-specific value namespaces.
