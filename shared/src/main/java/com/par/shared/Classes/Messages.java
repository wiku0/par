package com.par.shared.Classes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Messages implements Serializable {
    Date date = Calendar.getInstance().getTime();
    String msg;
    User user;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
