package com.javalab.tutorial.map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(HashMapTest.class);
    private static Map<Integer, Integer> hashMap = new HashMap<>();
    private final static int hashMapSize = 1000000;

    @BeforeAll
    static void setup() {
        LOGGER.info("HashMapTest#setup");
        LOGGER.info("Setting test environment");

        for (int count = 0; count < hashMapSize; count++) {
            hashMap.put(count, count);
        }
    }

    @Test
    void whenHavingLargeSizeHashMap_thenSearchingElementsByKeyHasBetterPerformanceOverSearchingByValue () {
        LOGGER.info("HashMapTest#whenHavingLargeSizeHashMap_thenSearchingElementsByKeyHasBetterPerformanceOverSearchingByValue");

        int rand = (int) (Math.random() * hashMapSize) + 1;

        LOGGER.info("Searching key: {}", rand);

        Long startTimeSearchByKey = System.currentTimeMillis();
        hashMap.containsKey(rand);
        Long endTimeSearchByKey = System.currentTimeMillis();

        LOGGER.info("Searching by key consumed: {} milliseconds", endTimeSearchByKey - startTimeSearchByKey);

        Long startTimeSearchByValue = System.currentTimeMillis();
        hashMap.containsValue(rand);
        Long endTimeSearchByValue = System.currentTimeMillis();

        LOGGER.info("Searching by value consumed: {} milliseconds", endTimeSearchByValue - startTimeSearchByValue);

        Assertions.assertThat(endTimeSearchByValue - startTimeSearchByValue > endTimeSearchByKey - startTimeSearchByKey);
    }
}
