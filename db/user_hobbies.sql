create table user_hobbies(
id int(20) auto_increment primary key comment "序号",
hobby_name varchar(20),
user_id int(20),
constraint kf_user_hobbies_user_id foreign key(user_id) references users(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;