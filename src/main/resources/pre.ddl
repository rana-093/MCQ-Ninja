create table admin
(
    id       integer      not null auto_increment,
    email    varchar(255) not null,
    name     varchar(100) not null,
    password varchar(20)  not null,
    address  varchar(30)  not null,
    primary key (id)
);

create table student
(
    id       integer      not null auto_increment,
    email    varchar(255) not null,
    name     varchar(100) not null,
    password varchar(20)  not null,
    active   bit          not null,
    primary key (id)
);

create table subject
(
    id   integer      not null auto_increment,
    name varchar(100) not null,
    primary key (id)
);

create table topic
(
    id         integer     not null auto_increment,
    name       varchar(20) not null,
    subject_id integer     not null,
    primary key (id),
    foreign key (subject_id) references subject (id)
);

create table answer
(
    id            integer not null auto_increment,
    choosenOption varchar(255),
    result        bit     not null,
    question_id   integer,
    primary key (id),
    FOREIGN KEY (question_id) REFERENCES question (id)
);

create table question
(
    id            integer      not null auto_increment,
    content       varchar(300) not null,
    correctOption varchar(255),
    explanation   varchar(500),
    optionA       varchar(100) not null,
    optionB       varchar(100) not null,
    optionC       varchar(100) not null,
    optionD       varchar(100) not null,
    used          bit          not null,
    topic_id      integer      not null,
    primary key (id)
);


create table exam
(
    id        integer not null auto_increment,
    endDate   date,
    endTime   datetime,
    startDate date,
    startTime datetime,
    primary key (id)
);

create table examQuestions
(
    exam_id     integer not null,
    question_id integer not null,
    FOREIGN KEY (exam_id) REFERENCES exam (id),
    foreign key (question_id) references question (id)
);

create table result
(
    id         integer not null auto_increment,
    score      integer not null,
    exam_id    integer,
    student_id integer,
    primary key (id),
    foreign key (exam_id) references exam (id),
    foreign (student_id) references student (id)
);

create table selectedOptions
(
    result_id     integer not null,
    answer_id integer not null,
    foreign key (result_id) references result (id),
    foreign key (answer_id) references answer (id)
);

create table userExamRegistration
(
    id         integer not null auto_increment,
    exam_id    integer,
    student_id integer,
    primary key (id),
    foreign key (exam_id) references exam (id),
    foreign key (student_id) references student (id)
);