package ru.job4j.ex;

public class Fact {
        public int calc(int n) {
            if (n < 0) {
                throw new IllegalArgumentException("Введите значение не меньше 0");
            }
        int rsl = 1;
        for (int index = 1; index <= n; index++) {
            rsl *= index;
        }
        return rsl;
    }

    public static void main(String[] args) {
            Fact fact = new Fact();
        System.out.println(fact.calc(5));
        System.out.println(fact.calc(-5));
    }
}
