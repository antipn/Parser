package com.parser.antipn.parser.models;


import lombok.Data;

@Data
public class CsvInputDataRow {
    private InputDataRow data;
    private int line;
    private String result;

}
