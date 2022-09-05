package com.parser.antipn.parser.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
//выходные данные в таком формате в JSON

@JsonPropertyOrder({"id","orderId","amount","comment","filename","line","result"})
public class OutputDataRow {

    private Integer Id;
    private Integer orderId;
    private Double amount;
    @JsonIgnore
    private String currency;
    private String comment;
    @JsonProperty("filename")
    private String fileName;
    private Integer line;
    private String result;

}
