# NGHyFloAPI Skeleton Manifest

## Repository Name
NGHyFloAPI

## Last Updated
2026-05-22

## Current Delivery Slice
R1-004 — Shared-Kernel Value Object and Event Metadata Test Coverage

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

## Actual Repository State

### Completed Baseline
- Maven project baseline exists.
- Dependency governance report exists.
- NGHyFlo Spring Boot entrypoint exists.
- NGHyFlo application identity constants exist.
- Bootstrap status endpoint exists.
- Production profile baseline exists.
- Platform audit/outbox migration baseline exists.
- Partial shared-kernel primitives exist.
- Partial platform security/audit/outbox primitives exist.
- Partial identity-access module exists.
- Partial organization module exists.

### Completed R0 Stabilization Commits

#### R0-001 — Source Formatting and Header Normalization
Updated selected generated files:
- `src/test/java/dz/sh/trc/nghyflo/bootstrap/BootstrapStatusControllerTest.java`
- `src/main/java/dz/sh/trc/nghyflo/platform/events/outbox/OutboxEvent.java`
- `src/main/java/dz/sh/trc/nghyflo/platform/audit/AuditChainHasher.java`
- `src/main/java/dz/sh/trc/nghyflo/platform/security/authorization/PolicyDecision.java`
- `src/main/java/dz/sh/trc/nghyflo/modules/identityaccess/domain/model/User.java`
- `src/main/java/dz/sh/trc/nghyflo/modules/identityaccess/application/service/IdentityApplicationService.java`
- `src/main/java/dz/sh/trc/nghyflo/modules/organization/domain/model/Employee.java`

Scope completed:
- Added mandatory NGHyFlo headers to selected generated Java files.
- Reformatted selected compressed Java source.
- Removed duplicate imports from bootstrap status test.
- Preserved behavior intentionally.

Limitations:
- Full repository-wide Java tree normalization remains incomplete.
- `UserController.java` still requires request/command separation and additional formatting in a later identity cleanup commit.

#### R0-002 — GitHub Actions CI Pipeline
Added:
- `.github/workflows/ci.yml`

CI gates:
- `mvn -q -DskipTests compile`
- `mvn -q test`
- `mvn -q verify`

#### R0-003 — ArchUnit Modular-Monolith Guardrails
Added:
- `src/test/java/dz/sh/trc/nghyflo/architecture/DddLayeringRulesTest.java`
- `src/test/java/dz/sh/trc/nghyflo/architecture/ModuleDependencyRulesTest.java`
- `src/test/java/dz/sh/trc/nghyflo/architecture/NoCrossModuleJpaAccessTest.java`
- `src/test/java/dz/sh/trc/nghyflo/architecture/AdvisoryModuleIsolationTest.java`

Active guardrails:
- Domain layer must not depend on infrastructure.
- Domain layer must not depend on API.
- Domain layer must not depend on Spring Framework or JPA.
- API layer must not depend on infrastructure.
- Application layer must not depend on API.
- Application layer must not depend on infrastructure.
- Domain/application layers must not depend on Spring Data repositories.
- API layer must not depend on JPA infrastructure packages.
- Advisory modules must not depend on approval, publication, or incident-closure command types.

### Completed R1 Shared-Kernel Commits

#### R1-001 — Typed Identifier Hardening
Added:
- `src/main/java/dz/sh/trc/nghyflo/shared/domain/value/IdentifierValues.java`
- `src/test/java/dz/sh/trc/nghyflo/shared/SharedKernelIdentifierTest.java`

Updated selected identifiers:
- `TenantId`
- `RegionId`
- `StructureId`
- `PipelineId`
- `SegmentId`
- `StationId`
- `MeasurementPointId`
- `SensorId`
- `GatewayId`
- `UserId`
- `EmployeeId`
- `WorkflowInstanceId`
- `EventId`
- `CorrelationId`
- `CausationId`

Scope completed:
- Replaced empty identifier records with UUID validation.
- Added `newId()` factories.
- Added `of(String value)` factories.
- Preserved canonical record constructors for existing call sites.
- Added focused shared-kernel identifier tests.

