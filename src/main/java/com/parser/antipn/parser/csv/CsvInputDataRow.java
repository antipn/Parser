package com.parser.antipn.parser.csv;


import com.parser.antipn.parser.iodata.InputDataRow;
import lombok.Data;

@Data
public class CsvInputDataRow {
    private InputDataRow data;
    private int line;
    private String result;

}
