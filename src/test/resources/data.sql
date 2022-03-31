INSERT INTO CENTER(id,name) VALUES (1, 'Barcelona');
INSERT INTO CENTER(id,name) VALUES (2, 'Madrid');


INSERT INTO CENTER_TRANSCODE(id,name,center_id) VALUES (1, 'BCN', 1);

INSERT INTO T_PERSON(saga, username, email, name, lastname, center, grade, businesscode, pucode, startdate, jobrole) values ('TEMPSAGA', 'TEMPUSERNAME', 'temp@temp', 'TEMPNAME', 'TEMPLASTNAME', 'BCN', 'D', 'TEMPBUSINESSCODE', 'TEMPPUCODE', '20030712', 'TEMPJOBROLE');

INSERT INTO PERSON(id, saga, username, department, name, lastname, active, customer, grade, role, businesscode, center_id, email, details, hours) values (1, 'TEMPSAGA', 'TEMPUSERNAME', 'TEMPDEPARTMENT', 'TEMPNAME', 'TEMPLASTNAME', 1, 'TEMPCUSTOMER', 'C', 'TEMPROLE', 'TEMPBUSINESSCODE', 1, 'temp@temp', 'TEMPDETAILS', 5);

