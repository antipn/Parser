package com.parser.antipn.parser;

import lombok.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CsvParser {


    public static void main(String[] args) throws Exception {
        //тестирование входящих
        List<String> testInputStrings = new ArrayList<>();

        String testOK = "1,100,USD,оплата заказа";
        String testNOK1 = "1a,100,USD,оплата заказа";
        String testNOK2 = "1,100a,USD,оплата заказа";
        String testNOK3 = "1,100,,оплата заказа";
        String testNOK4 = "1,100,USD,оплата заказа,test4";
        String testNOK5 = "1,100,USD";
        String testNOK6 = null;
        String testNOK7 = "1,200.5,EUR,оплата заказа2";

        testInputStrings.add(testOK);
        testInputStrings.add(testNOK1);
        testInputStrings.add(testNOK2);
        testInputStrings.add(testNOK3);
        testInputStrings.add(testNOK4);
        testInputStrings.add(testNOK5);
        testInputStrings.add(testNOK6);
        testInputStrings.add(testNOK7);

        CsvParser test = new CsvParser(); //test variable

        System.out.println("! Testing individual rows");
        for (int i = 0; i < testInputStrings.size(); i++) {
            if (test.csvParser(testInputStrings.get(i)) == null) {
                System.out.println(i + " test is not passed for " + testInputStrings.get(i));
            } else {
                System.out.println(i + " test is passed for " + testInputStrings.get(i));
            }
        }


        System.out.println("! Testing list of input rows");
        test.csvParserList(null); //nok null list
        List<String> emptyList = new ArrayList<>();
        test.csvParserList(emptyList); //nok empty list
        test.csvParserList(testInputStrings); //ok but not all
        System.out.println(test.csvParserList(testInputStrings).size()); //добавится только одна строка, null, пустые и неправильные не добавляются в список


        System.out.println("! Testing file reading");
        test.readFile("src//main//resources//input_files//input_data.txt");


        System.out.println("! Testing file reading -> list correct rows");
        //list all rows from file
        List<String> testListFromFile = test.readFile("src//main//resources//input_files//input_data.txt");
        // list of good rows
        List<InputDataRow> testListInputDataRow = test.csvParserList(testListFromFile);
        //result list of processed rows from original file
        System.out.println("Result list of processed rows from original file");
        for (InputDataRow row : testListInputDataRow) {
            System.out.println(row);
        }

    }

    // String: 1,100,USD,оплата заказа -> InputDataRow
    public InputDataRow csvParser(String inputRow) throws Exception {
        if (inputRow == null) {
            System.out.println("Input row is null");
            return null;
        }
        InputDataRow inputDataRow = new InputDataRow();

        try {
            String[] splitedRow = inputRow.split(",");
            //checking length of parameters
            for (int i = 0; i < splitedRow.length; i++) {
                if (splitedRow[i].length() == 0) {
                    System.out.println("Empty data in row: " + inputRow + " , on position = " + (i + 1));
                    return null;
                }

            }
            //checking count of parameters
            if (splitedRow.length != 4) {
                System.out.println();
                System.out.println("Input row has incorrect format, are required 4, you have " + splitedRow.length);
                return null;
            }
            //setting data to output format
            inputDataRow.setId(Integer.parseInt(splitedRow[0])); //id
            inputDataRow.setSum(Double.parseDouble(splitedRow[1])); //sum
            inputDataRow.setCurrency(splitedRow[2]); //currency
            inputDataRow.setDescription(splitedRow[3]); //description
            return inputDataRow;

        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("Part of row can't be converted to number");
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong...");
            return null;
        }
    }

    //processing list of rows -> list of InputDataRow
    public List<InputDataRow> csvParserList(List<String> inputRows) throws Exception {

        List<InputDataRow> resultList = new ArrayList<>();
        if (inputRows == null) {
            System.out.println("Input list is null");
            return null;
        }
        if (inputRows.isEmpty()) {
            System.out.println("Input list is empty");
            return null;
        }
        for (String inputRow : inputRows) {
            InputDataRow inputDataRow = csvParser(inputRow);
            if (inputDataRow != null) { //proceeded string -> InputDataRow is not null
                resultList.add(inputDataRow);
            }
        }
        return resultList;
    }

    //reading file and creating list with rows
    public List<String> readFile(String path) {
        File file;
        List<String> resultList = new ArrayList<>();
        if (!((file = new File(path)).canRead())) { //файл не существует и не может быть прочитан
            System.out.println("File doesnt exist and can not be read");
            return null;
        }
        try {
            System.out.println("!!!!!!!!!!!");

            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            //не работает    String[] fileRows = file.list();
            String fileRow;

            while ((fileRow = fileReader.readLine()) != null) {
                resultList.add(fileRow);
            }
            System.out.println("Размер списка со строками " + resultList.size());
            return resultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("There is problem with file " + file.getName());
        }
        return null;
    }

}
