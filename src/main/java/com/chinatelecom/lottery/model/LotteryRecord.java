package com.chinatelecom.lottery.model;

import org.springframework.data.annotation.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/13/13
 * Time: 9:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class LotteryRecord {
    @Id
    private String id;
    private String phoneNumber;
    private PrizeType prizeType;
    private String userName;
    private String userLocation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PrizeType getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(PrizeType prizeType) {
        this.prizeType = prizeType;
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
}
