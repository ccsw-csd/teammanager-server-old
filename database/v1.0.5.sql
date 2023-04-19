-- team_management.festive_audit definition

CREATE TABLE `festive_audit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `center_id` bigint(20) NOT NULL,
  `year` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `log` varchar(4000) NOT NULL,
  `username` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `festive_audit_FK` (`center_id`),
  CONSTRAINT `festive_audit_FK` FOREIGN KEY (`center_id`) REFERENCES `center` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


