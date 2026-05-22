-- NGHyFloAPI
-- Product: NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
-- Migration: V006__nghyflo_organization_model_completion
-- Purpose: Complete organization model persistence for units, positions, shift coverage plans, staffing requirements, and normalized coverage references.

alter table nghyflo.ng_org_employee
    add column if not exists employee_number varchar(64);

create table if not exists nghyflo.ng_org_unit(
    id varchar(64) primary key,
    unit_code varchar(128) not null unique,
    unit_name varchar(256) not null,
    parent_id varchar(64),
    structure_id varchar(64),
    active boolean not null default true,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    constraint fk_ng_org_unit_parent foreign key(parent_id) references nghyflo.ng_org_unit(id)
);

create table if not exists nghyflo.ng_org_position(
    id varchar(64) primary key,
    position_code varchar(128) not null unique,
    title varchar(256) not null,
    organization_unit_id varchar(64) not null,
    active boolean not null default true,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    constraint fk_ng_org_position_unit foreign key(organization_unit_id) references nghyflo.ng_org_unit(id)
);

create table if not exists nghyflo.ng_org_position_capability(
    position_id varchar(64) not null,
    capability_code varchar(128) not null,
    created_at timestamp not null default now(),
    primary key(position_id, capability_code),
    constraint fk_ng_org_position_capability_position foreign key(position_id) references nghyflo.ng_org_position(id)
);

create table if not exists nghyflo.ng_org_shift_coverage_plan(
    id varchar(64) primary key,
    employee_id varchar(64) not null,
    position_id varchar(64) not null,
    shift_id varchar(64) not null,
    status varchar(64) not null,
    created_at timestamp not null,
    updated_at timestamp not null default now(),
    constraint fk_ng_org_shift_plan_employee foreign key(employee_id) references nghyflo.ng_org_employee(id),
    constraint fk_ng_org_shift_plan_position foreign key(position_id) references nghyflo.ng_org_position(id),
    constraint fk_ng_org_shift_plan_shift foreign key(shift_id) references nghyflo.ng_org_shift(id)
);

create table if not exists nghyflo.ng_org_staffing_requirement(
    id varchar(64) primary key,
    position_id varchar(64) not null,
    shift_id varchar(64) not null,
    required_count integer not null,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    constraint fk_ng_org_staffing_requirement_position foreign key(position_id) references nghyflo.ng_org_position(id),
    constraint fk_ng_org_staffing_requirement_shift foreign key(shift_id) references nghyflo.ng_org_shift(id),
    constraint ck_ng_org_staffing_requirement_count check(required_count > 0)
);

create table if not exists nghyflo.ng_org_coverage_ref(
    id varchar(64) primary key,
    owner_type varchar(64) not null,
    owner_id varchar(64) not null,
    reference_type varchar(64) not null,
    reference_id varchar(64) not null,
    created_at timestamp not null default now(),
    constraint uq_ng_org_coverage_ref unique(owner_type, owner_id, reference_type, reference_id)
);

create index if not exists idx_ng_org_employee_number
    on nghyflo.ng_org_employee(employee_number);
create index if not exists idx_ng_org_unit_parent
    on nghyflo.ng_org_unit(parent_id);
create index if not exists idx_ng_org_unit_structure
    on nghyflo.ng_org_unit(structure_id);
create index if not exists idx_ng_org_position_unit
    on nghyflo.ng_org_position(organization_unit_id);
create index if not exists idx_ng_org_shift_plan_employee_status
    on nghyflo.ng_org_shift_coverage_plan(employee_id, status);
create index if not exists idx_ng_org_shift_plan_shift
    on nghyflo.ng_org_shift_coverage_plan(shift_id);
create index if not exists idx_ng_org_staffing_requirement_position_shift
    on nghyflo.ng_org_staffing_requirement(position_id, shift_id);
create index if not exists idx_ng_org_coverage_ref_owner
    on nghyflo.ng_org_coverage_ref(owner_type, owner_id);
create index if not exists idx_ng_org_coverage_ref_reference
    on nghyflo.ng_org_coverage_ref(reference_type, reference_id);
