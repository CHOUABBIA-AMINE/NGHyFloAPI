# Current Repository Tree

## Root Inventory
- `.github/workflows/` (CI pipelines)
- `docs/adr/` (architectural decision records)
- `docs/api/` (OpenAPI placeholder)
- `docs/architecture/` (architecture/reference docs)
- `src/main/java/dz/sh/trc/nghyflo/` (application code)
- `src/main/resources/` (configuration + Flyway migrations)
- `src/test/java/dz/sh/trc/nghyflo/` (unit + architecture tests)
- Build/runtime: `pom.xml`, `Makefile`, `docker-compose*.yml`, `checkstyle.xml`

## Source Tree (Condensed)

```text
src/main/java/dz/sh/trc/nghyflo
├── bootstrap
│   ├── api
│   ├── config
│   └── health
├── modules
│   ├── identityaccess
│   │   ├── api/rest/request
│   │   ├── application/{auth,command,dto,security,service}
│   │   ├── domain/{model,repository,value}
│   │   └── infrastructure/{configuration,persistence,persistence/jpa,security}
│   └── organization
│       ├── api/rest/{mapper,request}
│       ├── application/{command,dto,mapper,port,service}
│       ├── domain/{model,value}
│       └── infrastructure/{configuration,persistence/jpa}
├── platform
│   ├── audit
│   ├── configuration
│   ├── events/outbox
│   ├── exception
│   ├── observability/{correlation,logging,metrics}
│   ├── security/{authentication,authorization}
│   └── tenancy
└── shared
    ├── api
    ├── application
    ├── domain/{event,exception,model,value}
    └── infrastructure
```

## Resource Tree

```text
src/main/resources
├── application.yml
├── application-{dev,test,staging,production}.yml
└── db/migration
    ├── V001__nghyflo_platform_audit_outbox.sql
    ├── V002__nghyflo_identity_access.sql
    ├── V003__nghyflo_organization.sql
    ├── V003__nghyflo_platform_audit_outbox_hardening.sql
    ├── V004__nghyflo_identity_access_schema_completion.sql
    ├── V005__nghyflo_organization_staffing.sql
    └── V006__nghyflo_organization_model_completion.sql
```

## Test Tree (Condensed)
- `architecture/` (ArchUnit guardrails)
- `bootstrap/` readiness/controller tests
- `modules/identityaccess/**` module-specific tests
- `modules/organization/**` module-specific tests
- `platform/**` audit/security/exception/correlation/outbox tests
- `shared/**` shared kernel/domain contract tests


## R01-C01 Baseline Inventory Artifacts
- `docs/roadmap/inventory/baseline-file-manifest.md`
- `docs/roadmap/inventory/duplicate-candidate-manifest.md`
