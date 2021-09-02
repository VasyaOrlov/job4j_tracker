package ru.job4j.stream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class ProfilesTest {
    @Test
    public void collectTest() {
        List<Profile> list = List.of(
                new Profile(new Address("Moscow", "Krasnay", 1, 1)),
                new Profile(new Address("Moscow", "Krasnay", 1, 2)),
                new Profile(new Address("Moscow", "Krasnay", 1, 3))
                );
        Profiles profiles = new Profiles();
        List<Address> rsl = profiles.collect(list);
        List<Address> expect = List.of(
                new Address("Moscow", "Krasnay", 1, 1),
                new Address("Moscow", "Krasnay", 1, 2),
                new Address("Moscow", "Krasnay", 1, 3)
        );
        assertThat(rsl, is(expect));
    }

}