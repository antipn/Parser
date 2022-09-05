package com.parser.antipn.parser;

import com.parser.antipn.parser.models.OutputDataRow;
import com.parser.antipn.parser.readers.DataCsvSupplier;

import java.util.List;

public interface OrderParser {

    public List<OutputDataRow> parse(DataCsvSupplier supplier);
}
