package com.babysitter.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.babysitter.entity.BabySitter;

/**
 * Class to process the pay for a Babysitter based on the dates.
 *
 */
public class BabySitterImpl {
    /**
     * Method to validate the date range.
     * @param babySitter
     * @return boolean
     */
    public boolean isValid(BabySitter babySitter) {
        boolean isValid = false;
        
        if (babySitter.getStartTime() != null && babySitter.getEndTime() != null && babySitter.getBedTime() != null) {
            long diff = babySitter.getEndTime().getTime() - babySitter.getStartTime().getTime();
            long diffHours = diff / (60 * 60 * 1000) % 24;
            babySitter.setTotalHours(diffHours);
            long diffDays = diff / (24 * 60 * 60 * 1000);
            if (diffDays >= 1) {
                System.out.println("Invalid Date Range, should be one day difference - day  difference: " + diffDays);
                return false;
            }
            if (diffHours > 11 || diffHours < 0) {
                System.out.println("Invalid start Time,end time - hours difference: " + diffHours);
                return false;
            }
            isValid = true;
        } else {
            System.out.println("Invalid start Time,end time and bed time");
        }
        
        return isValid;
    }
    
    public int processPay(BabySitter babySitter) {
        int amount = 0;
        if (!isValid(babySitter)) {
            return 0;
        } else {
            amount = 1;
        }
        //split the hours to calculate bedtime hours,active hours and worked hours
        return amount;
    }
    
    public int calculateActiveHours(BabySitter babySitter) {
        int activeHours = 0;
        long diff = babySitter.getEndTime().getTime() - babySitter.getStartTime().getTime();
        long diffHours = diff / (60 * 60 * 1000) % 24;
        //Still tot be worked on.
        return activeHours;
    }
    
    /**
     * calculateSleepHours
     * @param babySitter
     * @return long
     */
    public long calculateSleepHours(BabySitter babySitter) {
        long sleepHours = 0;
        if (babySitter.getBedTime().after(babySitter.getEndTime())) {
            System.out.println(" Wrong data bedtime after endtime");
            return sleepHours;
        }
        long diff = babySitter.getEndTime().getTime() - babySitter.getStartTime().getTime();
        sleepHours = diff / (60 * 60 * 1000) % 24;
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
            System.out.println(" Wrong data startTime after endtime");
            return midNightHours;
        }
        Calendar calcDate = new GregorianCalendar();
        calcDate.setTime(babySitter.getEndTime());
        int endHour = calcDate.get(Calendar.HOUR_OF_DAY);
        calcDate.setTime(babySitter.getStartTime());
        int startHour = calcDate.get(Calendar.HOUR_OF_DAY);
        
        if (startHour <= 4) {
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
