create table users (
  username VARCHAR(256),
  password VARCHAR(256),
  enabled boolean 
);

create table user_roles (
  user_role_id INTEGER,
  username VARCHAR(256),
  role VARCHAR(256)
);
