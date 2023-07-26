package requests;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

/**
 *
 * Service to handle REST requests.
 *
 * @author Marcel N&ouml;hre
 *
 */
public class RestService {
    private CloseableHttpClient client;
    private CloseableHttpResponse httpResponse;

    /**
     * Sets up the RestService.
     */
    public RestService() {
        setupConnectionManager();
    }

    /**
     * Sets up the connection pool.
     */
    private void setupConnectionManager() {
        HttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager();
        this.client = HttpClients.custom().setConnectionManager(poolingConnectionManager).build();
    }

    /**
     * Http get request to the given url.
     *
     * @param url The url to send the request to
     * @return 0) resultCode 1) response
     */
    public String[] httpGet(String url) {
        int resultCode = -1;
        String response = "";
        try {
            HttpGet get = new HttpGet(url);
            this.httpResponse = this.client.execute(get);
            response = EntityUtils.toString(this.httpResponse.getEntity());
            resultCode = this.httpResponse.getStatusLine().getStatusCode();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return new String[] {Integer.toString(resultCode), response};
    }

    /**
     * Http post request to the given url with the given input as a body.
     *
     * @param url The url to send the request to.
     * @param input The request body
     * @return 0) resultCode 1) response
     */
    public String[] httpPost(String url, String input) {
        int resultCode = -1;
        String response = "";
        try {
            HttpPost post = new HttpPost();
            post.setEntity(new StringEntity(input));
            this.httpResponse = client.execute(post);
            response = EntityUtils.toString(this.httpResponse.getEntity());
            resultCode = this.httpResponse.getStatusLine().getStatusCode();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return new String[] {Integer.toString(resultCode), response};
    }

    /**
     * Http put request to the given url with input as body and uri parameters.
     *
     * @param path The uri path to send the request to
     * @param input The request body
     * @param parameters The uri parameters
     * @return 0) resultCode 1) response
     */
    public String[] httpPut(String path, String input, String[][] parameters) {
        int resultCode = -1;
        String response = "";
        try {
            HttpPut put = new HttpPut();
            try {
                URIBuilder builder = new URIBuilder().setScheme("http").setHost("localhost:1111").setPath(path);
                for (String[] parameter: parameters) {
                    builder.addParameter(parameter[0], parameter[1]);
                }
                put.setURI(builder.build());
            } catch (URISyntaxException e) {
            }
            put.setHeader("Content-type", "application/json");
            put.setEntity(new StringEntity(input));
            this.httpResponse = client.execute(put);
            response = EntityUtils.toString(this.httpResponse.getEntity());
            resultCode = this.httpResponse.getStatusLine().getStatusCode();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return new String[] {Integer.toString(resultCode), response};
    }

    /**
     * Http delete request to send the request to.
     *
     * @param url The url to send the request to
     * @param input The request body
     * @return 0) resultCode 1) response
     */
    public String[] httpDelete(String url, String input) {
        int resultCode = -1;
        String response = "";
        try {
            HttpDeleteWithBody delete = new HttpDeleteWithBody(url);
            delete.setHeader("Content-type", "application/json");
            delete.setEntity(new StringEntity(input));
            this.httpResponse = this.client.execute(delete);
            response = EntityUtils.toString(this.httpResponse.getEntity());
            resultCode = this.httpResponse.getStatusLine().getStatusCode();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return new String[] {Integer.toString(resultCode), response};
    }
}
