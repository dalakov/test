insert into appUser(id, username,password,enabled) values (1,'test','qwerty', 'true');
insert into appUser(id, username,password,enabled) values (2, 'neo','123', 'true');

insert into approle (id, rolename) values (1, 'admin');
insert into approle (id, rolename) values (2, 'appUser');
insert into approle (id, rolename) values (3, 'poweruser');

insert into userrole (appuser_id, approle_id) values (1, 1);
insert into userrole (appuser_id, approle_id) values (2, 2);
insert into userrole (appuser_id, approle_id) values (1, 1);
insert into userrole (appuser_id, approle_id) values (1, 2);
