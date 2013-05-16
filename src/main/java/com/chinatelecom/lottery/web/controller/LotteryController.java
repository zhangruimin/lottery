package com.chinatelecom.lottery.web.controller;

import com.chinatelecom.lottery.model.LotteryRecord;
import com.chinatelecom.lottery.model.LotteryStatus;
import com.chinatelecom.lottery.model.PrizeType;
import com.chinatelecom.lottery.repository.LotteryRepository;
import com.chinatelecom.lottery.repository.LotteryStatusRepository;
import com.chinatelecom.lottery.service.TicketService;
import com.chinatelecom.lottery.web.dto.LotteryRecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/lottery")
public class LotteryController extends BaseController {
    private static final String LOTTERY = "lottery";
    private static final String LOTTERY_RESULT = "lottery_result";
    private static final String LOTTERY_ERROR = "lottery_error";
    private final TicketService ticketService;
    private LotteryRepository lotteryRepository;
    private LotteryStatusRepository lotteryStatusRepository;

    @Autowired
    public LotteryController(TicketService ticketService, LotteryRepository lotteryRepository,
                             LotteryStatusRepository lotteryStatusRepository) {
        this.ticketService = ticketService;
        this.lotteryRepository = lotteryRepository;
        this.lotteryStatusRepository = lotteryStatusRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
        setStatus(model);
        setExistedLotteries(model);
        return LOTTERY;
    }

    private void setStatus(ModelMap model) {
        LotteryStatus lotteryStatus = lotteryStatusRepository.findOne();
        if (lotteryStatus != null && !lotteryStatus.isOpened()) {
            model.addAttribute("status", "false");
        } else{
            model.addAttribute("status", "true");
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional(propagation = Propagation.SUPPORTS)
    public String lottery(ModelMap model, HttpSession session, String phone) {
        try {
            checkPhoneNumber(phone);
            LotteryRecord lottery = ticketService.lottery(phone, getCurrentUser(session));
            model.addAttribute("lottery", LotteryRecordDto.from(lottery));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return LOTTERY_ERROR;
        }
        setExistedLotteries(model);
        return LOTTERY_RESULT;
    }

    private void checkPhoneNumber(String phone) {
        if (!phone.startsWith("133") && !phone.startsWith("153")
                && !phone.startsWith("180") && !phone.startsWith("181") &&
                !phone.startsWith("189")) {
            throw new IllegalArgumentException("号段不正确，只有电信手机号才能参与抽奖！");
        }
    }

    private void setExistedLotteries(ModelMap model) {
        List<LotteryRecord> results = lotteryRepository.findByPrizeType(PrizeType.SPECIAL);
        List<LotteryRecordDto> dtos = new ArrayList<LotteryRecordDto>();
        for (LotteryRecord r : results) {
            LotteryRecordDto dto = LotteryRecordDto.from(r);
            dto.setPhoneNumber(dto.getPhoneNumber().substring(0, 3) + "XXXX" + dto.getPhoneNumber().substring(7));
            dtos.add(dto);
        }
        model.addAttribute("specialPrizes", dtos);
    }
}
