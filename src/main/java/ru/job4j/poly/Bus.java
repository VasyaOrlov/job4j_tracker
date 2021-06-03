package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void go() {
        System.out.println("bzzzz");
    }

    @Override
    public void passengers(int value) {
        System.out.println("full");
    }

    @Override
    public int refuel(int gas) {
        return gas * 50;
    }
}
