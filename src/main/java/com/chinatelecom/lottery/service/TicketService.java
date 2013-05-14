package com.chinatelecom.lottery.service;

import com.chinatelecom.lottery.model.LotteryRecord;
import com.chinatelecom.lottery.model.TicketState;
import com.chinatelecom.lottery.model.User;
import com.chinatelecom.lottery.repository.LotteryRepository;
import com.chinatelecom.lottery.repository.TicketStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2/21/13
 * Time: 8:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class TicketService {
    private static Object lock = new Object();
    private final TicketStateRepository ticketStateRepository;
    private final LotteryRepository lotteryRepository;

    @Autowired
    public TicketService(TicketStateRepository ticketStateRepository, LotteryRepository lotteryRepository) {
        this.ticketStateRepository = ticketStateRepository;
        this.lotteryRepository = lotteryRepository;
    }

    public TicketState getTicketState() {
        TicketState state = ticketStateRepository.findOne();
        if (state == null) {
            state = new TicketState();
            ticketStateRepository.save(state);
        }
        return state;
    }


    @Transactional(propagation= Propagation.SUPPORTS)
    public void addTickets(int blank, int special, int normal) {
        synchronized (lock) {
            TicketState state = getTicketState();
            state.setBlankNotUsed(state.getBlankNotUsed() + blank);
            state.setNormalNotUsed(state.getNormalNotUsed() + normal);
            state.setSpecialNotUsed(state.getSpecialNotUsed() + special);
            ticketStateRepository.save(state);
        }
    }


    @Transactional(propagation= Propagation.SUPPORTS)
    public void removeTickets(int blank, int special, int normal) {
        synchronized (lock) {
            TicketState state = getTicketState();
            if (blank > state.getBlankNotUsed() || normal > state.getNormalNotUsed() || special > state.getSpecialNotUsed()) {
                throw new IllegalArgumentException("要移除的奖券数不能少于剩余奖券数");
            }
            state.setBlankNotUsed(state.getBlankNotUsed() - blank);
            state.setNormalNotUsed(state.getNormalNotUsed() - normal);
            state.setSpecialNotUsed(state.getSpecialNotUsed() - special);
            ticketStateRepository.save(state);
        }
    }

    @Transactional(propagation= Propagation.SUPPORTS)
    public LotteryRecord lottery(String phone, User currentUser) {
        synchronized (lock) {
            if (lotteryRepository.findByPhone(phone) != null) {
                throw new IllegalArgumentException("请勿重复抽奖!");
            }
            TicketState ticketState = getTicketState();

            LotteryRecord lottery = ticketState.lottery(phone);
            lottery.setUserName(currentUser.getUserName());
            lottery.setUserLocation(currentUser.getLocation());
            lotteryRepository.save(lottery);
            ticketStateRepository.save(ticketState);
            return lottery;
        }
    }
}
