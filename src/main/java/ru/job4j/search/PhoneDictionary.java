package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private final ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public ArrayList<Person> find(String key) {
        Predicate<Person> name = (i) -> i.getName().contains(key);
        Predicate<Person> surname = (i) -> i.getSurname().contains(key);
        Predicate<Person> phone = (i) -> i.getPhone().contains(key);
        Predicate<Person> address = (i) -> i.getAddress().contains(key);
        Predicate<Person> combine = name.or(surname.or(phone.or(address)));
        var result = new ArrayList<Person>();
        for (var person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}