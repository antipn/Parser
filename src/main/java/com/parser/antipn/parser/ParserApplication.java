package com.parser.antipn.parser;


import com.parser.antipn.parser.readers.DataCsvSupplier;
import com.parser.antipn.parser.readers.FileSystemReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ParserApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(ParserApplication.class, args);
        CsvParser parser = context.getBean(CsvParser.class);
        parser.parse(new FileSystemReader("src//main//resources//input_files//input_data.txt"));

    }

}
