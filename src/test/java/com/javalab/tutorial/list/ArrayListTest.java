package com.javalab.tutorial.list;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayListTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(ArrayListTest.class);
    private final static int listSize = 1000000;

    @Test
    void whenModifyingArrayListWhileIterating_thenShouldThrowException() {
        LOGGER.info("ArrayListTest#whenModifyingArrayListWhileIterating_thenShouldThrowException");

        List<String> customers = new ArrayList<>();
        customers.add("String_01");
        customers.add("String_02");
        customers.add("String_03");
        customers.add("String_04");

        Iterator<String> iterator = customers.iterator();
        Assertions.assertThatThrownBy(() -> {
            while (iterator.hasNext()) {
                customers.remove(2);
                iterator.next();
            }
        }).isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenUsingStreamAPI_thenAllElementsShouldBeReturned() {
        LOGGER.info("ArrayListTest#whenUsingStreamAPI_thenAllElementsShouldBeReturned");

        List<String> customers = new ArrayList<>();
        customers.add("String_01");
        customers.add("String_02");
        customers.add("String_03");
        customers.add("String_04");

        Set<String> matchingStrings = new HashSet<>(Arrays.asList("String_01", "String_03"));

        List<String> result = customers.stream().filter(matchingStrings::contains).collect(Collectors.toList());

        Assertions.assertThat(result).isEqualTo(Arrays.asList("String_01", "String_03"));
    }

    @Test
    void whenProcessingLargeSizeArrayList_thenAddingElementShouldConsumedLessTimeThanInsertingElement() {
        LOGGER.info("ArrayListTest#whenProcessingLargeSizeArrayList_thenAddingElementShouldConsumedLessTimeThanInsertingElement");

        List<Integer> intList = new ArrayList<>();

        createArrayList(intList);

        Long startTimeAdding = System.currentTimeMillis();
        intList.add(0);
        Long endTimeAdding = System.currentTimeMillis();

        LOGGER.info("Adding consumed: {} milliseconds", endTimeAdding - startTimeAdding);

        Long startTimeInserting = System.currentTimeMillis();
        intList.add(0, 0);
        Long endTimeInserting = System.currentTimeMillis();

        LOGGER.info("Inserting consumed: {} milliseconds", endTimeInserting - startTimeInserting);

        Assertions.assertThat(endTimeInserting - startTimeInserting > endTimeAdding - startTimeAdding);
    }

    @Test
    void whenSettingArrayListSize_thenLargeSizeArrayListShouldHaveBetterPerformance() {
        LOGGER.info("ArrayListTest#whenSettingArrayListSize_thenLargeSizeArrayListShouldHaveBetterPerformance");

        Long startTimeAddingDefaultArrayList = System.currentTimeMillis();
        List<Integer> defaultList = new ArrayList<>();
        createArrayList(defaultList);
        Long endTimeAddingDefaultArrayList = System.currentTimeMillis();

        LOGGER.info("Default ArrayList consumed: {} milliseconds", endTimeAddingDefaultArrayList - startTimeAddingDefaultArrayList);

        Long startTimeAddingArrayListDefinedSize = System.currentTimeMillis();
        List<Integer> definedSizeList = new ArrayList<>(listSize);
        createArrayList(definedSizeList);
        Long endTimeAddingArrayListDefinedSize = System.currentTimeMillis();

        LOGGER.info("Defined size ArrayList consumed: {} milliseconds", endTimeAddingArrayListDefinedSize - startTimeAddingArrayListDefinedSize);

        Assertions.assertThat(endTimeAddingArrayListDefinedSize - startTimeAddingArrayListDefinedSize > endTimeAddingDefaultArrayList - startTimeAddingDefaultArrayList);
    }

    @Test
    void whenThreadsAccessingArrayList_thenArrayListShouldNotWorkCorrectly() throws InterruptedException {

        LOGGER.info("ArrayListTest#whenThreadsAccessingArrayList_thenArrayListShouldNotWorkCorrectly");

        List<Integer> intList = new ArrayList<>();
        createArrayList(intList);

        Thread minusThread = new Thread(() -> doMinusOnArray(intList));
        Thread sumThread = new Thread(() -> doSumOnArray(intList));

        minusThread.start();
        sumThread.start();
        minusThread.join();
        sumThread.join();

        int result = 0;
        for (int listIndex = 0; listIndex < intList.size(); listIndex++) {
            result += intList.get(listIndex);
        }

        LOGGER.info("Sum of ArrayList element is {}", result);
        /**
         * As ArrayList is not synchronized this assertion can make wrong result!
         * Assertions.assertThat(result).isEqualTo(0);
         */
    }

    private void createArrayList(List list) {
        for (int listIndex = 0; listIndex < listSize; listIndex++) {
            list.add(0);
        }
    }

    private void doSumOnArray(List<Integer> list) {
        for (int listIndex = 0; listIndex < list.size(); listIndex++) {
            list.set(listIndex, list.get(listIndex) + listIndex);
        }
    }

    private void doMinusOnArray(List<Integer> list) {
        for (int listIndex = 0; listIndex < list.size(); listIndex++) {
            list.set(listIndex, list.get(listIndex) - listIndex);
        }
    }
}
