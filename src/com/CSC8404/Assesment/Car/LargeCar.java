package com.CSC8404.Assesment.Car;

public class LargeCar extends CarFactory
{
    private final int baseFuelConsumptionRate = 10;
    private final int fuelConsumptionIncrement = 5;
    private final int kilometersPriorIncrease = 50;
    private final int largeCarFuelCapacity = 49;

    LargeCar(RegistrationNumber registrationNumber)
    {
        super(registrationNumber);
        setLittersCapacity(largeCarFuelCapacity);
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
            throw new UnsupportedOperationException("Car cannot be driven and no journey has been taken");
        }

        int diff = 0;

        if(kilometers > kilometersPriorIncrease)
        {
            diff = kilometers - kilometersPriorIncrease;
            kilometers = 50;
        }

        int result = (int)(Math.ceil((double) (kilometers / baseFuelConsumptionRate)) +
                Math.ceil((double)(diff / (baseFuelConsumptionRate + fuelConsumptionIncrement))));

        setCurrentFuelLevel(getCurrentFuelLevel() - result);

        return result;
    }
}
