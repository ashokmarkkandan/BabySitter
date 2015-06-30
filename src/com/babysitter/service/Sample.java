package com.babysitter.service;

import java.util.Calendar;
import java.util.Date;

public class Sample {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        /* Date date = new Date();
         SimpleDateFormat sdf = new SimpleDateFormat(pattern, formatSymbols)
         
         System.out.println(date);*/
        
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2015, 05, 15, 17, 00, 00);
        Date utilDate = cal.getTime();
        System.out.println(utilDate);
        System.out.println(cal.get(Calendar.HOUR_OF_DAY));
        
        System.out.println("testing github commits");
        System.out.println("deleting");
        System.out.println("deleting");
    }
    
}
