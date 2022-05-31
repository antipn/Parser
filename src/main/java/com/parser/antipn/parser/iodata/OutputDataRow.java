package com.parser.antipn.parser.iodata;

import lombok.Data;

@Data
public class OutputDataRow {


    private int id;
    private double amount;
    private String currency;
    private String comment;
    private String fileName;
    private int line;
    private String result;


}
