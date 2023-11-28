CREATE TABLE center (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO center(id, name) VALUES (1, 'Barcelona');
INSERT INTO center(id, name) VALUES (2, 'Madrid');

CREATE TABLE center_transcode (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  center_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT center_transcode_center_fk FOREIGN KEY (center_id) REFERENCES center(id)
);

INSERT INTO center_transcode(id, name, center_id) VALUES (1, 'BCN', 1);

CREATE TABLE province (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO province(id, name) VALUES (1, 'Barcelona');

CREATE TABLE t_person (
  saga varchar(25)  NOT NULL,
  username varchar(25)  NOT NULL,
  email varchar(100)  NOT NULL,
  name varchar(50)  NOT NULL,
  lastname varchar(100)  NOT NULL,
  center varchar(50)  DEFAULT NULL,
  grade varchar(5)  DEFAULT NULL,
  businesscode varchar(50)  DEFAULT NULL,
  pucode varchar(25)  DEFAULT NULL,
  startdate varchar(25)  DEFAULT NULL,
  jobrole varchar(50)  DEFAULT NULL,
  entity_ldap varchar(50) DEFAULT NULL,
  global_employee_id varchar(25) DEFAULT NULL,
  location varchar(50) DEFAULT NULL,
  KEY I_T_PERSON (saga)
);

INSERT INTO T_PERSON(saga, username, email, name, lastname, center, grade, businesscode, pucode, startdate, jobrole)
values ('TEMP_SAGA', 'TEMP_USERNAME', 'TEM_EMAIL', 'TEMP_NAME', 'TEMP_LASTNAME', 'BCN', 'D', 'TEMPBUSINESSCODE', 'TEMP_PUCODE', '20030712', 'TEMP_JOBROLE');

CREATE TABLE person (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  saga varchar(25)  DEFAULT NULL,
  username varchar(25)  DEFAULT NULL,
  department varchar(10) DEFAULT 'CCSw',
  name varchar(50)  NOT NULL,
  lastname varchar(100)  NOT NULL,
  active int(1) NOT NULL DEFAULT '1',
  customer varchar(100)  DEFAULT NULL,
  grade varchar(5) DEFAULT NULL,
  role varchar(50)  DEFAULT NULL,
  businesscode varchar(50)  DEFAULT NULL,
  center_id bigint(20) DEFAULT NULL,
  email varchar(100)  DEFAULT NULL,
  details varchar(200)  DEFAULT NULL,
  hours int(10) NOT NULL DEFAULT '8',
  province_id bigint(20) DEFAULT NULL,
  updated_at datetime DEFAULT NULL,
  updated_by varchar(25) DEFAULT NULL,
  manager varchar(200) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_USERNAME (username),
  CONSTRAINT person_center_fk FOREIGN KEY (center_id) REFERENCES center (id),
  CONSTRAINT person_province_FK FOREIGN KEY (province_id) REFERENCES province (id)
);

INSERT INTO PERSON(id, saga, username, department, name, lastname, active, grade, role, businesscode, center_id, email, details, hours, province_id, updated_at, updated_by, manager)
values (1, 'TEMP_SAGA', 'TEMP_USERNAME', 'TEMP_DEPARTMENT', 'TEMP_NAME', 'TEMP_LASTNAME', 1, 'C', 'TEMP_ROLE', 'TEMP_BUSINESSCODE', 1, 'temp@temp', 'TEMP_DETAILS', 5 , 1, null, null, 'TEMP_MANAGER');

