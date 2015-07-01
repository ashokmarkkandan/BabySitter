package com.babysitter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
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
        try {
            Date startTime = intializeDate("06/30/2015 17:00:00");
            Date endTime = intializeDate("07/01/2015 03:00:00");
            Date bedTime = intializeDate("07/01/2015 01:00:00");
            
            babySitter.setStartTime(startTime);
            babySitter.setEndTime(endTime);
            babySitter.setBedTime(bedTime);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        
    }
    
    @Test
    public void whenValidDateRangeIsChecked() {
        Assert.assertEquals(true, impl.isValid(babySitter));
    }
    
    @Test
    public void whenProcessingPayIsChecked() {
        int amount = impl.processPay(babySitter);
        Assert.assertEquals(amount > 0, true);
    }
    
    private Date intializeDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return format.parse(dateString);
    }
}
