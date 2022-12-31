package requests;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
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

public class RestService {
    private CloseableHttpClient client;
	private CloseableHttpResponse httpResponse;
    
    public RestService() {
    	setupConnectionManager();
    }
	
	
    private void setupConnectionManager() {
        HttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager();
        this.client = HttpClients.custom().setConnectionManager(poolingConnectionManager).build();
    }
    
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
    
    public String[] httpPost(String url, String input) {
    	int resultCode = -1;
    	String response = "";
    	try {
    		HttpPost post = new HttpPost();
    		StringEntity entity = new StringEntity(input);
    		post.setEntity(entity);
    		this.httpResponse = client.execute(post);
    		response = EntityUtils.toString(this.httpResponse.getEntity());
    		resultCode = this.httpResponse.getStatusLine().getStatusCode();
    	} catch(IOException io) {
    		io.printStackTrace();
    	}
		return new String[] {Integer.toString(resultCode), response};
    }
    
    public String[] httpPut(String path, String input, String[][] parameter) {
    	int resultCode = -1;
    	String response = "";
    	try {
    		HttpPut put = new HttpPut();
    		StringEntity entity = new StringEntity(input);
			try {
	    		URIBuilder builder = new URIBuilder()
					    .setScheme("http")
					    .setHost("localhost:1111");
	    		
				put.setURI(builder.build());
			} catch (URISyntaxException e) {
			}
			put.setHeader("Content-type", "application/json");
    		put.setEntity(entity);
    		this.httpResponse = client.execute(put);
    		response = EntityUtils.toString(this.httpResponse.getEntity());
    		resultCode = this.httpResponse.getStatusLine().getStatusCode();
    	} catch(IOException io) {
    		io.printStackTrace();
    	}
		return new String[] {Integer.toString(resultCode), response};
    }
    
    public String[] httpDelete(String url) {
    	int resultCode = -1;
    	String response = "";
		try {
			HttpDelete delete = new HttpDelete(url);
			this.httpResponse = this.client.execute(delete);
			response = EntityUtils.toString(this.httpResponse.getEntity());
			resultCode = this.httpResponse.getStatusLine().getStatusCode();
		} catch (IOException io) {
			io.printStackTrace();
		}
		return new String[] {Integer.toString(resultCode), response};
    }
}
