DROP DATABASE star_trek;
CREATE DATABASE star_trek;
Use star_trek
DROP TABLE IF EXISTS officers;
CREATE TABLE officers (
  id           INTEGER NOT NULL AUTO_INCREMENT,
  officer_rank VARCHAR(20) NOT NULL,
  first_name   VARCHAR(50) NOT NULL,
  last_name    VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO officers(officer_rank, first_name, last_name) VALUES('CAPTAIN', 'James', 'Kirk');
INSERT INTO officers(officer_rank, first_name, last_name) VALUES('CAPTAIN', 'Jean-Luc', 'Picard');
INSERT INTO officers(officer_rank, first_name, last_name) VALUES('CAPTAIN', 'Benjamin', 'Sisko');
INSERT INTO officers(officer_rank, first_name, last_name) VALUES('CAPTAIN', 'Kathryn', 'Janeway');
INSERT INTO officers(officer_rank, first_name, last_name) VALUES('CAPTAIN', 'Jonathan', 'Archer');
