# Application Layer Audit (Phase 2)

## IdentityAccess Application Analysis
- `IdentityApplicationService` orchestrates user creation/role assignment/listing directly through `UserRepository` and credential port.
- `AuthenticationApplicationService` encapsulates login/refresh/logout orchestration with session/renewal ports and token issuance.

### Findings
- **CQRS boundary partial:** commands and DTOs are explicit, but dedicated query handlers/read models are not consistently separated.
- **Mixed responsibility (Medium):** service methods include orchestration + mapping + error semantics; mapping is not fully extracted.
- **Time-source coupling (Low/Medium):** direct `Instant.now()` usage in services bypasses shared clock abstraction.

## Organization Application Analysis
- `OrganizationCoverageService` is the strongest use-case service (ports + mapper + decision response).
- `OrganizationApplicationService` uses in-memory maps and random IDs for entity lifecycle commands.

### Findings
- **Service bloat / mixed concerns (High):** `OrganizationApplicationService` mixes command handling, temporary persistence, and read-model retrieval.
- **Improper domain access style (Medium):** direct construction of shared value objects and UUID generation in application service rather than dedicated factories/policies.
- **Orchestration duplication (Medium):** coverage evaluation path duplicated across command variants (`EvaluateCoverageCommand` + mapped operational scope command).

## DTO / Mapper / Validator Findings
- DTO strategy exists (`api.rest.request`, `application.dto`) but not uniformly validated via Bean Validation annotations.
- Mapper strategy is mixed (manual classes vs dependency presence of MapStruct), indicating inconsistency.
- Validation logic is embedded in record constructors, which creates transport-validation coupling to exception strategy.

## Application Layer Summary
- Strength: explicit commands and use-case naming.
- Gaps: query-side separation, validation consistency, time abstraction usage, and service responsibility boundaries.
