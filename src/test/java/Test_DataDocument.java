import org.junit.jupiter.api.Test;
import simplewebscraper.WebScraper;
import simplewebscraper.datadocument.DataDocument;
import simplewebscraper.datadocument.DataDocumentBackend;
import simplewebscraper.exception.FieldException;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class Test_DataDocument {
    File file = new File("./src/test/java/test.html");
    WebScraper scraperFile = WebScraper.get(file);
    DataDocument document = DataDocument.create(DataDocumentBackend.create(scraperFile));

    @Test
    public void addField_method_using_dataCollector_ok() {
        assertDoesNotThrow(() -> document.addField("testCol", scraper -> scraper.getHtmlElementsByTag("h1")));
    }

    @Test
    public void addField_method_using_dataCollector_throws_exception() throws FieldException {
        document.addField("testCol", scraper -> scraper.getHtmlElementsByTag("h1"));
        assertThrows(FieldException.class, () ->  document.addField("testCol", scraper -> scraper.getHtmlElementsByTag("h1")));
    }

    @Test
    public void addField_method_using_xpath_ok() {
        assertDoesNotThrow(() -> document.addField("testCol", "//h1"));
    }

    @Test
    public void addField_method_using_xpath_throws_exception() throws FieldException {
        document.addField("testCol", "//h1");
        assertThrows(FieldException.class, () ->  document.addField("testCol", "//h1"));
    }

    @Test
    public void getData_method_ok() throws FieldException {
        document.addField("testCol", scraper -> scraper.getHtmlElementsByTag("h1"));
        assertEquals(document.getData().get("testCol").get(0), "Hello, test!");
    }

    @Test
    public void getDataByFieldName_method_ok() throws FieldException {
        document.addField("testCol", scraper -> scraper.getHtmlElementsByClassName("classTest"));
        String[] test = new String[] {"Hei 0", "Hei 1"};

        assertArrayEquals(document.getDataByFieldName("testCol").toArray(), test);
    }

    @Test
    public void getDataByFieldName_method_throws_exception() {
        assertThrows(FieldException.class, () -> document.getDataByFieldName("testing"));
    }

    @Test
    public void getDataAsJsonString_method_ok() throws FieldException {
        document.addField("testCol", scraper -> scraper.getHtmlElementsByClassName("classTest"));
        String toJson = """
                {
                  "testCol": [
                    "Hei 0",
                    "Hei 1"
                  ]
                }""";
        assertEquals(document.getDataAsJsonString(), toJson);


    }
}
