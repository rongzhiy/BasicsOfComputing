package cn.bugstack.middleware.test;

import cn.bugstack.middleware.test.infrastructure.dao.IUserDao;
import cn.bugstack.middleware.test.infrastructure.po.User;
import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Resource
    private IUserDao userDao;


    @Test
    public void test_connectDatabase() {
        Connection connection = null;
        try {
            // 加载数据库驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 建立数据库连接
            String url = "jdbc:mysql://localhost:3306/bugstack_01?serverTimezone=GMT%2B8&useSSL=false";
            String username = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, username, password);
            // 断言连接成功
            Assert.assertNotNull(connection);
        } catch (ClassNotFoundException e) {
            logger.error("找不到数据库驱动程序", e);
            Assert.fail("找不到数据库驱动程序");
        } catch (SQLException e) {
            logger.error("无法连接到数据库", e);
            Assert.fail("无法连接到数据库");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("关闭数据库连接时出错", e);
                }
            }
        }
    }

    @Test
    public void test_queryUserInfoByUserId() {
        User user = userDao.queryUserInfoByUserId(new User("980765512"));
        logger.info("测试结果：{}", JSON.toJSONString(user));
    }



    @Test
    public void test_insertUser() {
        User user = new User();
        user.setUserId("120765510");
        user.setUserNickName("小傅哥");
        user.setUserHead("01_50");
        user.setUserPassword("123456");

        userDao.insertUser(user);
    }

//    @Test
//    public void test_insertUser0() {
//        User user = new User();
//        user.setUserId("980765512");
//        user.setUserNickName("小傅哥");
//        user.setUserHead("01_50");
//        user.setUserPassword("123456");
//
//        userDao.insertUser(user);
//    }

}
