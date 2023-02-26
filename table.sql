create table branch(
	id int AUTO_INCREMENT,
    name varchar(50),
    primary key(id)
);
create table students(
	id int not null AUTO_INCREMENT,
    fname varchar(30)not null,
    lname varchar(30)not null,
    aadhar_number numeric(12)not null,
    DOB date not null,
    Branch_id int not null,
    Contact_no numeric(10)not null,
    Email_id varchar(50)not null,
    Address varchar(255)not null,
    password varchar(30),
    primary key (id),
    unique (aadhar_number,Email_id,Contact_no),
    foreign key(branch_id) references branch(id)
);
create table exams(
	id int not null auto_increment,
    name varchar(50),
    held_year varchar(4),
    primary key (id),
    unique  (name)
    );
create table subjects(
	id int not null auto_increment,
    name varchar(50),
    code numeric(6),
    primary key(id),
    unique(name,code)
);
create table classroom(
	id int not null auto_increment,
    number int not null,
    floor int not null,
    wing varchar(10) not null,
    capacity int not null,
    primary key (id)
    
);
create table admin(
	id int not null auto_increment,
    email_id varchar(50),
    password varchar(50),
    primary key(id)
); 
create table exam_subject(
	id int not null auto_increment,
    exam_id int,
    subject_id int,
    date_of_exam datetime,
    primary key(id),
    foreign key(exam_id) references exams(id),
    foreign key(subject_id) references subjects(id)
);
create table enroll(
	id int not null auto_increment,
    student_id int not null,
    exam_id int not null,
    primary key(id),
    foreign key(student_id) references students(id),
    foreign key(exam_id) references exams(id)
);
create table student_classroom(
	id int not null auto_increment,
    classroom_id int not null,
    student_id int not null,
    exam_subject_id int not null,
    date_of_exam datetime,
    primary key(id),
    foreign key(student_id) references students(id),
    foreign key(exam_subject_id) references exam_subject(id),
    foreign key(classroom_id) references classroom(id)
);
