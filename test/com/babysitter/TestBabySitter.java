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
        
        Date startTime = intializeDate("06/30/2015 17:00:00");
        Date endTime = intializeDate("07/01/2015 03:00:00");
        Date bedTime = intializeDate("07/01/2015 01:00:00");
        
        babySitter.setStartTime(startTime);
        babySitter.setEndTime(endTime);
        babySitter.setBedTime(bedTime);
        
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
    
    @Test
    public void WhenProcessingSleepHours() {
        long sleepHours = impl.calculateSleepHours(babySitter);
        Assert.assertEquals(sleepHours >= 0 && sleepHours <= 11, true);
        babySitter.setBedTime(intializeDate("07/01/2015 05:00:00"));
        sleepHours = impl.calculateSleepHours(babySitter);
        Assert.assertEquals(sleepHours == 0, true);
        
    }
    
    @Test
    public void WhenProcessingMidNightHours() {
        long midNightHours = impl.calculatePostMidnightHours(babySitter);
        Assert.assertEquals(midNightHours == 3, true);
    }
    
    private Date intializeDate(String dateString) {
        Date parseDate = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            parseDate = format.parse(dateString);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return parseDate;
    }
}
