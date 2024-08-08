create database scoula_db;
create user 'scoula'@'%' identified by '1234';
grant all privileges on scoula_db.* to 'scoula'@'%';