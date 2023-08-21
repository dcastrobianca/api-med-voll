create TABLE appointments(
    id bigint not null auto_increment,
    doctor_id bigint not null,
    patient_id bigint not null,
    date_time datetime not null,

    primary key (id),
    foreign key (doctor_id) references doctors(id),
    foreign key (patient_id) references patients(id)
);