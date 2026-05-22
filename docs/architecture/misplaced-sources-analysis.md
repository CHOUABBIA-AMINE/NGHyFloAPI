# Misplaced Sources Analysis (Phase 2)

## M1 — `shared.api.RequestCorrelationFilter`
- **Issue:** cross-cutting observability concern is placed in shared API as empty artifact.
- **Correct target:** `platform.observability.correlation` (already contains concrete implementation).
- **Impact:** ownership confusion and duplicate type naming across layers.

## M2 — `OrganizationApplicationService` in-memory orchestration
- **Issue:** application service embeds persistence-like storage concerns (`Map` state) and pseudo-repository behavior.
- **Correct target:** replace with port-driven orchestration in application service; persistence delegated to infrastructure adapters.
- **Impact:** violates clean hexagonal boundaries and can diverge from transactional behavior of JPA path.

## M3 — Request validation via constructor exceptions
- **Issue:** transport validation behavior is embedded in DTO constructor logic instead of explicit validation layer.
- **Correct target:** API validation annotations + centralized validation/exception mapping policy.
- **Impact:** inconsistent validation semantics and less expressive API contract metadata.

## M4 — Mapper-level status transition application
- **Issue:** infra mapper applies lifecycle status transitions while rehydrating domain objects.
- **Correct target:** domain reconstitution/factory methods or dedicated state restoration policy.
- **Impact:** domain behavior partially hidden in infrastructure mapping.
