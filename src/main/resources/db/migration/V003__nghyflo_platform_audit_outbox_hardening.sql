-- NGHyFloAPI
-- Product: NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
-- Migration: V003__nghyflo_platform_audit_outbox_hardening
-- Purpose: Add industrial audit/outbox metadata needed for immutable audit chains, replay, and reliable event delivery.

alter table nghyflo.ng_operational_audit_event
    add column if not exists actor_id varchar(64),
    add column if not exists actor_type varchar(64),
    add column if not exists tenant_id varchar(64),
    add column if not exists target_type varchar(128),
    add column if not exists target_id varchar(128),
    add column if not exists action_code varchar(128),
    add column if not exists correlation_id varchar(64),
    add column if not exists causation_id varchar(64),
    add column if not exists previous_hash varchar(128),
    add column if not exists event_hash varchar(128),
    add column if not exists schema_version int not null default 1,
    add column if not exists reason_code varchar(128),
    add column if not exists recorded_at timestamp not null default now();

alter table nghyflo.ng_security_audit_event
    add column if not exists actor_id varchar(64),
    add column if not exists actor_type varchar(64),
    add column if not exists tenant_id varchar(64),
    add column if not exists target_type varchar(128),
    add column if not exists target_id varchar(128),
    add column if not exists action_code varchar(128),
    add column if not exists correlation_id varchar(64),
    add column if not exists causation_id varchar(64),
    add column if not exists previous_hash varchar(128),
    add column if not exists event_hash varchar(128),
    add column if not exists schema_version int not null default 1,
    add column if not exists reason_code varchar(128),
    add column if not exists recorded_at timestamp not null default now();

alter table nghyflo.ng_outbox_event
    add column if not exists tenant_id varchar(64),
    add column if not exists causation_id varchar(64),
    add column if not exists source_module varchar(128),
    add column if not exists event_name varchar(192),
    add column if not exists event_classification varchar(64),
    add column if not exists schema_version int not null default 1,
    add column if not exists error_reason text,
    add column if not exists last_attempt_at timestamp,
    add column if not exists dispatched_at timestamp,
    add column if not exists created_at timestamp not null default now(),
    add column if not exists updated_at timestamp not null default now();

alter table nghyflo.ng_outbox_dead_letter
    add column if not exists aggregate_type varchar(128),
    add column if not exists aggregate_id varchar(128),
    add column if not exists event_type varchar(128),
    add column if not exists correlation_id varchar(64),
    add column if not exists causation_id varchar(64),
    add column if not exists source_module varchar(128),
    add column if not exists schema_version int not null default 1,
    add column if not exists payload text,
    add column if not exists retry_count int not null default 0;

create table if not exists nghyflo.ng_audit_chain_checkpoint(
    id varchar(64) primary code,
    target_type varchar(128) not null,
    target_id varchar(128) not null,
    latest_audit_id varchar(64) not null,
    latest_hash varchar(128) not null,
    updated_at timestamp not null default now(),
    unique(target_type, target_id)
);

create index if not exists idx_ng_operational_audit_actor
    on nghyflo.ng_operational_audit_event(actor_id);
create index if not exists idx_ng_operational_audit_target
    on nghyflo.ng_operational_audit_event(target_type, target_id);
create index if not exists idx_ng_operational_audit_correlation
    on nghyflo.ng_operational_audit_event(correlation_id);
create index if not exists idx_ng_operational_audit_hash
    on nghyflo.ng_operational_audit_event(event_hash);

create index if not exists idx_ng_security_audit_actor
    on nghyflo.ng_security_audit_event(actor_id);
create index if not exists idx_ng_security_audit_target
    on nghyflo.ng_security_audit_event(target_type, target_id);
create index if not exists idx_ng_security_audit_correlation
    on nghyflo.ng_security_audit_event(correlation_id);
create index if not exists idx_ng_security_audit_hash
    on nghyflo.ng_security_audit_event(event_hash);

create index if not exists idx_ng_outbox_status_retry
    on nghyflo.ng_outbox_event(status, next_retry_at);
create index if not exists idx_ng_outbox_aggregate
    on nghyflo.ng_outbox_event(aggregate_type, aggregate_id);
create index if not exists idx_ng_outbox_correlation
    on nghyflo.ng_outbox_event(correlation_id);
create index if not exists idx_ng_outbox_source_module
    on nghyflo.ng_outbox_event(source_module);

create index if not exists idx_ng_outbox_dead_letter_event
    on nghyflo.ng_outbox_dead_letter(outbox_event_id);
create index if not exists idx_ng_outbox_dead_letter_correlation
    on nghyflo.ng_outbox_dead_letter(correlation_id);
