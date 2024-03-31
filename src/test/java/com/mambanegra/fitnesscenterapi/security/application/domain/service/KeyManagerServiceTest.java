package com.mambanegra.fitnesscenterapi.security.application.domain.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.mambanegra.fitnesscenterapi.security.application.port.out.FileReader;
import java.security.Key;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class KeyManagerServiceTest {

    public static final String TEST_KEY_PATH = "test/key/path";
    public static final String RSA_PRIVATE_KEY = "-----BEGIN RSA PRIVATE KEY-----\nBASE64ENCODEDKEYBITS----END RSA PRIVATE KEY-----";

    @Mock
    FileReader mockFileReader;

    KeyManagerService keyManagerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockFileReader.readFile(TEST_KEY_PATH)).thenReturn(RSA_PRIVATE_KEY);
        keyManagerService = new KeyManagerService(mockFileReader, TEST_KEY_PATH);
    }

    @Test
    void testLoadPrivateKey() {
        Key privateKey = keyManagerService.getPrivateKey();

        assertNotNull(privateKey, "The private key in the KeyManagerService should not be null after calling the loadPrivateKey() method");
    }
}