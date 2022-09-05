package com.parser.antipn.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.parser.antipn.parser.models.OutputDataRow;

public interface OutputConverter {
    public String convert(OutputDataRow outputDataRow) throws JsonProcessingException;
}
