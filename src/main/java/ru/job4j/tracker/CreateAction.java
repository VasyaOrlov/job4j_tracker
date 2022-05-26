package ru.job4j.tracker;

import java.sql.SQLException;

public class CreateAction implements UserAction {
    private final Output out;

    public CreateAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add new Item";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Create a new Item ====");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        if (tracker.add(item) != null) {
            out.println("Добавленная заявка: " + item);
        }
        return true;
    }
}
