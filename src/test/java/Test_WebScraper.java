import org.junit.jupiter.api.Test;
import simplewebscraper.HtmlElement;
import simplewebscraper.HtmlElements;
import simplewebscraper.WebScraper;


import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Test_WebScraper {

    File file = new File("./src/test/java/test.html");
    WebScraper scraperFile = WebScraper.get(file);

    @Test
    public void webScraper_using_url_title_correct() {
        WebScraper scraper = WebScraper.get("https://itstud.hiof.no/~fredris/webutvikling/oblig4/");
        assertEquals(scraper.title(), "Ressursside - Webutvikling | Fredrik Sommerseth");
    }

    @Test
    public void webScraper_using_url_title_incorrect() {
        WebScraper scraper = WebScraper.get("https://itstud.hiof.no/~fredris/webutvikling/oblig4/");
        assertNotEquals(scraper.title(), "test");
    }

    @Test
    public void webScraper_using_file_title_correct() {
        assertEquals(scraperFile.title(), "Hello, world!");
    }

    @Test
    public void webScraper_using_file_title_incorrect() {
        assertNotEquals(scraperFile.title(), "test");
    }

    @Test
    public void webScraper_using_file_getHtmlElementsById_correct() {
        HtmlElement element = scraperFile.getHtmlElementById("heading");
        assertEquals(element.getText(), "Hello, test!");
    }

    @Test
    public void webScraper_using_file_getHtmlElementsById_incorrect() {
        HtmlElement element = scraperFile.getHtmlElementById("heading");
        assertNotEquals(element.getText(), "Hello");
    }

    @Test
    public void webScraper_using_file_getHtmlElementsByClassName_correct() {
        HtmlElements elements = scraperFile.getHtmlElementsByClassName("classTest");
        for (int i = 0; i < 1; i++) {
            assertEquals(elements.get(i).getText(), "Hei "+ i);
        }
    }

    @Test
    public void webScraper_using_file_getHtmlElementsByClassName_incorrect() {
        HtmlElements elements = scraperFile.getHtmlElementsByClassName("classTest");
        for (int i = 0; i < 1; i++) {
            assertNotEquals(elements.get(i).getText(), "Test "+ i);
        }
    }

    @Test void webScraper_using_file_getHtmlElementsByTag_correct() {
        HtmlElements elements = scraperFile.getHtmlElementsByTag("h2");
        assertEquals(elements.get(0).getText(), "h2");
    }

    @Test void webScraper_using_file_getHtmlElementsByTag_incorrect() {
        HtmlElements elements = scraperFile.getHtmlElementsByTag("h1");
        assertNotEquals(elements.get(0).getText(), "h2");
    }

    @Test void webScraper_using_file_getHtmlElementsByAttributeAndValue_correct() {
        HtmlElements elements = scraperFile.getHtmlElementsByAttributeAndValue("about", "test");
        assertEquals(elements.get(0).getText(), "Test123");
    }

    @Test void webScraper_using_file_getHtmlElementsByAttributeAndValue_incorrect() {
        HtmlElements elements = scraperFile.getHtmlElementsByAttributeAndValue("about", "test");
        assertNotEquals(elements.get(0).getText(), "Failed");
    }

    @Test void webScraper_using_file_getHtmAndValue_correct() {
        HtmlElements elements = scraperFile.getHtmlElementsByAttributeAndValue("about", "test");
        assertEquals(elements.get(0).getText(), "Test123");
    }

    @Test void webScraper_using_file_getHtmAndValue_incorrect() {
        HtmlElements elements = scraperFile.getHtmlElementsByAttributeAndValue("about", "test");
        assertNotEquals(elements.get(0).getText(), "Hei123");
    }

    @Test void webScraper_using_file_getHtmlElementsUsingSelector_correct() {
        HtmlElements elements = scraperFile.getHtmlElementsUsingSelector("div h2");
        assertEquals(elements.get(0).getText(), "h2");
    }

    @Test void webScraper_using_file_getHtmlElementsUsingSelector_incorrect() {
        HtmlElements elements = scraperFile.getHtmlElementsUsingSelector("div h2");
        assertNotEquals(elements.get(0).getText(), "fail");
    }

    @Test void webScraper_using_file_getHtmlElementsByXpath_correct() {
        HtmlElements elements = scraperFile.getHtmlElementsByXpath("//section/p");
        assertEquals(elements.get(0).getText(), "Hei 0");
        assertEquals(elements.get(1).getText(), "Hei 1");
    }

    @Test void webScraper_using_file_getHtmlElementsByXpath_incorrect() {
        HtmlElements elements = scraperFile.getHtmlElementsByXpath("//section/p");
        assertNotEquals(elements.get(0).getText(), "Test123");
        assertNotEquals(elements.get(1).getText(), "Dummy");
    }

    @Test void webScraper_using_file_HtmlElement_attribute() {
        HtmlElements elements = scraperFile.getHtmlElementsByXpath("//img");
        HtmlElement element = elements.get(0);
        assertEquals(element.attribute("src"), "bilde.jpg");
        assertEquals(element.attribute("alt"), "test");
    }

    @Test void webScraper_using_file_HtmlElement_getTagName() {
        HtmlElements elements = scraperFile.getHtmlElementsByXpath("//img");
        HtmlElement element = elements.get(0);
        assertEquals(element.getTagName(), "img");
    }

    @Test void webScraper_using_file_HtmlElement_getFirstElement() {
        HtmlElements elements = scraperFile.getHtmlElementsByXpath("//img");
        assertEquals(elements.getFirstElement().getTagName(), "img");
    }
}
