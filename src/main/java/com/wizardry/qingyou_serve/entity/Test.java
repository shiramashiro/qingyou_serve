package com.wizardry.qingyou_serve.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 这是一个实体类案例，可删除。下面有三个注解，Data、NoArgsConstructor、AllArgsConstructor。
 * 这是 lombok 框架的东西，Data 代替了你写 Getter 和 Setter 的步骤，NoArgsConstructor 和 AllArgsConstructor 代替了
 * 你写无参构造函数和有参构造函数的步骤。
 *
 * @author shiramashiro
 * @since 2022年2月27日18:37:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    public int id;
    public String uname;
    public String pwd;

}
