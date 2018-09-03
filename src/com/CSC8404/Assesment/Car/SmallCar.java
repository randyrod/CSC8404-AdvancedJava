package com.CSC8404.Assesment.Car;

public class SmallCar extends CarFactory
{
    private final int fuelConsumptionRate = 20;
    private final int smallCarFuelCapacity = 49;

    SmallCar(RegistrationNumber registrationNumber)
    {
        super(registrationNumber);
        setLittersCapacity(smallCarFuelCapacity);
    }


    /***
     * Determines whether the car can be driven
     * If the can can be driven it calculates the amount of fuel consumed during a journey, deducts
     * that amount from the fuel in the tank.
     * If the car cannot be driven the method throws an exception
     * @param kilometers desired amount of kilometers to drive the car
     * @return total amount of litters consumed by the trip
     * @throws UnsupportedOperationException
     */
    public int driveCar(int kilometers)
    {
        if(!getIsCarDrivable())
        {
            throw new UnsupportedOperationException("Car cannot be driven");
        }

        int result = (int)Math.ceil((double) kilometers / fuelConsumptionRate);

        setCurrentFuelLevel(getCurrentFuelLevel() - result);

        return result;
    }
}
