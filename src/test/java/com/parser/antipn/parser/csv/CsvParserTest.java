package com.parser.antipn.parser.csv;

import com.parser.antipn.parser.CsvParser;
import com.parser.antipn.parser.exception.ErrorRowCatcher;
import com.parser.antipn.parser.models.InputDataRow;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CsvParserTest {

    CsvParser csvParser = new CsvParser();
    //List<String> listTest = new ArrayList<>();


    public CsvParserTest() {
//        String string1 = "1,100,USD,оплата заказа";
//        String string2 = "1,200.5,EUR,оплата заказа2";
//        String stringNok1 = "1a,100,USD,оплата заказа";
//        String stringNok2 = "1,100a,USD,оплата заказа";
//        String stringNok3 = "1,100,,оплата заказа";
//        String stringNok4 = "1,100,USD,оплата заказа,test4";
//        String stringNok5 = "1,100,USD";
//        String stringNok6 = null;
//        String stringNok7 = "";
//        listTest.add(string1);
//        listTest.add(string2);
//        listTest.add(stringNok1);
//        listTest.add(stringNok2);
//        listTest.add(stringNok3);
//        listTest.add(stringNok4);
//        listTest.add(stringNok5);
//        listTest.add(stringNok6);
//        listTest.add(stringNok7);
    }


    @DisplayName("Testing if input row is null")
    @Test
    public void parseRowIsNull() {
        ErrorRowCatcher exception = Assertions.assertThrows(ErrorRowCatcher.class, () -> {
            csvParser.parseRow(null);
        });
    }

    @DisplayName("Testing if input row is blank")
    @Test
    public void parseRowIsBlank() {
        ErrorRowCatcher exception = Assertions.assertThrows(ErrorRowCatcher.class, () -> {
            csvParser.parseRow("");
        });
    }

    @DisplayName("Testing if input row has less or more parameters that required (4)")
    @Test
    public void parseRowLackOfFilledFields() {
        List<String> listIncorectRows = new ArrayList<>();

        listIncorectRows.add("1,100,USD,оплата заказа,test4"); // > 4 parameters
        listIncorectRows.add("1,100,USD"); // < 4 parameters

        for (String input : listIncorectRows) {
            if (input.split(",").length != 4) {
                ErrorRowCatcher exeption = Assertions.assertThrows(ErrorRowCatcher.class, () -> {
                    csvParser.parseRow(input);
                });
            }
        }
    }

    @DisplayName("Testing acceptable type of input data less or more parameters that required (4)")
    @Test
    public void parseRowIncorrectFormatOfFieldInRow() {
        List<String> listIncorectRows = new ArrayList<>();

        listIncorectRows.add("1a,100,USD,оплата заказа"); // 1st parameters - int - there is string
        listIncorectRows.add("2,1000a,USD,оплата заказа2"); // 2nd parameters - double - there is string

        for (String input : listIncorectRows) {
            ErrorRowCatcher exeption = Assertions.assertThrows(ErrorRowCatcher.class, () -> {
                csvParser.parseRow(input);
            });
        }
    }

    @DisplayName("Testing that passing row is correct")
    @Test
    public void parseRowIsCorrect() {
        //входящий список строк
        List<String> listRows = new ArrayList<>();
        listRows.add("1,100,USD,оплата заказа");
        listRows.add("2,1000,EUR,оплата заказа 2");

        //вручную сделали парсинг которые будем ожидать от метода
        InputDataRow actualInputData1 = new InputDataRow();
        actualInputData1.setId(1);
        actualInputData1.setSum(100);
        actualInputData1.setCurrency("USD");
        actualInputData1.setDescription("оплата заказа");

        InputDataRow actualInputData2 = new InputDataRow();
        actualInputData2.setId(2);
        actualInputData2.setSum(1000);
        actualInputData2.setCurrency("EUR");
        actualInputData2.setDescription("оплата заказа 2");
        //список данные ручной обработки
        List<InputDataRow> actualList = new ArrayList<>();
        actualList.add(actualInputData1);
        actualList.add(actualInputData2);
        //немножно стрима
        List<InputDataRow> expectedInputData = listRows.stream()
                .map(row -> csvParser.parseRow(row)) //обрабатываем каждую строку
                .collect(Collectors.toList());  //каждую обработанную строку в список
        //нам необходимо проверить что InputDataRow сделаланная вручную == InputDataRow что вернул метод
        Assertions.assertEquals(expectedInputData, actualList);
    }

    @DisplayName("ID with some letter -> error")
    @Test
    public void testIdWithLetter() {
        List<String> listIncorectRows = new ArrayList<>();

        listIncorectRows.add("1a,100,USD,оплата заказа");
        listIncorectRows.add("2a,1000,USD,оплата заказа2");

        for (String input : listIncorectRows) {
            ErrorRowCatcher exeption = Assertions.assertThrows(ErrorRowCatcher.class, () -> {
                csvParser.parseRow(input);
            });
        }
    }

    @DisplayName("There is missing parameter (empty) in row")
    @Test
    public void testMissingParamateres(){
        List<String> listIncorectRows = new ArrayList<>();

        listIncorectRows.add("1,,USD,оплата заказа");
        listIncorectRows.add("2,1000,оплата заказа2");

        for (String input : listIncorectRows) {
            ErrorRowCatcher exeption = Assertions.assertThrows(ErrorRowCatcher.class, () -> {
                csvParser.parseRow(input);
            });
        }
    }


}
