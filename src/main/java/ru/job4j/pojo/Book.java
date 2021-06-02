package ru.job4j.pojo;

public class Book {
    private String name;
    private int value;

    public Book(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
