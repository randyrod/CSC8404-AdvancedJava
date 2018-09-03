package com.CSC8404.Assesment.Car;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegistrationNumberTest {

    public RegistrationNumber registrationNumberOne;
    public RegistrationNumber registrationNumberTwo;
    @Before
    public void setUp() throws Exception {
        registrationNumberOne = RegistrationNumber.getInstance("AA12", "ABC");
        registrationNumberTwo = RegistrationNumber.getInstance("AA12", "ABC");
    }

    @Test
    public void getRegistrationComponentOne() throws Exception {
        assertEquals("AA12", registrationNumberOne.getRegistrationComponentOne());
    }

    @Test
    public void getRegistrationComponentTwo() throws Exception {
        assertEquals("ABC", registrationNumberOne.getRegistrationComponentTwo());
    }

    @Test
    public void getFullRegistrationNumber() throws Exception {
        assertEquals("AA12 ABC", registrationNumberOne.getFullRegistrationNumber());
    }


    @Test(expected = IllegalArgumentException.class)
    public void getInstance() throws Exception {
        registrationNumberTwo = RegistrationNumber.getInstance("1234", "AAA");
    }
}