package ru.job4j.oop;

public class Doctor extends Profession {
    private String treat;

    public Doctor(String name, String surname, String education, String treat) {
        super(name, surname, education);
        this.treat = treat;
    }

    public String getTreat() {
        return treat;
    }

    public short tooth(Dentist a) {
        return 24;
    }
}
