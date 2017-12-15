package com.bigw.tt.fundamentallib;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void collection_matchers() {
        List<Integer> list = Arrays.asList(5,  2, 4);
        assertThat(list, hasSize(3));
        assertThat(list, containsInAnyOrder(2, 4, 5));
        assertThat(list, everyItem(greaterThan(1)));
    }

    @Test
    public void arrays_matchers() {
        Integer[] ints = new Integer[]{7, 5, 12, 16};
        assertThat(Arrays.asList(ints), hasSize(4));
        assertThat(Arrays.asList(ints), contains(7, 5, 12, 16));

        assertThat(ints, arrayWithSize(4));
        assertThat(ints, arrayContaining(7, 5, 12, 16));
    }

    @Test
    public void bean_matcher() {
        Todo todo = new Todo(0, "", "", 0);
        assertThat(todo, hasProperty("summary"));

        todo = new Todo(0, "Learn Hamcrest", "Learn Hamcrest", 0);
        assertThat(todo, hasProperty("summary", equalTo("Learn Hamcrest")));

        Todo todo1 = new Todo(0, "a", "b", 0);
        Todo todo2 = new Todo(0, "a", "b", 0);
        assertThat(todo1, samePropertyValuesAs(todo2));
    }

    @Test
    public void string_matchers() {
        assertThat("", isEmptyString());
        assertThat("", isEmptyOrNullString());
    }

     public static class Todo {
        private final long id;
        private String summary;
        private String description;
        private int year;

        public Todo(long id, String summary, String description, int year) {
            this.id = id;
            this.summary = summary;
            this.description = description;
            this.year = year;
        }

        public long getId() {
            return id;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }
}