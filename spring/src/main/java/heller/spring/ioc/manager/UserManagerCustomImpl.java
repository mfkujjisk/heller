package heller.spring.ioc.manager;

import heller.spring.ioc.domain.User;
import org.springframework.stereotype.Service;

/**
 * Created by zhouzhanghe on 2016/11/15.
 */
@Service
public class UserManagerCustomImpl implements UserManager {
    public void save(User user) {
        System.out.println(this.getClass().getName()+"\t"+ user);
    }
}
