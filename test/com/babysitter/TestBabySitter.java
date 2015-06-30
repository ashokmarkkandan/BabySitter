package com.babysitter;

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
    }
    
    @Test
    public void whenTheStartTimeIsOverFivePM() {
        
        babySitter.setStartTime(17);
        Assert.assertEquals(true, impl.processPay(babySitter));
    }
}
