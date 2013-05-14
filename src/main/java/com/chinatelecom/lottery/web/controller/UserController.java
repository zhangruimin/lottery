package com.chinatelecom.lottery.web.controller;

import com.chinatelecom.lottery.model.User;
import com.chinatelecom.lottery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2/25/13
 * Time: 9:08 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/users")
public class UserController extends BaseController{
    private static final String DEVICES = "devices";
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "createUser",method= RequestMethod.GET)
    public @ResponseBody
    String createUser(ModelMap model, @RequestParam String userId, @RequestParam String userName,@RequestParam String password) {
        User user = new User();
        user.setId(userId);
        user.setUserName(userName);
        user.setPassword(password);
        userRepository.save(user);
        return "Success";
    }
}
