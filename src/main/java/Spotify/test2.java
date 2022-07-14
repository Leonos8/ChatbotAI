package Spotify;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This example shows how to get information about the user that is 'connected' to the
 * access token. The methods used (api.authorizationCodeGrant and api.getMe) are synchronous, but are
 * available asynchronously as well. The scopes necessary for this example are 'user-read-private'
 * and 'user-read-email'.
 *
 * The authorization flow used is documented in detail at
 * https://developer.spotify.com/spotify-web-api/authorization-guide/#authorization_code_flow
 *
 * Details about requesting the current user's information is documented at
 * https://developer.spotify.com/spotify-web-api/get-users-profile/ in the
 * "Authorization Code" section.
 */
public class test2 {

    final static String clientId = "8b37674c0d4748498ac6af9561d70054";
    final String clientSecret = "d44fd4ae66c148d9af98de4c959fe6e3";
    //final String code = "<insert code>";
    final static String redirectUri = "http://ACE.com/spotify-redirect";
	
	
	
	public static void main(String[] args) {
        try {
            String url_auth = 
            "https://accounts.spotify.com/authorize?"
            + "client_id="+clientId+"&"
            + "response_type=code&"
            + "redirect_uri="+redirectUri+"&"
            + "scope=user-read-private%20user-read-email&"
            + "state=34fFs29kd09";

            System.out.println(url_auth);

            URL url = new URL(url_auth);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }

}