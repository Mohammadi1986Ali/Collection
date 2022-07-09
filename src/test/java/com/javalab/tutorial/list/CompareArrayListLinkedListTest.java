package com.javalab.tutorial.list;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CompareArrayListLinkedListTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(CompareArrayListLinkedListTest.class);

    static List<Object> OBJECTS_ARRAY_LIST = new ArrayList<>();
    static List<Object> OBJECTS_LINKED_LIST = new LinkedList<>();

    private final static int listSize = 10000000;

    @BeforeAll
    static void setup() {
        LOGGER.info("CompareArrayListLinkedListTest#setup");
        LOGGER.info("Setting test environment");

        for (int listIndex = 0; listIndex < listSize; listIndex++) {
            OBJECTS_ARRAY_LIST.add(new Object());
            OBJECTS_LINKED_LIST.add(new Object());
        }
    }

    @Test
    void whenProcessingLargeSizeList_thenArrayListConsumedMoreTimeThanLinkedList() {

        LOGGER.info("CompareArrayListLinkedListTest#whenProcessingLargeSizeList_thenArrayListConsumedMoreTimeThanLinkedList");

        Long startTimeArrayList = System.currentTimeMillis();
        OBJECTS_ARRAY_LIST.add(listSize/2, new Object());
        Long endTimeArrayList = System.currentTimeMillis();

        LOGGER.info("ArrayList consumed: {} milliseconds", endTimeArrayList - startTimeArrayList);

        Long startTimeLinkedList = System.currentTimeMillis();
        OBJECTS_ARRAY_LIST.add(listSize/2, new Object());
        Long endTimeLinkedList = System.currentTimeMillis();

        LOGGER.info("LinkedList consumed: {} milliseconds", endTimeLinkedList - startTimeLinkedList);

        Assertions.assertThat(endTimeArrayList - startTimeArrayList > endTimeLinkedList - startTimeLinkedList);
    }
}
