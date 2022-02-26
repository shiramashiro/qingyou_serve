create table user_traveles(
    id int(20) primary key,
    location varchar(30),
    times datetime comment '时间',
    traveled_date datetime comment '旅行时间',
    is_plan varchar(30) comment '计划',
    planning_date datetime comment '计划时间',
    user_id int(20),
    constraint kf_user_traveles_user_id foreign key(user_id) references users(id)
);