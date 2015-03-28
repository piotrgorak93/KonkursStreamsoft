import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * @author Piotr Górak dnia 2015-03-28.
 */
public class DataDownloader {
    public Document downloadData(String urlToParse) {
        URL url = null;
        Document doc = null;
        url = connectToURL(urlToParse, url);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;

        db = createDocument(dbf, db);
        doc = retrieveDocument(url, doc, db);

        return doc;
    }

    private Document retrieveDocument(URL url, Document doc, DocumentBuilder db) {
        try {
            doc = db.parse(url.openStream());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            System.err.println("Brak polaczenia");
            System.exit(0);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(0);
        }
        return doc;
    }

    private DocumentBuilder createDocument(DocumentBuilderFactory dbf, DocumentBuilder db) {
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return db;
    }

    private URL connectToURL(String urlToParse, URL url) {
        try {
            url = new URL(urlToParse);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
