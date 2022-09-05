package com.parser.antipn.parser.readers;

import java.util.List;

public interface DataCsvSupplier {
    List<String> getLines();
    String getFilename();

}
