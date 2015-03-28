import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * @author Piotr Górak dnia 2015-03-28.
 */
public class DataParser {
    private Document downloadedData;
    private NodeList nList;

    public DataParser(Document downloadedData) {
        this.downloadedData = downloadedData;
        this.nList = downloadedData.getElementsByTagName("pozycja");
    }


    public void parseData(Boolean isTabelaC) {
        getTableName();
        if (isTabelaC) {
            getRatingDate();
        }
        getDate();
        getTheValues(isTabelaC);
    }

    public String getHeader(String label, String elementToGet) {
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
        return getHeader("Tabela :", "numer_tabeli");
    }

    public String getDate() {
        return getHeader("Data publikacji", "data_publikacji");
    }

    public String getRatingDate() {
        return getHeader("Data notowania", "data_notowania");
    }


    public String getValue(String label, String elementToGet, int i) {
        String dataToReturn = null;

        Node nNode = nList.item(i);
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            dataToReturn = eElement.getElementsByTagName(elementToGet).item(0).getTextContent();
            System.out.println(label + " : " + dataToReturn);
        }
        return dataToReturn;
    }

    public String getCurrency(int i) {
        return getValue("Waluta", "nazwa_waluty", i);
    }

    public String getConversion(int i) {
        return getValue("Przelicznik", "przelicznik", i);
    }

    public String getCurrencyCode(int i) {
        return getValue("Kod waluty", "kod_waluty", i);
    }

    public String getAverageExchangeRate(int i) {
        return getValue("Kurs średni", "kurs_sredni", i);
    }

    public String getPurchaseRate(int i) {
        return getValue("Kurs kupna", "kurs_kupna", i);
    }

    public String getSellRate(int i) {
        return getValue("Kurs sprzedaży", "kurs_sprzedazy", i);
    }

    public void getTheValues(Boolean isTabelaC) {
        System.out.println();
        for (int i = 0; i < nList.getLength(); i++) {
            getCurrency(i);
            getConversion(i);
            getCurrencyCode(i);
            if (isTabelaC) {
                getPurchaseRate(i);
                getSellRate(i);
            } else
                getAverageExchangeRate(i);
            System.out.println();
        }
    }
}
