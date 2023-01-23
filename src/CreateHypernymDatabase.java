/**
 * @author Tal Ishon.
 * CreateHypernymDatabase class.
 */
public class CreateHypernymDatabase {
    /**
     * Main method.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        HypernymDatabase data = new HypernymDatabase();
        data.loadFromFiles(args[0]);
        data.dumpToFile(args[1]);
    }
}
