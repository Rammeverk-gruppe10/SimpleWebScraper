package simplewebscraper;

import java.io.File;

/**
 * The type Web scraper.
 */
public class WebScraper {
    private final HtmlDocument document;

    private WebScraper(String url) {
        document = new HtmlDocument(url);
    }

    private WebScraper(File file) {
        document = new HtmlDocument(file);
    }

    /**
     * Get web scraper.
     *
     * @param url Url to page
     * @return the web scraper
     */
    public static WebScraper get(String url) {
        return new WebScraper(url);
    }

    /**
     * Get web scraper.
     *
     * @param file the file
     * @return the web scraper
     */
    public static WebScraper get(File file) {
        return new WebScraper(file);
    }

    /**
     * Gets elements by tag.
     *
     * @param tag the tag
     * @return the elements by tag
     */
    public HtmlElements getHtmlElementsByTag(String tag)
    {
        return document.getHtmlElementsByTag(tag);
    }

    /**
     * Gets elements by class name.
     *
     * @param className the class name
     * @return the elements by class name
     */
    public HtmlElements getHtmlElementsByClassName(String className)
    {
        return document.getHtmlElementsByClassName(className);
    }

    /**
     * Gets element by id.
     *
     * @param id the id
     * @return the element by id
     */
    public HtmlElement getHtmlElementById(String id)
    {
        return document.getHtmlElementById(id);
    }

    /**
     * Gets elements by attribute and value.
     *
     * @param attr  the attr
     * @param value the value
     * @return the elements by attribute and value
     */
    public HtmlElements getHtmlElementsByAttributeAndValue(String attr, String value)
    {
        return document.getHtmlElementsByAttributeAndValue(attr, value);
    }

    /**
     * Selector html elements.
     *
     * @param selector Css selector syntax
     * @return HtmlElements
     */
    public HtmlElements getHtmlElementsUsingSelector(String selector)
    {
        return document.getHtmlElementsUsingSelector(selector);
    }

    /**
     * Gets html elements by xpath.
     *
     * @param xpathString the xpath string
     * @return HtmlElements using xpath
     */
    public HtmlElements getHtmlElementsByXpath(String xpathString) {
        return document.getHtmlElementsByXpath(xpathString);
    }

    /**
     * Title string.
     *
     * @return The title in HTML document
     */
    public String title() {
        return document.title();
    }

    private String elementToString(HtmlElement element) {
        if (element == null)
            return "";
        return element.getText();
    }
}



