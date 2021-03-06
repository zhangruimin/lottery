package com.chinatelecom.lottery.web.controller;

import com.chinatelecom.lottery.model.LotteryStatus;
import com.chinatelecom.lottery.model.User;
import com.chinatelecom.lottery.repository.LotteryStatusRepository;
import com.chinatelecom.lottery.repository.UserRepository;
import com.chinatelecom.lottery.web.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2/25/13
 * Time: 9:08 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private UserRepository userRepository;
    private LotteryStatusRepository lotteryStatusRepository;

    @Autowired
    public LoginController(UserRepository userRepository ,
                           LotteryStatusRepository lotteryStatusRepository) {
        this.userRepository = userRepository;
        this.lotteryStatusRepository = lotteryStatusRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(ModelMap model) {
        setStatus(model);
        return "login";
    }

    private void setStatus(ModelMap model) {
        LotteryStatus lotteryStatus = lotteryStatusRepository.findOne();
        if (lotteryStatus != null && !lotteryStatus.isAllowRegister()) {
            model.addAttribute("status", "false");
        } else{
            model.addAttribute("status", "true");
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processForm(LoginForm loginForm, HttpSession session) {
        if(loginForm.isSA()){
            session.setAttribute("currentUser", User.SA());
            return "redirect:admin/managePrize";
        }
        User user = userRepository.findUser(loginForm.getUserName(), loginForm.getPassword());
        if (user == null) {
            return "login";
        }
        session.setAttribute("currentUser", user);
        return "redirect:lottery";
    }
}
