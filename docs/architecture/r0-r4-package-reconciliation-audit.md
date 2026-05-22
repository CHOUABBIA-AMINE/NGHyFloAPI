# R0-R4 Package Reconciliation Audit

## Status
R4-005 stabilization executed on 2026-05-22.

## Stabilization Fixes Applied

### Bootstrap
- `NGHyFloApiApplication` now uses `@SpringBootApplication(scanBasePackages = "dz.sh.trc.nghyflo")`.
- Bootstrap entrypoint formatting was normalized.

### Correlation
- `CorrelationContext` now uses `ThreadLocal<CorrelationId>` instead of static shared mutable state.
- `CorrelationConfiguration` registers `RequestCorrelationFilter` as a Spring bean.

### Identity Runtime Wiring
- Removed runtime `InMemoryUserRepository` bean from `IdentityAccessConfiguration`.
- JPA repository adapter remains the runtime `UserRepository` provider through JPA persistence configuration.

### Standards Normalization
- `Username` normalized with NGHyFlo header, braces, and formatting.
- `CoverageAllocation` normalized with NGHyFlo header and explicit validation.

### Documentation
- `skeleton-manifest.md` reconciled through R4-005.
- `implementation-sequence.md` updated to block R5 until R0-R4 gaps are closed.

## Remaining R0-R4 Gaps

### R1 Shared Kernel
- `KilometerPost` and `GeoCoordinate` still require value-object hardening.

### R2 Platform
- `PasswordHash.java` still needs standards normalization; connector safety checks blocked patching.
- Correlation headers should be centralized in `CorrelationHeaders` if connector allows.
- Exception mapping should be revisited after compile/test feedback.

### R3 Identity
- Missing domain models:
  - `Permission.java`
  - `AccessPolicy.java`
  - `LoginAttempt.java`
- Identity auth infrastructure still missing:
  - user-session JPA adapter
  - renewal-artifact JPA adapter
  - session artifact issuer implementation
- Several identity files still need mandatory header normalization.

### R4 Organization
- `organization.domain.value` currently remains incomplete beyond assignment/shift identifiers.
- Missing organization domain models:
  - organization unit
  - position
  - responsibility profile
  - shift assignment
  - staffing requirement
- Organization persistence lacks:
  - shift JPA entity
  - shift Spring Data repository
  - shift adapter
  - normalized coverage storage
- Organization management APIs still missing for employee, unit, position, shift, and coverage management.

## Next Required Commits
1. R4-006 — Complete organization value objects and staffing responsibility primitives.
2. R4-007 — Complete organization domain model.
3. R4-008 — Complete organization persistence.
4. R4-009 — Complete organization management API.
5. R4-010 — Run/fix compile, test, verify, Checkstyle, and ArchUnit.
6. R3-FIX-001 — Complete identity domain gaps.
7. R3-FIX-002 — Complete identity auth infrastructure.
8. R2-FIX-001 — Harden platform correlation/exception layer.
9. R1-FIX-001 — Complete remaining shared-kernel value objects.

## Rule
Do not start R5 workflow until R4-010 passes and the R0-R4 gaps above are either completed or explicitly deferred in an architecture decision record.
