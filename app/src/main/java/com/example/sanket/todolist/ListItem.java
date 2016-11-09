package com.example.sanket.todolist;

import java.util.Date;

/**
 * Created by Happy on 11/5/2016.
 */
public class ListItem {

    private long item_id;
    private String title;
    private String detail;
    private int ispassword;
    private String password;
    private String last_update_date;

    public ListItem(String title,String detail,String last_update_date){
        this.item_id = new Date().getTime();
        this.title = title;
        this.detail = detail;
        this.ispassword = 0;
        this.password = "";
        this.last_update_date = last_update_date;
    }

    public ListItem() {

    }

    public String getLast_update_date() {
        return last_update_date;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public int ispassword() {
        return ispassword;
    }

    public String getPassword() {
        return password;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setIspassword(int ispassword) {
        this.ispassword = ispassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.last_update_date = lastUpdateDate;
    }
}
