package com.javalab.tutorial.set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class TreeSetTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(TreeSetTest.class);

    private static TreeSet<String> treeSet = new TreeSet<>(Comparator.comparing(String::length));

    @BeforeAll
    static void setup() {
        LOGGER.info("TreeSetTest#setup");
        LOGGER.info("Setting test environment");

        treeSet.add("X");
        treeSet.add("XX");
        treeSet.add("XXXXX");
        treeSet.add("XXX");
        treeSet.add("XXXX");
    }

    @Test
    void whenIteratingTreeSet_shouldIterateTreeSetInAscendingOrder() {
        LOGGER.info("TreeSetTest#whenIteratingTreeSet_shouldIterateTreeSetInAscendingOrder");

        List<String> result = new ArrayList<>();
        int count = 0;

        Iterator<String> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            result.add(count, iterator.next());
            count++;
        }

        Assertions.assertThat(result.get(0)).isEqualTo("X");
        Assertions.assertThat(result.get(1)).isEqualTo("XX");
        Assertions.assertThat(result.get(2)).isEqualTo("XXX");
        Assertions.assertThat(result.get(3)).isEqualTo("XXXX");
        Assertions.assertThat(result.get(4)).isEqualTo("XXXXX");
    }

    @Test
    public void whenUsingHeadSet_shouldReturnHeadSetElements() {
        LOGGER.info("TreeSetTest#whenUsingHeadSet_shouldReturnHeadSetElements");

        /**
         * SubSet method will return the elements ranging from fromElement to toElement.
         * Note that fromElement is inclusive and toElement is exclusive.
         * */

        SortedSet<String> subSet = treeSet.headSet("XXX");
        Assertions.assertThat(subSet).isEqualTo(treeSet.subSet("X", "XXX"));
    }

    @Test
    public void whenUsingTailSet_shouldReturnTailSetElements() {
        LOGGER.info("TreeSetTest#whenUsingTailSet_shouldReturnTailSetElements");

        Set<String> subSet = treeSet.tailSet("XXX");

        Assertions.assertThat(subSet).isEqualTo(treeSet.subSet("XXX", true, "XXXXX", true));
    }

    @Test
    public void whenAddingNullToNonEmptyTreeSet_shouldThrowException() {
        LOGGER.info("TreeSetTest#whenAddingNullToNonEmptyTreeSet_shouldThrowException");

        Assertions.assertThatThrownBy(()->
            treeSet.add(null)
        ).isInstanceOf(NullPointerException.class);
    }
}
