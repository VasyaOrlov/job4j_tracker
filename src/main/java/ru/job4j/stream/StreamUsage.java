package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamUsage {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(-2, 3, 1, 7, -4, -100);
        List<Integer> filterList = list.stream().filter(
                x -> x > 0
        ).collect(Collectors.toList());
        filterList.forEach(System.out::println);
    }
}