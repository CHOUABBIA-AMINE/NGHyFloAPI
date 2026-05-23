# Audit Standardization

## Audit Model
- Immutable append-only audit records with hash-chain integrity.
- Uniform audit metadata fields:
  - actor
  - action
  - resource
  - tenant
  - correlation/causation ids
  - timestamp
  - decision/effect

## Rules
1. All sensitive commands emit audit events.
2. Security policy decisions produce auditable decision records.
3. Workflow approvals/rejections always audited with SoD context.
4. Export and retention policies centrally managed.

## Normalization Actions
- Standardize audit event schema across contexts.
- Align audit writer usage behind platform audit port.
