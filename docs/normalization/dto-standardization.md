# DTO Standardization

## DTO Rules
1. DTOs are transport/application contracts only; never domain entities.
2. Request DTOs stay in `api.rest.request` (or protocol-equivalent ingress package).
3. Response DTOs stay in `application.dto` or `api.rest.response` consistently per context.
4. No DTO sharing across bounded contexts without explicit contract package.

## Contract Normalization
- Standard metadata envelope for external APIs:
  - `correlationId`
  - `timestamp`
  - `tenant`
  - `version`
- Error DTOs normalized around one ProblemDetails contract.

## Backward Compatibility
- Version DTOs for breaking changes.
- Keep additive evolution by default.
