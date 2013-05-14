package com.chinatelecom.lottery.web.form;

import com.chinatelecom.lottery.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/18/13
 * Time: 8:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserInfoForm {
    private String userName;
    private String password;
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toUser(){
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setLocation(location);
        return user;
    }
}
