package ru.job4j.stream;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.List;

public class MatrixToListTest {
    @Test
    public void convertTest() {
        MatrixToList matrixToList = new MatrixToList();
        Integer[][] matrix = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        List<Integer> rsl = matrixToList.convert(matrix);
        List<Integer> expect = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertThat(expect, is(rsl));
    }
}