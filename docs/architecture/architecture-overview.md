# NGHyFloAPI Architecture Overview

## Current Delivery Status
- Phase 0.1 completed: dependency governance baseline.
- Phase 0.2 completed: project quality gates.
- Phase 0.3 completed: bootstrap hardening with startup readiness probe contracts.

## Bootstrap Runtime Baseline
- `GET /nghyflo/api/v1/bootstrap/status` exposes system identity and bootstrap status.
- Bootstrap status is derived from readiness probe contract aggregation.
- Startup runner logs bootstrap initialization for operator traceability.

## TODO
- Add detailed bounded-context architecture mapping.
- Add explicit outbox/event backbone sequencing diagram.
- Add security boundary deep dive in dedicated architecture document.
