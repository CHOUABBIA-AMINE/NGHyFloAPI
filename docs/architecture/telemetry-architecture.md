# Telemetry Architecture (Target)

## Ingestion pipeline
1. Protocol ingestion (MQTT, Kafka, OPC-UA/SCADA connectors).
2. Envelope parsing + schema validation.
3. Deduplication/idempotency using message key + source timestamp window.
4. Quality checks (range, units, sensor state, continuity).
5. Enrichment (topology binding, operational context, correlation).
6. Write path:
   - hot path -> TSDB
   - contextual state -> OLTP projections
   - event emission -> outbox

## Telemetry ingestion architecture
```mermaid
flowchart LR
  subgraph Sources
    SCADA[SCADA OPC-UA]
    MQTT[MQTT Topics]
    KFK[Kafka Topics]
  end

  subgraph TelemetryBC[Telemetry BC]
    ADP[Protocol Adapters]
    VAL[Schema + Quality Validation]
    DED[Dedup/Idempotency]
    ENR[Context Enrichment]
    CMD[Ingestion Command Handler]
    OUT[Outbox Writer]
  end

  TSDB[(TSDB)]
  PG[(PostgreSQL)]
  BUS[(Kafka Event Bus)]
  MON[Monitoring BC]
  ANA[Analytics BC]

  SCADA --> ADP
  MQTT --> ADP
  KFK --> ADP
  ADP --> VAL --> DED --> ENR --> CMD
  CMD --> TSDB
  CMD --> PG
  CMD --> OUT --> BUS
  BUS --> MON
  BUS --> ANA
```

## MQTT / Kafka strategy
- MQTT for edge ingestion and low-latency sensor streams.
- Kafka for durable internal event backbone and backpressure smoothing.
- Bridge/adapters in Integration BC normalize protocol-specific details.

## TSDB strategy
- Partition by time + asset hierarchy.
- Retention tiers: hot operational window, warm historical, cold archive.
- Downsampling jobs feed Analytics BC aggregates.

## Monitoring flow tie-in
- telemetry.quality.failed -> Monitoring alert rule evaluation.
- telemetry.anomaly.detected -> Incident candidate creation workflow.
