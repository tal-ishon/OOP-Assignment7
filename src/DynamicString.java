// ID 315242297

/**
 * @author Tal Ishon.
 * The type Dynamic string.
 */
public class DynamicString {
    private String dynamicString;

    /**
     * DynamicString constructor.
     * Instantiates a new Dynamic string.
     *
     * @param s the s
     */
    public DynamicString(String s) {
        dynamicString = s;
    }

    /**
     * Concat string.
     *
     * @param s the s
     * @return the string
     */
    public String concat(String s) {
        dynamicString = dynamicString + s;
        return dynamicString;
    }
    @Override
    public String toString() {
        return dynamicString;
    }
}
