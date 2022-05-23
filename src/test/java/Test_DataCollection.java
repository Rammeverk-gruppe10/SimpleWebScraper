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

    @Test
    public void create_column_ok() throws InvalidColumnException {
        assertDoesNotThrow(() -> collection.createColumn("test"));
    }

    @Test
    public void create_column_throw_exception() throws InvalidColumnException {
        collection.createColumn("test");
        assertThrows(InvalidColumnException.class, () -> collection.createColumn("test"));
    }

    @Test
    public void setWebScraper_method_test() {
        WebScraper scraper = WebScraper.get("https://www.hiof.no");
        assertDoesNotThrow(() -> collection.setWebScraper(scraper));
    }


}
