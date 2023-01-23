import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Tal Ishon.
 * Hypernym Class.
 */
public class Hypernyms extends TreeMap<String, Hyponyms> {

    /**
     * Gets hyponyms of hypernym.
     *
     * @param hypernym the hypernym
     * @return the hyponyms of hypernym
     */
    public Hyponyms getHyponymsOfHypernym(String hypernym) {
        if (!containsKey(hypernym)) {
            Hyponyms hyponyms = new Hyponyms();
            put(hypernym, hyponyms);
        }
        return get(hypernym);
    }

    /**
     * Gets hypernym lines method.
     * Creates the lines of hypernyms which have more then 2 hyponyms.
     *
     * @return the hypernym lines
     */
    public List<String> getHypernymLines() {
        List<String> lines = new ArrayList<>();
        forEach((k, v) -> {
            if (v.size() >= 0) {
                lines.add(k + ": " + v.getHyponymline());
            }
        });
        return lines;
    }
}
