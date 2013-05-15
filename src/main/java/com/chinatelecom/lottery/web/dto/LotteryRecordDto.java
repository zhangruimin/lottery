package com.chinatelecom.lottery.web.dto;

import com.chinatelecom.lottery.model.LotteryRecord;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2/28/13
 * Time: 10:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class LotteryRecordDto {
    private String phoneNumber;
    private String prize;
    private String prizeState;
    private String userName;
    private String userLocation;
    private Date timestamp;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getPrizeState() {
        return prizeState;
    }

    public void setPrizeState(String prizeState) {
        this.prizeState = prizeState;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public static LotteryRecordDto from(LotteryRecord record) {
        LotteryRecordDto dto = new LotteryRecordDto();
        dto.phoneNumber = record.getPhoneNumber();
        switch (record.getPrizeType()) {
            case BLANK:
                dto.prize = "谢谢参与！";
                dto.prizeState="未中奖";
                break;
            case SPECIAL:
                dto.prize = "特等奖！";
                dto.prizeState="特等奖";
                break;
            case NORMAL:
                dto.prize = "纪念奖！";
                dto.prizeState="纪念奖";
                break;
        }
        dto.userLocation = record.getUserLocation();
        dto.userName = record.getUserName();
        dto.timestamp = record.getTimestamp();
        return dto;
    }
}
