package ru.job4j.lambda;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import java.util.List;
import java.util.Arrays;

public class CountTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        Count function = new Count();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D, 17D);
        assertThat(result, is(expected));
    }

    @Test
    public void quadFunction() {
        Count function = new Count();
        List<Double> result = function.diapason(1, 3, x -> Math.pow(x, 3));
        List<Double> expected = Arrays.asList(1D, 8D, 27D);
        assertThat(result, is(expected));
    }

    @Test
    public void func() {
        Count function = new Count();
        List<Double> result = function.diapason(2, 4, x -> Math.pow(2, x));
        List<Double> expected = Arrays.asList(4D, 8D, 16D);
        assertThat(result, is(expected));
    }
}