package com.parser.antipn.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class CsvParser {
    private static String errorString = "";

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
        String testOK7 = "1,200.5,EUR,оплата заказа2";

        testInputStrings.add(testOK);
        testInputStrings.add(testNOK1);
        testInputStrings.add(testNOK2);
        testInputStrings.add(testNOK3);
        testInputStrings.add(testNOK4);
        testInputStrings.add(testNOK5);
        testInputStrings.add(testNOK6);
        testInputStrings.add(testOK7);

        CsvParser test = new CsvParser(); //test variable

        System.out.println("! Testing individual rows");
        for (int i = 0; i < testInputStrings.size(); i++) {
            if (test.parseRow(testInputStrings.get(i)) == null) {
                System.out.println(i + " test is not passed for " + testInputStrings.get(i));
            } else {
                System.out.println(i + " test is passed for " + testInputStrings.get(i));
            }
        }


        System.out.println("! Testing list of input rows");
        test.parseRows(null); //nok null list
        List<String> emptyList = new ArrayList<>();
        test.parseRows(emptyList); //nok empty list
        test.parseRows(testInputStrings); //ok but not all
        System.out.println(test.parseRows(testInputStrings).size()); //добавится только одна строка, null, пустые и неправильные не добавляются в список


        System.out.println("! Testing file reading");
        test.readFile("src//main//resources//input_files//input_data.txt");


        System.out.println("! Testing file reading -> list all rows with result");
        //list all rows from file
        List<String> testListFromFile = test.readFile("src//main//resources//input_files//input_data.txt");
        // list of good rows
        List<CsvInputDataRow> testListCsvInputDataRow = test.parseRows(testListFromFile);
        //result list of processed rows from original file
        System.out.println("Result list of processed all rows from original file");
        for (CsvInputDataRow row : testListCsvInputDataRow) {
            System.out.println(row);
        }

        System.out.println("! parseCsv -> CsvInputDataFile");
        for (CsvInputDataRow csvInputDataRow: test.parseCsv("src//main//resources//input_files//input_data.txt").getData() ) {
            System.out.println(csvInputDataRow);
        }


        System.out.println("! List OutputDataRow from converterToJson.convert");
        CsvInputDataFile csvFile = test.parseCsv("src//main//resources//input_files//input_data.txt");
        ConverterToJson converterToJson = new ConverterToJson();
        for (OutputDataRow output : converterToJson.convert(csvFile)) {
            System.out.println(output);
        }

        System.out.println("! Testing converterToJson.convertToJson");
        for (OutputDataRow output : converterToJson.convert(csvFile)) {
            System.out.println(converterToJson.convertToJson(output));
        }



    }

    // parsing one row -> InputDataRow
    public InputDataRow parseRow(String inputRow) throws Exception {
        InputDataRow inputDataRow = new InputDataRow();
        if (inputRow == null) {
            System.out.println("Input row is null");
            return null;
        }
        try {
            String[] splitedRow = inputRow.split(",");
            //checking length of parameters
            for (int i = 0; i < splitedRow.length; i++) {
                if (splitedRow[i].length() == 0) {
                    errorString = "Empty data in row: " + inputRow + " , on position = " + (i + 1);
                    //System.out.println("Empty data in row: " + inputRow + " , on position = " + (i + 1));
                    System.out.println(errorString);
                    return null;
                }
            }
            //checking count of parameters
            if (splitedRow.length != 4) {
                System.out.println();
                errorString = "Input row has incorrect format, are required 4, you have " + splitedRow.length;
                System.out.println(errorString);

                return null;
            }
            //setting data to output format
            inputDataRow.setId(Integer.parseInt(splitedRow[0])); //id
            inputDataRow.setSum(Double.parseDouble(splitedRow[1])); //sum
            inputDataRow.setCurrency(splitedRow[2]); //currency
            inputDataRow.setDescription(splitedRow[3]); //description
            return inputDataRow;
        } catch (NumberFormatException e) {
            System.out.println(e);
            //errorString = e.getMessage();
            errorString = e.toString();
            //System.out.println("Part of data can't be converted to number");
            return null;
        } catch (Exception e) {
            System.out.println(e);
            //errorString = e.getMessage();
            errorString = e.toString();
            //System.out.println("Something went wrong...");
            return null;
        }
    }

    //processing list of rows -> list of CsvInputDataRow
    public List<CsvInputDataRow> parseRows(List<String> inputRows) throws Exception {
        // standard checking
        if (inputRows == null) {
            System.out.println("Input list is null");
            return null;
        }
        if (inputRows.isEmpty()) {
            System.out.println("Input list is empty");
            return null;
        }
        List<CsvInputDataRow> result = new ArrayList<>();
        for (int i = 0; i < inputRows.size(); i++) {
            CsvInputDataRow csvInputDataRow = new CsvInputDataRow(); // data line result
            //System.out.println("Обработка " + i + " строки " + inputRows.get(i));
            //ВАЛЮТА ТУТ
            InputDataRow inputDataRow = parseRow(inputRows.get(i)); //processing row
            csvInputDataRow.setData(inputDataRow); //adding processed row
            csvInputDataRow.setLine(i + 1); //adding row number
            if (inputDataRow != null) { //if succeed parsing -> OK
                csvInputDataRow.setResult("OK");
            } else { //if parsing tailed -> errorString
                csvInputDataRow.setResult(errorString); // I hope that is accessible
            }
            //csvInputDataRow is ready to be kept
            //System.out.println("Результат обработки " + inputDataRow);
            result.add(csvInputDataRow);
        }
        //return resultList;
        return result;
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
            //System.out.println("!!!!!!!!!!!");
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            //не работает    String[] fileRows = file.list();
            String fileRow;

            while ((fileRow = fileReader.readLine()) != null) {
                resultList.add(fileRow);
            }
            //System.out.println("Размер списка со строками " + resultList.size());
            return resultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("There is problem with file " + file.getName());
        }
        return null;
    }

    // parsing csv: fileName - > CsVInputDataFile
    public CsvInputDataFile parseCsv(String fileName) {
        CsvInputDataFile csvInputDataFile = new CsvInputDataFile();

        try {
            csvInputDataFile.setData(parseRows(readFile(fileName)));
            csvInputDataFile.setFileName(fileName);
            return csvInputDataFile;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null; //если не получилось
    }
}
