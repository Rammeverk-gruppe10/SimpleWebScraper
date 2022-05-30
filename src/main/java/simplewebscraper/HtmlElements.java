package simplewebscraper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type Html elements.
 */
public class HtmlElements extends ArrayList<HtmlElement> {
    /**
     * Instantiates a new Html elements.
     */
    public HtmlElements() {
    }

    /**
     * Instantiates a new Html elements.
     *
     * @param elementsHtml the elements html
     */
    public HtmlElements(List<HtmlElement> elementsHtml) {
        super(elementsHtml);
    }

    /**
     * Instantiates a new Html elements.
     *
     * @param elementsHtml the elements html
     */
    public HtmlElements(Collection<HtmlElement> elementsHtml) {
        super(elementsHtml);
    }

    /**
     * To list as string array list.
     *
     * @return the array list
     */
    public ArrayList<String> toListAsString() {
        ArrayList<String> elementsText = new ArrayList<>();
        for (HtmlElement element : this) {
            elementsText.add(element.getText());
        }
        return elementsText;
    }

    /**
     * Gets first HtmlElement in HtmlElements.
     *
     * @return the first Html ELement
     */
    public HtmlElement getFirstElement() {
        return this.get(0);
    }


}

