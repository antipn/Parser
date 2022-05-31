package com.parser.antipn.parser.csv;

import com.parser.antipn.parser.iodata.OutputDataRow;

import java.util.ArrayList;
import java.util.List;

public class ConverterToJson {

    //output format
    //{“id”:1,“orderId”:1,”amount”:100,”comment”:”оплата заказа”,”filename”:”orders.csv”,”line”:1,”result”:”OK”}
    public List<OutputDataRow> convert(CsvInputDataFile csvInputDataFile) {
        List<OutputDataRow> outputDataRows = new ArrayList<>();
        for (CsvInputDataRow input : csvInputDataFile.getData()) {
            OutputDataRow outputDataRow = new OutputDataRow();
            if (input.getData() != null) {
                outputDataRow.setId(input.getData().getId());                   //orderId
                outputDataRow.setAmount(input.getData().getSum());              //amount
                outputDataRow.setCurrency(input.getData().getCurrency());       //currency
                outputDataRow.setComment(input.getData().getDescription());     //comment
                outputDataRow.setFileName(csvInputDataFile.getFileName());      //filename
                outputDataRow.setLine(input.getLine());                         //line
                outputDataRow.setResult(input.getResult());                     //result
                outputDataRows.add(outputDataRow);
            }
        }

        return outputDataRows;
    }

    public String convertToJson(OutputDataRow outputDataRow) {

        int id = outputDataRow.getId();
        int orderId = outputDataRow.getId();
        double amount = outputDataRow.getAmount();
        String comment = outputDataRow.getComment();
        String fileName = outputDataRow.getFileName();
        int line = outputDataRow.getLine();
        String result = outputDataRow.getResult();

        String outputString = String.format("{\"id\":\"%d\",\"orderId\":\"%d\",\"amount\":\"%.2f\",\"comment\":\"%s\",\"filename\":\"%s\",\"line\":\"%d\",\"result\":\"%s\"}", id, orderId, amount, comment, fileName, line, result);


        return outputString;
    }
}
