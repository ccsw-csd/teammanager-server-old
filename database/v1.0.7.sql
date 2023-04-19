ALTER TABLE team_management.`group` ADD public BIT DEFAULT 0 NULL;

create or replace view public_group as 
select concat(group_id, '-MEM-', vgma.person_id) as id, group_id, vgma.person_id, p.username from `group` g join v_group_members_all vgma on vgma.group_id = g.id join person p on vgma.person_id = p.id
where g.public = 1	
union
select concat(group_id, '-MAN-', gm.person_id) as id, group_id, gm.person_id, p.username from `group` g join group_manager gm on gm.group_id = g.id join person p on gm.person_id = p.id
where g.public = 1;


ALTER TABLE team_management.person ADD created_by_ldap BIT default 0 NULL;

update person set created_by_ldap = 1;

commit;



CREATE TABLE `release_note` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `text` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;	
	

CREATE TABLE `release_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_read` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `I_RELEASE_USER_1` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;




INSERT INTO team_management.release_note (version, `text`, `role`) 
VALUES('1.1.0', '<i>Fix</i>: Issue in the user edit service.', null);

INSERT INTO team_management.release_note (version, `text`, `role`) 
VALUES('1.1.0', '<i>Feature</i>: Added release notes popup.', null);

INSERT INTO team_management.release_note (version, `text`, `role`) 
VALUES('1.1.0', '<i>Feature</i>: Added group name in the Forecast detail.', 'GESTOR');

INSERT INTO team_management.release_note (version, `text`, `role`) 
VALUES('1.1.0', '<i>Feature</i>: A group can be made visible to all group members without them being managers.', 'GESTOR');

insert into release_user(username, last_read)
select username, 0 from `user` u;

