CREATE TABLE Admin(
	id SERIAL PRIMARY KEY NOT NULL,
	nom VARCHAR(255),
	email VARCHAR(255),
	motDePasse VARCHAR(255)
);

INSERT INTO admin (nom,email,motDePasse) VALUES ('Jaheem Harris','jaheemharris.admin@gmail.com','jaheemisadmin');

CREATE TABLE auth_user(
	id SERIAL PRIMARY KEY NOT NULL,
	nom VARCHAR(255),
	prenom VARCHAR(255),
	email VARCHAR(255),
	motDePasse VARCHAR(255)
);

INSERT INTO auth_user (nom,prenom,email,motDePasse) VALUES ('Jean','Renois','jean.renois.user@gmail.com','$2a$12$gSF8xx86HweYoiDO43HTt.yQYvtOhM4.owKg0jfBkmNVRE3uAAdaS');
INSERT INTO auth_user(nom,prenom,email,motDePasse) VALUES ('Harris','Jaheem','jaheemharris.admin@outlook.fr','$2a$12$gSF8xx86HweYoiDO43HTt.yQYvtOhM4.owKg0jfBkmNVRE3uAAdaS');
INSERT INTO auth_user(nom,prenom,email,motDePasse) VALUES ('Rakotofringa','Hevitra Taingina','hevitrataingina.chief@gmail.com','$2a$12$gSF8xx86HweYoiDO43HTt.yQYvtOhM4.owKg0jfBkmNVRE3uAAdaS');
INSERT INTO auth_user(nom,prenom,email,motDePasse) VALUES ('Hubert','Netees','hubert.netees@gmail.com','$2a$12$gSF8xx86HweYoiDO43HTt.yQYvtOhM4.owKg0jfBkmNVRE3uAAdaS');

CREATE TABLE auth_role(
	id SERIAL PRIMARY KEY NOT NULL,
	nom VARCHAR(50)
);


INSERT INTO auth_role(nom) VALUES ('ROLE_ADMIN');
INSERT INTO auth_role(nom) VALUES ('ROLE_CHIEF');
INSERT INTO auth_role(nom) VALUES ('ROLE_USER');

CREATE TABLE User_role(
	idUser INT NOT NULL,
	idRole INT NOT NULL,
	FOREIGN KEY (idUser) REFERENCES auth_user(id),
	FOREIGN KEY (idRole) REFERENCES auth_role(id)
);

INSERT INTO user_role VALUES (1,1);
INSERT INTO user_role VALUES (1,2);
INSERT INTO user_role VALUES (1,3);
INSERT INTO user_role VALUES (2,2);
INSERT INTO user_role VALUES (2,3);
INSERT INTO user_role VALUES (3,3);

CREATE TABLE ConfirmationToken(
	id SERIAL PRIMARY KEY NOT NULL,
	token VARCHAR(255),
	dateExpiration TIMESTAMP
);

--relation : user:1,n     0,n:role

CREATE TABLE Region(
	id SERIAL PRIMARY KEY NOT NULL,
	nom VARCHAR(255),
	latitude decimal,
	longitude decimal
);

