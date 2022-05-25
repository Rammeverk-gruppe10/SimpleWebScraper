package simplewebscraper.datacollection;

import simplewebscraper.*;
import simplewebscraper.datawriter.CsvDataWriter;
import simplewebscraper.datawriter.DataWriter;
import simplewebscraper.datawriter.JsonDataWriter;
import simplewebscraper.exception.InvalidColumnException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The type Data collection.
 */
public class DataCollection {
    /**
     * The Columns.
     */
    HashMap<String, List<String>> columns;
    /**
     * The Column name.
     */
    ArrayList<String> columnNames;
    /**
     * The Data.
     */
    HashMap<String, DataCollector> data;
    /**
     * The Web scraper.
     */
    WebScraper webScraper;

    private DataCollection(WebScraper webScraper) {
        this.webScraper = webScraper;
        this.columns = new HashMap<>();
        this.data = new HashMap<>();
        this.columnNames = new ArrayList<>();
    }

    /**
     * Create data collection.
     *
     * @param webScraper the web scraper
     * @return the data collection
     */
    public static DataCollection create(WebScraper webScraper) {
        return new DataCollection(webScraper);
    }

    /**
     * Set a new WebScraper
     *
     * @param webscraper the webscraper to be used
     */
    public void setWebScraper(WebScraper webscraper) {
        this.webScraper = webscraper;
    }

    /**
     * Create column.
     *
     * @param columnName the column name
     * @throws InvalidColumnException the invalid column exception
     */
    public void createColumn(String columnName) throws InvalidColumnException {
        if (this.columns.containsKey(columnName))
            throw new InvalidColumnException("Column already exists");

        this.columnNames.add(columnName);
        this.columns.put(columnName, new ArrayList<>());
    }

    /**
     * Create column and add data.
     *
     * @param columnName the column name
     * @param data       the data
     * @throws InvalidColumnException the invalid column exception
     */
    public void createColumnAndAddData(String columnName, HtmlElements data) throws InvalidColumnException {
        if (this.columns.containsKey(columnName))
            throw new InvalidColumnException("Column already exists");

        ArrayList<String> dataList = data.toListAsString();
        this.columnNames.add(columnName);
        this.columns.put(columnName, dataList);
    }

    /**
     * Create column and add data.
     *
     * @param columnName the column name
     * @param xpath      the selector
     * @throws InvalidColumnException the invalid column exception
     */
    public void createColumnAndAddData(String columnName, String xpath) throws InvalidColumnException {
        if (this.columns.containsKey(columnName))
            throw new InvalidColumnException("Column already exists");

        this.columnNames.add(columnName);
        this.columns.put(columnName, webScraper.getHtmlElementsByXpath(xpath).toListAsString());
    }

    /**
     * Create column and add data.
     *
     * @param columnName the column name
     * @param collector  the collector
     * @throws InvalidColumnException the invalid column exception
     */
    public void createColumnAndAddDataLocation(String columnName, DataCollector collector) throws InvalidColumnException {
        if (this.columns.containsKey(columnName))
            throw new InvalidColumnException("Column already exists");

        this.columnNames.add(columnName);
        this.data.put(columnName, collector);

        this.columns.put(columnName, new ArrayList<>());
    }

    /**
     * Create column and add data.
     *
     * @param columnName the column name
     * @param collector  the collector
     * @param webScraper the web scraper
     * @throws InvalidColumnException the invalid column exception
     */
    public void createColumnAndAddDataLocation(String columnName, DataCollector collector, WebScraper webScraper) throws InvalidColumnException {
        if (columns.containsKey(columnName))
            throw new InvalidColumnException("Column already exists");

        this.columnNames.add(columnName);
        ArrayList<String> dataLocation = collector.collectHtmlElements(webScraper).toListAsString();
        this.columns.put(columnName, dataLocation);
    }


    // BUG if data not already is collected using - collectData()
    /**
     * Append data to existing column.
     *
     * @param columnName the column name
     * @param collector  the collector
     * @throws InvalidColumnException the invalid column exception
     */
    public void addDataLocationToExistingColumn(String columnName, DataCollector collector) throws InvalidColumnException {
        if (columns.containsKey(columnName)) {
            data.put(columnName, collector);
        } else
            throw new InvalidColumnException("Column does not exist");
    }

    /**
     * Add data to existing column.
     *
     * @param columnName the column name
     * @param data       the data
     * @throws InvalidColumnException the invalid column exception
     */
    public void addDataToExistingColumn(String columnName, HtmlElements data) throws InvalidColumnException {
        if (columns.containsKey(columnName)) {
            ArrayList<String> dataList = data.toListAsString();
            columns.get(columnName).addAll(dataList);
        } else {
            throw new InvalidColumnException("Column does not exist");
        }
    }

    /**
     * Add data to column.
     *
     * @param columnName the column name
     * @param elements   the elements
     */
    public void addDataToColumn(String columnName, HtmlElements elements) {
        if (columns.containsKey(columnName)) {
            for (HtmlElement element : elements) {
                columns.get(columnName).add(element.getText());
            }
        }
    }

    /**
     * Add data to column.
     *
     * @param columnName the column name
     * @param string     the string
     */
    public void addDataToColumn(String columnName, String string) {
        if (columns.containsKey(columnName)) {
            columns.get(columnName).add(string);
        } else
            System.out.println("Exception");
    }

    /**
     * Collects data.
     */
    public void collectData() {
        data.forEach((key, value) -> {
            if (this.columns.containsKey(key)) {
                ArrayList<String> dataToCollect = value.collectHtmlElements(this.webScraper).toListAsString();
                this.columns.get(key).addAll(dataToCollect);
            }
        });
        data = new HashMap<>();
    }

    // NEW METHOD
    public List<String> getDataAsList(String columnName) throws InvalidColumnException {
        if (!columns.containsKey(columnName))
            throw new InvalidColumnException("Column does not exist");
        return columns.get(columnName);
    }

    /**
     * Writes data to a JSON-file.
     *
     * @param fileName the name of the file
     */
    public void writeToJSON(String fileName) {
        DataWriter dataWriter = new JsonDataWriter(fileName);
        dataWriter.writeDataToFile(columnNames, columns);
    }

    /**
     * Writes data to a CSV-file.
     *
     * @param fileName the name of the file
     */
    public void writeToCSV(String fileName) {
        DataWriter dataWriter = new CsvDataWriter(fileName);
        dataWriter.writeDataToFile(columnNames, columns);
    }
}


