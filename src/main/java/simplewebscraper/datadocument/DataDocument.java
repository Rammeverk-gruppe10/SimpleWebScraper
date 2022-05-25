package simplewebscraper.datadocument;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import simplewebscraper.DataCollector;
import simplewebscraper.HtmlElement;
import simplewebscraper.HtmlElements;
import simplewebscraper.exception.FieldNotFoundException;
import simplewebscraper.exception.InvalidColumnException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataDocument {
    DataDocumentBackend backend;

    HashMap<String, HtmlElements> fieldsHtmlElements;
    HashMap<String, List<HtmlElement>> fieldsHtmlElement;

    public DataDocument(DataDocumentBackend backend) {
        this.backend = backend;
        this.fieldsHtmlElements = new HashMap<>();
        this.fieldsHtmlElement = new HashMap<>();
    }

    public void addField(String fieldName, String xpath) throws FieldNotFoundException {
        if (!fieldsHtmlElements.containsKey(fieldName))
            fieldsHtmlElements.put(fieldName, backend.getData(xpath));
        else
            throw new FieldNotFoundException("Field already exists");
    }

    public void addField(String fieldName, DataCollector collector) throws FieldNotFoundException {
        if (!fieldsHtmlElements.containsKey(fieldName))
            fieldsHtmlElements.put(fieldName, backend.getData(collector));
        else
            throw new FieldNotFoundException("Field already exists");
    }

    // Test
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

    public List<String> getDataByFieldName(String fieldName) throws FieldNotFoundException {
        ArrayList<String> text = new ArrayList<>();
        if (!fieldsHtmlElements.containsKey(fieldName))
            throw new FieldNotFoundException("Field does not exist");

        return fieldsHtmlElements.get(fieldName).toListAsString();
    }

    // For testing only
    public void testWrite() {
        fieldsHtmlElements.forEach(
                (key, value)
                        -> {
                    for (HtmlElement element : value){
                        System.out.println(element.getText());
                    }
                }
                );
    }

    public String getDataAsJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(getData());
    }
}
