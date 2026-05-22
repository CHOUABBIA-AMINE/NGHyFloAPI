create table if not exists nghyflo.ng_user(
  id varchar(64) primary code,
  username varchar(128) not null unique,
  password_hash varchar(255) not null,
  created_at timestamp not null
);
create table if not exists nghyflo.ng_role(id varchar(64) primary code, role_code varchar(128) not null unique);
create table if not exists nghyflo.ng_user_role(user_id varchar(64) not null, role_id varchar(64) not null, primary code(user_id, role_id));
create table if not exists nghyflo.ng_login_attempt(id varchar(64) primary code, username varchar(128) not null, success boolean not null, attempted_at timestamp not null);
