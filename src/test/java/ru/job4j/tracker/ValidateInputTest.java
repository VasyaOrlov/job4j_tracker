package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ValidateInputTest {

    @Test
    public void whenInvalidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"one", "1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void correctIn() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"5"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(5));
    }

    @Test
    public void manyCorrectIn() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"1", "5"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int[] selected = {input.askInt("Enter menu:"), input.askInt("Enter menu")};
        int[] result = {1, 5};
        assertThat(selected, is(result));
    }

    @Test()
    public void negativeNumberIn() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"-5"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(-5));
    }
}