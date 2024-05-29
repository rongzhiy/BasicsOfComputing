package cn.bugstack.middleware.test.interfaces;

import cn.bugstack.middleware.test.infrastructure.dao.IUserDao;
import cn.bugstack.middleware.test.infrastructure.po.User;
import cn.bugstack.middleware.test.interfaces.dto.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IUserDao userDao;

    @RequestMapping(path = "/api/queryUserInfoById", method = RequestMethod.GET)
    public User queryUserInfoById(){
        return userDao.queryUserInfoByUserId(new User("980765512"));
    }

}
