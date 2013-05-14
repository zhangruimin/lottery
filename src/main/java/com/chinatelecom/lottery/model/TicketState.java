package com.chinatelecom.lottery.model;

import org.springframework.data.annotation.Id;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/13/13
 * Time: 9:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class TicketState {
    @Id
    private String id;
    private int blankUsed;
    private int specialUsed;
    private int normalUsed;
    private int blankNotUsed;
    private int specialNotUsed;
    private int normalNotUsed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBlankUsed() {
        return blankUsed;
    }

    public void setBlankUsed(int blankUsed) {
        this.blankUsed = blankUsed;
    }

    public int getSpecialUsed() {
        return specialUsed;
    }

    public void setSpecialUsed(int specialUsed) {
        this.specialUsed = specialUsed;
    }

    public int getNormalUsed() {
        return normalUsed;
    }

    public void setNormalUsed(int normalUsed) {
        this.normalUsed = normalUsed;
    }

    public int getBlankNotUsed() {
        return blankNotUsed;
    }

    public void setBlankNotUsed(int blankNotUsed) {
        this.blankNotUsed = blankNotUsed;
    }

    public int getSpecialNotUsed() {
        return specialNotUsed;
    }

    public void setSpecialNotUsed(int specialNotUsed) {
        this.specialNotUsed = specialNotUsed;
    }

    public int getNormalNotUsed() {
        return normalNotUsed;
    }

    public void setNormalNotUsed(int normalNotUsed) {
        this.normalNotUsed = normalNotUsed;
    }

    public LotteryRecord lottery(String phone) {
        LotteryRecord lotteryRecord = new LotteryRecord();
        lotteryRecord.setPhoneNumber(phone);
        Random random = new Random();

        int notUsed = getNotUsed();

        if (notUsed <= 0) {
            lotteryRecord.setPrizeType(PrizeType.BLANK);
            blankNotUsed = Math.max(0, blankNotUsed - 1);
            blankUsed++;
            return lotteryRecord;
        }

        int r = random.nextInt(notUsed);

        if (r >= 0 && r < getSpecialNotUsed()) {
            lotteryRecord.setPrizeType(PrizeType.SPECIAL);
            specialNotUsed--;
            specialUsed++;
        } else if (r >= getSpecialNotUsed() && r < getNormalNotUsed() + getSpecialNotUsed()) {
            lotteryRecord.setPrizeType(PrizeType.NORMAL);
            normalNotUsed--;
            normalUsed++;
        } else {
            lotteryRecord.setPrizeType(PrizeType.BLANK);
            blankNotUsed = Math.max(0, blankNotUsed - 1);
            blankUsed++;
        }
        return lotteryRecord;
    }

    private int getNotUsed() {
        return getNormalNotUsed() + getSpecialNotUsed() + getBlankNotUsed();
    }
}
