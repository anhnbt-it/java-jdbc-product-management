create database test
go
use test
go
create table category(cid int primary key, cname nvarchar(100))
go
create table Product(pid int primary key identity(1,1),
pname nvarchar(50) not null unique, quantity int, price money, 
cid int foreign key references category(cid))
go
insert into category values(1,'apple')
go
insert into category values(2,'SamSung')
go