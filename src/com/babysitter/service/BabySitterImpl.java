package com.babysitter.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.babysitter.entity.BabySitter;

/**
 * Class to process the pay for a Babysitter based on the dates.
 *
 */
public class BabySitterImpl {
    
    public static final int START_TO_BED_RATE = 12;
    public static final int MIDNIGHT_RATE = 16;
    public static final int BED_TO_MIDNIGHT_RATE = 8;
    
    /**
     * Method to validate the date range.
     * @param babySitter
     * @return boolean
     */
    public boolean isValid(BabySitter babySitter) {
        boolean isValid = false;
        
        if (babySitter.getStartTime() != null && babySitter.getEndTime() != null) { // && babySitter.getBedTime() != null) {
            long diff = babySitter.getEndTime().getTime() - babySitter.getStartTime().getTime();
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            if (diffDays >= 1) {
                System.out.println("Invalid Date Range, should be one day difference - day  difference: " + diffDays);
                return false;
            }
            if (diffHours > 11 || diffHours < 0) {
                System.out.println("Invalid start Time,end time - hours difference: " + diffHours);
                return false;
            }
            
            Calendar calcDate = new GregorianCalendar();
            calcDate.setTime(babySitter.getStartTime());
            int startHour = calcDate.get(Calendar.HOUR_OF_DAY);
            if (startHour < 17) {
                System.out.println("start time cannot be before 5pm");
                return false;
            }
            calcDate.setTime(babySitter.getEndTime());
            int endHour = calcDate.get(Calendar.HOUR_OF_DAY);
            if (endHour > 4 && endHour < 17) {
                System.out.println("End time cannot be after 4am");
                return false;
            }
            isValid = true;
        } else {
            System.out.println("Invalid start Time,end time and bed time");
        }
        
        return isValid;
    }
    
    public long processPay(BabySitter babySitter) {
        long amount = 0;
        if (!isValid(babySitter)) {
            return 0;
        }
        
        long totalHours = calculateTotalHours(babySitter);
        long sleepHours = calculateSleepHours(babySitter);
        long midnightHours = calculatePostMidnightHours(babySitter);
        
        amount = midnightHours * MIDNIGHT_RATE;
        long midNightSleepHours = sleepHours - midnightHours;
        long activeNonMidnightHours = totalHours - midnightHours;
        
        if (midNightSleepHours > 0) {
            amount += (midNightSleepHours) * BED_TO_MIDNIGHT_RATE;
            activeNonMidnightHours -= midNightSleepHours;
        }
        if (activeNonMidnightHours > 0) {
            amount += (activeNonMidnightHours) * START_TO_BED_RATE;
        }
        return amount;
    }
    
    /**
     * Calculating Active hours
     * @param babySitter
     * @return long
     */
    public long calculateTotalHours(BabySitter babySitter) {
        
        if (babySitter.getStartTime().after(babySitter.getEndTime())) {
            System.out.println(" Wrong data : startTime after endtime");
            return 0;
        }
        long diff = babySitter.getEndTime().getTime() - babySitter.getStartTime().getTime();
        return diff / (60 * 60 * 1000) % 24;
    }
    
    /**
     * calculateSleepHours
     * @param babySitter
     * @return long
     */
    public long calculateSleepHours(BabySitter babySitter) {
        long sleepHours = 0;
        if (babySitter.getBedTime() != null) {
            if (babySitter.getBedTime().after(babySitter.getEndTime())) {
                System.out.println(" Wrong data: bedtime after endtime");
                return sleepHours;
            }
            long diff = babySitter.getEndTime().getTime() - babySitter.getBedTime().getTime();
            sleepHours = diff / (60 * 60 * 1000) % 24;
        }
        return sleepHours;
    }
    
    /**
     * CalculatePostMidnightHours
     * @param babySitter
     * @return long
     */
    public long calculatePostMidnightHours(BabySitter babySitter) {
        long midNightHours = 0;
        if (babySitter.getStartTime().after(babySitter.getEndTime())) {
            System.out.println(" Wrong data: startTime after endtime");
            return midNightHours;
        }
        Calendar calcDate = new GregorianCalendar();
        calcDate.setTime(babySitter.getEndTime());
        int endHour = calcDate.get(Calendar.HOUR_OF_DAY);
        calcDate.setTime(babySitter.getStartTime());
        int startHour = calcDate.get(Calendar.HOUR_OF_DAY);
        if (endHour > 4) {
            return 0;
        }
        else if (startHour <= 4) {
            midNightHours = endHour - startHour;
        } else {
            midNightHours = endHour;
        }
        
        System.out.println("startHour:" + startHour);
        System.out.println("endHour:" + endHour);
        System.out.println("midnightHours:" + midNightHours);
        
        return midNightHours;
    }
    
}
