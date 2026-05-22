-- NGHyFloAPI
-- Product: NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
-- Migration: V004__nghyflo_identity_access_schema_completion
-- Purpose: Complete identity-access persistence schema for roles, permissions, groups, sessions, tokens, service accounts, and access audit logs.

alter table nghyflo.ng_user
    add column if not exists employee_id varchar(64),
    add column if not exists tenant_id varchar(64),
    add column if not exists enabled boolean not null default true,
    add column if not exists locked boolean not null default false,
    add column if not exists last_login_at timestamp,
    add column if not exists updated_at timestamp not null default now();

alter table nghyflo.ng_role
    add column if not exists description varchar(512),
    add column if not exists system_role boolean not null default false,
    add column if not exists created_at timestamp not null default now(),
    add column if not exists updated_at timestamp not null default now();

create table if not exists nghyflo.ng_permission(
    id varchar(64) primary key,
    permission_code varchar(128) not null unique,
    description varchar(512),
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

create table if not exists nghyflo.ng_role_permission(
    role_id varchar(64) not null,
    permission_id varchar(64) not null,
    created_at timestamp not null default now(),
    primary key(role_id, permission_id),
    constraint fk_ng_role_permission_role foreign key(role_id) references nghyflo.ng_role(id),
    constraint fk_ng_role_permission_permission foreign key(permission_id) references nghyflo.ng_permission(id)
);

create table if not exists nghyflo.ng_group(
    id varchar(64) primary key,
    group_code varchar(128) not null unique,
    description varchar(512),
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

create table if not exists nghyflo.ng_group_user(
    group_id varchar(64) not null,
    user_id varchar(64) not null,
    created_at timestamp not null default now(),
    primary key(group_id, user_id),
    constraint fk_ng_group_user_group foreign key(group_id) references nghyflo.ng_group(id),
    constraint fk_ng_group_user_user foreign key(user_id) references nghyflo.ng_user(id)
);

create table if not exists nghyflo.ng_group_role(
    group_id varchar(64) not null,
    role_id varchar(64) not null,
    created_at timestamp not null default now(),
    primary key(group_id, role_id),
    constraint fk_ng_group_role_group foreign key(group_id) references nghyflo.ng_group(id),
    constraint fk_ng_group_role_role foreign key(role_id) references nghyflo.ng_role(id)
);

create table if not exists nghyflo.ng_user_session(
    id varchar(64) primary key,
    user_id varchar(64) not null,
    opened_at timestamp not null,
    closed_at timestamp,
    active boolean not null default true,
    source_address varchar(128),
    user_agent varchar(512),
    correlation_id varchar(64),
    constraint fk_ng_user_session_user foreign key(user_id) references nghyflo.ng_user(id)
);

create table if not exists nghyflo.ng_refresh_token(
    id varchar(64) primary key,
    session_id varchar(64) not null,
    token_hash varchar(512) not null,
    expires_at timestamp not null,
    revoked boolean not null default false,
    created_at timestamp not null default now(),
    revoked_at timestamp,
    constraint fk_ng_refresh_token_session foreign key(session_id) references nghyflo.ng_user_session(id)
);

create table if not exists nghyflo.ng_service_account(
    id varchar(64) primary key,
    name varchar(128) not null unique,
    tenant_id varchar(64),
    enabled boolean not null default true,
    created_at timestamp not null default now(),
    disabled_at timestamp
);

create table if not exists nghyflo.ng_service_account_role(
    service_account_id varchar(64) not null,
    role_id varchar(64) not null,
    created_at timestamp not null default now(),
    primary key(service_account_id, role_id),
    constraint fk_ng_service_account_role_account foreign key(service_account_id) references nghyflo.ng_service_account(id),
    constraint fk_ng_service_account_role_role foreign key(role_id) references nghyflo.ng_role(id)
);

create table if not exists nghyflo.ng_credential_policy(
    id varchar(64) primary key,
    policy_code varchar(128) not null unique,
    minimum_length int not null,
    require_rotation boolean not null default false,
    rotation_period_days int,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    constraint ck_ng_credential_policy_min_length check(minimum_length >= 8)
);

create table if not exists nghyflo.ng_access_log_entry(
    id varchar(64) primary key,
    username varchar(128) not null,
    accepted boolean not null,
    source_address varchar(128),
    occurred_at timestamp not null,
    correlation_id varchar(64),
    failure_reason varchar(256)
);

alter table nghyflo.ng_user_role
    add column if not exists created_at timestamp not null default now();

alter table nghyflo.ng_login_attempt
    add column if not exists source_address varchar(128),
    add column if not exists correlation_id varchar(64),
    add column if not exists failure_reason varchar(256);

create index if not exists idx_ng_user_username
    on nghyflo.ng_user(username);
create index if not exists idx_ng_user_employee
    on nghyflo.ng_user(employee_id);
create index if not exists idx_ng_user_tenant
    on nghyflo.ng_user(tenant_id);

create index if not exists idx_ng_role_code
    on nghyflo.ng_role(role_code);
create index if not exists idx_ng_permission_code
    on nghyflo.ng_permission(permission_code);
create index if not exists idx_ng_group_code
    on nghyflo.ng_group(group_code);

create index if not exists idx_ng_user_session_user_active
    on nghyflo.ng_user_session(user_id, active);
create index if not exists idx_ng_user_session_correlation
    on nghyflo.ng_user_session(correlation_id);

create index if not exists idx_ng_refresh_token_session
    on nghyflo.ng_refresh_token(session_id);
create index if not exists idx_ng_refresh_token_revoked
    on nghyflo.ng_refresh_token(revoked);

create index if not exists idx_ng_service_account_tenant
    on nghyflo.ng_service_account(tenant_id);
create index if not exists idx_ng_access_log_username_time
    on nghyflo.ng_access_log_entry(username, occurred_at);
create index if not exists idx_ng_access_log_correlation
    on nghyflo.ng_access_log_entry(correlation_id);
create index if not exists idx_ng_login_attempt_username_time
    on nghyflo.ng_login_attempt(username, attempted_at);
create index if not exists idx_ng_login_attempt_correlation
    on nghyflo.ng_login_attempt(correlation_id);
