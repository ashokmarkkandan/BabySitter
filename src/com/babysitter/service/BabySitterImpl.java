package com.babysitter.service;

import com.babysitter.entity.BabySitter;

public class BabySitterImpl {
    
    public boolean processPay(BabySitter babySitter) {
        
        boolean ispayProcessed = false;
        
        if (babySitter.getStartTime() >= 17) {
            ispayProcessed = true;
        }
        
        return ispayProcessed;
    }
    
}
