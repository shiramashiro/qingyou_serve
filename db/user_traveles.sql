create table user_traveles(
id int(20) auto_increment primary key comment "序号",
location varchar(30),
times int comment '旅行次数',
traveled_date datetime comment '旅行时间',
is_plan tinyint comment '是否执行',
planning_date datetime comment '计划时间',
user_id int(20) comment "用户id",
constraint kf_user_traveles_user_id foreign key(user_id) references users(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;