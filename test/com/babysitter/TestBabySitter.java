package com.babysitter;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.babysitter.entity.BabySitter;
import com.babysitter.service.BabySitterImpl;

public class TestBabySitter {
    
    BabySitter babySitter;
    BabySitterImpl impl;
    
    @Before
    public void setUp() {
        babySitter = new BabySitter();
        impl = new BabySitterImpl();
    }
    
    @Test
    public void whenTheStartTimeIsOverFivePM() {
        
        Date startTime = initializeTimings(2015, 05, 15, 17, 00, 00);
        Date endTime = initializeTimings(2015, 05, 16, 04, 00, 00);
        Date bedTime = initializeTimings(2015, 05, 15, 20, 00, 00);
        babySitter.setStartTime(startTime);
        
    }
    
    @Test
    public void whenTheEndTimeIsBeforeFourAm() {
        
    }
    
    private Date initializeTimings(int year, int month, int date, int hours, int mins, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month, date, hours, mins, seconds);
        return cal.getTime();
    }
}
