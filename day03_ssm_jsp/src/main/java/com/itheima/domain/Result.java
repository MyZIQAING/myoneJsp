package com.itheima.domain;

import java.io.Serializable;

public class Result implements Serializable {
    private Boolean biaoZhi;
    private String excetion;
    private Object date;

    public Result() {
    }

    public Result(Boolean biaoZhi, String excetion, Object date) {
        this.biaoZhi = biaoZhi;
        this.excetion = excetion;
        this.date = date;
    }

    public Result(Boolean biaoZhi, Object date) {
        this.biaoZhi = biaoZhi;
        this.date = date;
        this.excetion=null;
    }

    public Result(Boolean biaoZhi, String excetion) {

        this.biaoZhi = biaoZhi;
        this.excetion = excetion;
        this.date=null;
    }

    public Boolean getBiaoZhi() {
        return biaoZhi;
    }

    public void setBiaoZhi(Boolean biaoZhi) {
        this.biaoZhi = biaoZhi;
    }

    public String getExcetion() {
        return excetion;
    }

    public void setExcetion(String excetion) {
        this.excetion = excetion;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Result{" +
                "biaoZhi=" + biaoZhi +
                ", excetion='" + excetion + '\'' +
                ", date=" + date +
                '}';
    }
}
