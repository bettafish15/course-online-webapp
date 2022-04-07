
    create table questions (
       id bigint identity not null,
        title nvarchar(255),
        tag nvarchar(255),
        content nvarchar(255),
        question_id bigint FOREIGN KEY REFERENCES questions(id),
        student_id bigint FOREIGN KEY REFERENCES students(id),
        primary key (id),

    );

   