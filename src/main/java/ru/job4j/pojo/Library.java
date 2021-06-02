package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book first = new Book("Clean code", 100);
        Book second = new Book("Sea", 150);
        Book third = new Book("earth", 96);
        Book fourth = new Book("moon", 200);
        Book[] books = new Book[] {first, second, third, fourth};
        for (Book book : books) {
            System.out.println(book.getName() + " " + book.getValue());
        }
        Book tmp = books[0];
        books[0] = books[3];
        books[3] = tmp;
        System.out.println();
        for (Book book : books) {
            System.out.println(book.getName() + " " + book.getValue());
        }
        System.out.println();
        for (Book book : books) {
            if (book.getName().equals("Clean code")) {
                System.out.println(book.getName() + " " + book.getValue());
            }
        }
     }
}
