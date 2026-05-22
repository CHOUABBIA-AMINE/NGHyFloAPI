-- NGHyFloAPI
-- Product: NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
-- Migration: V005__nghyflo_organization_staffing
-- Purpose: Add organization persistence schema for employees, shifts, staffing coverage, and coverage allocations.

create table if not exists nghyflo.ng_org_employee(
    id varchar(64) primary key,
    full_name varchar(256) not null,
    structure_id varchar(64),
    status varchar(64) not null,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

create table if not exists nghyflo.ng_org_shift(
    id varchar(64) primary key,
    shift_code varchar(128) not null,
    starts_at timestamp not null,
    ends_at timestamp not null,
    created_at timestamp not null default now(),
    constraint ck_ng_org_shift_window check(ends_at > starts_at)
);

create table if not exists nghyflo.ng_org_coverage_allocation(
    id varchar(64) primary key,
    employee_id varchar(64) not null,
    shift_id varchar(64) not null,
    region_ids text not null default '',
    structure_ids text not null default '',
    pipeline_ids text not null default '',
    station_ids text not null default '',
    status varchar(64) not null,
    created_at timestamp not null,
    updated_at timestamp not null default now(),
    constraint fk_ng_org_coverage_employee foreign key(employee_id) references nghyflo.ng_org_employee(id),
    constraint fk_ng_org_coverage_shift foreign key(shift_id) references nghyflo.ng_org_shift(id)
);

create index if not exists idx_ng_org_employee_structure
    on nghyflo.ng_org_employee(structure_id);
create index if not exists idx_ng_org_employee_status
    on nghyflo.ng_org_employee(status);
create index if not exists idx_ng_org_shift_code
    on nghyflo.ng_org_shift(shift_code);
create index if not exists idx_ng_org_shift_window
    on nghyflo.ng_org_shift(starts_at, ends_at);
create index if not exists idx_ng_org_coverage_employee_status
    on nghyflo.ng_org_coverage_allocation(employee_id, status);
create index if not exists idx_ng_org_coverage_shift
    on nghyflo.ng_org_coverage_allocation(shift_id);
