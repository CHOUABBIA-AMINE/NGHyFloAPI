# Logging Standardization

## Logging Principles
- Structured logs only (JSON format in production).
- Mandatory correlation and tenant fields.
- Distinct log channels: application, security, audit, integration, telemetry.

## Rules
1. No sensitive payload logging (tokens, secrets, credential material).
2. Stable event keys for searchability.
3. Error logs reference canonical error code and correlation ID.
4. Protocol adapters log ingress/egress summaries, not raw secrets.

## Telemetry Alignment
- Logs, metrics, and traces use shared correlation model.
- Log sampling policy by volume class (telemetry high-rate vs business low-rate).
