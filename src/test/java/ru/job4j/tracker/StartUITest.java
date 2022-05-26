package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "Item name", "1"}
        );
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction(out));
        actions.add(new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        List<UserAction> actions = new ArrayList<>();
        actions.add(new EditAction(out));
        actions.add(new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = new ArrayList<>();
        actions.add(new DeleteAction(out));
        actions.add(new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        Assert.assertNull(tracker.findById(item.getId()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0"}
        );
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu:" + System.lineSeparator()
                        + "0. Exit Program" + System.lineSeparator()
        ));
    }

    @Test
    public void whenShowAllAction() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[] {"0", "a", "1", "2"});
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction(out));
        actions.add(new ShowAllAction(out));
        actions.add(new ExitAction(out));
        String ln = System.lineSeparator();
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu:" + ln
                + "0. Add new Item" + ln
                + "1. Show all items" + ln
                + "2. Exit Program" + ln
                + "=== Create a new Item ====" + ln
                + "Добавленная заявка: id: 1, name: a, created: " + ln
                + "Menu:" + ln
                + "0. Add new Item" + ln
                + "1. Show all items" + ln
                + "2. Exit Program" + ln
                + "=== Show all items ====" + ln
                + "id: 1, name: a, created: " + ln
                + "Menu:" + ln
                + "0. Add new Item" + ln
                + "1. Show all items" + ln
                + "2. Exit Program" + ln
        ));
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"1", "0"}
        );
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Exit Program" + ln
                        + "Wrong input, you can select: 0 .. 0" + ln
                        + "Menu:" + ln
                        + "0. Exit Program" + ln
                )
        );
    }
}