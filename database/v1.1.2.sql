ALTER TABLE team_management.release_user ADD last_connection varchar(50) NULL;


INSERT INTO team_management.release_note(version, text, `role`)
VALUES('1.1.2', '<i>Fix</i>: Issue when editing groups without being Manager.', 'GESTOR');

INSERT INTO team_management.release_note(version, text, `role`)
VALUES('1.1.2', '<i>Feature</i>: Change in the order of the forecast list. It is now sorted by first name + surname.', 'GESTOR');

INSERT INTO team_management.release_note(version, text, `role`)
VALUES('1.1.2', '<i>Feature</i>: Change in user permissions. Any person with grade D, E and F by default has the role of MANAGER.', 'GESTOR');

INSERT INTO team_management.release_note(version, text, `role`)
VALUES('1.1.2', '<i>Feature</i>: Added summary when exporting forecast by tabs.', 'GESTOR');


