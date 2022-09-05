package com.parser.antipn.parser.readers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//получение файла из FileSystem
public class FileSystemReader implements DataCsvSupplier {
    private String filePath;

    public FileSystemReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> getLines() {
        Path pathFile = Paths.get(filePath);
        boolean exists = Files.exists(pathFile);
        if (exists == true) {
            try {
                return Files.readAllLines(pathFile);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("There is problem with file " + pathFile.getFileName());
            }
        }
        return null;
    }

    @Override
    public String getFilename() {
        return Paths.get(filePath).getFileName().toString();
    }
}
