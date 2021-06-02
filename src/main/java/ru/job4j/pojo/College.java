package ru.job4j.pojo;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("Vasiliy Orlov");
        student.setGroup(1);
        student.setData("02.06.2021");
        System.out.println(student.getName());
        System.out.println(student.getGroup());
        System.out.println(student.getData());
    }
}
