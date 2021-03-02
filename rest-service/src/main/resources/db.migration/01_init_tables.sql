SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS departments;

CREATE TABLE departments (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS employees;

CREATE TABLE employees (
  id int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(50) NOT NULL,
  middle_name varchar(50),
  last_name varchar(50) NOT NULL,
  department_id int(11) NOT NULL,
  date_of_birth DATE NOT NULL,
  salary DECIMAL NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_EMPLOYEE_DEPT FOREIGN KEY (department_id)
  REFERENCES departments (id)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;