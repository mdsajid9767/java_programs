package lld;

// Composition Engine with con-create Class
/* start
class Engine {
    private int horsePower;
    private String fuelType;

    Engine(int horsePower, String fuelType) {
        this.horsePower = horsePower;
        this.fuelType = fuelType;
    }

    public boolean ignite() {
        System.out.println("Engine (" + fuelType + ", " + horsePower + "hp) ignited.");
        return true;
    }

    public void shutOff() {
        System.out.println("Engine shut off.");
    }
}

abstract class Vehicle {
    protected String brand;
    protected String model;
    protected Engine engine;

    Vehicle(String brand, String model, Engine engine) {
        this.brand = brand;
        this.model = model;
        this.engine = engine;
    }

    public abstract boolean start();

    public void stop() {
        engine.shutOff();
        System.out.println(brand + " " + model + " stopped.");
    }
}

class Car extends Vehicle {
    private int numbersDoor;

    Car(int numbersDoor, String brand, String model, Engine engine) {
        super(brand, model, engine);
        this.numbersDoor = numbersDoor;
    }

    @Override
    public boolean start() {
        System.out.println("Turning key for " + brand + " " + model + "...");
        return engine.ignite();
    }
}

public class VehicleSystemLLDMain {
    public static void main(String[] args) {

        Engine v8Engine = new Engine(450, "petrol");
        Car car = new Car(4, "Ford", "Mustang", v8Engine);

        car.start();
    }
}
End */

interface Engine {
    boolean ignite();

    void shutOff();
}

class PetrolEngine implements Engine {
    private int horsePower;

    PetrolEngine(int horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public boolean ignite() {
        System.out.println("Petrol engine (" + horsePower + "hp) cranking and igniting fuel...");
        return true;
    }

    @Override
    public void shutOff() {
        System.out.println("Petrol engine shut off.");
    }
}

class ElectricEngine implements Engine {
    private int batteryCapacityKwh;

    ElectricEngine(int batteryCapacityKwh) {
        this.batteryCapacityKwh = batteryCapacityKwh;
    }

    @Override
    public boolean ignite() {
        System.out.println("Electric motor powering up from " + batteryCapacityKwh + "kWh battery...");
        return true;
    }

    @Override
    public void shutOff() {
        System.out.println("Electric motor powered down.");
    }
}

abstract class Vehicle {
    protected String brand;
    protected String model;
    protected Engine engine;

    Vehicle(String brand, String model, Engine engine) {
        this.brand = brand;
        this.model = model;
        this.engine = engine;
    }

    public abstract boolean start();

    public void stop() {
        engine.shutOff();
        System.out.println(brand + " " + model + " stopped.");
    }
}

class ElectricCar extends Vehicle {
    private int rangeKm;

    ElectricCar(int rangeKm, String brand, String model, Engine engine) {
        super(brand, model, engine);
        this.rangeKm = rangeKm;
    }

    @Override
    public boolean start() {
        System.out.println("Pressing power button on " + brand + " " + model + "...");
        return engine.ignite();
    }
}

class PetrolCar extends Vehicle {
    private int numberDoors;

    PetrolCar(int numberDoors, String brand, String model, Engine engine) {
        super(brand, model, engine);
        this.numberDoors = numberDoors;
    }

    @Override
    public boolean start() {
        System.out.println("Pressing power button on " + brand + " " + model + "...");
        return engine.ignite();
    }
}

public class VehicleSystemLLDMain {
    public static void main(String[] args) {

        Engine motor = new ElectricEngine(75);
        ElectricCar electricCar = new ElectricCar(500, "Tesla", "Model 3", motor);
        electricCar.start();

        Engine petrolMotor = new PetrolEngine(450);
        PetrolCar petrolCar = new PetrolCar(4, "Ford", "Mustang", petrolMotor);
        petrolCar.start();
    }
}