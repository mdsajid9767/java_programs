package lld;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

enum Size {
    SMALL(1),
    MEDIUM(2),
    LARGE(3);

    private final int capacity;

    Size(int capacity) {
        this.capacity = capacity;
    }

    public boolean canFit(Size vehicleSize) {
        return this.capacity >= vehicleSize.capacity;
    }
}

abstract class Vehicle1 {
    private final String licensePlate;
    private final Size size;

    protected Vehicle1(String licensePlate, Size size) {
        if (licensePlate == null || licensePlate.isBlank()) {
            throw new IllegalArgumentException("License plate cannot be empty");
        }

        if (size == null) {
            throw new IllegalArgumentException("Vehicle size cannot be null");
        }

        this.licensePlate = licensePlate;
        this.size = size;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Size getSize() {
        return size;
    }

    public abstract String getVehicleType();

    public String getDetails() {
        return getVehicleType() +
                " | License Plate: " + licensePlate +
                " | Size: " + size;
    }
}

class Car extends Vehicle1 {

    public Car(String licensePlate, Size size) {
        super(licensePlate, size);
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }
}

class Bike extends Vehicle1 {

    public Bike(String licensePlate, Size size) {
        super(licensePlate, size);
    }

    @Override
    public String getVehicleType() {
        return "Bike";
    }
}

interface PricingStrategy {
    double calculateFee(long hoursParked);
}

class HourlyPricing implements PricingStrategy {
    private static final double RATE_PER_HOUR = 50.0;

    @Override
    public double calculateFee(long hoursParked) {
        return hoursParked * RATE_PER_HOUR;
    }
}

class ParkingSpot {
    private final int spotNumber;
    private final Size spotSize;
    private Vehicle1 vehicle;
    private long parkedAtMillis;

    public ParkingSpot(int spotNumber, Size spotSize) {
        this.spotNumber = spotNumber;
        this.spotSize = spotSize;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public Vehicle1 getVehicle() {
        return vehicle;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public boolean canFit(Vehicle1 vehicle) {
        return isAvailable() && spotSize.canFit(vehicle.getSize());
    }

    public void park(Vehicle1 vehicle) {
        if (!canFit(vehicle)) {
            throw new IllegalStateException(
                    "Vehicle cannot fit in spot " + spotNumber
            );
        }

        this.vehicle = vehicle;
        this.parkedAtMillis = System.currentTimeMillis();
    }

    public long removeVehicle() {
        if (vehicle == null) {
            throw new IllegalStateException(
                    "Spot " + spotNumber + " is already empty"
            );
        }

        long parkedDurationMillis =
                System.currentTimeMillis() - parkedAtMillis;

        this.vehicle = null;
        this.parkedAtMillis = 0;

        return parkedDurationMillis;
    }
}

class ParkingReceipt {
    private final String licensePlate;
    private final int spotNumber;
    private final long hoursParked;
    private final double fee;

    public ParkingReceipt(
            String licensePlate,
            int spotNumber,
            long hoursParked,
            double fee
    ) {
        this.licensePlate = licensePlate;
        this.spotNumber = spotNumber;
        this.hoursParked = hoursParked;
        this.fee = fee;
    }

    @Override
    public String toString() {
        return """
                --------------------------------
                         PARKING RECEIPT
                --------------------------------
                License Plate : %s
                Spot Number   : %d
                Hours Parked  : %d
                Amount        : Rs %.2f
                --------------------------------
                """.formatted(
                licensePlate,
                spotNumber,
                hoursParked,
                fee
        );
    }
}

class ParkingLot {
    private final List<ParkingSpot> spots = new ArrayList<>();
    private final PricingStrategy pricingStrategy;

    public ParkingLot(
            int numberOfSpots,
            Size spotSize,
            PricingStrategy pricingStrategy
    ) {
        if (numberOfSpots <= 0) {
            throw new IllegalArgumentException(
                    "Number of spots must be greater than zero"
            );
        }

        this.pricingStrategy = pricingStrategy;

        for (int i = 1; i <= numberOfSpots; i++) {
            spots.add(new ParkingSpot(i, spotSize));
        }
    }

    public boolean parkVehicle(Vehicle1 vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.canFit(vehicle)) {
                spot.park(vehicle);

                System.out.println("Vehicle parked successfully.");
                System.out.println(vehicle.getDetails());
                System.out.println("Spot Number: " + spot.getSpotNumber());

                return true;
            }
        }

        System.out.println(
                "Parking failed. No suitable spot available for " +
                        vehicle.getDetails()
        );

        return false;
    }

    public ParkingReceipt unparkVehicle(Vehicle1 vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.getVehicle() == vehicle) {
                long parkedDurationMillis = spot.removeVehicle();

                // Round up partial hours.
                long hoursParked = Math.max(
                        1,
                        TimeUnit.MILLISECONDS.toHours(
                                parkedDurationMillis
                        )
                );

                // If there are remaining minutes/seconds, charge another hour.
                if (parkedDurationMillis % TimeUnit.HOURS.toMillis(1) != 0) {
                    hoursParked++;
                }

                double fee = pricingStrategy.calculateFee(hoursParked);

                ParkingReceipt receipt = new ParkingReceipt(
                        vehicle.getLicensePlate(),
                        spot.getSpotNumber(),
                        hoursParked,
                        fee
                );

                System.out.println("Vehicle unparked successfully.");
                System.out.println(receipt);

                return receipt;
            }
        }

        throw new IllegalArgumentException(
                "Vehicle with license plate " +
                        vehicle.getLicensePlate() +
                        " is not parked"
        );
    }
}

public class ParkingLotSystemLLDMain {

    public static void main(String[] args) {
        Vehicle1 car = new Car("KA-01-1234", Size.MEDIUM);

        PricingStrategy pricingStrategy = new HourlyPricing();

        ParkingLot parkingLot = new ParkingLot(
                2,
                Size.MEDIUM,
                pricingStrategy
        );

        boolean parked = parkingLot.parkVehicle(car);

        if (parked) {
            try {
                // Only for testing, so that the parking duration is visible.
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            parkingLot.unparkVehicle(car);
        }
    }
}