# NGHyFloAPI Architecture Overview

> Canonical navigation: [Architecture Documentation Index](index.md)


## Current Delivery Status
- Phase 0.1 completed: dependency governance baseline.
- Phase 0.2 completed: project quality gates.
- Phase 0.3 completed: bootstrap hardening with readiness probe contracts.
- Phase 0.4 completed: shared kernel activation baseline.

## Shared Kernel Activation Scope
- Added shared domain model contracts (`AggregateRoot`, `DomainEvent`, lifecycle markers).
- Added shared value objects for identity and measurement concepts.
- Added shared event envelope and metadata primitives.
- Added shared domain exception taxonomy.
- Added shared application, API, and infrastructure contract placeholders for subsequent slices.

## TODO
- Replace placeholder shared API records with final response envelope structure.
- Add ArchUnit-based dependency enforcement test suite over shared kernel and modules.
- Implement shared infrastructure concrete behavior in platform core slice.
- Phase 0.3 completed: bootstrap hardening with startup readiness probe contracts.

## Bootstrap Runtime Baseline
- `GET /nghyflo/api/v1/bootstrap/status` exposes system identity and bootstrap status.
- Bootstrap status is derived from readiness probe contract aggregation.
- Startup runner logs bootstrap initialization for operator traceability.

## TODO
- Add detailed bounded-context architecture mapping.
- Add explicit outbox/event backbone sequencing diagram.
- Add security boundary deep dive in dedicated architecture document.
# architecture-overview.md

## TODO
