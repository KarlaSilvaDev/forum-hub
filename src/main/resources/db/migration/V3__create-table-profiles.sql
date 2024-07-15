create table profiles(
    id bigint not null auto_increment,
    name varchar(100) not null, -- Name of the profile('ADMIN', 'MODERATOR')

    primary key(id)
);
