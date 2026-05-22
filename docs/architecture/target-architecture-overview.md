# NGHyFlo Target Architecture Overview (Phase 3)

## Architectural North Star
NGHyFlo target state is a **DDD + Hexagonal + Modular Monolith** designed for Sonatrach-scale operations, with explicit seams for progressive microservice extraction.

### Core principles
- Strict bounded contexts with explicit ownership.
- Inward dependency rule (domain/application independent from frameworks).
- CQRS-ready application layer with command/query separation by package and contracts.
- Event-driven integration via transactional outbox.
- Shared-kernel discipline (minimal, stable, ubiquitous only).
- Operational-grade observability, security, and audit-by-default.

## Macro Architecture
```mermaid
flowchart LR
  subgraph Edge[Industrial Edge / External]
    SCADA[SCADA/OPC-UA]
    MQTT[MQTT Brokers]
    ERP[ERP / CMMS]
    GIS[GIS/Topology Feeds]
    IAM[Enterprise IdP]
  end

  subgraph NGH[NGHyFlo Modular Monolith Runtime]
    API[API Gateway Layer]
    BC1[Topology BC]
    BC2[Telemetry BC]
    BC3[Planning BC]
    BC4[Monitoring BC]
    BC5[Incidents BC]
    BC6[Workflow BC]
    BC7[Analytics BC]
    BC8[Integration BC]
    BC9[Audit BC]
    BC10[IdentityAccess BC]
    BC11[Organization BC]
    BC12[Security BC]
    SK[Shared Kernel]
    Outbox[(Transactional Outbox)]
  end

  subgraph Data[Data Plane]
    PG[(PostgreSQL OLTP)]
    TSDB[(Timeseries DB)]
    OBJ[(Object Storage)]
    BUS[(Kafka Event Bus)]
  end

  SCADA --> API
  MQTT --> API
  ERP --> API
  GIS --> API
  IAM --> API

  API --> BC1
  API --> BC2
  API --> BC3
  API --> BC4
  API --> BC5
  API --> BC6
  API --> BC7
  API --> BC8

  BC1 --> PG
  BC2 --> PG
  BC2 --> TSDB
  BC3 --> PG
  BC4 --> PG
  BC5 --> PG
  BC6 --> PG
  BC7 --> PG
  BC9 --> OBJ

  BC1 --> Outbox
  BC2 --> Outbox
  BC3 --> Outbox
  BC4 --> Outbox
  BC5 --> Outbox
  BC6 --> Outbox
  BC7 --> Outbox
  Outbox --> BUS
```

## Target outcomes
- High ingestion throughput and deterministic workflow processing.
- Consistent enforcement of RBAC/ABAC + segregation of duties.
- Full traceability for operational and regulatory audit.
- Predictable extraction path for high-load contexts (Telemetry, Analytics, Workflow, Integration).