INSERT INTO Region(nom,latitude,longitude) VALUES('Diana' , -13.2043844,49.1111434);
INSERT INTO Region(nom,latitude,longitude) VALUES('Sava' , -14.2938722,49.7734024);
INSERT INTO Region(nom,latitude,longitude) VALUES('Itasy' , -19.0535322,46.9922011);
INSERT INTO Region(nom,latitude,longitude) VALUES('Analamanga' , -18.6000004,47.5131002);
INSERT INTO Region(nom,latitude,longitude) VALUES('Vakinankaratra' , -19.7113095,46.8355481);
INSERT INTO Region(nom,latitude,longitude) VALUES('Bongolava' , -18.5916863,46.3078167);
INSERT INTO Region(nom,latitude,longitude) VALUES('Sofia' , -15.2538402,48.2562163);
INSERT INTO Region(nom,latitude,longitude) VALUES('Boeny' , -16.2349278,46.1292672);
INSERT INTO Region(nom,latitude,longitude) VALUES('Betsiboka' , -17.095744,47.2600709);
INSERT INTO Region(nom,latitude,longitude) VALUES('Melaky' , -17.7541481,44.9790052);
INSERT INTO Region(nom,latitude,longitude) VALUES('Alaotra Mangoro' , -18.0186831,48.3549363);
INSERT INTO Region(nom,latitude,longitude) VALUES('Atsinanana' , -18.9771559,48.8742141);
INSERT INTO Region(nom,latitude,longitude) VALUES('Analanjirofo' , -16.317265,49.491491);
INSERT INTO Region(nom,latitude,longitude) VALUES('Amoron'' i Mania' , -20.4591292,46.7263922);
INSERT INTO Region(nom,latitude,longitude) VALUES('Haute Matsiatra' , -21.4690638,46.4663496);
INSERT INTO Region(nom,latitude,longitude) VALUES('Vatovavy Fitovinany' , -21.2660334,47.8637087);
INSERT INTO Region(nom,latitude,longitude) VALUES('Atsimo Atsinanana' , -23.2235237,47.2835176);
INSERT INTO Region(nom,latitude,longitude) VALUES('Ihorombe' , -22.6327896,46.0995658);
INSERT INTO Region(nom,latitude,longitude) VALUES('Menabe' , -20.0355933,45.1084888);
INSERT INTO Region(nom,latitude,longitude) VALUES('Atsimo Andrefana' , -23.0907053,44.4013326);
INSERT INTO Region(nom,latitude,longitude) VALUES('Androy' , -24.6805968,45.5243806);
INSERT INTO Region(nom,latitude,longitude) VALUES('Anosy' , -24.0624748,46.2617665);

CREATE TABLE chief_region(
	idUser INT NOT NULL,
	idRegion INT NOT NULL,
	FOREIGN KEY (idUser) REFERENCES auth_user(id),
	FOREIGN KEY (idRegion) REFERENCES region(id)
);

INSERT INTO chief_region VALUES (2,8);

CREATE TABLE Responsable(
	id SERIAL PRIMARY KEY NOT NULL,
	idRegion INT,
	nom VARCHAR(255),
	prenom VARCHAR(255),
	email VARCHAR(255),
	motDePasse VARCHAR(255),
	FOREIGN KEY (idRegion) REFERENCES Region(id)
);

-- CREATE TABLE TokenResponsable(
-- 	id SERIAL PRIMARY KEY NOT NULL,
-- 	idResponsable INT,
-- 	token VARCHAR(255),
-- 	dateExpiration TIMESTAMP,
-- 	FOREIGN KEY (idResponsable) REFERENCES Responsable(id)
-- );

CREATE TABLE TypeSignalement(
	id SERIAL PRIMARY KEY NOT NULL,
	type VARCHAR(255),
	couleur VARCHAR(255)
);

INSERT INTO typesignalement(type,couleur) VALUES ('Route abîmée','red');
INSERT INTO typesignalement(type,couleur) VALUES ('Ordures','green');
INSERT INTO typesignalement(type,couleur) VALUES ('Accident','blue');

CREATE TABLE Status(
	id SERIAL PRIMARY KEY NOT NULL,
	libelle VARCHAR(255)
);

INSERT INTO status(libelle) VALUES ('Nouveau');
INSERT INTO status(libelle) VALUES ('En cours de traitement');
INSERT INTO status(libelle) VALUES ('Terminé');

-- CREATE TABLE Utilisateur(
-- 	id SERIAL PRIMARY KEY NOT NULL,
-- 	nom VARCHAR(255),
-- 	prenom VARCHAR(255),
-- 	email VARCHAR(255),
-- 	motDePasse VARCHAR(255)
-- );

-- INSERT INTO Utilisateur (nom,prenom,email,motDePasse) VALUES ('Jean','Renois','jean.renois.user@gmail.com','jeanrenois');

-- CREATE TABLE TokenUtilisateur(
-- 	id SERIAL PRIMARY KEY NOT NULL,
-- 	idUtilisateur INT,
-- 	token VARCHAR(255),
-- 	dateExpiration TIMESTAMP,
-- 	FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur(id)
-- );

