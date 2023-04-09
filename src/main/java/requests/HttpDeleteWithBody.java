package requests;

import java.net.URI;

import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

/**
 *
 * Template to handle http delete requests with a body.
 *
 * @author Marcel N&ouml;hre
 *
 */
@NotThreadSafe
class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";

    /**
     * Getter for the method name.
     * 
     * @return The method name 
     */
    public String getMethod() {
        return METHOD_NAME;
    }

    /**
     * Sets up http delete request that can contain a body.
     *
     * @param uri The requested URI
     */
    HttpDeleteWithBody(String uri) {
        super();
        setURI(URI.create(uri));
    }
}
