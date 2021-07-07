-- Hi Database I'm a comment

drop table accounts;
drop table clients;

create table clients (
	id 				serial primary key,
	first_name 		varchar(25),
	last_name		varchar(30),
	email			varchar(50),
	password		varchar(50)
);
create table accounts (
	id 				serial primary key,
	client_id 		int references clients(id) on delete set null,
	account_name 	varchar(50),
	account_type 	varchar(20),
	balance 		numeric(12,2)
);

insert into clients (first_name, last_name, email, password) values ('Henriette', 'Preedy', 'hpreedy0@t.co', 'ayWP97');
insert into clients (first_name, last_name, email, password) values ('Wye', 'Oller', 'woller1@java.com', 'aY77Kap5w1l2');
insert into clients (first_name, last_name, email, password) values ('Freddy', 'Franceschelli', 'ffranceschelli2@bloomberg.com', 'XtyvDls5l');
insert into clients (first_name, last_name, email, password) values ('Heida', 'Borthe', 'hborthe3@umn.edu', '1b19iGaFlqfT');
insert into clients (first_name, last_name, email, password) values ('Lon', 'Rustich', 'lrustich4@a8.net', 'ctGVBe');
insert into clients (first_name, last_name, email, password) values ('Agneta', 'Bassil', 'abassil5@mashable.com', 'I8544n1rVZL');
insert into clients (first_name, last_name, email, password) values ('Niles', 'Karolewski', 'nkarolewski6@scribd.com', 'Vz7YYALMdsp');
insert into clients (first_name, last_name, email, password) values ('Mirilla', 'Stennings', 'mstennings7@shareasale.com', '9DnmGqo7koE');
insert into clients (first_name, last_name, email, password) values ('Valdemar', 'Spyvye', 'vspyvye8@engadget.com', 'mQ4XwUTd');
insert into clients (first_name, last_name, email, password) values ('Eugenius', 'Wiseman', 'ewiseman9@nifty.com', 'I8Ls9C1o');
insert into clients (first_name, last_name, email, password) values ('Hiram', 'Bonifas', 'hbonifasa@ow.ly', 'jXVLDBZW3');
insert into clients (first_name, last_name, email, password) values ('Isidro', 'Ropkins', 'iropkinsb@topsy.com', 'kfeNpIyP4');
insert into clients (first_name, last_name, email, password) values ('Josee', 'Plain', 'jplainc@passwordomniture.com', 'b0bLOe3rlEf');
insert into clients (first_name, last_name, email, password) values ('Rosetta', 'Mccaull', 'rmccaulld@businessweek.com', 'o3qeyf');
insert into clients (first_name, last_name, email, password) values ('Petr', 'Duggen', 'pduggene@state.gov', 'BwH79uRQ');
insert into clients (first_name, last_name, email, password) values ('Kirbee', 'Shill', 'kshillf@amazon.co.jp', 'DAb0lMYsLups');
insert into clients (first_name, last_name, email, password) values ('Katrine', 'Kittle', 'kkittleg@cornell.edu', 'nFWoWOxL0');
insert into clients (first_name, last_name, email, password) values ('Etti', 'Scholard', 'escholardh@1688.com', 'loE2inso');
insert into clients (first_name, last_name, email, password) values ('Wilfred', 'Atto', 'wattoi@123-reg.co.uk', 'D7u16UTQ');
insert into clients (first_name, last_name, email, password) values ('Osmund', 'Frost', 'ofrostj@shutterfly.com', 'DAycDGgg');

