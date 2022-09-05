package com.parser.antipn.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parser.antipn.parser.models.CsvInputDataFile;
import com.parser.antipn.parser.models.CsvInputDataRow;
import com.parser.antipn.parser.models.OutputDataRow;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//класс формирование выходных данных в JSON формате
@Component
public class ConverterToJson implements OutputConverter {

    //output format
    //{“id”:1,“orderId”:1,”amount”:100,”comment”:”оплата заказа”,”filename”:”orders.csv”,”line”:1,”result”:”OK”}
    public List<OutputDataRow> convert(CsvInputDataFile csvInputDataFile) {
        List<OutputDataRow> outputDataRows = new ArrayList<>();
        for (CsvInputDataRow input : csvInputDataFile.getData()) {
            OutputDataRow outputDataRow = new OutputDataRow();
            if (input.getData() != null) {
                outputDataRow.setOrderId(input.getData().getId());              //orderId
                outputDataRow.setAmount(input.getData().getSum());              //amount
                outputDataRow.setCurrency(input.getData().getCurrency());       //currency
                outputDataRow.setComment(input.getData().getDescription());     //comment
                outputDataRow.setFileName(csvInputDataFile.getFileName());      //filename
                outputDataRow.setLine(input.getLine());                         //line
                outputDataRow.setResult(input.getResult());                     //result
                outputDataRows.add(outputDataRow);
            } else {
                //для вывода неправильных строк, в задании не сказано в каком формате точно выводить часть данных заменено на -1 и null
                outputDataRow.setOrderId(null);                                 //orderId
                outputDataRow.setAmount(null);                                  //amount
                outputDataRow.setCurrency(null);                                //currency
                outputDataRow.setComment(null);                                 //comment
                outputDataRow.setFileName(csvInputDataFile.getFileName());      //filename
                outputDataRow.setLine(input.getLine());                         //line
                outputDataRow.setResult(input.getResult());                     //result
                outputDataRows.add(outputDataRow);
            }
        }

        return outputDataRows;
    }

    public String manualConvertToJson(OutputDataRow outputDataRow) {

        int id = outputDataRow.getOrderId();
        int orderId = outputDataRow.getOrderId();
        double amount = outputDataRow.getAmount();
        String comment = outputDataRow.getComment();
        String fileName = outputDataRow.getFileName();
        int line = outputDataRow.getLine();
        String result = outputDataRow.getResult();

        String outputString = String.format("{\"id\":%d,\"orderId\":%d,\"amount\":%.2f,\"comment\":\"%s\",\"filename\":\"%s\",\"line\":%d,\"result\":\"%s\"}", id, orderId, amount, comment, fileName, line, result);


        return outputString;
    }

    public String autoConverterToJSON(OutputDataRow outputDataRow) throws JsonProcessingException {

        String jsonResult = new ObjectMapper().writeValueAsString(outputDataRow);

        return jsonResult;
    }

    @Override
    public String convert(OutputDataRow outputDataRow) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(outputDataRow);
    }
}
