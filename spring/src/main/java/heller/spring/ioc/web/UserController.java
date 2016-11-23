package heller.spring.ioc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhouzhanghe on 2016/11/22.
 */

@Controller
@RequestMapping("/userController")
public class UserController {

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(){
        System.out.println("test^");
        return "test";
    }
}
