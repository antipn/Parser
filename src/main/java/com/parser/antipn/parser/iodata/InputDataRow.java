package com.parser.antipn.parser.iodata;


import lombok.Data;

@Data
//входящие данные в таком формате
public class InputDataRow {

    private int id;
    private double sum;
    private String currency;
    private String description;
}
