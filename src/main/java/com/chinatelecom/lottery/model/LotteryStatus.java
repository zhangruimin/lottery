package com.chinatelecom.lottery.model;

import org.springframework.data.annotation.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/13/13
 * Time: 9:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class LotteryStatus {
    @Id
    private String id;
    private boolean opened = true;
    private boolean allowRegister = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public boolean isAllowRegister() {
        return allowRegister;
    }

    public void setAllowRegister(boolean allowRegister) {
        this.allowRegister = allowRegister;
    }
}
