# Duplication Analysis (Phase 2)

## D1 — Correlation Filter Duplication
- **Overlap:** `shared.api.RequestCorrelationFilter` (empty placeholder) vs `platform.observability.correlation.RequestCorrelationFilter` (real filter).
- **Canonical:** `platform.observability.correlation.RequestCorrelationFilter`.
- **Obsolete candidate:** `shared.api.RequestCorrelationFilter`.
- **Merge/Delete strategy:** consolidate to platform implementation; remove or repurpose shared placeholder to avoid ambiguous imports.

## D2 — Identifier Concept Duplication (`StructureId`)
- **Overlap:** shared `StructureId` value object namespace and organization-local `StructureId` namespace usage patterns.
- **Canonical:** module-local for bounded-context semantics *or* shared if globally ubiquitous (must choose one).
- **Obsolete candidate:** whichever namespace is less context-appropriate after ownership decision.
- **Merge/Delete strategy:** single authoritative type + adapter at context boundary.

## D3 — Request Set-Sanitization Duplication
- **Overlap:** repeated `safe(Set<String>)` normalization logic in multiple request DTO records.
- **Canonical:** centralized request normalization utility or validation mapper per module.
- **Obsolete candidate:** duplicated per-record helpers.
- **Merge/Delete strategy:** extract shared normalization function at API mapping layer.

## D4 — Persistence Model Strategy Duplication
- **Overlap:** in-memory organization lifecycle storage (`OrganizationApplicationService`) vs JPA-backed adapters in same bounded context.
- **Canonical:** persistent adapter-backed model for production path.
- **Obsolete candidate:** in-memory service path unless explicitly dedicated for test/demo profile.
- **Merge/Delete strategy:** isolate profile-specific implementation or remove redundant execution path.

## D5 — Architecture Test Intent Duplication/Drift
- **Overlap:** architecture rules for non-existent modules duplicate intent of isolation but do not validate active contexts.
- **Canonical:** active-module architecture rules.
- **Obsolete candidate:** stale forward-looking checks without corresponding modules.
- **Merge/Delete strategy:** keep as future template in separate suite, enforce active modules in main gate.
