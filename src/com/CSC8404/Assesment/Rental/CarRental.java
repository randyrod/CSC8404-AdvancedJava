package com.CSC8404.Assesment.Rental;

import com.CSC8404.Assesment.Car.Car;
import com.CSC8404.Assesment.Car.CarType;
import com.CSC8404.Assesment.Person.DrivingLicense;
import com.CSC8404.Assesment.Person.Person;

import java.util.List;

public interface CarRental
{

    long availableCars(CarType carType);

    Car getCar(Person person);

    List<Car> getRentedCars();

    Car issueCar(Person person, DrivingLicense drivingLicense, CarType carType);

    int terminateRental(Person person);

}
