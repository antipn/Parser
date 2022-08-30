package com.parser.antipn.parser;

import com.parser.antipn.parser.iodata.OutputDataRow;

import java.util.List;

public interface OrderParser {

    public List<OutputDataRow> parse(String pathFile);
}
