# SimpleWebScraper - Getting started

## Intro
SimpleWebScraper is a Java Framework built to scrape content from an HTML document using simple java code. The API provides the most used HTML5 DOM methods, including Xpath to fetch data. 
The framework also offers functionality to store scraped data and convert the data to formats such as CSV and JSON.

## Import the framework
The framework can be imported in multiple projects:

1. Java projects - using jar file
2. Gradle projects 
3. Maven projects

### Set Up Gradle
___
- Open build.gradle
- Add the following to the end of repositories:

``` java
repositories {
    ...
    maven { url 'https://jitpack.io' }
}
```
- Add the dependency:
``` java
dependencies {
    implementation 'com.github.Rammeverk-gruppe10:SimpleWebScraper:1.0.1'
}
```
  

### Set up Maven
___
- Open pom.xml
- Add the repository:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
- Add the dependency:
```xml
<dependencies>
    <dependency>
        <groupId>com.github.Rammeverk-gruppe10</groupId>
        <artifactId>SimpleWebScraper</artifactId>
        <version>1.0.1</version>
    </dependency>
</dependencies>  
```

## Using the framework

Example using the WebScraper to get all monitors from komplett.no, and get all HtmlElements with tag equal to 'h2' and all HtmlElements with class name equal to 'product-price-now':
``` java
WebScraper web = WebScraper.get("https://www.komplett.no/category/11158/datautstyr/skjermer/skjermer?nlevel=10000%C2%A710392%C2%A711158&hits=240");
HtmlElements productNames = web.getHtmlElementsByTag("h2");
HtmlElements productPrices = web.getHtmlElementsByClassName("product-price-now");
```

Example using WebScraper together with DataCollection to scrape and collect all monitors from komplett.no, and then convert the collected data to CSV file:
``` java
WebScraper web = WebScraper.get("https://www.komplett.no/category/11158/datautstyr/skjermer/skjermer?nlevel=10000%C2%A710392%C2%A711158&hits=240");

DataCollection dataCollection = DataCollection.create(web);

dataCollection.createColumnAndAddDataLocation("ProductName", s -> s.getHtmlElementsByTag("h2"));

dataCollection.createColumnAndAddDataLocation("ProductPrices", s -> s.getHtmlElementsByClassName("product-price-now"));

dataCollection.createColumnAndAddDataLocation("ProductStockStatus", s -> s.getHtmlElementsByXpath("//span[@class='stockstatus-stock-details']"));


dataCollection.collectData();

dataCollection.writeToCSV("monitors.csv");
```

See Javadoc for full documentation: https://javadoc.jitpack.io/com/github/Rammeverk-gruppe10/SimpleWebScraper/cf13ab2132/javadoc/






