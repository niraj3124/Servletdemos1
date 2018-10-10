SQL> create sequence movieSeq start with 100;

Sequence created.

SQL> create sequence theaterSeq start with 1000;

Sequence created.

SQL> create sequence bookSeq start with 2000;

Sequence created.

SQL> create table movies(movieId number(4) primary key, movie_Name varchar(30) unique);

Table created.

SQL> insert into movies values(movieSeq.nextval,'Harry Potter');

1 row created.

SQL> insert into movies values(movieSeq.nextval,'Chinni Kam');

1 row created.

SQL> insert into movies values(movieSeq.nextval,'Inception');

1 row created.

SQL> commit;

Commit complete.

SQL> select * from movies;

   MOVIEID MOVIE_NAME
---------- ------------------------------
       100 Harry Potter
       101 Chinni Kam
       102 Inception
       
SQL> create table theaters(theaterId number(5) primary key, theaterName varchar(30) unique, movieId number(4) references movies(movieId),availableTickets number(3), moviePlayTime timestamp,price number(5,2));

Table created.

SQL> insert into theaters values(theaterSeq.nextval,'Big Cinemas',100,70,'24-Oct-15 5:30:05 PM',245.74);

1 row created.

SQL> insert into theaters values(theaterSeq.nextval,'PVR Cinemas',100,20,'25-Oct-15 2:30:05 PM',200.74);

1 row created.

SQL>insert into theaters values(theaterSeq.nextval,'INOX',101,30,'26-Oct-15 10:30:05 AM',100);

1 row created.

SQL> insert into theaters values(theaterSeq.nextval,'City Pride Satara Road',101,2,'27-Oct-15 12:30:05 PM',175.50);

1 row created.

SQL> insert into theaters values(theaterSeq.nextval,'ESquare',102,0,'28-Oct-15 11:30:00 AM',195.50);

1 row created.

SQL>insert into theaters values(theaterSeq.nextval,'City Pride Kothrud',102,0,'29-Oct-15 01:30:00 PM',195.50);

1 row created.

SQL> commit;

Commit complete.

SQL> create table moviebooking(bid number(4) primary key, theaterName varchar(30
), ticketsBooked number(3), price number(5,2),movieTime timestamp, bookingdate d
ate);

Table created.

SQL> select * from movies;

   MOVIEID MOVIE_NAME
---------- ------------------------------
       100 Harry Potter
       101 Chinni Kam
       102 Inception

SQL> select * from theaters;

 THEATERID THEATERNAME                       MOVIEID AVAILABLETICKETS MOVIEPLAYT
IME                                                                    PRICE
---------- ------------------------------ ---------- ---------------- ----------
----------------------------------------------------------------- ----------
      1007 Big Cinemas                           100               20 24-SEP-15
05.30.05.000000 PM                                                    245.74
      1008 PVR Cinemas                           100               25 25-SEP-15
02.30.05.000000 PM                                                    200.74
      1010 INOX                                  101               23 26-SEP-15
10.30.05.000000 AM                                                       100
      1011 City Pride Satara Road                101               25 27-SEP-15
12.30.05.000000 PM                                                     175.5
      1012 Esquare                               102               22 28-SEP-15
11.30.05.000000 AM                                                     195.5
      1013 City Pride Kothrud                    102                5 29-SEP-15
01.30.05.000000 PM                                                     195.5

