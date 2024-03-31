package com.mambanegra.fitnesscenterapi.security.adapter.out;

import com.mambanegra.fitnesscenterapi.security.application.port.out.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderAdapter implements FileReader {
    @Override
    public String readFile(String uri) {
        Path filePath = Path.of(uri);
        if (Files.isRegularFile(filePath) && Files.isReadable(filePath)) {
            try {
                return Files.readString(filePath);
            } catch (IOException e) {
                throw new RuntimeException("Cant read the file", e);
            }
        }
        throw new RuntimeException("No such file or user have no permission");
    }
}
