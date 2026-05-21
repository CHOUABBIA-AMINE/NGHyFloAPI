# NGHyFlo Implementation Sequence

## Current Progress
Last updated: 2026-05-21

The repository has passed the initial skeleton-generation stage and is now in repository stabilization plus shared-kernel hardening.

## Completed Baseline
- Maven project baseline exists.
- Dependency strategy is documented.
- Spring Boot entrypoint exists.
- NGHyFlo application identity constants exist.
- Bootstrap status API exists.
- Production profile baseline exists.
- Platform audit/outbox migration baseline exists.
- Partial shared-kernel primitives exist.
- Partial platform security/audit/outbox primitives exist.
- Partial identity-access module exists.
- Partial organization module exists.

## Completed R0 Stabilization

### R0-001 — Source Formatting and Header Normalization
Status: Complete for selected high-risk generated files.

Result:
- Added headers to selected generated Java source.
- Reformatted selected compressed Java files.
- Cleaned bootstrap status test imports.

Remaining:
- Full repository-wide header normalization remains necessary as part of future cleanup.

### R0-002 — GitHub Actions CI Pipeline
Status: Complete.

Result:
- Added `.github/workflows/ci.yml`.
- CI runs compile, tests, and verify gates.

### R0-003 — ArchUnit Modular-Monolith Guardrails
Status: Complete.

Result:
- Added active ArchUnit tests for DDD layering, module dependencies, JPA access boundaries, and advisory module isolation.

### R0-004 — Repository Documentation Reconciliation
Status: Current.

Result:
- Documentation updated to reflect actual repository state.
- Old theoretical sequence replaced by corrected remaining sequence.

## Corrected Next Implementation Order

### R1 — Shared Kernel Hardening
1. R1-001 — Harden typed identifiers with validation and factories.
2. R1-002 — Replace primitive industrial measurement values with validated `BigDecimal` models.
3. R1-003 — Complete domain event and metadata contracts.
4. R1-004 — Add shared-kernel value object and event metadata tests.

### R2 — Platform Core Hardening
1. R2-001 — Harden outbox event model and status lifecycle.
2. R2-002 — Harden audit chain and audit metadata model.
3. R2-003 — Harden actor resolver and no-spoofing boundary.
4. R2-004 — Complete RBAC, ABAC, and segregation-of-duty evaluators.
5. R2-005 — Complete global exception and correlation handling.
6. R2-006 — Expand audit and outbox migration for industrial metadata.

### R3 — Identity Access Correction and Completion
1. R3-001 — Replace weak password hashing with `PasswordEncoder` boundary.
2. R3-002 — Separate API request DTOs from application commands.
3. R3-003 — Complete role, permission, and session model.
4. R3-004 — Replace in-memory identity persistence with JPA adapters.
5. R3-005 — Complete identity-access schema migration.
6. R3-006 — Implement login, refresh, logout, and session flow.
7. R3-007 — Add executable identity and authentication tests.

### R4 — Organization Completion
1. Complete staffing and operational scope model.
2. Implement employee, structure, region, and operational responsibility use cases.
3. Add organization persistence and APIs.

### R5 — Workflow Integrity
1. Implement workflow definition, instance, actor, approval, and immutable transition events.
2. Enforce no actor spoofing and segregation of duty.
3. Add workflow persistence, APIs, and tests.

### R6 — Topology Foundation
1. Implement pipeline topology, assets, GIS placeholders, and topology versioning.
2. Add topology persistence, APIs, and tests.

### R7 — Measurement Lifecycle
1. Implement raw, validated, approved, and published measurement lifecycle.
2. Integrate workflow, audit, and outbox.

### R8 — Telemetry Ingestion
1. Implement telemetry registry, raw measurement ingestion, quality classification, deduplication, and dead letters.
2. Add SCADA, OPCUA, MQTT, DNP3, historian adapter interfaces.

### R9 — Operational State and Replay
1. Implement current operational state, snapshots, KPIs, and replay cursor.

### R10 — Anomaly, Alert, Incident
1. Implement anomaly detection, alert acknowledgement, incident escalation, corrective actions, and lifecycle audit.

### R11 — Planning, Historian, Reporting, Integration, Notification
1. Implement operational support bounded contexts after core operational flow is stable.

### R12 — Event Backbone Hardening
1. Harden outbox dispatch, retry, idempotency, dead-letter, and event topic contracts.

### R13 — Advisory Modules
1. Implement intelligence, digital twin, and hydraulics as advisory-only modules.

### R14 — Edge and Deployment Readiness
1. Implement edge store-forward skeleton, deployment manifests, and observability stack.

## Execution Rule
Every commit must:
- create or modify source/docs/tests;
- preserve NGHyFlo naming and headers;
- pass or report compile/test/verify status;
- update documentation if architecture or sequence changes;
- avoid jumping ahead of the corrected sequence.
