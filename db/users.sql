create table users(
id int(20) auto_increment primary key comment "序号",
psw varchar(20) unique  comment "密码",
uname varchar(15) unique comment "用户名",
phone varchar(11)  unique comment "手机号",
email varchar(30) unique comment "邮箱",
created_date datetime comment "创建日期",
birthday datetime comment "生日",
signature varchar(30) comment "个性签名",
sex varchar(3) comment "性别" ,
location varchar(30) comment "地区",
constellation varchar(3) comment "星座",
age int(3) comment "年龄",
avatar text comment "URL坐标",
occupation varchar(10) comment "职业"
)ENGINE=InnoDB DEFAULT CHARSET=utf8;