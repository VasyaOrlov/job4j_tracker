package ru.job4j.collection;

import org.junit.Test;
import java.util.Comparator;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class JobTest {

    @Test
    public void whenCompatorByNameAndPrority() {
        Comparator<Job> cmpNamePriority = new SortJobNameDown().thenComparing(new SortJobPrDown());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void sortJobNameUpTest() {
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Fix bug", 4),
                new Job("X task", 0),
                new Job("Fix bug", 2)
        );
        Collections.sort(jobs, new SortJobNameUp());
        List<Job> expect = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("X task", 0)
        );
        assertThat(expect, is(jobs));
    }

    @Test
    public void sortJobNameDownTest() {
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Fix bug", 4),
                new Job("X task", 0),
                new Job("Fix bug", 2)
        );
        Collections.sort(jobs, new SortJobNameDown());
        List<Job> expect = Arrays.asList(
                new Job("X task", 0),
                new Job("Fix bug", 1),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2)
        );
        assertThat(expect, is(jobs));
    }

    @Test
    public void sortJobPrUpTest() {
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("X task", 0)
        );
        Collections.sort(jobs, new SortJobPrUp());
        List<Job> expect = Arrays.asList(
                new Job("X task", 0),
                new Job("Fix bug", 1),
                new Job("Fix bug", 2),
                new Job("Fix bug", 4)
        );
        assertThat(expect, is(jobs));
    }

    @Test
    public void sortJobPrDownTest() {
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("X task", 0)
        );
        Collections.sort(jobs, new SortJobPrDown());
        List<Job> expect = Arrays.asList(
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("Fix bug", 1),
                new Job("X task", 0)
        );
        assertThat(expect, is(jobs));
    }
}