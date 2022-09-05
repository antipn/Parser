package com.parser.antipn.parser.readers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WebReader implements DataCsvSupplier {
    private final String filePath = "Web source";
    private final String fileName = "Web input data";
    private String inputData;

    public WebReader(String inputData) {
        this.inputData = inputData;
    }

    @Override
    public List<String> getLines() {
        return Arrays.stream(inputData.split(";")).collect(Collectors.toList());
    }

    @Override
    public String getFilename() {
        return fileName;
    }
}
