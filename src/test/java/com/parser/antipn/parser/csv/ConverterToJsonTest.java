package com.parser.antipn.parser.csv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.parser.antipn.parser.iodata.OutputDataRow;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

class ConverterToJsonTest {

    @Test
    void convertToJson() throws JSONException, JsonProcessingException {
        //тестирование что правильно формируются JSON из строк файла которые мы подаем на вход
//        CsvParser csvParser = new CsvParser();
//        for (CsvInputDataRow input : csvParser.parseRows(csvParser.readFile("src//main//resources//input_files//input_data.txt"))) {
//            System.out.println(input);
//        }
//        System.out.println("__________");
//        for (CsvInputDataRow input : csvParser.parseCsv("src//main//resources//input_files//input_data.txt").getData()) {
//            System.out.println(input);
//        }
//      System.out.println("__________");
//        for (OutputDataRow outputDataRow : converterToJson.convert(csvParser.parseCsv("src//main//resources//input_files//input_data.txt"))) {
//            System.out.println(converterToJson.convertToJson(outputDataRow));
//        }
// CsvParser csvParser = new CsvParser();
        ConverterToJson converterToJson = new ConverterToJson();
        OutputDataRow outputDataRow = new OutputDataRow();

        outputDataRow.setId(1);
        outputDataRow.setOrderId(1);
        outputDataRow.setAmount(1.9);
        outputDataRow.setCurrency("USD");
        outputDataRow.setComment("Оплата заказа тестового");
        outputDataRow.setFileName("Тестовы файл.csv");
        outputDataRow.setLine(1);
        outputDataRow.setResult("OK");

        String expected = "{\"id\":1,\"orderId\":1,\"amount\":1.9,\"comment\":\"Оплата заказа тестового\",\"filename\":\"Тестовы файл.csv\",\"line\":1,\"result\":\"OK\"}";

        JSONAssert.assertEquals(expected, converterToJson.manualConvertToJson(outputDataRow), true);
        JSONAssert.assertEquals(expected, converterToJson.autoConverterToJSON(outputDataRow), true);

        System.out.println("Manual JSON: ");
        System.out.println(expected);

        System.out.println("From our converter JSON: ");
        System.out.println(converterToJson.manualConvertToJson(outputDataRow));

        System.out.println("From Jackson");
        System.out.println(converterToJson.autoConverterToJSON(outputDataRow));

    }
}