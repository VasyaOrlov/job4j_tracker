package ru.job4j.poly;

public class VehiclePark {
    public static void main(String[] args) {
        Vehicle first = new Car();
        Vehicle second = new Train();
        Vehicle third = new Plane();
        Vehicle[] vehicles = new Vehicle[] {first, second, third};
        for (Vehicle vehicle : vehicles) {
            System.out.print(vehicle.getClass().getSimpleName() + " - ");
            vehicle.move();
        }
    }
}
