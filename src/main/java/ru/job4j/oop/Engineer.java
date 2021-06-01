package ru.job4j.oop;

public class Engineer extends Profession {
    private String create;

    public Engineer(String name, String surname, String education, String create) {
        super(name, surname, education);
        this.create = create;
    }

    public String getCreate() {
        return create;
    }

    public String code(Programmer x) {
        return "No";
    }
}
