package com.CSC8404.Assesment;

import com.CSC8404.Assesment.Car.CarType;
import com.CSC8404.Assesment.Person.DrivingLicense;
import com.CSC8404.Assesment.Person.Person;
import com.CSC8404.Assesment.Rental.Rental;

import java.util.Calendar;

public class UseRental
{
    public static void main(String[] args)
    {
        Rental rt = new Rental();

        //rt.availableCars(CarType.SmallCar);

        Calendar cal = Calendar.getInstance();
        cal.set(1991, 8, 23);
        Person p = new Person("Randy", "Rodriguez", cal.getTime());

        Calendar issueDate = Calendar.getInstance();

        issueDate.set(2006, 10, 20);
        DrivingLicense dl = DrivingLicense.getInstance(p, issueDate.getTime(), true);

        rt.issueCar(p, dl, CarType.SmallCar);
        rt.issueCar(p, dl, CarType.LargeCar);

        rt.availableCars(CarType.SmallCar);
    }
}
