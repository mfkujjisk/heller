package heller.spring.ioc;

import com.alibaba.fastjson.JSON;
import heller.spring.ioc.domain.User;
import heller.spring.ioc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhouzhanghe on 2016/11/15.
 */
public class IOCTest {
    private static final Logger logger = LoggerFactory.getLogger(IOCTest.class);
    static  final Map<Integer, User> users = new HashMap<Integer, User>();

    static void test(int j){
        for(int k = 0 ; k < 1000; k ++) {
            User user = new User();
        }
        if(true){
            for(int i = 0 ; i < 5000; i ++) {
                for(int k = 0 ; k < 10000; k ++) {
                    User user = new User();
                    users.put(i+k,user);
                }
                if(i == 4000)
                    test2(i);
                else
                    test(i);
            }
        }
    }

    static void test2(int i){
        throw new RuntimeException("---");
    }



    public static void main(String[] args) {
        /*ApplicationContext context = new ClassPathXmlApplicationContext("./spring-config.xml");
        UserService userService = (UserService)context.getBean("userServiceImpl");

        User user = new User();
        user.setId(1L);
        user.setName("zhouzhanghe");
        userService.save(user);*/
        /*ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
        map.put("aaa","1");

        ConcurrentHashMap<String, String> map2 = new ConcurrentHashMap<String, String>();
        map2.put("aaa","2");

        map.putAll(map2);
        System.out.println(map);*/

        /*User user = new User();
        user.setId(1l);
        user.setName("ZZH");
        System.out.println(JSON.toJSONString(user));
        User user2  = JSON.parseObject("{\"id\":1,\"name\":\"ZZH\"}",User.class);
        System.out.println(user2);*/

        try{
            test(1);
        }catch (Exception e){
            logger.error("d",e);
            System.out.println(e.getStackTrace());
            for(StackTraceElement ste : e.getStackTrace()){
                ste.getClassName();
            }
        }
    }
}
