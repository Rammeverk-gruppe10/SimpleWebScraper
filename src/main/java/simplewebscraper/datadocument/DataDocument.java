package simplewebscraper.datadocument;

import simplewebscraper.IDataCollector;
import simplewebscraper.HtmlElement;
import simplewebscraper.HtmlElements;
import simplewebscraper.datawriter.DataWriter;
import simplewebscraper.datawriter.JsonDataWriter;
import simplewebscraper.exception.FieldNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The type Data document.
 */
public class DataDocument {
    /**
     * The Backend.
     */
    DataDocumentBackend backend;

    /**
     * The Fields html elements.
     */
    HashMap<String, HtmlElements> fieldsHtmlElements;
    /**
     * The Fields html element.
     */
    HashMap<String, List<HtmlElement>> fieldsHtmlElement;

    /**
     * Instantiates a new Data document.
     *
     * @param backend the backend
     */
    public DataDocument(DataDocumentBackend backend) {
        this.backend = backend;
        this.fieldsHtmlElements = new HashMap<>();
        this.fieldsHtmlElement = new HashMap<>();
    }

    /**
     * Add field.
     *
     * @param fieldName the field name
     * @param xpath     the xpath
     * @throws FieldNotFoundException the field not found exception
     */
    public void addField(String fieldName, String xpath) throws FieldNotFoundException {
        if (!fieldsHtmlElements.containsKey(fieldName))
            fieldsHtmlElements.put(fieldName, backend.getData(xpath));
        else
            throw new FieldNotFoundException("Field already exists");
    }

    /**
     * Add field.
     *
     * @param fieldName the field name
     * @param collector the collector
     * @throws FieldNotFoundException the field not found exception
     */
    public void addField(String fieldName, IDataCollector collector) throws FieldNotFoundException {
        if (!fieldsHtmlElements.containsKey(fieldName))
            fieldsHtmlElements.put(fieldName, backend.getData(collector));
        else
            throw new FieldNotFoundException("Field already exists");
    }


    /**
     * Gets data.
     *
     * @return the data
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
     * Gets data by field name.
     *
     * @param fieldName the field name
     * @return the data by field name
     * @throws FieldNotFoundException the field not found exception
     */
    public List<String> getDataByFieldName(String fieldName) throws FieldNotFoundException {
        ArrayList<String> text = new ArrayList<>();
        if (!fieldsHtmlElements.containsKey(fieldName))
            throw new FieldNotFoundException("Field does not exist");

        return fieldsHtmlElements.get(fieldName).toListAsString();
    }

    /**
     * Print fields.
     */
    public void printFields() {
        fieldsHtmlElements.forEach(
                (key, value)
                        -> {
                    for (HtmlElement element : value){
                        System.out.println(element.getText());
                    }
                }
                );
    }

    /**
     * Gets data as json string.
     *
     * @return the data as json string
     */
    public String getDataAsJsonString() {
        DataWriter writer = new JsonDataWriter("null");
        return writer.dataAsString(getData());
    }
}
