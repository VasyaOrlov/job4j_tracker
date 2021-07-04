package ru.job4j.tracker;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import org.junit.Test;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;

public class ItemSortIdDownTest {
    @Test
    public void sortTest() {
        Item first = new Item("petr");
        first.setId(2);
        Item second = new Item("egor");
        second.setId(3);
        Item third = new Item("ivan");
        third.setId(1);
        List<Item> items = Arrays.asList(
                first,
                second,
                third
        );
        List<Item> expect = Arrays.asList(
                second,
                first,
                third
        );
        Collections.sort(items, new ItemSortIdDown());
        assertThat(expect, is(items));
    }
}