package heller.spring.ioc.service;

import heller.spring.ioc.domain.User;
import heller.spring.ioc.manager.UserManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhouzhanghe on 2016/11/15.
 */
public class UserServiceImpl implements UserService {
    List<UserManager> userManagerList;

    public List<UserManager> getUserManagerList() {
        return userManagerList;
    }

    public void setUserManagerList(List<UserManager> userManagerList) {
        this.userManagerList = userManagerList;
    }

    public void save(User user) {
        for(UserManager userManager : userManagerList){
            userManager.save(user);
        }
    }
}
