# Coding Standards Analysis (Discovery)

## Style & Tooling
- Java 21 baseline (`maven.compiler.release=21`).
- Checkstyle enforced during `validate` phase (`maven-checkstyle-plugin`).
- Maven Enforcer requires Java >=21 and Maven >=3.9.

## Documentation/Header Conventions
- Many classes use a structured banner header including:
  - project/product/author/owner
  - name, created/updated dates
  - type/layer/package/description
- Header convention appears highly consistent in recently updated files.

## DTO Conventions
- Request DTOs grouped under `api.rest.request`.
- Response DTOs grouped under `application.dto`.
- Naming style typically suffixes: `Request`, `Response`, or `...Response`.

## Mapper Conventions
- Mapper classes explicitly suffixed `Mapper`.
- Mapper placement varies by concern:
  - API mappers in `api.rest.mapper`
  - Persistence mappers in `infrastructure.persistence.jpa`
  - Application mappers in `application.mapper`
- Mapping approach is mixed: manual mapper classes and MapStruct dependency present.

## Validation Conventions
- Bean Validation dependency exists (`spring-boot-starter-validation`).
- Request DTO validation annotations are expected in API request records/classes; full annotation coverage should be verified in deeper phase.

## Logging & Telemetry Strategy
- Observability package split into correlation/logging/metrics.
- Correlation context + request filters indicate request tracing strategy.
- Micrometer Prometheus registry dependency indicates metrics export target.

## Exception Handling Strategy
- Centralized `@RestControllerAdvice` global handler in platform layer.
- Problem-details style mapper used to convert exceptions into standard API error envelopes.
- Specialized platform exception handlers exist for telemetry/integration/security scenarios.

## Testing & Quality Strategy
- Unit and architecture tests span shared/platform/modules/bootstrap.
- CI and quality gates run compile + test + verify consistently.
