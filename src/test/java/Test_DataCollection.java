import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import simplewebscraper.InvalidColumnException;
import simplewebscraper.WebScraper;
import simplewebscraper.datacollection.DataCollection;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class Test_DataCollection {
    File file = new File("./src/test/java/test.html");
    WebScraper scraperFile = WebScraper.get(file);
    DataCollection collection = DataCollection.create(scraperFile);

    @BeforeEach
    public void init() throws InvalidColumnException {
        collection.createColumnAndAddDataLocation("testCol", scraper -> scraper.getHtmlElementsByTag("h1"));
    }

    @Test
    public void create_column_ok() {
        assertDoesNotThrow(() -> collection.createColumn("test"));
    }

    @Test
    public void create_column_throws_exception() throws InvalidColumnException {
        collection.createColumn("test");
        assertThrows(InvalidColumnException.class, () -> collection.createColumn("test"));
    }

    @Test
    public void setWebScraper_method_test() {
        WebScraper scraper = WebScraper.get("https://www.hiof.no");
        assertDoesNotThrow(() -> collection.setWebScraper(scraper));
    }

    @Test
    public void createColumnAndAddData_method_usingHtmlElements_ok() {
        assertDoesNotThrow(() -> collection.createColumnAndAddData("test", scraperFile.getHtmlElementsByTag("h1")));
    }

    @Test
    public void createColumnAndAddData_method_usingHtmlElements_throws_exception() throws InvalidColumnException {
        collection.createColumnAndAddData("test", scraperFile.getHtmlElementsByTag("h1"));
        assertThrows(InvalidColumnException.class, () -> collection.createColumnAndAddData("test", scraperFile.getHtmlElementsByTag("h1")));
    }

    @Test
    public void createColumnAndAddData_method_usingXpath_ok() {
        assertDoesNotThrow(() -> collection.createColumnAndAddData("test", "//section/p"));
    }

    @Test
    public void createColumnAndAddData_method_usingXpath_throws_exception() throws InvalidColumnException {
        collection.createColumnAndAddData("test", "//section/p");
        assertThrows(InvalidColumnException.class, () -> collection.createColumnAndAddData("test", "//section/p"));
    }

    @Test
    public void createColumnAndAddDataLocation_method_ok() {
        assertDoesNotThrow(() -> collection.createColumnAndAddDataLocation("testColumn",
                scraper -> scraper.getHtmlElementsByTag("h2")));
    }

    @Test
    public void createColumnAndAddDataLocation_method_throws_exception() throws InvalidColumnException {
        collection.createColumnAndAddDataLocation("testColumn",
                scraper -> scraper.getHtmlElementsByTag("h2"));
        assertThrows(InvalidColumnException.class, () -> collection.createColumnAndAddDataLocation("testColumn",
                scraper -> scraper.getHtmlElementsByTag("h2")));
    }

    @Test
    public void createColumnAndAddDataLocation_newWebScraper_method_ok() throws InvalidColumnException {
        assertDoesNotThrow(() -> collection.createColumnAndAddDataLocation("testColumn",
                scraper -> scraper.getHtmlElementsByTag("h1"), WebScraper.get(file)));
        assertEquals(collection.getDataAsList("testColumn").get(0), "Hello, test!");
    }

    @Test
    public void createColumnAndAddDataLocation_newWebScraper_method_throws_exception() throws InvalidColumnException {
        collection.createColumnAndAddDataLocation("testColumn",
                scraper -> scraper.getHtmlElementsByTag("h2"));
        assertThrows(InvalidColumnException.class, () -> collection.createColumnAndAddDataLocation("testColumn",
                scraper -> scraper.getHtmlElementsByTag("h2"), WebScraper.get(file)));
    }

    @Test
    public void addDataLocationToExistingColumn_method_ok() throws InvalidColumnException {
        assertDoesNotThrow(() -> collection.addDataLocationToExistingColumn("testCol",
                scraper -> scraper.getHtmlElementsByTag("h2")));
        collection.collectData();
//        String[] a = new String[] {"Hello, test!", "h2"};
//        System.out.println(collection.getDataAsList("testCol"));
////        assertArrayEquals(collection.getDataAsList("testCol").toArray(), a);
    }



}
