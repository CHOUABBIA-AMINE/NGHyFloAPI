# Module Normalization Rules

## Mandatory Structure Per Context
- `api`
- `application`
- `domain`
- `infrastructure`

## Rules
1. No context may import another context's `infrastructure` package.
2. Cross-context interaction only through application ports/contracts or events.
3. Domain models are private to context except explicit shared-kernel primitives.
4. Shared-kernel limited to truly cross-context abstractions.
5. Platform modules (security/audit/observability/eventing) expose interfaces, not internals.

## Canonical Boundaries
- `security`, `audit`, and `eventing` become platform-runtime service contexts.
- `integration` owns protocol adapters (OPC-UA/MQTT/Kafka/SCADA).
- `telemetry` owns ingestion semantics, quality checks, and event publishing contracts.

## Governance
- Every module must have:
  - dependency rule tests,
  - package ownership tests,
  - documented inbound/outbound ports.
