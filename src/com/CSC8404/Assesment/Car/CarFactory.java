package com.CSC8404.Assesment.Car;

import java.util.HashMap;

public abstract class CarFactory implements Car
{
    private RegistrationNumber registrationNumber;

    private int littersCapacity;

    private boolean isRented;

    private int currentFuelLitters;

    private static HashMap<String, Car> cars = new HashMap<>();

    CarFactory(RegistrationNumber registrationNumber)
    {
        this.registrationNumber = registrationNumber;
    }

    /***
     * Get a unique instance of a Car from the type of car and the provided registration number
     * @param carType type of the car that the instance should be created for
     * @param registrationNumber Unique registration number of the car
     * @return Instance of a new or existing unique car
     */
    public static Car getInstance(CarType carType, RegistrationNumber registrationNumber)
    {
        Car car = cars.get(registrationNumber);

        if(car == null)
        {
            switch (carType)
            {
                case SmallCar:
                    car = new SmallCar(registrationNumber);
                    break;
                case LargeCar:
                    car = new LargeCar(registrationNumber);
                    break;
            }
            cars.put(registrationNumber.getFullRegistrationNumber(), car);
        }

        return car;
    }


    /***
     * Determine wether a car is already rented out
     * @return true if the car is currently rented to someone
     */
    public boolean getIsRented()
    {
        return isRented;
    }

    /***
     * Update the state of the car if it is rented out or not
     * @param isRented true if the car has been rented
     */
    public void setIsRented(boolean isRented)
    {
        this.isRented = isRented;
    }

    /***
     * Add a given number of whole litres to the fuel tank (up to the tank's capacity)
     * @param litters amount of litters to be added to the cars fuel tank
     * @return amount of litters there were possible to be added to the fuel tank of the car
     */
    public int addFuel(int litters)
    {
        if (litters < 0) return 0;

        if((litters + currentFuelLitters) > littersCapacity)
        {
            int addedLitters = littersCapacity - currentFuelLitters;
            currentFuelLitters = littersCapacity;
            return addedLitters;
        }
        else
        {
            currentFuelLitters += litters;
            return litters;
        }
    }

    /***
     * Get the current car's registration number
     * @return registration number object assigned to the car
     */
    public RegistrationNumber getRegistrationNumber()
    {
        return registrationNumber;
    }

    /***
     * Determine if the fuel tank of the car is full
     * @return true if the tank is full, false if it is not
     */
    public boolean getIsFuelTankFull()
    {
        return currentFuelLitters == littersCapacity;
    }

    /***
     * Get the total capacity of the car's tank
     * @return total capacity of the tank of the car
     */
    public int getFuelLittersCapacity()
    {
        return littersCapacity;
    }

    /***
     * Get the current level of fuel that is in the car's tank
     * @return The current fuel level of the car
     */
    public int getCurrentFuelLevel()
    {
        return currentFuelLitters;
    }

    /***
     * Determines whether the car is able to be driven based on the following criteria
     * The car must be rented before it can be driven
     * The car must not have 0 or less litter of fuel
     * @return true if the car can be driven, false if the car cannot be driven
     */
    public boolean getIsCarDrivable() {
        return isRented && (currentFuelLitters > 0);
    }

    /***
     * Set the current fuel level of the car
     * @param fuelLevel fuel level that is going to be set to the car's fuel tank
     */
    public void setCurrentFuelLevel(int fuelLevel) {
        currentFuelLitters = fuelLevel;
    }

    /***
     * Set the total capacity that the car's fuel tank can reach
     * @param littersCapacity total capacity of the car's tank
     */
    public void setLittersCapacity(int littersCapacity) {
        this.littersCapacity = littersCapacity;
    }
}
