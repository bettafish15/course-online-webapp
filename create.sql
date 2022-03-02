create sequence hibernate_sequence start with 1 increment by 1;

    create table roles (
       id bigint not null,
        createdAt datetime2,
        role varchar(255),
        updatedAt datetime2,
        primary key (id)
    );

    create table users (
       id bigint not null,
        createdAt datetime2,
        email varchar(255),
        firstName varchar(255),
        lastName varchar(255),
        password varchar(255),
        phone varchar(255),
        updatedAt datetime2,
        userName varchar(255),
        role_id bigint,
        primary key (id)
    );

    alter table users 
       add constraint FKp56c1712k691lhsyewcssf40f 
       foreign key (role_id) 
       references roles;
