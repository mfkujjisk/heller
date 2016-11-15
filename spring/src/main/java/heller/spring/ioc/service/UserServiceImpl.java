package heller.spring.ioc.service;

import heller.spring.ioc.domain.User;
import heller.spring.ioc.manager.UserManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhouzhanghe on 2016/11/15.
 */
public class UserServiceImpl implements UserService {
    @Resource
    private UserManager userManager;

    public void save(User user) {
        userManager.save(user);
    }
}