CREATE TABLE Signalement(
	id SERIAL PRIMARY KEY NOT NULL,
	idType INT,
	idUtilisateur INT,
	idRegion INT,
	idStatus INT,
	description TEXT,
	image VARCHAR(255),
	dateSignalement TIMESTAMP,
	latitude DECIMAL,
	longitude DECIMAL,
	FOREIGN KEY (idRegion) REFERENCES Region(id),
	FOREIGN KEY (idUtilisateur) REFERENCES auth_user(id),
	FOREIGN KEY (idType) REFERENCES TypeSignalement(id),
	FOREIGN KEY (idStatus) REFERENCES Status(id)
);

INSERT INTO signalement(idType,idUtilisateur,idRegion,idStatus,description,image,dateSignalement,latitude,longitude) VALUES (1,1,2,1,'Lavaka be mahafaty amle lalana','image1.png','2021-11-13 12:00:12',-12.232,15.23232);
INSERT INTO signalement(idType,idUtilisateur,idRegion,idStatus,description,image,dateSignalement,latitude,longitude) VALUES (1,1,2,1,'rano be tsy ahitana amle lalana','image1.png','2021-12-24 16:23:12',-12.3242,15.34323);
INSERT INTO signalement(idType,idUtilisateur,idRegion,idStatus,description,image,dateSignalement,latitude,longitude) VALUES (1,1,3,1,'hazo be nenjera ampvon lalana','image1.png','2022-01-13 13:24:11',-12.76567,15.787564);
INSERT INTO signalement(idType,idUtilisateur,idRegion,idStatus,description,image,dateSignalement,latitude,longitude) VALUES (2,1,16,1,'Propagation d''ordures au milieu de la rue','image1.png','2022-01-09 15:00:05',-12.3049,15.0923);
INSERT INTO signalement(idType,idUtilisateur,idRegion,idStatus,description,image,dateSignalement,latitude,longitude) VALUES (2,1,19,1,'fako maimbo be miangona tsisy maka','image1.png','2022-01-17 10:12:11',-12.57489,15.023934);
INSERT INTO signalement(idType,idUtilisateur,idRegion,idStatus,description,image,dateSignalement,latitude,longitude) VALUES (2,1,8,1,'fako ftsn maingona amn dal','image1.png','2022-01-14 07:01:07',-12.9483,15.089943);
INSERT INTO signalement(idType,idUtilisateur,idRegion,idStatus,description,image,dateSignalement,latitude,longitude) VALUES (3,1,21,1,'accident be moto nandon olona','image1.png','2021-11-21 11:45:00',-12.84830,15.2323);
INSERT INTO signalement(idType,idUtilisateur,idRegion,idStatus,description,image,dateSignalement,latitude,longitude) VALUES (3,1,8,1,'avion nenjera','image1.png','2021-12-27 09:10:33',-12.734768,15.8478);
INSERT INTO signalement(idType,idUtilisateur,idRegion,idStatus,description,image,dateSignalement,latitude,longitude) VALUES (3,1,8,1,'accident latsaka anaty antsana','image1.png','2022-12-29 15:32:22',-12.60923,15.10304);
INSERT INTO signalement(idType,idUtilisateur,idRegion,idStatus,description,image,dateSignalement,latitude,longitude) VALUES (3,1,2,3,'accident matsiravina nahafaty olona 3','image1.png','2022-01-18 19:01:25',-12.3242,15.34323);
INSERT INTO signalement(idType,idUtilisateur,idRegion,idStatus,description,image,dateSignalement,latitude,longitude) VALUES (3,1,2,3,'accident matsiravina nahafaty olona 3','image1.png','2022-01-18 19:01:25',-12.3242,15.34323);
INSERT INTO signalement(idType,idUtilisateur,idRegion,idStatus,description,image,dateSignalement,latitude,longitude) VALUES (1,1,null,1,'Lavaka be tsisy andeanana','image1.png','2022-01-12 12:05:55',-12.55634,15.94534);
INSERT INTO signalement(idType,idUtilisateur,idRegion,idStatus,description,image,dateSignalement,latitude,longitude) VALUES (2,1,null,1,'Tsentsin fako le rano mandeha','image1.png','2022-01-20 11:33:12',-12.42356,15.45345);
INSERT INTO signalement(idType,idUtilisateur,idRegion,idStatus,description,image,dateSignalement,latitude,longitude) VALUES (3,1,null,1,'olona ray maty tsy tra-dra vodona ny fiara las ntsoka','image1.png','2022-01-17 16:43:02',-12.67543,15.42445);



