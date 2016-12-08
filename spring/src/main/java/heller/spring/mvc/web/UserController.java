package heller.spring.mvc.web;

import heller.spring.ioc.domain.User;
import heller.spring.ioc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zhouzhanghe on 2016/11/22.
 */

@Controller
@RequestMapping("/userController")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(){
        System.out.println("test^");
        userService.save(new User());
        return "test";
    }
}
