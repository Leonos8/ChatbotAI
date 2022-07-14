//import static junit.framework.TestCase.fail;
package Spotify;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;

/**
 * This example shows how to get refresh an access token asynchronously. There's a
 * synchronous version of the method available as well.
 *
 * The authorization flow used is documented in detail at
 * https://developer.spotify.com/spotify-web-api/authorization-guide/#client-credentials-flow
 * in the "Client Credentials" section.
 */
public class test {

  public static void main(String[] strings) {

	  final String clientId = "8b37674c0d4748498ac6af9561d70054";
	  final String clientSecret = "d44fd4ae66c148d9af98de4c959fe6e3";

    final Api api = Api.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .build();

    System.out.println(api.getAlbum("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"));
    System.out.println(api.getMe().toString());
    //api.createPlaylist("245en17uzefapgrppemb6rc64", "test");
    /* Create a request object. */
    final ClientCredentialsGrantRequest request = api.clientCredentialsGrant().build();

    /* Use the request object to make the request, either asynchronously (getAsync) or synchronously (get) */
    final SettableFuture<ClientCredentials> responseFuture = request.getAsync();

    /* Add callbacks to handle success and failure */
    Futures.addCallback(responseFuture, new FutureCallback<ClientCredentials>() {
      @Override
      public void onSuccess(ClientCredentials clientCredentials) {
        /* The tokens were retrieved successfully! */
        System.out.println("Successfully retrieved an access token! " + clientCredentials.getAccessToken());
        System.out.println("The access token expires in " + clientCredentials.getExpiresIn() + " seconds");

        /* Please note that this flow does not return a refresh token.
         * That's only for the Authorization code flow */
      }

      @Override
      public void onFailure(Throwable throwable) {
        /* An error occurred while getting the access token. This is probably caused by the client id or
         * client secret is invalid. */
    	  System.out.println("FAIL");
        //fail("Failed to resolve future: " + throwable.getMessage());
      }
    });

  }
}