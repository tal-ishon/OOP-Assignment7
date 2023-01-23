import java.util.TreeMap;

/**
 * @author Tal Ishon.
 * The type Hyponym.
 */
public class Hyponyms extends TreeMap<String, Integer> {
    /**
     * Add hyponym.
     *
     * @param hyponym the hyponym
     */
    public void addHyponym(String hyponym) {
        if (containsKey(hyponym)) {
            Integer count = get(hyponym);
            replace(hyponym, ++count);
        } else {
            put(hyponym, 1);
        }
    }

    /**
     * GetHyponymline method.
     *
     * @return the hyponymline
     */
    public String getHyponymline() {
        DynamicString line = new DynamicString("");
        TreeMap<Integer, DynamicString> temp = new TreeMap();
        forEach((k, v) -> {
            if (temp.containsKey(v)) {
                DynamicString ds = temp.get(v);
                ds.concat(", " + k + " (" + v.toString() + ")");
                temp.put(v, ds);
            } else {
                // making sure not to add "," before first value
                if (!firstKey().equals(k)) {
                    temp.put(v, new DynamicString(", " + k + " (" + v.toString() + ")"));
                } else {
                    temp.put(v, new DynamicString(k + " (" + v.toString() + ")"));

                }
            }
        });
        temp.forEach((k, v) -> {
            line.concat(v.toString());
        });
        return line.toString();
    }
}