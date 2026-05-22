# Infrastructure Layer Audit (Phase 2)

## Persistence and JPA
- IdentityAccess uses JPA entities/repositories + adapter (`JpaUserRepositoryAdapter`) and dedicated mapper.
- Organization uses multiple JPA entities and Spring Data repositories with lookup/allocation adapters.
- Flyway is present with module/platform migration tracks.

## Key Findings
- **Persistence leakage risk (Medium):** mappers perform state reconstitution logic (status transitions) that resembles domain behavior.
- **Infra-domain coupling (Medium):** adapter-level role materialization and csv parsing in mappers embeds business-adjacent semantics.
- **Duplicate persistence semantics (Medium):** in-memory repositories/services coexist with JPA implementations, causing divergent execution models.
- **Migration anomaly (High):** duplicate version prefix `V003__...` across two migration files may break deterministic ordering depending on Flyway settings.

## Security Infrastructure
- Password encoder adapter and security principal/authenticated actor abstractions are present.
- Resource server/security starter exists, but end-to-end auth integration coverage appears partial.

## Outbox/Eventing
- Outbox contracts (`OutboxWriter`, `OutboxDispatcher`, `OutboxCleanupJob`, repository, retry/dead-letter policy types) are defined.
- Current shape is interface-heavy; concrete dispatch/cleanup wiring completeness should be verified in runtime configuration.

## Configuration
- Dedicated module/platform/bootstrap configurations exist, aligning with modular structure.
- Potential overlap between shared/platform API concerns suggests configuration ownership boundaries need tightening.
