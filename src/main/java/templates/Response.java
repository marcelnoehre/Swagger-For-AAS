package templates;

import java.util.ArrayList;

import utils.Transform;

/**
 *
 * Template for a response instance.
 *
 * @author Marcel N&ouml;hre
 *
 */
public class Response {
    private String[] keys = new String[] {"description", "schema", "headers"};
    private String resultCode;
    private String description;
    private Schema schema;
    private Header[] headers;

    /**
     * Create a response instance.
     *
     * @param resultCode the resultCode of the response
     * @param description the message to descripe the response
     * @param schema the schema of the response
     * @param headers the lsit of header of the response
     */
    public Response(String resultCode, String description, Schema schema, Header[] headers) {
        this.resultCode = resultCode;
        this.description = description;
        this.schema = schema;
        this.headers = headers;
    }

    /**
     * Get the resultcode of a response.
     *
     * @return the resultcode of a response
     */
    public String getResultCode() {
        return this.resultCode;
    }

    /**
     * Get a array of all template values.
     *
     * @return array of all template values
     */
    private String[] getValueArray() {
        ArrayList<String> headerList = new ArrayList<String>();
        String[] headerArr = new String[headerList.size()];
        if (this.headers != null) {
            for (Header header : headers) {
                String headerCheck;
                try {
                    headerCheck = header.asJson();
                } catch (NullPointerException itemsNull) {
                    headerCheck = null;
                }
                headerList.add(headerCheck);
            }
            int i = 0;
            for (String header : headerList) {
                headerArr[i] = header;
                i++;
            }
        }
        String schema;
        try {
            schema = this.schema.asJson();
        } catch (NullPointerException schemaNull) {
            schema = null;
        }
        return new String[] {this.description, schema, Transform.arrayToJson(headerArr)};
    }

    /**
     * Get the instance as JSON string.
     *
     * @return json string of the instance
     */
    public String asJson() {
        return Transform.instanceToJson(keys, this.getValueArray());
    }
}
