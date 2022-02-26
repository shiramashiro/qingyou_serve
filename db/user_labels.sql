create table user_label(
    id int(20) primary key,
    label_name varchar(10) unique,
    user_id int(20),
    constraint kf_user_label_user_id foreign key(user_id) references users(id)
);