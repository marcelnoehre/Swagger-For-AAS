package templates;

import utils.Transform;

/**
 *
 * Template for a contact instance.
 *
 * @author Marcel N&oumlhre
 *
 */
public class Contact {
    private final String[] keys = new String[] {"email"};
    private String email;

    /**
     * Create a contact instance.
     *
     * @param email the email
     */
    public Contact(String email) {
        this.email = email;
    }

    /**
     * Get a array of all template values.
     *
     * @return array of all template values
     */
    private String[] getValueArray() {
        return new String[] {this.email};
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
