package com.chinatelecom.lottery.web.controller;

import com.chinatelecom.lottery.model.User;
import com.chinatelecom.lottery.repository.UserRepository;
import com.chinatelecom.lottery.web.form.UserInfoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2/25/13
 * Time: 9:08 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    private UserRepository userRepository;

    @Autowired
    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(Map model) {
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processForm(ModelMap modelMap, UserInfoForm registerForm, HttpSession session) {
        User existedUser = userRepository.findUser(registerForm.getUserName());
        if(existedUser!=null){
            modelMap.addAttribute("error","用户名已经被注册！");
            return "register";
        }
        User user = registerForm.toUser();
        userRepository.save(user);
        session.setAttribute("currentUser", user);
        return "redirect:lottery";
    }
}
