create table user_hobbies(
id int(20) auto_increment primary key comment "序号",
hobby_name varchar(20) comment "爱好类型" ,
user_id int(20) comment "用户名",
constraint kf_user_hobbies_user_id foreign key(user_id) references users(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;