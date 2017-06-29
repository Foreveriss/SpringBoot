DROP DATBASE IF EXISTS mldn ;
CREATE DATBASE mldn CHARACTER SET UTF8 ;
USE mldn ;
CREATE TABLE dept (
	deptno		BIGINT		AUTO_INCREMENT ,
	dname		VARCHAR(50) ,
	CONSTRAINT pk_deptno PRIMARY KEY(deptno)
) ;
INSERT INTO dept(dname) VALUES ('开发部') ;
INSERT INTO dept(dname) VALUES ('财务部') ;
INSERT INTO dept(dname) VALUES ('市场部') ;
INSERT INTO dept(dname) VALUES ('后勤部') ;
INSERT INTO dept(dname) VALUES ('公关部') ;