package templates;

/**
*
* Template to send a test put request.
*
* @author Marcel N&ouml;hre
*
*/
public class PutExample{
    private String idShort;
    private String id;
    private String name;
    private Object modelType;

    /**
     * Create a put example to execute a submodel element request (put).
     *
     * @param idShort the shortId of the put object
     * @param modelType the model type of the put object
     */
    public PutExample(String idShort, Object modelType) {
        this.idShort = idShort;
        this.modelType = modelType;
    }

    /**
     * Create a put example to execute a put request.
     *
     * @param idShort the shortId of the put object
     * @param id the identification of the put object
     */
    public PutExample(String idShort, String id) {
        this.idShort = idShort;
        this.id = id;
    }

    /**
     * Create a inner model type object for a put object.
     *
     * @param name the name of the model type
     */
    public PutExample(String name) {
        this.name = name;
    }
}
