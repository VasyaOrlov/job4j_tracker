package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private Connection cn;

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        int rsl = 0;
        try (PreparedStatement ps = cn.prepareStatement(
                "insert into items (name, created) values (?, ?);"
        )) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            rsl = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rsl == 0) {
            throw new ConcurrentModificationException("Заявка не добавлена");
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        int rsl = 0;
        try (PreparedStatement ps = cn.prepareStatement(
                "update items set name = ?, created = ? where id = ?;"
        )) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            ps.setInt(3, id);
            rsl = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl > 0;
    }

    @Override
    public boolean delete(int id) {
        int rsl = 0;
        try (PreparedStatement ps = cn.prepareStatement(
                "delete from items where id = ?;"
        )) {
            ps.setInt(1, id);
            rsl = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl > 0;
    }

    @Override
    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        try (Statement ps = cn.createStatement()) {
            try (ResultSet rs = ps.executeQuery("select * from items;")) {
                while (rs.next()) {
                    Item item = new Item(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                    item.setCreated(rs.getTimestamp("created").toLocalDateTime());
                    list.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement(
                "select * from items where name = ?;")) {
            ps.setString(1, key);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                    item.setCreated(rs.getTimestamp("created").toLocalDateTime());
                    list.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        try (PreparedStatement ps = cn.prepareStatement(
                "select * from items where id = ?;")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    item = new Item(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                    item.setCreated(rs.getTimestamp("created").toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
}