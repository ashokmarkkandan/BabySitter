package com.babysitter.entity;

import java.util.Date;

public class BabySitter {
    
    public Date startTime;
    
    public Date endTime;
    
    public Date bedTime;
    
    public Date getBedTime() {
        return bedTime;
    }
    
    public void setBedTime(Date bedTime) {
        this.bedTime = bedTime;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
}
