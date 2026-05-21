# NGHyFloAPI Skeleton Manifest

## Repository Name
NGHyFloAPI

## Last Updated
2026-05-21

## Current Delivery Slice
R0-004 — Repository Documentation Reconciliation

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

## Current Technical Debt
- Some generated Java files still lack mandatory NGHyFlo headers.
- Some source files still use compressed formatting.
- Shared-kernel value objects are present but not industrial-grade yet.
- Some measurement value objects still use primitives and must be replaced with validated `BigDecimal` models.
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
R1-001 — Harden shared-kernel typed identifiers with validation and factories.

## Corrected Immediate Sequence
1. R1-001 — Harden typed identifiers.
2. R1-002 — Replace primitive industrial measurement values.
3. R1-003 — Complete domain event and metadata contracts.
4. R1-004 — Add shared-kernel value object and event metadata tests.
5. R2-001 — Harden outbox event model and status lifecycle.
6. R2-002 — Harden audit chain and audit metadata model.
7. R2-003 — Harden actor resolver and no-spoofing boundary.
8. R2-004 — Complete RBAC, ABAC, and segregation-of-duty evaluators.
9. R2-005 — Complete global exception and correlation handling.
10. R2-006 — Expand audit and outbox migration for industrial metadata.
11. R3-001 — Replace weak password hashing with `PasswordEncoder` boundary.
