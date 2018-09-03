package com.CSC8404.Assesment.Rental;

import com.CSC8404.Assesment.Car.*;
import com.CSC8404.Assesment.Person.DrivingLicense;
import com.CSC8404.Assesment.Person.Person;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Optional;
import java.util.Date;

public class Rental implements CarRental
{
    private HashMap<Person, Car> rentedCars = new HashMap<>();

    private ArrayList<Car> availableCars = new ArrayList<>();

    private final int LargeCarRequiredAge = 25;
    private final int SmallCarRequiredAge = 20;
    private final int LargeCarLicenseYears = 5;
    private final int SmallCarLicenseYears = 1;

    public Rental() {
        Initialize();
    }

    public long availableCars(CarType carType) {

        long result = 0;
        
        switch (carType)
        {
            case SmallCar:
                result = availableCars.stream().filter(c -> !c.getIsRented() && c instanceof SmallCar)
                        .count();
                break;
            case LargeCar:
                result =  availableCars.stream().filter(c -> !c.getIsRented() && c instanceof LargeCar)
                        .count();
                break;
        }

        return result;
    }

    private void Initialize()
    {
        for(int i = 10; i < 40; i++)
        {

            String registrationComponentOne = "AB" + i;
            String registrationComponentTwo = "ABC";

            RegistrationNumber reg = RegistrationNumber.getInstance(registrationComponentOne, registrationComponentTwo);;
            if(i < 30)
            {
                availableCars.add(CarFactory.getInstance(CarType.SmallCar, reg));
            }
            else
            {
                availableCars.add(CarFactory.getInstance(CarType.LargeCar, reg));
            }



        }

        for(int i = 30; i < 40; i++)
        {
            String registrationComponentOne = "AB" + i;
            String registrationComponentTwo = "ABC";
        }
    }

    public Car getCar(Person person) {
        return rentedCars.get(person);
    }

    public ArrayList<Car> getRentedCars() {
        ArrayList<Car> result = new ArrayList<>();

        availableCars.forEach(x -> result.add(x));

        return result;
    }

    public Car issueCar(Person lessor, DrivingLicense drivingLicense, CarType carType) {
        if(lessor == null || drivingLicense == null)
        {
            throw new IllegalArgumentException("Invalid arguments provided for Person or DrivingLicense");
        }

        if(!isPersonEligibleToRent(lessor, drivingLicense, carType))
        {
            throw new UnsupportedOperationException("Person is not eligible to rent");
        }

        Optional<Car> availableCar = null;

        switch (carType)
        {
            case SmallCar:
                availableCar = availableCars.stream().filter(x -> !x.getIsRented() && x instanceof SmallCar).findFirst();
                break;
            case LargeCar:
                availableCar = availableCars.stream().filter(x -> !x.getIsRented() && x instanceof LargeCar).findFirst();
                break;
        }

        if(availableCar == null || !availableCar.isPresent())
        {
            throw new UnsupportedOperationException("There are no available cars");
        }

        Car rentedCar = availableCar.get();

        rentedCar.setIsRented(true);

        rentedCar.setCurrentFuelLevel(rentedCar.getFuelLittersCapacity());

        rentedCars.put(lessor, rentedCar);

        return rentedCar;
    }

    public int terminateRental(Person lessor) {

        if(lessor == null)
        {
            throw new IllegalArgumentException();
        }

        Car c = rentedCars.get(lessor);

        if(c == null)
        {
            throw new UnsupportedOperationException("There is no record matching the provided Person");
        }

        rentedCars.remove(lessor);

        c.setIsRented(false);

        return (c.getCurrentFuelLevel() <= 0) ? c.getFuelLittersCapacity()
                : (c.getFuelLittersCapacity() - c.getCurrentFuelLevel());
    }

    private boolean isPersonEligibleToRent(Person lessor, DrivingLicense license, CarType carType)
    {
        if(lessor == null)
        {
            throw new IllegalArgumentException();
        }

        int requiredAge = 0, requiredLicenseYears = 0;

        switch (carType)
        {
            case LargeCar:
                requiredAge = LargeCarRequiredAge;
                requiredLicenseYears = LargeCarLicenseYears;
                break;
            case SmallCar:
                requiredAge = SmallCarRequiredAge;
                requiredLicenseYears = SmallCarLicenseYears;
                break;
        }

        int lessorAge = calculateDateYears(lessor.getDateOfBirth());
        int licenseAge = calculateDateYears(license.getIssueDate());

        return lessorAge >= requiredAge
                && (license.getIsFullLicense() && licenseAge >= requiredLicenseYears)
                && !rentedCars.containsKey(lessor);

    }

    private int calculateDateYears(Date date)
    {
        Calendar dob = Calendar.getInstance();
        dob.setTime(date);

        Calendar currentDate = Calendar.getInstance();

        int diff = currentDate.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if(dob.get(Calendar.MONTH) > currentDate.get(Calendar.MONTH)
                || (dob.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH)
                    && dob.get(Calendar.DATE) > currentDate.get(Calendar.DATE)))
        {
            diff--;
        }

        return diff;
    }
}
