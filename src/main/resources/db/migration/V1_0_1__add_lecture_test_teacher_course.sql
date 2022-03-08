
    create table courses (
       id bigint identity not null,
        content varchar(255),
        image_url varchar(255),
        title varchar(255),
        teacher_id bigint not null,
        primary key (id)
    );

    create table lectures (
       id bigint identity not null,
        content varchar(255),
        index_order bigint,
        name bigint,
        resource_url varchar(255),
        week bigint,
        course_id bigint not null,
        primary key (id)
    );

    create table teachers (
       id bigint identity not null,
        user_id bigint,
        primary key (id)
    );

    create table tests (
       id bigint identity not null,
        content varchar(255),
        index_order bigint,
        name bigint,
        week bigint,
        course_id bigint not null,
        primary key (id)
    );

    alter table lectures 
       add constraint UKl37qgxi98qe6j4ah0duhn24he unique (week, index_order);

    alter table lectures 
       add constraint UK205e2wdn267nuwqphqvpb4all unique (week, name);

    alter table teachers 
       add constraint UKcd1k6xwg9jqtiwx9ybnxpmoh9 unique (user_id);

    alter table tests 
       add constraint UK14e9pu34lv8xrlxp774ctgga8 unique (week, index_order);

    alter table tests 
       add constraint UK1oyupuj94fo4yqwfdcl6yfi41 unique (week, name);

    alter table courses 
       add constraint FK468oyt88pgk2a0cxrvxygadqg 
       foreign key (teacher_id) 
       references teachers;

    alter table lectures 
       add constraint FKsj4m8ipr4qnehoyxk7kbu3ide 
       foreign key (course_id) 
       references courses;

    alter table teachers 
       add constraint FKb8dct7w2j1vl1r2bpstw5isc0 
       foreign key (user_id) 
       references users;

    alter table tests 
       add constraint FKnn88a30eakyhdu5nt1m5trxit 
       foreign key (course_id) 
       references courses;