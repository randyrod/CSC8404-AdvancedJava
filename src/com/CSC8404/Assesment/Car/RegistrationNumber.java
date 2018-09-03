package com.CSC8404.Assesment.Car;

import java.util.HashMap;
import java.util.Random;

public final class RegistrationNumber
{
    private String registrationComponentOne;

    private String registrationComponentTwo;

    private static final String componentOnePattern = "^[A-Z]{2}[0-9]{2}$";
    private static final String componentTwoPattern = "^[A-Z]{3}$";

    private static final HashMap<String, RegistrationNumber> registrationsHashMap = new HashMap<>();

    RegistrationNumber(String componentOne, String componentTwo)
    {
        this.registrationComponentOne = componentOne;
        this.registrationComponentTwo = componentTwo;
    }

    /***
     * Get the first portion of the registration number
     * @return a string containing the first component of the registration number
     */
    public String getRegistrationComponentOne()
    {
        return registrationComponentOne;
    }

    /***
     * Get the second portion of the registration number
     * @return a string containing the second component of the registration number
     */
    public String getRegistrationComponentTwo()
    {
        return registrationComponentTwo;
    }

    /***
     * Get the two components of a registration number in one string
     * @return
     * Combined registration components separated by a space
     */
    public String getFullRegistrationNumber()
    {
        return registrationComponentOne + " " + registrationComponentTwo;
    }


    /***
     * Get an existing instance of a car's registration number, or create a new one
     * @param componentOne First portion of the license example: AA12
     * @param componentTwo Second portion of the license example: ABC
     * @return registration number if the components match the pattern.
     * @throws IllegalArgumentException if the components do not match the pattern
     */
    public static RegistrationNumber getInstance(String componentOne, String componentTwo)
    {
        if(componentOne == null || componentTwo == null
                || componentOne.equals("") || componentTwo.equals("")
                || !componentOne.matches(componentOnePattern)
                || !componentTwo.matches(componentTwoPattern))
        {
            throw new IllegalArgumentException("The components do not match the pattern");
        }

        if(!registrationsHashMap.containsKey(componentOne + componentTwo))
        {
            registrationsHashMap.put(componentOne + componentTwo, new RegistrationNumber(componentOne, componentTwo));
        }

        return registrationsHashMap.get(componentOne + componentTwo);

    }

    @Override
    public boolean equals(Object rhs) {
        if(this == rhs) return  true;

        if(!(rhs instanceof RegistrationNumber)) return false;

        RegistrationNumber rNumber = (RegistrationNumber) rhs;

        return (registrationComponentOne == null ?
                    rNumber.registrationComponentOne == null
                    : registrationComponentOne.equals(rNumber.registrationComponentOne))
                && (registrationComponentTwo == null ?
                    rNumber.registrationComponentTwo == null
                    : registrationComponentTwo.equals(rNumber.registrationComponentTwo));
    }

    @Override
    public int hashCode() {
        final int multiplier = 37;
        int hc = 17;
        hc = (multiplier * hc) + (registrationComponentOne == null ? 0 : registrationComponentOne.hashCode());

        hc = (multiplier * hc) + (registrationComponentTwo == null ? 0 : registrationComponentTwo.hashCode());

        return hc;
    }
}
