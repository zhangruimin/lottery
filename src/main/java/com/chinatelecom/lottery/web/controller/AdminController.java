package com.chinatelecom.lottery.web.controller;

import com.chinatelecom.lottery.model.LotteryRecord;
import com.chinatelecom.lottery.model.PrizeType;
import com.chinatelecom.lottery.model.TicketState;
import com.chinatelecom.lottery.model.User;
import com.chinatelecom.lottery.repository.LotteryRepository;
import com.chinatelecom.lottery.repository.UserRepository;
import com.chinatelecom.lottery.service.TicketService;
import com.chinatelecom.lottery.web.dto.LotteryRecordDto;
import com.chinatelecom.lottery.web.form.UserInfoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2/25/13
 * Time: 9:08 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    private static final String MANAGE_PRIZE = "managePrize";
    private static final String QUERY_PRIZE = "queryPrize";
    private static final String MANAGE_USER = "manageUser";
    private final TicketService ticketService;
    private LotteryRepository lotteryRepository;
    private UserRepository userRepository;

    @Autowired
    public AdminController(TicketService ticketService, LotteryRepository lotteryRepository, UserRepository userRepository) {
        this.ticketService = ticketService;
        this.lotteryRepository = lotteryRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "managePrize", method = RequestMethod.GET)
    public String index(HttpSession session, ModelMap model) {
        checkSA(session);
        TicketState ticketState = ticketService.getTicketState();
        model.addAttribute("ticketState", ticketState);
        return MANAGE_PRIZE;
    }

    @RequestMapping(value = "queryPrize", method = RequestMethod.GET)
    public String queryPrize(HttpSession session, ModelMap model, String phone, String specialOnly) {
        checkSA(session);
        List<LotteryRecord> results = new ArrayList<LotteryRecord>();
        if (phone == null || phone.trim().equals("")) {
            results = lotteryRepository.findAll();
        } else {
            LotteryRecord byPhone = lotteryRepository.findByPhone(phone);
            if (byPhone != null) {
                results.add(byPhone);
            }
        }
        List<LotteryRecordDto> dtos = new ArrayList<LotteryRecordDto>();
        for (LotteryRecord r : results) {
            if("true".equals(specialOnly)&& !PrizeType.SPECIAL.equals(r.getPrizeType())){
                continue;
            }
            dtos.add(LotteryRecordDto.from(r));
        }
        model.addAttribute("lotteryRecords", dtos);
        return QUERY_PRIZE;
    }

    @RequestMapping(value = "addTickets", method = RequestMethod.POST)
    public String addTickets(HttpSession session, Integer blank, Integer special, Integer normal) {
        checkSA(session);
        ticketService.addTickets(defaultValue(blank), defaultValue(special), defaultValue(normal));
        return "redirect:managePrize";
    }

    @RequestMapping(value = "removeTickets", method = RequestMethod.POST)
    public String removeTickets(HttpSession session, Integer blank, Integer special, Integer normal) {
        checkSA(session);
        ticketService.removeTickets(defaultValue(blank), defaultValue(special), defaultValue(normal));
        return "redirect:managePrize";
    }

    @RequestMapping(value = "manageUser", method = RequestMethod.GET)
    public String manageUser(HttpSession session, ModelMap modelMap) {
        checkSA(session);
        List<User> users = userRepository.findAll();
        modelMap.addAttribute("users", users);
        return MANAGE_USER;
    }

    @RequestMapping(value = "removeUser",method = RequestMethod.GET)
    public String removeUser(String id, HttpSession session) {
        checkSA(session);
        userRepository.remove(id);
        return "redirect:manageUser";
    }

    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    public String addUser(UserInfoForm registerForm, HttpSession session) {
        checkSA(session);
        User user = registerForm.toUser();
        userRepository.save(user);
        return "redirect:manageUser";
    }

    private void checkSA(HttpSession session) {
        if (!"sa".equals(getCurrentUser(session).getUserName().toLowerCase())) {
            throw new RuntimeException("不具备管理员权限！");
        }
    }

    private int defaultValue(Integer i){
        if(i==null){
            return 0;
        }
        return i;
    }
}

