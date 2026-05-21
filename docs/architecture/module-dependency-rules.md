# Module Dependency Rules

## Purpose
Define quality-gate dependency constraints for the NGHyFlo modular monolith.

## Rules
- Domain layer has no dependency on Spring, JPA, web, or infrastructure classes.
- Application layer depends on domain/shared only.
- Infrastructure depends on application/domain/shared.
- API depends on application contracts and DTOs.
- Cross-module interactions must go through application contracts and outbox events.

## TODO
- Add ArchUnit test classes for each rule in Phase 0.4/1.0.
