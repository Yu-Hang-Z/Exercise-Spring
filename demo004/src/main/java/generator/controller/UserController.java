package generator.controller;

import generator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by zhangyuhang
 * @Classname UserController
 * @Description TODO
 * @Date 2021/9/3 15:59
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("wrong")
    public int wrong(String name){
        return userService.createUserWrong1("name");
    }
}
