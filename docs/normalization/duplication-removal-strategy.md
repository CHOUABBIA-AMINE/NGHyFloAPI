# Duplication Removal Strategy

## Scope
Normalize duplicated concepts detected in Phase 2 across domain values, filters, handlers, and tests.

## Canonical Implementations
1. **Organization identity values**
   - Canonical: `modules.organization.domain.value.EmployeeId`, `StructureId`.
   - Non-canonical: shared-kernel duplicates with same semantic scope.
2. **Correlation filter**
   - Canonical: `platform.observability.correlation.RequestCorrelationFilter`.
   - Non-canonical: shared API duplicate filter.
3. **Platform tests**
   - Canonical locations are feature package tests (`platform.audit`, `platform.events.outbox`, `platform.security.authentication`).
   - Non-canonical duplicates in broader `platform` root test package.

## Merge Strategy
- **Step 1**: inventory references and runtime registrations.
- **Step 2**: switch all imports/usages to canonical type.
- **Step 3**: preserve wire contracts (JSON/event field names) via compatibility checks.
- **Step 4**: remove obsolete classes and duplicate tests.
- **Step 5**: run architecture tests + module tests + contract tests.

## Obsolete Package Removal Strategy
- Remove only after:
  - zero compile-time references,
  - zero Spring bean registrations,
  - zero external contract dependencies.
- Remove in smallest possible PR slices by concern.

## Complexity Bands
- Low: duplicate tests.
- Medium: value object duplication removal.
- Medium-High: filter/exception-chain dedup due to ordering and runtime behavior.

## Safety Controls
- Add temporary deprecation markers for one release cycle where needed.
- Enforce forbidden-import rules in architecture tests.
