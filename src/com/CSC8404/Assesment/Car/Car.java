package com.CSC8404.Assesment.Car;

public interface Car
{
    int addFuel(int fuelLitters);

    RegistrationNumber getRegistrationNumber();

    int getFuelLittersCapacity();

    boolean getIsFuelTankFull();

    int driveCar(int kilometers);

    int getCurrentFuelLevel();

    void setCurrentFuelLevel(int fuelLevel);

    boolean getIsCarDrivable();

    boolean getIsRented();

    void setIsRented(boolean isRented);

    void setLittersCapacity(int capacity);
}
