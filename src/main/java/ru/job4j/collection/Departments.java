package ru.job4j.collection;

import java.util.*;

public class Departments {

    public static List<String> fillGaps(List<String> deps) {
        Set<String> tmp = new LinkedHashSet<>();
        for (String value : deps) {
            String start = "";
            for (String el : value.split("/")) {
                start = "".equals(start) ? el : start + "/" + el;
                tmp.add(start);
            }
        }
        return new ArrayList<>(tmp);
    }

    public static void sortAsc(List<String> orgs) {
        Collections.sort(orgs);
    }

    public static void sortDesc(List<String> orgs) {
        Collections.sort(orgs, new DepDescComp());
    }

    public static void main(String[] args) {
        List<String> x = Arrays.asList("k2", "k3/sk1", "k1/sk1/ssk2", "k3/sk2/ssk2");
        for (String e : fillGaps(x)) {
            System.out.println(e);
        }
        System.out.println();
        sortDesc(x);
        for (String e : fillGaps(x)) {
            System.out.println(e);
        }
        System.out.println();
        sortAsc(x);
        for (String e : fillGaps(x)) {
            System.out.println(e);
        }
    }
}