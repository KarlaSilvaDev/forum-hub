create table topics(

    id bigint not null auto_increment,
    title varchar(100) not null,
    message varchar(1000) not null unique,
    created_at varchar(6) not null unique,
    status varchar(100) not null,
    author varchar(100) not null,
    course varchar(100) not null,
    answers varchar(9) not null,

    primary key(id)

);