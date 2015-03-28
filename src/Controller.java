import org.w3c.dom.Document;

/**
 * @author Piotr Górak dnia 2015-03-28.
 */
public class Controller {
    public static void main(String[] args) {
        Document tabelaA = new DataDownloader().downloadData("http://www.nbp.pl/kursy/xml/a060z150327.xml");
        Document tabelaB = new DataDownloader().downloadData("http://www.nbp.pl/kursy/xml/b012z150325.xml");
        Document tabelaC = new DataDownloader().downloadData("http://www.nbp.pl/kursy/xml/c060z150327.xml");

        new DataParser(tabelaA).parseData();
        new DataParser(tabelaB).parseData();

    }
}
