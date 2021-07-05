package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rsl;
        int length = Math.min(left.length(), right.length());
        char[] first = left.toCharArray();
        char[] second = right.toCharArray();
        for (int i = 0; i < length; i++) {
            rsl = Character.compare(first[i], second[i]);
            if (rsl != 0) {
                return rsl;
            }
        }
        return Integer.compare(left.length(), right.length());
    }
}