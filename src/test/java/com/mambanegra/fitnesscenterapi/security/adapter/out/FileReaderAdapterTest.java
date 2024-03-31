package com.mambanegra.fitnesscenterapi.security.adapter.out;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileReaderAdapterTest {

    FileReaderAdapter fileReaderAdapter;

    @BeforeEach
    void setUp() {
        fileReaderAdapter = new FileReaderAdapter();
    }

    @Test
    void readFile() {
        String result = fileReaderAdapter.readFile("../jwtRS256.key");
        assertFalse(result.isBlank());
    }
}