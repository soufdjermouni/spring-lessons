CREATE TABLE employee (
  employee_id BIGINT NOT NULL,
  company_id BIGINT NOT NULL,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  email VARCHAR(255),
  age INT,
  PRIMARY KEY (employee_id, company_id)
);
