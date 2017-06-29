-- 删除数据库
DROP DATABASE IF EXISTS mldn ;
-- 创建数据库
CREATE DATABASE mldn CHARACTER SET UTF8 ;
-- 使用数据库
USE mldn ;
CREATE TABLE member(
	mid			VARCHAR(50) ,
	name		VARCHAR(50) ,
	password	VARCHAR(32) ,
	locked		INT ,
	CONSTRAINT pk_mid PRIMARY KEY(mid)
) ;
CREATE TABLE role (
	rid			VARCHAR(50)  ,
	title		VARCHAR(50) ,
	CONSTRAINT pk_rid PRIMARY KEY(rid)
) ;
CREATE TABLE action (
	actid		VARCHAR(50)	,
	title		VARCHAR(50) ,
	rid			VARCHAR(50) ,
	CONSTRAINT pk_actid PRIMARY KEY(actid) 
) ;
CREATE TABLE member_role (
	mid			VARCHAR(50) ,
	rid			VARCHAR(50) 
) ;
INSERT INTO member(mid,name,password,locked) VALUES ('mldnjava','mldn','2E866BF58289E01583AD418F486A69DF',0) ;
INSERT INTO member(mid,name,password,locked) VALUES ('admin','admin','2E866BF58289E01583AD418F486A69DF',0) ;
INSERT INTO role(rid,title) VALUES ('emp','雇员管理') ;
INSERT INTO role(rid,title) VALUES ('dept','部门管理') ;
INSERT INTO action(actid,title,rid) VALUES ('emp:add','雇员入职','emp') ;
INSERT INTO action(actid,title,rid) VALUES ('emp:remove','雇员离职','emp') ;
INSERT INTO action(actid,title,rid) VALUES ('emp:list','雇员列表','emp') ;
INSERT INTO action(actid,title,rid) VALUES ('emp:edit','雇员编辑','emp') ;
INSERT INTO action(actid,title,rid) VALUES ('dept:list','部门列表','dept') ;
INSERT INTO action(actid,title,rid) VALUES ('dept:edit','部门编辑','dept') ;
INSERT INTO member_role(mid,rid) VALUES ('mldnjava','emp') ;
INSERT INTO member_role(mid,rid) VALUES ('admin','emp') ;
INSERT INTO member_role(mid,rid) VALUES ('admin','dept') ;
