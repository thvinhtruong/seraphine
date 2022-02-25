CREATE SCHEMA IF NOT EXISTS Sample;
USE Sample;

create table Athlete (
	ANr INT UNIQUE NOT NULL,
    AName VARCHAR(20),
    Country VARCHAR(20),
    Age INT,
    primary key(ANr)
);

create table Competition (
	CNr INT UNIQUE NOT NULL,
    CName VARCHAR(20),
    DateHappen Date,
    Location VARCHAR(20),
    WorldRecordHolder VARCHAR(20),
    primary key(CNr)
);

create table take_part (
	ANr INT NOT NULL,
    CNr INT NOT NULL,
    Medal VARCHAR(20)
);

insert into Athlete values(1, "david", "usa", 20);
insert into Athlete values(2, "thang", "vietnam", 21);
insert into Athlete values(3, "trung ngoo", "china", 22);
insert into Athlete values(4, "hai", "japan", 19);
insert into Athlete values(5, "klangkhang", "korea", 18);
insert into Athlete values(4711, "sigangoo", "korea", 25);
insert into Athlete values(432, "ming black", "vietnam", 19);
insert into Athlete values(10, "tri", "vietnam", 19);
insert into Athlete values(11, "thong", "vietnam", 19);

insert into Competition values(1, "ai ngoo nhat?", '2022-02-02', "vietnam", "ming black");
insert into Competition values(2, "ai xamlol nhat?", '2022-03-05', "china", "trung ngoo");
insert into Competition values(3, "ai cu ngan nhat?", '2022-02-02', "usa", "tri");
insert into Competition values(4, "ai cat toc buong 1", '2022-10-02', "germany", "ming black");

insert into take_part values(1, 1, "gold");
insert into take_part values(5, 2, "gold");
insert into take_part values(2, 1, "silver");
insert into take_part values(3, 2, "diamond");
insert into take_part values(4, 2, "silver");
insert into take_part values(1, 3, "gold");
insert into take_part values(2, 3, "gold");
insert into take_part values(4, 3, "gold");
insert into take_part values(432, 1, "diamond");
insert into take_part values(432, 4, "diamond");
insert into take_part values(10, 3, "diamond");
insert into take_part values(4711, 3, "gold");


select * from Athlete;
select * from Competition;
select * from take_part;





