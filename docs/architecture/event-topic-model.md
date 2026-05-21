# Event Topic Model

- Prefix: `nghyflo`
- Outbox topic naming convention:
  - `nghyflo.identityaccess.events`
  - `nghyflo.workflow.events`
  - `nghyflo.measurement.events`
  - `nghyflo.telemetry.events`

All external dispatch must originate from persisted outbox records.
