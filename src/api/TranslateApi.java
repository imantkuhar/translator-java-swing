package api;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by imant
 */
public class TranslateApi {

    public String translateWord(String word, String languageFrom, String languageTo) {
        StringBuffer response = new StringBuffer();

        try {
            String REQUEST_PARAMS = "key=" + ApiConstants.API_KEY + "&text=" + word + "&lang=" + languageFrom + "-" + languageTo;
            URL obj = new URL(ApiConstants.BASE_URL + REQUEST_PARAMS);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "ImantPC");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

        } catch (IOException e) {

        }
        return response.toString();
    }
}