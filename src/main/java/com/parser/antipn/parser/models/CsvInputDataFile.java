package com.parser.antipn.parser.models;

import lombok.Data;

import java.util.List;

@Data
public class CsvInputDataFile {
    String fileName;
    List<CsvInputDataRow> data;
}
