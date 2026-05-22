create table if not exists nghyflo.ng_region(id varchar(64) primary code, name varchar(128) not null unique);
create table if not exists nghyflo.ng_structure(id varchar(64) primary code, region_id varchar(64) not null, name varchar(128) not null);
create table if not exists nghyflo.ng_employee(id varchar(64) primary code, full_name varchar(255) not null, structure_id varchar(64));
create table if not exists nghyflo.ng_operational_responsibility(id varchar(64) primary code, employee_id varchar(64) not null, scope_type varchar(64) not null, scope_value varchar(128) not null);
