package com.parser.antipn.parser.iodata;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
//выходные данные в таком формате в JSON

@JsonPropertyOrder({"id","orderId","amount","comment","filename","line","result"})
public class OutputDataRow {

    private int Id;
    private int orderId;
    private double amount;
    @JsonIgnore
    private String currency;
    private String comment;
    @JsonProperty("filename")
    private String fileName;
    private int line;
    private String result;

}
