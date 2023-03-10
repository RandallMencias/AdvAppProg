DROP TABLE Faculty;
DROP TABLE Course;
DROP TABLE Manager;

CREATE TABLE Faculty (
   faculty_Name varchar(40) NOT NULL,
   office varchar(40) NOT NULL,
   faculty_ID varchar(40) NOT NULL,
   PRIMARY KEY (faculty_ID)
);

CREATE TABLE Course (
   course_ID varchar (40) NOT NULL,
   course varchar (40) NOT NULL,
   faculty_ID varchar (4) NOT NULL,
   PRIMARY KEY (faculty_ID)
);

CREATE TABLE Manager (
   Faculty_ID varchar (40) NOT NULLL,
   CourseF_ID varchar (40) NOT NULL,
   FOREIGN KEY (Faculty_ID) REFERENCES Faculty (faculty_ID), 
   FOREIGN KEY (Course_ID) REFERENCES Course (faculty_ID)
);

INSERT INTO Faculty (faculty_Name, office, faculty_ID)
VALUES 
   ('Black Anderson','MTC-218','A52990'), 
   ('Debby Angels','MTC-320','A77587');

INSERT INTO Course (course_ID, course, faculty_ID)
VALUES
   ('CSC-132A','Introduction to Programming','A52990'),
   ('CSC-230A','Java How to Program','A77587');

INSERT INTO authorISBN (authorID,isbn)
VALUES
   ('A52990','A52990'),
   ('A77587','A77587');
