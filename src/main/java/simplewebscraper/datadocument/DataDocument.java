package simplewebscraper.datadocument;

import simplewebscraper.IDataCollector;
import simplewebscraper.HtmlElement;
import simplewebscraper.HtmlElements;
import simplewebscraper.datawriter.DataWriter;
import simplewebscraper.datawriter.JsonDataWriter;
import simplewebscraper.exception.FieldException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to store data from a site as a document.
 */
public class DataDocument {
    /**
     * The Backend Class needed to getData.
     */
    DataDocumentBackend backend;

    /**
     * HashMap to store fields and HtmlElements (data) in the document.
     */
    HashMap<String, HtmlElements> fieldsHtmlElements;

    /**
     * Create DataDocument
     *
     * @param backend the backend
     * @return DataDocument
     */
    public static DataDocument create(DataDocumentBackend backend) {
        return new DataDocument(backend);
    }

    private DataDocument(DataDocumentBackend backend) {
        this.backend = backend;
        this.fieldsHtmlElements = new HashMap<>();
    }

    /**
     * Add field and get data using xpath.
     *
     * @param fieldName the field name
     * @param xpath     the xpath
     * @throws FieldException the field not found exception
     */
    public void addField(String fieldName, String xpath) throws FieldException {
        if (!fieldsHtmlElements.containsKey(fieldName))
            fieldsHtmlElements.put(fieldName, backend.getData(xpath));
        else
            throw new FieldException("Field already exists");
    }

    /**
     * Add field and add data using IDataCollector (lambda expression).
     *
     * @param fieldName the field name
     * @param collector the collector <code>WebScraper scraper = WebScraper.get("https://www.hiof.no");         DataDocumentBackend backend = new DataDocumentBackend(scraper);         DataDocument document = new DataDocument(backend);         document.addField("Arrangementer", "//a[@class='vrtx-event-component-title summary']"); </code>
     * @throws FieldException the field not found exception
     */
    public void addField(String fieldName, IDataCollector collector) throws FieldException {
        if (!fieldsHtmlElements.containsKey(fieldName))
            fieldsHtmlElements.put(fieldName, backend.getData(collector));
        else
            throw new FieldException("Field already exists");
    }


    /**
     * Gets hashmap of collection of data.
     *
     * @return Hashmap with fields and data (String).
     */
    public HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> documentData = new HashMap<>();
        fieldsHtmlElements.forEach(
                (key, value)
                        -> {
                    if (!documentData.containsKey(key))
                        documentData.put(key, new ArrayList<>());
                        documentData.get(key).addAll(fieldsHtmlElements.get(key).toListAsString());
                }
        );
        return documentData;
    }

    /**
     * Gets data by field name as a list of strings.
     *
     * @param fieldName the field name
     * @return the data as list of strings by field name
     * @throws FieldException the field not found exception
     */
    public List<String> getDataByFieldName(String fieldName) throws FieldException {
        ArrayList<String> text = new ArrayList<>();
        if (!fieldsHtmlElements.containsKey(fieldName))
            throw new FieldException("Field does not exist");

        return fieldsHtmlElements.get(fieldName).toListAsString();
    }


    /**
     * Gets data in json format as string.
     *
     * @return the data as json string
     */
    public String getDataAsJsonString() {
        DataWriter writer = new JsonDataWriter("null");
        return writer.dataAsString(getData());
    }
}
