package com.parser.antipn.parser.contollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.parser.antipn.parser.ConverterToJson;
import com.parser.antipn.parser.OrderParser;
import com.parser.antipn.parser.readers.DataCsvSupplier;
import com.parser.antipn.parser.readers.WebReader;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/app/v1") //наш контроллер начинает с этого пути + остальное методами и их маппингом
public class ParserController {
    private final OrderParser parser;
    private final ConverterToJson converter;

    public ParserController(OrderParser parser, ConverterToJson converter) {
        this.parser = parser;
        this.converter = converter;
    }

    //localhost:8080/app/v1/convert/csv
    @PostMapping("/convert/csv")
    public List<String> parseCsv(@RequestBody DataCsvSupplier supplier) {

        List<String> result = new ArrayList<>();
//        parser.parse(supplier).stream()
//                .forEach(elem -> {
//                    try {
//                        result.add(converter.convert(elem));
//                    } catch (JsonProcessingException e) {
//                        e.printStackTrace();
//                    }
//                });
        System.out.println("метод отработал");
        return null;
    }

    //localhost:8080/app/v1/convert/csv/test
    @GetMapping("/convert/csv/test")
    public String test(){
        return "Success";
    }
}
