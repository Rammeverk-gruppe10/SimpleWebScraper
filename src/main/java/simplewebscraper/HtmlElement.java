package simplewebscraper;

import org.jsoup.nodes.Element;

/**
 * The type Html element.
 */
public class HtmlElement {

    private final Element element;

    public HtmlElement(Element element) {
        this.element = element;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return element.text();
    }

    /**
     * Gets tag name.
     *
     * @return the tag name
     */
    public String getTagName() {
        return element.tagName();
    }

    /**
     * Gets the value of an attribute in Html Element.
     *
     * @param attribute the attribute
     * @return the string
     */
    public String attribute(String attribute) {
        return element.attr(attribute);
    }
}


