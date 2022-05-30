package simplewebscraper;

/**
 * The interface Html document.
 */
public interface IHtmlDocument {
    /**
     * Gets html elements by tag.
     *
     * @param tag the tag
     * @return the html elements by tag
     */
    HtmlElements getHtmlElementsByTag(String tag);

    /**
     * Gets html elements by class name.
     *
     * @param className the class name
     * @return the html elements by class name
     */
    HtmlElements getHtmlElementsByClassName(String className);

    /**
     * Gets html element by id.
     *
     * @param id the id
     * @return the html element by id
     */
    HtmlElement getHtmlElementById(String id);

    /**
     * Gets html elements by attribute and value.
     *
     * @param attr  the attr
     * @param value the value
     * @return the html elements by attribute and value
     */
    HtmlElements getHtmlElementsByAttributeAndValue(String attr, String value);

    /**
     * Gets html elements that contains.
     *
     * @param text the text
     * @return the html elements that contains
     */
    HtmlElements getHtmlElementsThatContains(String text);

    /**
     * Gets html elements using selector.
     *
     * @param text the text
     * @return the html elements using selector
     */
    HtmlElements getHtmlElementsUsingSelector(String text);

    /**
     * Gets html elements by xpath.
     *
     * @param xpathString the xpath string
     * @return the html elements by xpath
     */
    HtmlElements getHtmlElementsByXpath(String xpathString);

    /**
     * Gets title of Html document
     *
     * @return the string
     */
    String title();
}
