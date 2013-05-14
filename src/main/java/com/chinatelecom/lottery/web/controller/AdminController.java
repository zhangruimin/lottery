package com.chinatelecom.lottery.web.controller;

import com.chinatelecom.lottery.model.LotteryRecord;
import com.chinatelecom.lottery.model.TicketState;
import com.chinatelecom.lottery.repository.LotteryRepository;
import com.chinatelecom.lottery.service.TicketService;
import com.chinatelecom.lottery.web.dto.LotteryRecordDto;
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
    private final TicketService ticketService;
    private LotteryRepository lotteryRepository;

    @Autowired
    public AdminController(TicketService ticketService, LotteryRepository lotteryRepository) {
        this.ticketService = ticketService;
        this.lotteryRepository = lotteryRepository;
    }

    @RequestMapping(value = "managePrize", method = RequestMethod.GET)
    public String index(HttpSession session, ModelMap model) {
        checkSA(session);
        TicketState ticketState = ticketService.getTicketState();
        model.addAttribute("ticketState", ticketState);
        return MANAGE_PRIZE;
    }

    @RequestMapping(value = "queryPrize", method = RequestMethod.GET)
    public String queryPrize(HttpSession session, ModelMap model, String phone) {
        checkSA(session);
        List<LotteryRecord> results = new ArrayList<LotteryRecord>();
        if (phone == null || phone.trim().equals("")) {
            results = lotteryRepository.findAll();
        } else {
            results.add(lotteryRepository.findByPhone(phone));
        }
        List<LotteryRecordDto> dtos = new ArrayList<LotteryRecordDto>();
        for (LotteryRecord r : results) {
            dtos.add(LotteryRecordDto.from(r));
        }
        model.addAttribute("lotteryRecords", dtos);
        return QUERY_PRIZE;
    }

    @RequestMapping(value = "addTickets", method = RequestMethod.POST)
    public String addTickets(HttpSession session, int blank, int special, int normal) {
        checkSA(session);
        ticketService.addTickets(blank, special, normal);
        return "redirect:managePrize";
    }

    @RequestMapping(value = "removeTickets", method = RequestMethod.POST)
    public String removeTickets(HttpSession session, int blank, int special, int normal) {
        checkSA(session);
        ticketService.removeTickets(blank, special, normal);
        return "redirect:managePrize";
    }

    private void checkSA(HttpSession session) {
        if (!"sa".equals(getCurrentUser(session).getUserName().toLowerCase())) {
            throw new RuntimeException("不具备管理员权限！");
        }
    }
}

