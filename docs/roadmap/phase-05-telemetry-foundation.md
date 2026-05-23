# Phase 05 — Telemetry Foundation

## R05-C01
- **Goal**: Establish telemetry ingestion module boundaries.
- **Detailed Description**: Carve explicit ingress/application/domain/infrastructure packages and port contracts for OPC-UA/MQTT/Kafka/historian.
- **Files To Create**: telemetry module boundary docs + port interface stubs (no behavior change)
- **Files To Update**: module package structure declarations/config
- **Files To Move**: ingestion-related classes into telemetry/integration ownership
- **Files To Merge**: duplicated ingestion helpers
- **Files To Delete**: obsolete misplaced ingestion shells
- **Dependency Notes**: after shared-kernel normalization
- **Validation Requirements**: compile + architecture tests
- **Architectural Impact**: High
- **Risk Level**: Medium-High
- **Rollback Complexity**: High
- **Exact Commit Message**: `refactor(telemetry): normalize ingestion boundaries and adapter ports`

## R05-C02
- **Goal**: Normalize telemetry event contracts.
- **Detailed Description**: Standardize telemetry event metadata, idempotency keys, quality markers, and versioning.
- **Files To Create**: telemetry event contract matrix tests/docs
- **Files To Update**: event classes/outbox writers/consumers
- **Files To Move**: event definitions into telemetry ownership where needed
- **Files To Merge**: duplicate telemetry event payload classes
- **Files To Delete**: obsolete event variants
- **Dependency Notes**: after R05-C01
- **Validation Requirements**: event contract tests + outbox tests
- **Architectural Impact**: High
- **Risk Level**: Medium
- **Rollback Complexity**: Medium
- **Exact Commit Message**: `refactor(events): standardize telemetry event contracts and metadata`

## R05-C03
- **Goal**: Normalize telemetry persistence seams.
- **Detailed Description**: Separate transactional data vs TSDB writes and enforce append-only telemetry storage policy.
- **Files To Create**: persistence seam tests
- **Files To Update**: telemetry persistence adapters/config
- **Files To Move**: TSDB adapter classes into telemetry infra namespace
- **Files To Merge**: duplicate persistence utilities
- **Files To Delete**: obsolete mixed-responsibility persistence classes
- **Dependency Notes**: after R05-C02
- **Validation Requirements**: integration tests with profile-backed storage
- **Architectural Impact**: High
- **Risk Level**: Medium-High
- **Rollback Complexity**: High
- **Exact Commit Message**: `refactor(telemetry): normalize tsdb and transactional persistence boundaries`
