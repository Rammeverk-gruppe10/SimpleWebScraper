package simplewebscraper.datawriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The interface Data writer.
 */
public interface DataWriter {
    /**
     * Write data to file.
     *
     * @param columnNames the column names
     * @param columns     the columns
     */
    void writeDataToFile(ArrayList<String> columnNames, HashMap<String, List<String>> columns);

    /**
     * Data as string string.
     *
     * @param fields the fields
     * @return the string in json format
     */
    String dataAsString(HashMap<String, List<String>> fields);
}
