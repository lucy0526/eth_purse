package com.example.administrator.purseui1.entity;

/**
 * Created by Administrator on 2018/11/26.
 */

public class History {
    private String time;
    private String from;
    private String to;
    private String num;

    public History() {
    }

    public History(String time, String from, String to, String num) {
        this.time = time;
        this.from = from;
        this.to = to;
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