CREATE VIEW view_signalementdetails AS SELECT s.id AS idSignalement,s.idType,ts.type,ts.couleur,s.idUtilisateur,u.nom,u.prenom,s.idRegion,r.nom AS region,s.idStatus,st.libelle AS status,s.description,s.image,s.dateSignalement,s.latitude,s.longitude FROM Signalement s JOIN TypeSignalement ts ON s.idType=ts.id JOIN Region r ON s.idRegion=r.id
JOIN status st ON s.idStatus=st.id JOIN auth_user u ON s.idUtilisateur=u.id;

CREATE VIEW view_responsable_region AS SELECT resp.id AS idresponsable,reg.id AS idregion, reg.nom AS region,resp.nom,resp.prenom,resp.email FROM responsable AS resp JOIN region AS reg ON resp.idregion=reg.id;

CREATE VIEW view_signalements_non_affectes AS SELECT s.id AS idSignalement,s.idType,ts.type,s.idUtilisateur,u.nom,u.prenom,s.idregion,s.idregion AS region,s.idStatus,st.libelle AS status,s.description,s.image,s.dateSignalement,s.latitude,s.longitude FROM Signalement s JOIN TypeSignalement ts ON s.idType=ts.id
JOIN status st ON s.idStatus=st.id JOIN auth_user u ON s.idUtilisateur=u.id WHERE s.idregion is NULL;

CREATE VIEW view_nombre_region_type AS SELECT idregion,idtype,COUNT(idregion) as nombre FROM signalement GROUP BY idregion,idtype;

CREATE VIEW  view_region_type AS SELECT reg.id AS idregion,reg.nom AS region,vnrt.idtype,vnrt.nombre FROM region AS reg RIGHT OUTER JOIN view_nombre_region_type AS vnrt ON reg.id=vnrt.idregion;

CREATE VIEW view_stat_region_type AS SELECT ROW_NUMBER() OVER() AS id,vrt.idregion,vrt.region,ts.id AS idtype,ts.type,vrt.nombre FROM view_region_type AS vrt LEFT OUTER JOIN typesignalement AS ts ON vrt.idtype=ts.id;

CREATE VIEW view_nombre_region_status AS SELECT idregion,idstatus,COUNT(idregion) as nombre FROM signalement GROUP BY idregion,idstatus;

CREATE VIEW  view_region_status AS SELECT reg.id AS idregion,reg.nom AS region,vnrs.idstatus,vnrs.nombre FROM region AS reg RIGHT OUTER JOIN view_nombre_region_status AS vnrs ON reg.id=vnrs.idregion;

CREATE VIEW view_stat_region_status AS SELECT ROW_NUMBER() OVER() AS id,vrs.idregion,vrs.region,st.id AS idstatus,st.libelle AS status,vrs.nombre FROM view_region_status AS vrs LEFT OUTER JOIN status AS st ON vrs.idstatus=st.id;

CREATE VIEW view_nombre_effectue AS SELECT sgn.idregion,COUNT(idregion) AS nombre_effectue  FROM signalement AS sgn WHERE idstatus=3 GROUP BY idregion;

CREATE VIEW view_nombre_total AS SELECT sgn.idregion,COUNT(idregion) AS nombretotal  FROM signalement AS sgn GROUP BY idregion;

CREATE VIEW view_effectue AS SELECT vnt.idregion,vnt.nombretotal,COALESCE(vne.nombre_effectue,0) AS nombretermine,ROUND(CAST(COALESCE(CAST((vne.nombre_effectue*100) AS NUMERIC)/vnt.nombretotal,0) AS NUMERIC),2) AS effectue FROM view_nombre_total AS vnt LEFT OUTER JOIN view_nombre_effectue AS vne ON vnt.idregion=vne.idregion ;

CREATE VIEW view_stat_effectue AS SELECT reg.id AS idregion,reg.nom AS region,ve.nombretotal,ve.nombretermine,ve.effectue FROM region AS reg RIGHT OUTER JOIN view_effectue AS ve ON reg.id=ve.idregion;

--$2a$12$gSF8xx86HweYoiDO43HTt.yQYvtOhM4.owKg0jfBkmNVRE3uAAdaS