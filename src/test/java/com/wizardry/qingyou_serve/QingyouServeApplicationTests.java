package com.wizardry.qingyou_serve;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class QingyouServeApplicationTests {

    @Autowired  //自动装配
    private DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
