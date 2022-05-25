package com.parser.antipn.parser;

import java.util.ArrayList;
import java.util.List;

public class ConverterToJson {

    //Выходные данные
    //{“id”:1,“orderId”:1,”amount”:100,”comment”:”оплата заказа”,”filename”:”orders.csv”,”line”:1,”result”:”OK”}
    public List<OutputDataRow> convert(CsvInputDataFile csvInputDataFile) {

        List<OutputDataRow> outputDataRows = new ArrayList<>();


        for (int i = 0; i < csvInputDataFile.getData().size(); i++) {

            OutputDataRow outputDataRow = new OutputDataRow();

            if (csvInputDataFile.getData().get(i).getData() != null) { //если есть данные и строка обработана
                outputDataRow.setId(csvInputDataFile.getData().get(i).getData().getId());                   //orderId
                outputDataRow.setAmount(csvInputDataFile.getData().get(i).getData().getSum());               //amount
                outputDataRow.setCurrency(csvInputDataFile.getData().get(i).getData().getCurrency());       // currency
                outputDataRow.setComment(csvInputDataFile.getData().get(i).getData().getDescription());     //comment
                outputDataRow.setFileName(csvInputDataFile.getFileName());                                  //filename
                outputDataRow.setLine(csvInputDataFile.getData().get(i).getLine());                         //line
                outputDataRow.setResult(csvInputDataFile.getData().get(i).getResult());                     //result
                outputDataRows.add(outputDataRow);
            } else{

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
