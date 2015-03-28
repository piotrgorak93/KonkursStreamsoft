import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * @author Piotr Górak dnia 2015-03-28.
 */
public class DataParser {
    private Document downloadedData;

    public DataParser(Document downloadedData) {
        this.downloadedData = downloadedData;
    }

    public void parseData() {
        getTableName();
        getDate();
        System.out.println();
        getCurrency();
        getConversion();
        getCurrencyCode();
        getAverageExchangeRate();

    }

    public String getData(String label, String elementToGet) {
        String dataToReturn = null;
        NodeList header = downloadedData.getElementsByTagName("tabela_kursow");
        for (int temp = 0; temp < header.getLength(); temp++) {
            Node node = header.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                dataToReturn = eElement.getElementsByTagName(elementToGet).item(0).getTextContent();
                System.out.println(label + " : " + dataToReturn);
            }
        }
        return dataToReturn;
    }

    public String getTableName() {
        return getData("Tabela :", "numer_tabeli");
    }

    public String getDate() {
        return getData("Data publikacji", "data_publikacji");
    }

    public String getValue(String label, String elementToGet) {
        String dataToReturn = null;
        NodeList nList = downloadedData.getElementsByTagName("pozycja");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                dataToReturn = eElement.getElementsByTagName(elementToGet).item(0).getTextContent();
                System.out.println(label + " : " + dataToReturn);
            }
        }
        return dataToReturn;
    }

    public String getCurrency() {
        return getValue("Waluta", "nazwa_waluty");
    }

    public String getConversion() {
        return getValue("Przelicznik", "przelicznik");
    }

    public String getCurrencyCode() {
        return getValue("Kod waluty", "kod_waluty");
    }

    public String getAverageExchangeRate() {
        return getValue("Kurs średni", "kurs_sredni");
    }
}

