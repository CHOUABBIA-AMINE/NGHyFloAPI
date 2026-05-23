# Final Modular Architecture

## Vision
NGHyFlo evolves into a **DDD + Hexagonal + Modular Monolith** platform with strict bounded contexts and integration seams that are microservice-ready.

## Final Module Hierarchy

```text
nghyflo
├─ bootstrap
├─ shared-kernel
├─ platform-runtime
│  ├─ audit
│  ├─ security
│  ├─ eventing
│  ├─ observability
│  └─ tenancy
├─ modules
│  ├─ topology
│  ├─ telemetry
│  ├─ planning
│  ├─ monitoring
│  ├─ incidents
│  ├─ workflow
│  ├─ analytics
│  └─ integration
└─ edge-adapters
   ├─ scada-opcua
   ├─ scada-mqtt
   ├─ enterprise-kafka
   └─ historian-connectors
```

## Macro Architecture (Mermaid)
```mermaid
flowchart LR
  UI[REST / WebSocket / Operator UI] --> API[API Adapters]
  API --> APP[Application Services + CQRS Handlers]
  APP --> DOM[Domain Model by Bounded Context]
  APP --> PORTS[Outbound Ports]
  PORTS --> INFRA[(JPA/TSDB/Kafka/OPC-UA/MQTT Adapters)]
  DOM --> EVT[Domain Events]
  EVT --> BUS[(Internal Event Bus + Outbox)]
  BUS --> INT[Integration Context]
  BUS --> AUD[Audit Context]
  SEC[Security Context] --> API
  SEC --> APP
```

## Deployment Architecture (Mermaid)
```mermaid
flowchart TB
  subgraph Plant_Network
    PLC[PLC/RTU]
    SCADA[SCADA]
    OPC[OPC-UA Gateway]
    MQTTB[MQTT Broker]
  end
  subgraph Core_DC
    N1[NGHyFlo Node 1]
    N2[NGHyFlo Node 2]
    KAFKA[Kafka]
    PG[(PostgreSQL)]
    TSDB[(Timeseries DB)]
    OBJ[(Object Storage / Audit Evidence)]
  end
  PLC --> SCADA --> OPC --> N1
  PLC --> MQTTB --> N2
  N1 <--> KAFKA
  N2 <--> KAFKA
  N1 --> PG
  N2 --> PG
  N1 --> TSDB
  N2 --> TSDB
  N1 --> OBJ
```
