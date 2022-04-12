create table feedbacks (id bigint identity not null, content varchar(255), contentKey varchar(255), rating int, course_id bigint, student_id bigint, primary key (id));

alter table feedbacks add constraint FKpafds1ak183b9x26jdv9xnvof foreign key (course_id) references courses;
alter table feedbacks add constraint FK4ocysx6ldsioryb4bx1etqw32 foreign key (student_id) references students;
