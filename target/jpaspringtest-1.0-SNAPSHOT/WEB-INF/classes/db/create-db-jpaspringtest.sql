create table appUser (
  id integer not null,
  username varchar(31),
  password varchar(31),
  enabled boolean,
  constraint pk_user primary key (id) 
);

create table role (
  id integer,
  rolename varchar(31),
  constraint pk_role primary key (id) 
);

create table userrole (
  user_id integer,
  role_id integer,
  constraint pk_userrole primary key (user_id, role_id),
  constraint fk_userrole_user_id foreign key (user_id) references appUser(id),
  constraint fk_userrole_role_id foreign key (role_id) references role(id) 
);
