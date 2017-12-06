insert into users(username,password,enabled) values ('test','qwerty', 'true');
insert into users(username,password,enabled) values ('neo','123', 'true');
insert into user_roles (user_role_id, username, role) values (1, 'test', 'ROLE_USER');
insert into user_roles (user_role_id, username, role) values (2, 'neo', 'ROLE_USER');