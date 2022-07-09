package com.javalab.tutorial.map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Enumeration;
import java.util.Hashtable;

public class HashtableTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(HashtableTest.class);
    private static Hashtable<Integer, String> table;

    @BeforeAll
    static void setup() {
        LOGGER.info("HashtableTest#setup");
        LOGGER.info("Setting test environment");

        table = new Hashtable<>();
        table.put(1, "one");
        table.put(2, "two");
        table.put(3, "three");
        table.put(4, "four");
    }

    @Test
    public void whenModifyingHashtableWhileIteratingByEnum_thenWeWontGetException() {
        LOGGER.info("HashtableTest#whenModifyingHashtableWhileIteratingByEnum_thenWeWontGetException");

        Enumeration<Integer> enumKey = table.keys();
        table.remove(3);

        int count = 0;

        while (enumKey.hasMoreElements()) {
            enumKey.nextElement();
            count ++;
        }
        Assertions.assertThat(count).isEqualTo(3);
    }
}
