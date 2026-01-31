package com.johnny.transactiondemo.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConstantsTest {

    @DisplayName("Test Constants Values")
    @Test
    void testConstants() {
        Constants constants = new Constants();
        assertNotNull(constants);
    }
}
