package com.javalab.tutorial.set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

public class HashSetTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(HashSetTest.class);

    @Test
    void whenHashSetHasDuplicateInput_thenItKeepsUniqueElements() {
        LOGGER.info("HashSetTest#whenHashSetHasDuplicateInput_thenItKeepUniqueElements");

        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("String_01");
        hashSet.add("String_01");
        hashSet.add("String_02");
        hashSet.add("String_02");

        Assertions.assertThat(hashSet.size()).isEqualTo(2);
    }
}
