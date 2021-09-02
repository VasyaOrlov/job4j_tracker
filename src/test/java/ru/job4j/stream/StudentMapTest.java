package ru.job4j.stream;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class StudentMapTest {
    @Test
    public void inMapTest() {
        StudentMap studentMap = new StudentMap();
        List<Student> list = List.of(
                new Student(60, "Petrov"),
                new Student(65, "Ivanov"),
                new Student(64, "Petrov")
        );
        Map<String, Student> rsl = studentMap.inMap(list);
        Map<String, Student> expect = Map.of(
                "Petrov", new Student(60, "Petrov"),
                "Ivanov", new Student(65, "Ivanov")
        );
        assertThat(expect, is(rsl));
    }
}