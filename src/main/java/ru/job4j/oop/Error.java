package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void info() {
        System.out.println("1. " + active);
        System.out.println("2. " + status);
        System.out.println("3. " + message);
    }

    public static void main(String[] args) {
        Error error = new Error();
        error.info();
        Error errors = new Error(false, 2, "error");
        errors.info();
    }
}
