package com.CSC8404.Assesment.Person;

import java.util.Calendar;
import java.util.Date;

public class Person {

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    public Person()
    {
        firstName = "";
        lastName = "";
        dateOfBirth = Calendar.getInstance().getTime();
    }

    public Person(String firstName, String lastName, Date dateOfBirth)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    /***
     * Set the first name of the person for the current object
     * @param firstName first name to be added
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /***
     * Set the last name of the person for the current object
     * @param lastName last name to be added
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /***
     * Set the birth date of the person for the current object
     * @param dateOfBirth date object with the date of birth of the person
     */
    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    /***
     * get the first name of the person
     * @return string with the first name of the person
     */
    public String getFirstName()
    {
        return firstName;
    }

    /***
     * get the last name of the person
     * @return string with the last name of the person
     */
    public String getLastName()
    {
        return lastName;
    }

    /***
     * get the date of birth of the person
     * @return date object with the date of birth of the person
     */
    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    /***
     * Function to get the full name of a Person
     * @return
     * Returns the firstname and lastname of a person separated by a space ' '
     */
    public String getFullName()
    {
        return firstName + " " + lastName;
    }

    /***
     * @return
     * Returns the Person first name, last name and date of birth each one separated by a '-'
     * Example format
     * firstName-lastName-dateOfBirth
     */
    @Override
    public String toString() {
        return firstName + "-" + lastName + "-" + dateOfBirth;
    }

    @Override
    public int hashCode()
    {
        final int multiplier = 37;
        int hc = 17;

        hc = (multiplier * hc) + ((firstName == null) ? 0 : firstName.hashCode());

        hc = (multiplier * hc) + ((lastName == null) ? 0 : lastName.hashCode());

        return (multiplier * hc) + dateOfBirth.hashCode();
    }

    @Override
    public boolean equals(Object rhs) {

        if(this == rhs) return true;

        if(!(rhs instanceof Person)) return false;

        Person p = (Person)rhs;

        return dateOfBirth.equals(p.dateOfBirth)
                && (firstName == null
                    ? p.firstName == null
                    : firstName.equals(p.firstName))
                && (lastName == null
                    ? p.lastName == null
                    : lastName.equals(p.lastName));
    }
}
