# NGHyFloAPI Dependency Version Report

## Scope
Phase 0.1 — Dependency Governance (executed on 2026-05-21).

## Verification Method
- Maven Central metadata pages (online verification).
- Spring official documentation for parent/BOM alignment.
- Local build validation via Maven compile/test/verify.

## Selected Versions
| Dependency | Version | Source | Verified Online | Reason | Scope |
|---|---:|---|---|---|---|
| Java | 21 (LTS) | Spring Boot 4 compatibility baseline | Yes | Stable LTS runtime for industrial backend baseline | Runtime |
| Spring Boot Parent | 4.0.6 | Maven Central metadata + Spring docs | Yes | Latest stable parent found and build-compatible | Build BOM |
| Maven | 3.9.10 (local toolchain) | Local environment | Yes (runtime observed) | Compatible with Spring Boot 4 build plugins | Build tool |
| MapStruct | 1.6.3 | Maven Central | Yes | DTO mapping support for future module slices | Compile + annotation processor |
| springdoc-openapi-starter-webmvc-ui | 2.8.14 | Maven Central metadata | Yes | OpenAPI documentation scaffolding | Runtime |
| ArchUnit JUnit5 | 1.4.1 | Maven Central | Yes | Enforce modular-monolith/DDD architecture rules | Test |
| Testcontainers (junit-jupiter, postgresql) | 1.21.3 | Maven Central metadata | Yes | Integration test infrastructure baseline | Test |

## Spring Boot Managed Dependencies (No Explicit Version)
- spring-boot-starter-web
- spring-boot-starter-validation
- spring-boot-starter-actuator
- spring-boot-starter-security
- spring-boot-starter-data-jpa
- spring-boot-starter-websocket
- spring-boot-starter-oauth2-resource-server
- spring-boot-starter-cache
- spring-boot-starter-test
- spring-security-test
- flyway-core
- postgresql
- micrometer-registry-prometheus
- lombok

Rationale: managed through Spring Boot parent BOM unless explicit version pin is required.

## Deferred / Compatibility Watchlist
- spring-kafka: deferred until outbox contract is completed and runtime broker not required.
- spring-data-redis: deferred until caching/session strategy is defined.
- spring-modulith: deferred pending Spring Boot 4 compatibility confirmation in upcoming slice.

## TODO
- Add automated dependency update policy (renovate/dependabot) in Phase 0.2.
- Add CVE/license scan baseline in quality gates slice.
