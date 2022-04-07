    create table test_results (
       id bigint identity not null,
        isFinished bit,
        mark int,
        enrollment_id bigint,
        test_id bigint,
        primary key (id)
    );

    create table lecture_results (
       id bigint identity not null,
        isFinished bit,
        enrollment_id bigint,
        lecture_id bigint,
        primary key (id)
    );

    alter table lecture_results 
       add constraint FKilubriwpd907e6sorj5yyex3w 
       foreign key (enrollment_id) 
       references student_course_enrollments;

    alter table lecture_results 
       add constraint FKc6irehjspvc4b41dhq0he21lx 
       foreign key (lecture_id) 
       references lectures;

    alter table test_results 
       add constraint FKe8h1eocqdrkk27qmh5avrd2rv 
       foreign key (enrollment_id) 
       references student_course_enrollments;

    alter table test_results 
       add constraint FKeb5e15t9e5hn11gbkuub0xeln 
       foreign key (test_id) 
       references tests;
