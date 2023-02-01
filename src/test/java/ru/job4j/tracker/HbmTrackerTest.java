package ru.job4j.tracker;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class HbmTrackerTest {

    private final SessionFactory sf = new MetadataSources(
            new StandardServiceRegistryBuilder().configure().build()
    ).buildMetadata().buildSessionFactory();

    @Before
    public void clean() {
        var session = sf.openSession();
        session.beginTransaction();
        session.createQuery("delete from Item").executeUpdate();
        session.getTransaction();
        session.close();
    }

    @Test
    public void whenAddNewItemThenAddThisItem() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item("test");

            var result = tracker.add(item);
            assertThat(result.getName(), is("test"));

            var rsl = tracker.add(item);
            assertNull(rsl);
        }
    }

    @Test
    public void whenReplaceItem() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item("test");
            tracker.add(item);
            Item item2 = new Item("test2");

            var rsl = tracker.replace(item.getId(), item2);

            assertTrue(rsl);
            assertThat(tracker.findById(item.getId()).getName(), is("test2"));
        }
    }

    @Test
    public void whenDeleteItem() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item("test");
            int id = tracker.add(item).getId();

            boolean rsl = tracker.delete(id);
            assertTrue(rsl);
        }
    }

    @Test
    public void whenFindAll() {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item("item 1");
            tracker.add(item1);

            Item item2 = new Item("item 2");
            tracker.add(item2);

            Item item3 = new Item("item 3");
            tracker.add(item3);

            var rsl = tracker.findAll();
            var expect = List.of(item1, item2, item3);
            assertThat(expect, is(rsl));
        }
    }

    @Test
    public void whenFindByName() {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item("item name");
            tracker.add(item1);

            Item item2 = new Item("item name");
            tracker.add(item2);

            Item item3 = new Item("item 2");
            tracker.add(item3);

            var rsl = tracker.findByName("item name");
            var expect = List.of(item1, item2);
            assertThat(expect, is(rsl));
        }
    }

    @Test
    public void whenFindById() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item("test");
            int id = tracker.add(item).getId();
            assertThat(tracker.findById(id), is(item));
        }
    }
}