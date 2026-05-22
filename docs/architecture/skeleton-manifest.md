# NGHyFloAPI Skeleton Manifest

## Repository Name
NGHyFloAPI

## Last Updated
2026-05-22

## Current Delivery Slice
R4-005 — R0-R4 Stabilization, Wiring, Standards, and Documentation Reconciliation

## Product Identity
- Product: NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
- Repository: NGHyFloAPI
- Root package: `dz.sh.trc.nghyflo`
- API base path: `/nghyflo/api/v1`
- Database schema: `nghyflo`
- Event topic prefix: `nghyflo`
- Kubernetes namespace: `nghyflo`

## Java Version Selected
21 LTS

## Spring Boot Version Selected
4.0.6

## Dependency Verification Method
Dependency choices are recorded in `docs/architecture/dependency-version-report.md` and reflected in `pom.xml`.

## Actual Repository State After R4-005

### Completed Baseline
- Maven project baseline exists.
- Dependency governance report exists.
- NGHyFlo Spring Boot entrypoint exists and now scans `dz.sh.trc.nghyflo`.
- NGHyFlo application identity constants exist.
- Bootstrap status endpoint exists.
- Production profile baseline exists.
- GitHub Actions CI exists.
- ArchUnit modular-monolith guardrails exist.
- Flyway migrations exist through organization staffing baseline.

### Completed R0
- R0-001 source formatting/header normalization for selected high-risk files.
- R0-002 CI workflow.
- R0-003 ArchUnit modular-monolith guardrails.
- R0-004 documentation reconciliation baseline.

### Completed R1
- R1-001 typed identifier hardening.
- R1-002 industrial measurement value hardening.
- R1-003 domain event and metadata contracts.
- R1-004 shared-kernel value object and event metadata tests.

### Completed R2
- R2-001 outbox event model/status lifecycle hardening.
- R2-002 audit chain and audit metadata hardening.
- R2-003 actor resolver/no-spoofing boundary hardening.
- R2-004 RBAC, ABAC, operational scope, approval authority, and segregation-of-duty evaluators.
- R2-005 global exception and correlation handling.
- R2-006 audit/outbox industrial metadata migration.

### Completed R3
- R3-001 password encoder boundary and Spring Security adapter.
- R3-002 API request DTO separation from application commands.
- R3-003 identity role/session model partially completed.
- R3-004 JPA user/role repository adapter added.
- R3-005 identity schema completion migration.
- R3-006 login, refresh, logout, and session application flow.
- R3-007 executable identity mapper/response tests.

### Completed R4
- R4-001 organization staffing coverage, shift, and coverage allocation domain baseline.
- R4-002 organization coverage evaluation application service.
- R4-003 organization employee/coverage allocation persistence baseline and wiring.
- R4-004 organization coverage evaluation REST API.
- R4-005 stabilization fixes started: bootstrap scanning, request-scoped correlation context, correlation filter registration, identity runtime wiring cleanup, selected standards normalization, and documentation reconciliation.

## Known Gaps Before R5
- `organization.domain.value` is incomplete and currently only contains assignment/shift identifiers.
- Organization domain still needs organization unit, position, responsibility profile, and shift assignment model.
- Organization persistence still needs shift JPA adapter and normalized coverage tables.
- Identity domain is missing `Permission`, `AccessPolicy`, and `LoginAttempt` model files.
- Identity auth application ports exist, but session/renewal persistence adapters and token issuer infrastructure are missing.
- `PasswordHash.java` still requires standards normalization; connector safety checks blocked patching it.
- Some R3/R4 files still need mandatory header normalization.
- `KilometerPost` and `GeoCoordinate` still need shared-kernel hardening.
- Workflow, topology, measurement, telemetry, operational-state, anomaly/incident, planning, historian, reporting, integration, notification, advisory, and edge modules are still pending real implementation.

## Validation Status
- Compile: Not executable through connector environment.
- Tests: Not executable through connector environment.
- Verify: Not executable through connector environment.
- CI: Expected to execute on push or pull request, but latest run/status was not visible through connector.

## Next Recommended Commit
R4-006 — Complete organization value objects and staffing responsibility primitives.
