create table user_hobbies(
    id int(20) primary key,
    hobby_name varchar(20),
    user_id int(20),
    constraint kf_user_hobbies_user_id foreign key(user_id) references users(id)
) comment '用户业余爱好';