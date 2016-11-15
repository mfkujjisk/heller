package heller.spring.ioc.manager;

import heller.spring.ioc.domain.User;

/**
 * Created by zhouzhanghe on 2016/11/15.
 */
public class UserManagerImpl implements UserManager {
    public void save(User user) {
        System.out.println(this.getClass().getName()+"\t"+ user);
    }
}
