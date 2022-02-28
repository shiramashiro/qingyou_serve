create table user_labels(
id int(20) auto_increment primary key comment "序号",
label_name varchar(10) comment "标签名",
user_id int(20) comment "用户id",
constraint kf_user_label_user_id foreign key(user_id) references users(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
