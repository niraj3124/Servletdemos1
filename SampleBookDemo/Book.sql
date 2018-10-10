SQL> create sequence bookISBN;

SQL> create table book(ISBN number primary key, book_name varchar2(50), publish_date date, price number(7,2), availableqty number(3));

SQL> insert into book values(bookISBN.nextval,'Hibernate Recepies', '09-Sep-97',567,10);

SQL>  insert into book values(bookISBN.nextval,'Struts Recepies', '02-Sep-14',900,6);

SQL> commit;