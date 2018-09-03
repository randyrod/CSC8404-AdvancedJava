package com.CSC8404.Assesment.Person;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DrivingLicenseTest {

    DrivingLicense drivingLicense;
    Date issueDate;

    @Before
    public void setUp() throws Exception {
        Calendar tempDate = Calendar.getInstance();
        tempDate.set(1991, 8, 23);

        Person p = new Person("Randy", "Collado", tempDate.getTime());

        tempDate.set(2008, 8, 10);
        issueDate = tempDate.getTime();
        drivingLicense = DrivingLicense.getInstance(p, issueDate, true);
    }

    @Test
    public void getIssueDate() throws Exception {
        assertEquals(issueDate, drivingLicense.getIssueDate());
    }

    @Test
    public void getIsFullLicense() throws Exception {
        assertTrue(drivingLicense.getIsFullLicense());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInstance() throws Exception {

        DrivingLicense.getInstance(new Person(), issueDate, false);
    }

}