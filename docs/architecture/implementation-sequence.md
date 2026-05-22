# NGHyFlo Implementation Sequence

## Current Progress
Last updated: 2026-05-22

The repository has passed skeleton generation and now contains implemented slices through R4-005 stabilization. The next work must complete missing R0-R4 package coverage before starting R5 workflow.

## Completed Baseline
- Maven project baseline exists.
- Dependency strategy is documented.
- Spring Boot entrypoint exists and scans `dz.sh.trc.nghyflo`.
- Bootstrap status API exists.
- CI workflow exists.
- ArchUnit modular-monolith guardrails exist.
- Flyway migrations exist through `V005__nghyflo_organization_staffing.sql`.

## Completed Waves

### R0 — Repository Foundation
- R0-001 selected source formatting and header normalization.
- R0-002 GitHub Actions CI pipeline.
- R0-003 ArchUnit modular-monolith guardrails.
- R0-004 documentation reconciliation baseline.

### R1 — Shared Kernel
- R1-001 typed identifiers.
- R1-002 industrial measurement values.
- R1-003 event metadata and envelopes.
- R1-004 shared-kernel tests.

### R2 — Platform Core
- R2-001 outbox lifecycle.
- R2-002 audit chain and metadata.
- R2-003 actor resolver/no-spoofing boundary.
- R2-004 RBAC, ABAC, operational-scope, approval-authority, and SoD evaluators.
- R2-005 exception/correlation handling.
- R2-006 audit/outbox migration hardening.

### R3 — Identity Access
- R3-001 password encoder boundary.
- R3-002 API request DTO separation.
- R3-003 role/session model partially completed.
- R3-004 JPA user/role persistence.
- R3-005 identity schema completion.
- R3-006 authentication application flow.
- R3-007 executable identity tests for mapper/DTO coverage.

### R4 — Organization
- R4-001 staffing coverage, shift, and coverage allocation domain baseline.
- R4-002 coverage evaluation application service and platform scope mapping.
- R4-003 organization persistence baseline for employees and coverage allocations.
- R4-004 coverage evaluation REST API.
- R4-005 stabilization: bootstrap scan, correlation context, correlation filter registration, identity wiring cleanup, selected standards normalization, and docs reconciliation.

## Corrected Next Implementation Order

### R4 Completion Before Workflow
1. R4-006 — Complete organization value objects and staffing responsibility primitives.
2. R4-007 — Add organization unit, position, responsibility profile, shift assignment, and staffing requirement domain model.
3. R4-008 — Complete organization persistence including shift adapter and normalized coverage storage.
4. R4-009 — Expose organization management APIs for employee, unit, position, shift, and coverage management.
5. R4-010 — Run/fix compile, tests, verify, Checkstyle, and ArchUnit.

### R3 Fixes Required Before Workflow
1. R3-FIX-001 — Complete identity `Permission`, `AccessPolicy`, `LoginAttempt`, and standards normalization.
2. R3-FIX-002 — Add session/renewal persistence adapters and JWT session artifact issuer.

### R2/R1 Fixes Required Before Workflow
1. R2-FIX-001 — Complete correlation headers/configuration and exception mapping hardening.
2. R1-FIX-001 — Complete remaining shared geospatial/industrial value object hardening.

### R5 — Workflow Integrity
Start only after R4-010 passes.
1. R5-001 — Implement workflow definition, instance, transition, actor, approval chain, and immutable transition events.
2. R5-002 — Add workflow persistence.
3. R5-003 — Add workflow API.
4. R5-004 — Add workflow tests and authorization integration.

### R6 — Topology Foundation
Implement pipeline topology, assets, GIS placeholders, and topology versioning.

### R7 — Measurement Lifecycle
Implement raw, validated, approved, and published measurement lifecycle integrated with workflow, audit, and outbox.

### R8 — Telemetry Ingestion
Implement telemetry registry, raw ingestion, quality classification, deduplication, dead letters, and SCADA/OPCUA/MQTT/DNP3 adapter contracts.

### R9 — Operational State and Replay
Implement current operational state, snapshots, KPIs, and replay cursor.

### R10 — Anomaly, Alert, Incident
Implement anomaly detection, alert acknowledgement, incident escalation, corrective actions, and lifecycle audit.

### R11 — Planning, Historian, Reporting, Integration, Notification
Implement operational support bounded contexts after the core operational flow is stable.

### R12 — Event Backbone Hardening
Harden outbox dispatch, retry, idempotency, dead-letter, and event topic contracts.

### R13 — Advisory Modules
Implement intelligence, digital twin, and hydraulics as advisory-only modules.

### R14 — Edge and Deployment Readiness
Implement edge store-forward skeleton, deployment manifests, and observability stack.

## Execution Rules
Every commit must:
- create or modify source/docs/tests;
- preserve NGHyFlo naming and headers;
- avoid exposing domain objects directly through REST APIs;
- maintain modular-monolith boundaries;
- report compile/test/verify status honestly;
- update documentation when architecture or sequence changes;
- avoid starting R5 until R0-R4 stabilization and completion gates pass.
