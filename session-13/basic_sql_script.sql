show databases ;

use tns;

create table students (
id int primary key auto_increment,
name varchar(50) not null,
email varchar(50) unique,
age int,
course varchar(50)
);

select * from students;

show tables;

describe students;

insert into students(
name, email, age, course
) values 
('Pranay', 'p@gmail.com',24,'Java'),
('Vaibhav', 'a@gmail.com',25,'Python');

select version();

-- add records
insert into students(name, email, age, course)
values 
('Ram', 'r@gmail.com', 25,'Spring Boot'),
('Raj', 'ra@gmail.com',26,'AWS');

select * from students;

describe students;

select * from students where id = 5;

-- add multiple columns 
alter table students
add column class_year varchar(20),
add column phone varchar(50),
add column address varchar(50);

select * from students;

-- change a columns data type 
alter table students 
modify column phone varchar(25) not null; 

-- rename a column
alter table students
rename column course to course_name;

-- rename table 
rename table students to students_records;

-- remove a column
alter table students
drop column phone;

-- ALTER changes the table's structure,
-- not the table's name or the individual records directly.
 
 
 -- remove all rows
 truncate table students_records;

-- This removes all records but preserves the table structure.
-- There is no WHERE condition for TRUNCATE. 
 
 -- modify the records
 update students_records
 set course_name = "Core Java"
 where id = 1;
 
 -- modify multiple coulmns
 update students_records
 set age = 22, course_name = 'Java'
 where id = 2;
 
 -- update all matching students
 update students
 set course_name = "Core Ja"
where course_name = "Java"; 

-- If you omit WHERE, every row will be updated.

-- remove records 
delete from students_records
where id = 1;

-- remove every records  but preserve the table
delete from students_records;