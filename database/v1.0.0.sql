-- team_management.absence_local definition

CREATE TABLE `absence_local` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `saga` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `year` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `year` (`year`,`saga`),
  KEY `year_2` (`year`,`month`,`saga`),
  KEY `year_3` (`year`,`month`),
  KEY `I_absence_local_1` (`saga`)
) ENGINE=InnoDB AUTO_INCREMENT=301 DEFAULT CHARSET=utf8;


-- team_management.absence_pon definition

CREATE TABLE `absence_pon` (
  `saga` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `year` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `date` date NOT NULL,
  `status` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  KEY `year` (`year`),
  KEY `I_absence_pon_1` (`saga`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- team_management.center definition

CREATE TABLE `center` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


-- team_management.dates definition

CREATE TABLE `dates` (
  `date` date DEFAULT NULL,
  UNIQUE KEY `I_DATES` (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- team_management.`group` definition

CREATE TABLE `group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `external_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1194 DEFAULT CHARSET=utf8;


-- team_management.properties definition

CREATE TABLE `properties` (
  `code` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `value` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- team_management.t_absence definition

CREATE TABLE `t_absence` (
  `year` int(11) NOT NULL,
  `saga` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `from_date` date NOT NULL,
  `to_date` date NOT NULL,
  `days` int(11) NOT NULL,
  `status` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  KEY `I_T_ABSENCE` (`saga`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- team_management.t_admins definition

CREATE TABLE `t_admins` (
  `group_cn` varchar(100) DEFAULT NULL,
  `user_cn` varchar(100) DEFAULT NULL,
  KEY `i_t_admins_1` (`group_cn`),
  KEY `i_t_admins_2` (`user_cn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- team_management.t_group definition

CREATE TABLE `t_group` (
  `cn` varchar(100) DEFAULT NULL,
  `group_type` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `expire_date` varchar(10) DEFAULT NULL,
  `operation_type` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- team_management.t_members definition

CREATE TABLE `t_members` (
  `group_cn` varchar(100) DEFAULT NULL,
  `user_cn` varchar(100) DEFAULT NULL,
  KEY `i_t_members_1` (`group_cn`),
  KEY `i_t_members_2` (`user_cn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- team_management.t_operations definition

CREATE TABLE `t_operations` (
  `value_1` varchar(100) DEFAULT NULL,
  `value_2` varchar(100) DEFAULT NULL,
  `value_3` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- team_management.t_person definition

CREATE TABLE `t_person` (
  `saga` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `center` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `grade` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `businesscode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pucode` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `startdate` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `jobrole` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  KEY `I_T_PERSON` (`saga`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- team_management.t_subgroups definition

CREATE TABLE `t_subgroups` (
  `group_cn` varchar(100) DEFAULT NULL,
  `group_child_cn` varchar(100) DEFAULT NULL,
  KEY `i_t_subgroups_1` (`group_cn`),
  KEY `i_t_subgroups_2` (`group_child_cn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- team_management.`user` definition

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `I_USER_1` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


-- team_management.center_transcode definition

CREATE TABLE `center_transcode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `center_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `center_id` (`center_id`),
  CONSTRAINT `center_transcode_ibfk_1` FOREIGN KEY (`center_id`) REFERENCES `center` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;


-- team_management.festive definition

CREATE TABLE `festive` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `center_id` bigint(20) NOT NULL,
  `year` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `I_FESTIVE_1` (`center_id`,`year`),
  KEY `I_FESTIVE_2` (`year`),
  KEY `I_FESTIVE_3` (`center_id`),
  CONSTRAINT `festive_ibfk_1` FOREIGN KEY (`center_id`) REFERENCES `center` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=444 DEFAULT CHARSET=utf8;


-- team_management.group_manager definition

CREATE TABLE `group_manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `grupos_gestores_FK` (`group_id`),
  KEY `grupos_gestores_FK_1` (`person_id`),
  CONSTRAINT `grupos_gestores_FK` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=98594 DEFAULT CHARSET=utf8 COMMENT='Relacion ID de un grupo con 1 o mas gestores';


-- team_management.group_members definition

CREATE TABLE `group_members` (
  `group_id` bigint(20) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `grupos_usuarios_FK` (`group_id`),
  KEY `grupos_usuarios_FK_1` (`person_id`),
  CONSTRAINT `grupos_usuarios_FK` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=786749 DEFAULT CHARSET=utf8 COMMENT='Relacion ID grupo con 0 o varias personas (username)';


-- team_management.group_subgroup definition

CREATE TABLE `group_subgroup` (
  `group_id` bigint(20) NOT NULL,
  `subgroup_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `grupos_subgrupos_FK` (`group_id`),
  KEY `grupos_subgrupos_FK_1` (`subgroup_id`),
  CONSTRAINT `grupos_subgrupos_FK` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`),
  CONSTRAINT `grupos_subgrupos_FK_1` FOREIGN KEY (`subgroup_id`) REFERENCES `group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6224 DEFAULT CHARSET=utf8 COMMENT='Relacion grupos y subgrupos 1a 0-n';


-- team_management.person definition

CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `saga` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `center_id` bigint(20) DEFAULT NULL,
  `businesscode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `active` int(1) NOT NULL,
  `with_absence` int(1) DEFAULT NULL,
  `with_pon` int(1) DEFAULT NULL,
  `grade` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `center_id` (`center_id`),
  KEY `I_PERSON` (`saga`),
  KEY `I_PERSON_2` (`username`),
  CONSTRAINT `person_ibfk_1` FOREIGN KEY (`center_id`) REFERENCES `center` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18898 DEFAULT CHARSET=utf8;




INSERT INTO team_management.center (id, name) VALUES(1, 'Barcelona');
INSERT INTO team_management.center (id, name) VALUES(2, 'Madrid');
INSERT INTO team_management.center (id, name) VALUES(3, 'Asturias');
INSERT INTO team_management.center (id, name) VALUES(4, 'Murcia');
INSERT INTO team_management.center (id, name) VALUES(5, 'Sevilla');
INSERT INTO team_management.center (id, name) VALUES(6, 'Valencia');
INSERT INTO team_management.center (id, name) VALUES(7, 'Vigo');
INSERT INTO team_management.center (id, name) VALUES(8, 'India - Bangalore');
INSERT INTO team_management.center (id, name) VALUES(9, 'India - Mumbai');



INSERT INTO team_management.center_transcode (id, name, center_id) VALUES(1, 'MA2', 2);
INSERT INTO team_management.center_transcode (id, name, center_id) VALUES(2, 'BCN', 1);
INSERT INTO team_management.center_transcode (id, name, center_id) VALUES(3, 'VLC', 6);
INSERT INTO team_management.center_transcode (id, name, center_id) VALUES(4, 'AST', 3);
INSERT INTO team_management.center_transcode (id, name, center_id) VALUES(5, 'MJV', 4);
INSERT INTO team_management.center_transcode (id, name, center_id) VALUES(6, 'VL2', 6);
INSERT INTO team_management.center_transcode (id, name, center_id) VALUES(7, 'GTF', 2);
INSERT INTO team_management.center_transcode (id, name, center_id) VALUES(8, 'SVQ', 5);
INSERT INTO team_management.center_transcode (id, name, center_id) VALUES(9, 'VL3', 6);
INSERT INTO team_management.center_transcode (id, name, center_id) VALUES(10, 'Barcelona', 1);
INSERT INTO team_management.center_transcode (id, name, center_id) VALUES(11, 'VGO', 7);
INSERT INTO team_management.center_transcode (id, name, center_id) VALUES(12, 'SV2', 5);


INSERT INTO team_management.properties (code, value) VALUES('LDAP_PON', '2021-05-05 11:36:58');


insert into dates 
select date_add("2021-01-01", interval t.rowid DAY)
from (
	select @rowid:=@rowid+1 as rowid 
	from center a, center b, center c, center d, (SELECT @rowid:=-1) as init
) t
where t.rowid < 366 * 4;





