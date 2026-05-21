create schema if not exists nghyflo;
create table if not exists nghyflo.ng_operational_audit_event(id varchar(64) primary key, payload text not null, created_at timestamp not null default now());
create table if not exists nghyflo.ng_security_audit_event(id varchar(64) primary key, payload text not null, created_at timestamp not null default now());
create table if not exists nghyflo.ng_outbox_event(id varchar(64) primary key, aggregate_type varchar(128) not null, aggregate_id varchar(64) not null, event_type varchar(128) not null, payload text not null, status varchar(32) not null, retry_count int not null default 0, occurred_at timestamp not null, next_retry_at timestamp, correlation_id varchar(64));
create table if not exists nghyflo.ng_outbox_dead_letter(id varchar(64) primary key, outbox_event_id varchar(64) not null, reason text not null, created_at timestamp not null default now());
