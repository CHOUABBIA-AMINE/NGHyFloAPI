# Test Architecture Audit (Phase 2)

## Test Suite Shape
- Architecture tests (ArchUnit): layering, module dependency direction, no cross-module JPA access.
- Module tests: identityaccess and organization include API, application, domain, and selected infra tests.
- Platform/shared tests: exception handling, audit hashing, outbox event model, correlation context, shared kernel contracts.

## Strengths
- Clear architecture guardrails encoded as executable tests.
- Broad naming consistency (`*Test`) and package mirroring with production code.

## Gaps / Risks
- **Critical integration depth (Medium/High):** limited evidence of full integration tests across HTTP + DB + security + outbox together.
- **Advisory architecture tests drift (Low/Medium):** ArchUnit checks mention modules not currently present (`intelligence`, `digitaltwin`, `hydraulics`), reducing practical enforcement relevance.
- **Coverage asymmetry (Medium):** some platform abstractions (outbox cleanup/dispatch concrete wiring) appear less directly tested end-to-end.
- **Duplication indicator (Low/Medium):** similarly named tests exist across root/platform subpackages (e.g., actor spoofing / outbox event), suggesting potential overlap.

## Recommendation Direction (Audit-Only)
- Tighten integration scenario coverage for transactional boundaries and security/eventing interplay.
- Re-scope architecture tests to active module set or clearly mark forward-looking rules.
