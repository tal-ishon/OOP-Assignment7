import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tal Ishon.
 * The type Hypernym database.
 */
public class HypernymDatabase {
    private final Hypernyms hypernyms = new Hypernyms();

    /**
     * AddHyponymToHypernym method.
     *
     * @param hypernym the hypernym
     * @param hyponym  the hyponym
     */
    public void addHyponymToHypernym(String hypernym, String hyponym) {
        Hyponyms hyponyms = hypernyms.getHyponymsOfHypernym(hypernym);
        hyponyms.addHyponym(hyponym);
        System.out.println(hypernyms.getHypernymLines());
    }

    /**
     * DumpToFile method.
     * This method go over all the lines of hypernyms and put them in a new file.
     *
     * @param file the path of the new file we create which contains
     * the hypernyms and hyponyms.
     */
    public void dumpToFile(String file) {
        List<String> lines = hypernyms.getHypernymLines();
        System.out.println(lines);
        Path path = Paths.get(file);
        try {
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * LoadFromFiles method.
     * This method reads from files and according to regular expressions
     * puts hypernyms and hyponyms in data base.
     *
     * @param folder the folder
     */
    public void loadFromFiles(String folder) {
        Path path = Paths.get(folder);
        // The regex of 1-4 in assignment
        String s1 = "(?:<np>([\\w ]+)</np>,? (?:such as|especially|including)";
        String s2 = " <np>([\\w ]+)</np>(?:(?:, <np>([\\w ]+)</np>)*,";
        String s3 = "(?:and |or )?<np>([\\w ]+)</np>)?|(?:such <np>([\\w ]+)</np> as <np>([\\w ]+)</np>(?:(?:,";
        String s4 = "<np>([\\w ]+)</np>)*, (?:and |or )?<np>([\\w ]+)</np>)?))";
        Pattern hypernymFirstHyponymsAfter = Pattern.compile(s1 + s2 + s3 + s4);
        // The regex of 5 in assignment
        s1 = "(?:<np>([\\w ]+)</np>,? which is (?:(?:an example|a kind|a class) of )?<np>([\\w ]+)</np>)";
        Pattern hyponymsFirstHypernymAfter = Pattern.compile(s1);
        try {
            Files.list(path).forEach((file) -> {
                try {
                    Files.lines(file).forEach((line) -> {
                        Matcher m = hypernymFirstHyponymsAfter.matcher(line);
                        while (m.find()) {
                            String hypernym = m.group(1);
                            for (int i = 2; i <= m.groupCount(); ++i) {
                                String hyponym = m.group(i);
                                addHyponymToHypernym(hypernym, hyponym);
                            }
                        }
                        m = hyponymsFirstHypernymAfter.matcher(line);
                        while (m.find()) {
                            String hypernym = m.group(m.groupCount());
                            for (int i = 1; i < m.groupCount(); ++i) {
                                String hyponym = m.group(i);
                                addHyponymToHypernym(hypernym, hyponym);
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("has");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
