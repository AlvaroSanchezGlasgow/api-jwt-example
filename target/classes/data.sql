INSERT INTO T_USERS
(id,userName, password, firstName, lastName, email, comment, isActive) VALUES
(1,'alvaro',  '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 'Alvaro', 'Sanchez', 'ags@gmail.com', 'London Office' ,'Y');
INSERT INTO T_USERS
(id,userName, password, firstName, lastName,  email, comment, isActive) VALUES
(2,'luis',  '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 'Luis', 'Sanchez', 'ags@gmail.com', 'Glasgow Tupe' ,'Y');
INSERT INTO T_USERS
(id,userName, password, firstName, lastName,  email, comment, isActive) VALUES
(3,'alex',  '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 'Alex', 'Sanchez', 'ags@gmail.com', 'Glasgow' ,'Y');

--CLIENTS
INSERT INTO T_CLIENTS
(id,name,website,client_manager,registration_date) VALUES
(1,'ANGLIAN WATER','https://www.anglianwater.co.uk','JOHN FORD',TO_DATE('2020/12/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
INSERT INTO T_CLIENTS
(id,name,website,client_manager,registration_date) VALUES
(2,'NORTHERN GAS NETWORK','https://www.northerngasnetworks.co.uk','LIONEL SMITH',TO_DATE('2020/12/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
INSERT INTO T_CLIENTS
(id,name,website,client_manager,registration_date) VALUES
(3,'BULB','https://bulb.co.uk','ALVARO SANCHEZ',TO_DATE('2020/12/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));



