# Phase 06 — Workflow Foundation

## R06-C01
- **Goal**: Establish workflow context package structure.
- **Detailed Description**: Introduce explicit workflow bounded-context layers and isolate workflow orchestration from controllers.
- **Files To Create**: workflow package docs and orchestration interfaces
- **Files To Update**: planning/incidents integration touchpoints
- **Files To Move**: workflow logic out of API/controller paths if present
- **Files To Merge**: duplicate workflow decision utilities
- **Files To Delete**: obsolete controller-embedded workflow helpers
- **Dependency Notes**: depends on Phase 03 layering rules
- **Validation Requirements**: compile + architecture tests
- **Architectural Impact**: High
- **Risk Level**: Medium
- **Rollback Complexity**: Medium
- **Exact Commit Message**: `refactor(workflow): establish bounded context layering and orchestration seams`

## R06-C02
- **Goal**: Normalize workflow events and state transitions.
- **Detailed Description**: Create canonical workflow event types and transition policy checks including SoD gates.
- **Files To Create**: workflow transition matrix tests
- **Files To Update**: workflow domain events, handlers, policy evaluators
- **Files To Move**: state transition classes to domain layer
- **Files To Merge**: duplicated transition logic
- **Files To Delete**: obsolete transition variants
- **Dependency Notes**: after R06-C01
- **Validation Requirements**: workflow unit/integration tests
- **Architectural Impact**: High
- **Risk Level**: Medium
- **Rollback Complexity**: Medium
- **Exact Commit Message**: `refactor(workflow): standardize events and state transition policies`

## R06-C03
- **Goal**: Normalize scheduler/timer integration for workflows.
- **Detailed Description**: Isolate SLA timers, retries, and compensation scheduling under workflow infrastructure.
- **Files To Create**: workflow scheduler tests
- **Files To Update**: scheduler configs/jobs
- **Files To Move**: mixed scheduler components into workflow infra
- **Files To Merge**: duplicate retry/timer utilities
- **Files To Delete**: obsolete scheduler duplicates
- **Dependency Notes**: after R06-C02
- **Validation Requirements**: integration tests for timer-driven transitions
- **Architectural Impact**: Medium
- **Risk Level**: Medium
- **Rollback Complexity**: Medium
- **Exact Commit Message**: `refactor(workflow): normalize sla timer and compensation scheduling`
