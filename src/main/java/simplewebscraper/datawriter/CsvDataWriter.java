package simplewebscraper.datawriter;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * The type Csv data writer.
 */
public class CsvDataWriter implements DataWriter {
    /**
     * The File name.
     */
    String fileName;

    /**
     * Instantiates a new Csv data writer.
     *
     * @param fileName the file name
     */
    public CsvDataWriter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void writeDataToFile(ArrayList<String> columnNames, HashMap<String, List<String>> columns) {
        File file = new File(fileName);
        ArrayList<String> dataContent = new ArrayList<>();

        try {
            FileWriter output = new FileWriter(file);
            CSVWriter writer = new CSVWriter(output);
            String[] header = columnNames.toArray(new String[0]);
            writer.writeNext(header);

            int max = 0;
            for (String s : columnNames) {
                if (columns.get(s).size() >= max)
                    max = columns.get(s).size();
            }

            for (int i = 0; i < max; i++) {
                for (String s : columnNames) {
                    if (columns.get(s).get(i) != null)
                        dataContent.add(columns.get(s).get(i));
                    else
                        dataContent.add("Null");
                }
                String[] data = dataContent.toArray(new String[0]);
                writer.writeNext(data);

                dataContent.clear();
                Arrays.fill(data, null);
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


