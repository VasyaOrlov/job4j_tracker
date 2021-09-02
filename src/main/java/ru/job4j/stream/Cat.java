package ru.job4j.stream;

import ru.job4j.bank.User;

public class Cat {
    private String name;
    private String color;
    private int age;
    private double weight;
    private boolean vaccinations;

    @Override
    public String toString() {
        return "Cat{"
                + "name='" + name + '\''
                + ", color='" + color + '\''
                + ", age=" + age
                + ", weight=" + weight
                + ", vaccinations=" + vaccinations
                + '}';
    }

    static class Builder {
        private String name;
        private String color;
        private int age;
        private double weight;
        private boolean vaccinations;

        Builder buildName(String name) {
            this.name = name;
            return this;
        }

        Builder buildColor(String color) {
            this.color = color;
            return this;
        }

        Builder buildAge(int age) {
            this.age = age;
            return this;
        }

        Builder buildWeight(double weight) {
            this.weight = weight;
            return this;
        }

        Builder builderVaccinations(boolean vaccinations) {
            this.vaccinations = vaccinations;
            return this;
        }

        Cat build() {
            Cat user = new Cat();
            user.name = name;
            user.color = color;
            user.age = age;
            user.weight = weight;
            user.vaccinations = vaccinations;
            return user;
        }
    }

    public static void main(String[] args) {
        Cat cat = new Builder()
                .buildName("Kat")
                .buildColor("Black")
                .buildAge(1)
                .buildWeight(1.57)
                .builderVaccinations(true)
                .build();
        System.out.println(cat);
    }
}