insert into accounts (client_id, account_name, account_type, balance) values (2, 'Ntag', 'checking', -19.55);
insert into accounts (client_id, account_name, account_type, balance) values (16, 'Dabshots', 'savings', 1984.07);
insert into accounts (client_id, account_name, account_type, balance) values (10, 'Feednation', 'savings', 328.3);
insert into accounts (client_id, account_name, account_type, balance) values (9, 'Jamia', 'savings', -244.09);
insert into accounts (client_id, account_name, account_type, balance) values (18, 'Jabbercube', 'savings', 2445.51);
insert into accounts (client_id, account_name, account_type, balance) values (15, 'Realbuzz', 'checking', 116.36);
insert into accounts (client_id, account_name, account_type, balance) values (12, 'Skinder', 'savings', 575.48);
insert into accounts (client_id, account_name, account_type, balance) values (12, 'Dynabox', 'checking', 1907.38);
insert into accounts (client_id, account_name, account_type, balance) values (1, 'Realbridge', 'savings', 530.3);
insert into accounts (client_id, account_name, account_type, balance) values (7, 'Feedfish', 'checking', -180.03);
insert into accounts (client_id, account_name, account_type, balance) values (7, 'Flipopia', 'checking', 1298.38);
insert into accounts (client_id, account_name, account_type, balance) values (17, 'Wikizz', 'savings', 353.75);
insert into accounts (client_id, account_name, account_type, balance) values (18, 'Livefish', 'checking', 356.14);
insert into accounts (client_id, account_name, account_type, balance) values (7, 'Gigazoom', 'savings', 115.59);
insert into accounts (client_id, account_name, account_type, balance) values (2, 'Twitterbridge', 'savings', 2339.71);
insert into accounts (client_id, account_name, account_type, balance) values (13, 'Oyonder', 'savings', 889.03);
insert into accounts (client_id, account_name, account_type, balance) values (7, 'Topiczoom', 'checking', 2510.46);
insert into accounts (client_id, account_name, account_type, balance) values (9, 'Twitterlist', 'savings', -340.13);
insert into accounts (client_id, account_name, account_type, balance) values (20, 'Quinu', 'savings', 927.78);
insert into accounts (client_id, account_name, account_type, balance) values (17, 'Einti', 'checking', 749.13);
insert into accounts (client_id, account_name, account_type, balance) values (6, 'Avavee', 'checking', 645.58);
insert into accounts (client_id, account_name, account_type, balance) values (7, 'Mudo', 'savings', 1706.27);
insert into accounts (client_id, account_name, account_type, balance) values (5, 'Latz', 'savings', 2123.57);
insert into accounts (client_id, account_name, account_type, balance) values (20, 'Cogilith', 'checking', 1452.64);
insert into accounts (client_id, account_name, account_type, balance) values (13, 'Gabspot', 'checking', 2190.9);
insert into accounts (client_id, account_name, account_type, balance) values (1, 'Skaboo', 'savings', 2303.17);
insert into accounts (client_id, account_name, account_type, balance) values (17, 'Thoughtbridge', 'checking', -30.93);
insert into accounts (client_id, account_name, account_type, balance) values (17, 'Aimbo', 'checking', 2270.89);
insert into accounts (client_id, account_name, account_type, balance) values (14, 'Yambee', 'savings', -247.02);
insert into accounts (client_id, account_name, account_type, balance) values (13, 'Riffpath', 'savings', 1120.35);
insert into accounts (client_id, account_name, account_type, balance) values (11, 'Browsetype', 'savings', 2464.63);
insert into accounts (client_id, account_name, account_type, balance) values (18, 'Yoveo', 'checking', -317.47);
insert into accounts (client_id, account_name, account_type, balance) values (19, 'Abata', 'savings', 1117.52);
insert into accounts (client_id, account_name, account_type, balance) values (14, 'Jaxspan', 'savings', 969.99);
insert into accounts (client_id, account_name, account_type, balance) values (15, 'Yozio', 'checking', 416.57);
insert into accounts (client_id, account_name, account_type, balance) values (9, 'Mynte', 'checking', 422.26);
insert into accounts (client_id, account_name, account_type, balance) values (6, 'Realfire', 'savings', -364.38);
insert into accounts (client_id, account_name, account_type, balance) values (10, 'Quaxo', 'savings', -44.16);
insert into accounts (client_id, account_name, account_type, balance) values (14, 'Linkbuzz', 'savings', 2582.38);
insert into accounts (client_id, account_name, account_type, balance) values (5, 'Midel', 'checking', 2184.05);
insert into accounts (client_id, account_name, account_type, balance) values (19, 'Dabvine', 'checking', -245.75);
insert into accounts (client_id, account_name, account_type, balance) values (20, 'Rhynyx', 'checking', 1837.49);
insert into accounts (client_id, account_name, account_type, balance) values (8, 'Brainsphere', 'checking', -378.8);
insert into accounts (client_id, account_name, account_type, balance) values (15, 'Shuffletag', 'checking', 101.23);
insert into accounts (client_id, account_name, account_type, balance) values (17, 'Quimba', 'savings', 2246.03);
insert into accounts (client_id, account_name, account_type, balance) values (12, 'Agimba', 'checking', 44.02);
insert into accounts (client_id, account_name, account_type, balance) values (9, 'Fliptune', 'savings', 2454.68);
insert into accounts (client_id, account_name, account_type, balance) values (13, 'Livetube', 'checking', 568.77);
insert into accounts (client_id, account_name, account_type, balance) values (10, 'Gigaclub', 'checking', 95.45);
insert into accounts (client_id, account_name, account_type, balance) values (16, 'Nlounge', 'savings', 2124.72);

select * from accounts;