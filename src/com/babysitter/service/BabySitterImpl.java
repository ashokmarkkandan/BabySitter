package com.babysitter.service;

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
    
}
