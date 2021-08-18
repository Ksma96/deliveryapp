create table cargo_type
(
    id int auto_increment,
    name varchar(64) not null,
    constraint cargo_type_pk
        primary key (id)
);

create unique index cargo_type_name_index
    on cargo_type (name);

