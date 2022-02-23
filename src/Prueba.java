import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Prueba {
    public Prueba() {
    }

    public String main(String countri) {
        String site = "http://api.openweathermap.org/data/2.5/find?q="+countri+"&appid=6d5b39516045f1a21d068ac4cbe0600b";
        URL siteURL = null;
        try {
            siteURL = new URL(site);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection urlConnection = null;
        try {
            urlConnection = siteURL.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, List<String>> headers = urlConnection.getHeaderFields();
        Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();

        for (Map.Entry<String, List<String>> entry : entrySet) {
            String headerName = entry.getKey();
            if(headerName !=null){
                //System.out.print(headerName + ":");
                }
            List<String> headerValues = entry.getValue();
            for (String value : headerValues) {
                //System.out.print(value);
            }
            //System.out.println("");
        }



        try (BufferedReader reader = new BufferedReader(new InputStreamReader(siteURL.openStream()))) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                return inputLine;
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        return null;
    }
}
