package com.wizardry.qingyou.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

// 组件管理
@Component
public class MyDateHandler implements MetaObjectHandler {

    // 插入数据时的操作
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createdDate",new Date(),metaObject);

        //this.setFieldValByName("updateDate",new Date(),metaObject);
    }

    // 更新数据时的操作
    @Override
    public void updateFill(MetaObject metaObject) {
        //this.setFieldValByName("updateDate",new Date(),metaObject);
        //this.strictUpdateFill(metaObject, "updatessss", LocalDateTime.class, LocalDateTime.now());
    }
}
