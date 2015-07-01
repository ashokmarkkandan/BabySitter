package com.babysitter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.babysitter.entity.BabySitter;
import com.babysitter.service.BabySitterImpl;

/**
 * Junit test class to validate baby sitting hours -payment logic
 *
 */
public class TestBabySitter {
    
    BabySitter babySitter;
    BabySitterImpl impl;
    
    @Before
    public void setUp() {
        babySitter = new BabySitter();
        impl = new BabySitterImpl();
        
        Date startTime = intializeDate("06/30/2015 17:00:00");
        Date bedTime = intializeDate("07/01/2015 01:00:00");
        Date endTime = intializeDate("07/01/2015 03:00:00");
        
        babySitter.setStartTime(startTime);
        babySitter.setEndTime(endTime);
        babySitter.setBedTime(bedTime);
        
    }
    
    /**
     * Validating DateRange for the processing date
     */
    @Test
    public void whenValidDateRangeIsChecked() {
        Assert.assertEquals(true, impl.isValid(babySitter));
    }
    
    /**
     * Validating payment processing logic
     */
    @Test
    public void whenProcessingPayIsChecked() {
        long amount = impl.processPay(babySitter);
        Assert.assertEquals(amount == 132, true);
        // setting bedtime as 6pm  
        babySitter.setBedTime(intializeDate("06/30/2015 18:00:00"));
        amount = impl.processPay(babySitter);
        Assert.assertEquals(amount == 108, true);
        //setting endtime before midnight 11pm
        babySitter.setEndTime(intializeDate("06/30/2015 23:00:00"));
        amount = impl.processPay(babySitter);
        Assert.assertEquals(amount == 52, true);
        //baby sitter went with in an hour so no payment for it.
        babySitter.setBedTime(intializeDate("06/30/2015 17:15:00"));
        babySitter.setEndTime(intializeDate("06/30/2015 17:30:00"));
        amount = impl.processPay(babySitter);
        Assert.assertEquals(amount == 0, true);
        //setting bedtime to empty, the baby didnt go to sleep.
        babySitter.setBedTime(null);
        babySitter.setEndTime(intializeDate("06/30/2015 18:30:00"));
        amount = impl.processPay(babySitter);
        Assert.assertEquals(amount == 12, true);
        // with bedtime null - checking for active and midnight hours.
        babySitter.setEndTime(intializeDate("07/01/2015 04:00:00"));
        amount = impl.processPay(babySitter);
        Assert.assertEquals(amount == 148, true);
        // setting start time as 4pm, then no payment processed
        babySitter.setStartTime(intializeDate("06/30/2015 15:00:00"));
        babySitter.setEndTime(intializeDate("07/01/2015 01:00:00"));
        amount = impl.processPay(babySitter);
        Assert.assertEquals(amount == 0, true);
        //verifying whether endTime can be more than 4am
        babySitter.setStartTime(intializeDate("06/30/2015 15:00:00"));
        babySitter.setEndTime(intializeDate("07/01/2015 05:00:00"));
        amount = impl.processPay(babySitter);
        Assert.assertEquals(amount == 0, true);
        
    }
    
    /**
     * Validating sleephours
     */
    @Test
    public void WhenProcessingSleepHours() {
        long sleepHours = impl.calculateSleepHours(babySitter);
        Assert.assertEquals(sleepHours >= 0 && sleepHours <= 11, true);
        babySitter.setBedTime(intializeDate("07/01/2015 05:00:00"));
        sleepHours = impl.calculateSleepHours(babySitter);
        Assert.assertEquals(sleepHours == 0, true);
        
    }
    
    /**
     * validating midnight hours
     */
    @Test
    public void WhenProcessingMidNightHours() {
        long midNightHours = impl.calculatePostMidnightHours(babySitter);
        Assert.assertEquals(midNightHours == 3, true);
    }
    
    /**
     * validating total babysitting hours
     */
    @Test
    public void WhenProcessingTotalHours() {
        long totalHours = impl.calculateTotalHours(babySitter);
        Assert.assertEquals(totalHours == 10, true);
    }
    
    /**
     * Method to help in initializing dates with our desired time.
     * @param dateString
     * @return Date
     */
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
