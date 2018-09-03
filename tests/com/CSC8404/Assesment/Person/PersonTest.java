package com.CSC8404.Assesment.Person;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class PersonTest {

    Person person;
    Date dob;
    @Before
    public void setUp() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(1991, 8, 23);
        dob = cal.getTime();
        person = new Person("Randy", "Collado", dob);
    }

    @Test
    public void getFirstName() throws Exception {
        assertEquals("Randy", person.getFirstName());
    }

    @Test
    public void getLastName() throws Exception {
        assertEquals("Collado", person.getLastName());
    }

    @Test
    public void getDateOfBirth() throws Exception {
        assertEquals(dob, person.getDateOfBirth());
    }

    @Test
    public void getFullName() throws Exception {
        assertEquals("Randy Collado", person.getFullName());
    }

}