#### R1-002 — Industrial Measurement Value Hardening
Added:
- `src/main/java/dz/sh/trc/nghyflo/shared/domain/value/MeasurementValues.java`
- `src/test/java/dz/sh/trc/nghyflo/shared/IndustrialMeasurementValueTest.java`

Updated measurement value objects:
- `Pressure`
- `Temperature`
- `FlowRate`
- `Volume`
- `Density`
- `UnitOfMeasure`
- `QualityCode`
- `TimestampRange`

Scope completed:
- Replaced primitive numeric measurement fields with `BigDecimal` for the core pressure, temperature, flow-rate, volume, and density measurements.
- Added `UnitOfMeasure` validation and normalization.
- Added non-negative validation for pressure, flow-rate, volume, and density.
- Kept temperature able to represent below-zero values.
- Added timestamp range ordering validation.
- Added focused industrial measurement value tests.

Limitations:
- `KilometerPost` and `GeoCoordinate` still require follow-up hardening because connector safety checks blocked those patches.

#### R1-003 — Domain Event and Metadata Contract Completion
Added:
- `src/test/java/dz/sh/trc/nghyflo/shared/DomainEventContractTest.java`

Updated event contracts:
- `DomainEvent`
- `EventMetadata`
- `EventEnvelope`
- `EventType`
- `EventClassification`
- `OperationalDomainEvent`
- `WorkflowDomainEvent`
- `TelemetryDomainEvent`
- `SecurityDomainEvent`
- `IntegrationDomainEvent`

Scope completed:
- Added aggregate type, aggregate ID, event name, and schema version to the domain event contract.
- Added recorded timestamp to event metadata.
- Hardened event envelope validation.
- Added default event family classification through domain event marker interfaces.
- Added event contract tests covering envelope wrapping, metadata validation, and missing payload rejection.

#### R1-004 — Shared-Kernel Test Coverage Completion
Added:
- `src/test/java/dz/sh/trc/nghyflo/shared/EventMetadataValidationTest.java`

Updated:
- `src/test/java/dz/sh/trc/nghyflo/shared/SharedKernelIdentifierTest.java`
- `src/test/java/dz/sh/trc/nghyflo/shared/DomainEventContractTest.java`

Scope completed:
- Expanded identifier factory coverage across all typed identifiers hardened in R1-001.
- Added canonical constructor compatibility checks.
- Added invalid UUID and blank identifier checks across several identifier types.
- Added event metadata validation tests for missing actor, tenant, correlation, causation, source module, schema version, and recorded timestamp.
- Expanded event envelope validation checks for missing event ID, event name, invalid schema version, and missing payload.

## Current Technical Debt
- Some generated Java files still lack mandatory NGHyFlo headers.
- Some source files still use compressed formatting.
- `KilometerPost` and `GeoCoordinate` still use primitive numeric values and must be hardened in a follow-up cleanup.
- Platform outbox and audit classes exist but require lifecycle/status hardening.
- Identity password hashing is currently weak and must be replaced with `PasswordEncoder`.
- Identity API still exposes application command types directly in request bodies.
- Identity persistence is incomplete and still includes in-memory infrastructure.
- Organization model is partial.
- Workflow, topology, measurement, telemetry, operational-state, anomaly/incident, planning, historian, reporting, integration, notification, advisory, and edge modules remain pending or skeleton-only.

## Validation
- Compile: Not executed locally in connector environment.
- Tests: Not executed locally in connector environment.
- Verify: Not executed locally in connector environment.
- CI: Added and expected to execute on push or pull request.

## Next Recommended Commit
R2-001 — Harden outbox event model and status lifecycle.

## Corrected Immediate Sequence
1. R2-001 — Harden outbox event model and status lifecycle.
2. R2-002 — Harden audit chain and audit metadata model.
3. R2-003 — Harden actor resolver and no-spoofing boundary.
4. R2-004 — Complete RBAC, ABAC, and segregation-of-duty evaluators.
5. R2-005 — Complete global exception and correlation handling.
6. R2-006 — Expand audit and outbox migration for industrial metadata.
7. R3-001 — Replace weak password hashing with `PasswordEncoder` boundary.
