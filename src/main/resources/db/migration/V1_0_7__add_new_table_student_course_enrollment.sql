
    create table student_course_enrollments (
       id bigint identity not null,
        isFinished bit not null,
        course_id bigint not null,
        student_id bigint not null,
        primary key (id)
    );

    create table students (
       id bigint identity not null,
        user_id bigint,
        primary key (id)
    );

    alter table students 
       add constraint UKg4fwvutq09fjdlb4bb0byp7t unique (user_id);

    alter table student_course_enrollments 
       add constraint FK2ir93ssv9i26pms0i0ohpi5gl 
       foreign key (course_id) 
       references courses;

    alter table student_course_enrollments 
       add constraint FKetv96qjx3cj1lid5c3w9rfjqv 
       foreign key (student_id) 
       references students;
