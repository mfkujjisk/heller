package heller.spring.ioc;

import heller.spring.ioc.domain.User;
import heller.spring.ioc.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by zhouzhanghe on 2016/11/15.
 */
public class IOCTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("./spring-config.xml");
        UserService userService = (UserService)context.getBean("userServiceImpl");

        User user = new User();
        user.setId(1L);
        user.setName("zhouzhanghe");
        userService.save(user);
    }
}
