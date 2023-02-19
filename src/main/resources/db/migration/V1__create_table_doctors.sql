create table doctors(
    id bigint not null auto_increment,
    doctor_name varchar(100) not null,
    email varchar(100) not null unique,
    crm varchar(6) not null unique,
    speciality varchar(100) not null,
    street varchar(100) not null,
    district varchar(100) not null,
    postcode varchar(9)not null,
    complement varchar(100), 
    address_number varchar(20),
    address_state char(2) not null,
    city varchar(100) not null,

    primary key(id)
);