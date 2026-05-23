# Workflow Architecture

## Orchestration Model
- Workflow context provides long-running process orchestration.
- State machine per workflow type (approval, incident escalation, maintenance permit).
- Compensation actions for failure paths.
- Human task + automated gate hybrid.

## Workflow Orchestration (Mermaid)
```mermaid
flowchart LR
  START[Workflow Start Event] --> DEF[Workflow Definition Resolver]
  DEF --> SM[State Machine Engine]
  SM --> TASK[Task Executor]
  TASK --> SEC[Security Policy Gate]
  SEC --> DEC{Approved?}
  DEC -- Yes --> NEXT[Advance State]
  DEC -- No --> REJ[Reject/Compensate]
  NEXT --> EVT[Workflow Event Published]
  REJ --> EVT
  EVT --> INC[Incidents]
  EVT --> PLAN[Planning]
  EVT --> AUD[Audit]
```

## Workflow Rules
- Workflow definitions versioned and immutable after activation.
- SoD checks mandatory for approval states.
- SLA timers integrated with scheduler and incident escalation.
