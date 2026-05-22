# Module Inventory

## High-Level Component Counts (main Java)
- `shared`: 92 classes/interfaces
- `organization` module: 60 classes/interfaces
- `platform`: 43 classes/interfaces
- `identityaccess` module: 42 classes/interfaces
- `bootstrap`: 12 classes/interfaces

## Bootstrap
**Purpose:** application startup, profile/bootstrap identity, readiness probes, and bootstrap status endpoint.

## Shared Kernel
**Purpose:** cross-module abstractions and primitives.
- API envelopes and error contracts
- Application bus/handler contracts
- Domain abstractions (`AggregateRoot`, `ValueObject`, `DomainEvent`)
- Cross-cutting domain value objects and exceptions
- Infrastructure support (clock, serialization, repository helpers)

## Platform Module
**Purpose:** cross-cutting runtime services.
- Multi-tenancy context/filtering
- Security authentication/authorization evaluation
- Exception translation and API problem mapping
- Observability (correlation/logging/metrics)
- Audit chain + export/evidence abstractions
- Outbox dispatch/retry/cleanup infrastructure

## Identity Access Bounded Context
**Purpose:** users/roles/sessions/authentication.
- API: user controller + request DTOs
- Application: auth/identity services, command models, response DTOs
- Domain: user/role/session/service-account aggregates & value types
- Infrastructure: in-memory repo, JPA adapters/entities, security adapter, module config

## Organization Bounded Context
**Purpose:** organizational structure, staffing, coverage, scope evaluation.
- API: coverage/employee endpoints, REST->command mapper, request DTOs
- Application: commands, DTOs, mapping, ports, services
- Domain: structure/employee/position/coverage models and value types
- Infrastructure: JPA entities/repos/adapters + module config

## Cross-Cutting Workflows & Automation
- GitHub Actions: `ci.yml`, `quality-gates.yml` (compile, test, verify)
- Flyway SQL migrations define schema evolution for platform + module data stores
