# NGHyFloAPI Skeleton Manifest

## Repository Name
NGHyFloAPI

## Last Updated
2026-05-21

## Current Delivery Slice
R0-001 — Source Formatting and Header Normalization

## Java Version Selected
21 LTS

## Spring Boot Version Selected
4.0.6

## Dependency Verification Method
Maven Central metadata, Spring official documentation, and local Maven build validation recorded in `docs/architecture/dependency-version-report.md`.

## Completed Baseline
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

## R0-001 Files Updated
- `src/test/java/dz/sh/trc/nghyflo/bootstrap/BootstrapStatusControllerTest.java`
- `src/main/java/dz/sh/trc/nghyflo/platform/events/outbox/OutboxEvent.java`
- `src/main/java/dz/sh/trc/nghyflo/platform/audit/AuditChainHasher.java`
- `src/main/java/dz/sh/trc/nghyflo/platform/security/authorization/PolicyDecision.java`
- `src/main/java/dz/sh/trc/nghyflo/modules/identityaccess/domain/model/User.java`
- `src/main/java/dz/sh/trc/nghyflo/modules/identityaccess/application/service/IdentityApplicationService.java`
- `src/main/java/dz/sh/trc/nghyflo/modules/organization/domain/model/Employee.java`

## R0-001 Scope Completed
- Added missing NGHyFlo headers to selected generated Java files.
- Reformatted selected compressed Java source.
- Removed duplicate imports from bootstrap status test.
- Preserved behavior intentionally.

## R0-001 Limitations
- Full repository-wide Java tree enumeration was not available through the connector.
- Additional files still need header/format normalization in follow-up cleanup commits.
- `UserController.java` update was blocked by connector safety checks and remains to be normalized in a follow-up commit.

## Validation
- Compile: Not executed in connector environment.
- Tests: Not executed in connector environment.
- Verify: Not executed in connector environment.

## Known Risks
- Some generated Java files still lack mandatory headers.
- Some source files still use wildcard imports and compressed formatting.
- Identity password hashing remains intentionally unchanged in R0-001 and must be fixed in R3-001.
- Architecture tests are not yet active.

## Next Recommended Commit
R0-002 — Add GitHub Actions compile/test/verify pipeline.
