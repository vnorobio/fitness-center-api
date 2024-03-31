package com.mambanegra.fitnesscenterapi.security.application.domain.service;

import com.mambanegra.fitnesscenterapi.security.application.port.out.FileReader;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;

public class KeyManagerService {
    public static final String RSA_PRIVATE_KEY_HEADER = "-----BEGIN RSA PRIVATE KEY-----";
    public static final String RSA_PRIVATE_KEY_FOOTER = "-----END RSA PRIVATE KEY-----";
    private final FileReader fileReader;
    private final String keyFileUri;
    private Key privateKey;

    public KeyManagerService(FileReader fileReader, String keyFileUri) {
        this.fileReader = fileReader;
        this.keyFileUri = keyFileUri;
        loadPrivateKey();
    }

    private void loadPrivateKey() {
        String rawKey = fileReader.readFile(keyFileUri);
        String cleanKey = getCleanKey(rawKey);

        String base64KeyString = Base64.getEncoder().encodeToString(cleanKey.getBytes(StandardCharsets.UTF_8));

        this.privateKey = new SecretKeySpec(base64KeyString.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());
    }

    private String getCleanKey(String rawKey) {
        return rawKey
                .replace(RSA_PRIVATE_KEY_HEADER, "")
                .replaceAll(System.lineSeparator(), "")
                .replace(RSA_PRIVATE_KEY_FOOTER, "");
    }

    public Key getPrivateKey() {
        return privateKey;
    }

}
