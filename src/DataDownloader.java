import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Piotr Górak dnia 2015-03-28.
 */
public class DataDownloader {
    public String downloadData(String p_sURL)
    {
        URL oURL;
        URLConnection oConnection;
        BufferedReader oReader;
        String sLine;
        StringBuilder sbResponse;
        String sResponse = null;

        try
        {
            oURL = new URL(p_sURL);
            oConnection = oURL.openConnection();
            oReader = new BufferedReader(new InputStreamReader(oConnection.getInputStream()));
            sbResponse = new StringBuilder();

            while((sLine = oReader.readLine()) != null)
            {
                sbResponse.append(sLine);
            }

            sResponse = sbResponse.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return sResponse;
    }
}
