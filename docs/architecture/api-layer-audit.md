# API Layer Audit (Phase 2)

## Controllers and Endpoint Design
- Controllers are located in API packages and call application services (good layering baseline).
- No direct controller-to-JPA repository dependency detected in inspected controllers.

## Findings
- **Correlation inconsistency (Medium):** some controllers create correlation IDs directly while others use correlation context.
- **Validation placement inconsistency (Medium):** request records use constructor-thrown `IllegalArgumentException` rather than Bean Validation annotations + `@Valid` flow.
- **DTO strategy partial (Low/Medium):** response wrapping is consistent via `ApiResponse`, but request normalization logic is duplicated across request records.
- **OpenAPI maturity gap (Medium):** repository contains placeholder OpenAPI artifact; endpoint contract generation/annotation maturity appears incomplete.

## Controller Bloat
- Current controllers are relatively lean and focused on command mapping + service delegation.
- No major controller bloat found in inspected endpoints.

## Exception Mapping
- Platform-level exception handlers provide centralized mapping; API layer relies on this effectively.
