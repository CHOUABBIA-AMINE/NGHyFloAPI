# Architecture Violations Analysis (Phase 2)

## V1 — Bounded Context Type Ownership Ambiguity (`StructureId`)
- **Severity:** High
- **Impact:** ambiguous ubiquitous language, mapping friction, and potential accidental coupling between shared and organization contexts.
- **Refactor strategy (audit-level):** choose single ownership boundary and enforce conversion at context edges only.

## V2 — Dual Persistence Execution Model in Organization Flow
- **Severity:** High
- **Impact:** transactional inconsistency risk between in-memory and JPA-backed behavior; harder observability and correctness guarantees.
- **Refactor strategy (audit-level):** converge on one production path, isolate alternate path to explicit profile/tests.

## V3 — Cross-Cutting Concern Duplication (Correlation Filter)
- **Severity:** Medium
- **Impact:** duplicated class names and unclear platform/shared responsibility.
- **Refactor strategy (audit-level):** consolidate correlation filter ownership under platform observability.

## V4 — Validation Strategy Inconsistency
- **Severity:** Medium
- **Impact:** uneven request contract enforcement and exception-to-HTTP mapping behavior.
- **Refactor strategy (audit-level):** standardize on Bean Validation + `@Valid` + handler mapping conventions.

## V5 — Architecture Test Scope Drift
- **Severity:** Low/Medium
- **Impact:** some rules validate hypothetical modules, reducing signal-to-noise for current architecture conformance.
- **Refactor strategy (audit-level):** separate active enforcement rules from roadmap/future module templates.

## V6 — Migration Versioning Collision (`V003` duplicated)
- **Severity:** High
- **Impact:** non-deterministic or failing migration ordering depending on Flyway configuration and naming assumptions.
- **Refactor strategy (audit-level):** normalize migration numbering policy to strict monotonic uniqueness.
