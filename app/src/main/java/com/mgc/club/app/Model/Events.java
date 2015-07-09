package com.mgc.club.app.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by savva on 07.07.2015.
 */
public class Events implements Serializable {
    private int id;
    private String name;
    private String text;
    private Date start;
    private Date finish;
    private String eventcover;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public String getEventcover() {
        return eventcover;
    }

    public void setEventcover(String eventcover) {
        this.eventcover = eventcover;
    }
}
