import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;


public class SeparableEnemySolver {

    Graph g;

    /**
     * Creates a SeparableEnemySolver for a file with name filename. Enemy
     * relationships are biderectional (if A is an enemy of B, B is an enemy of A).
     */
    SeparableEnemySolver(String filename) throws java.io.FileNotFoundException {
        this.g = graphFromFile(filename);
    }

    /** Alterntive constructor that requires a Graph object. */
    SeparableEnemySolver(Graph g) {
        this.g = g;
    }

    /**
     * Returns true if input is separable, false otherwise.
     */
    public boolean isSeparable() {
        // TODO: Fix me
        Set<String> a = new HashSet<>();
        Set<String> b = new HashSet<>();
        for(String node: g.labels()){
            boolean a_add = true;
            boolean b_add = true;
           aloop: for(String anode: a){
                if(g.neighbors(node).contains(anode)){
                    a_add = false;
                    break aloop;
                } 
            }
            if(a_add){
                a.add(node);
                continue;
            }

           bloop: for(String bnode: b){
                if(g.neighbors(node).contains(bnode)){
                    b_add = false;
                    break bloop;
                }
            }
            if(b_add){
                b.add(node);
                continue;
            }
            return false;
        }
        return true;
    }

    public boolean isSeparable_three(){
        Set<String> a = new HashSet<>();
        Set<String> b = new HashSet<>();
        Set<String> c = new HashSet<>();

        for(String node: g.labels()){
            boolean a_add = true;
            boolean b_add = true;
            boolean c_add = true;

            for(String anode: a){
                if(g.neighbors(node).contains(anode)){
                    a_add = false;
                    break;
                }
            }
            if(a_add){
                a.add(node);
                continue;
            }

            for(String bnode: b){
                if(g.neighbors(node).contains(bnode)){
                    b_add = false;
                    break;
                }
            }
            if(b_add){
                b.add(node);
                continue;
            }

            for(String cnode: c){
                if(g.neighbors(node).contains(cnode)){
                    c_add = false;
                    break;
                }
            }
            if(c_add){
                c.add(node);
                continue;
            }
            return false;
        }
        return true;
    }


    /* HELPERS FOR READING IN CSV FILES. */

    /**
     * Creates graph from filename. File should be comma-separated. The first line
     * contains comma-separated names of all people. Subsequent lines each have two
     * comma-separated names of enemy pairs.
     */
    private Graph graphFromFile(String filename) throws FileNotFoundException {
        List<List<String>> lines = readCSV(filename);
        Graph input = new Graph();
        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) {
                for (String name : lines.get(i)) {
                    input.addNode(name);
                }
                continue;
            }
            assert(lines.get(i).size() == 2);
            input.connect(lines.get(i).get(0), lines.get(i).get(1));
        }
        return input;
    }

    /**
     * Reads an entire CSV and returns a List of Lists. Each inner
     * List represents a line of the CSV with each comma-seperated
     * value as an entry. Assumes CSV file does not contain commas
     * except as separators.
     * Returns null if invalid filename.
     *
     * @source https://www.baeldung.com/java-csv-file-array
     */
    private List<List<String>> readCSV(String filename) throws java.io.FileNotFoundException {
        List<List<String>> records = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            records.add(getRecordFromLine(scanner.nextLine()));
        }
        return records;
    }

    /**
     * Reads one line of a CSV.
     *
     * @source https://www.baeldung.com/java-csv-file-array
     */
    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        Scanner rowScanner = new Scanner(line);
        rowScanner.useDelimiter(",");
        while (rowScanner.hasNext()) {
            values.add(rowScanner.next().trim());
        }
        return values;
    }

    /* END HELPERS  FOR READING IN CSV FILES. */
    public static void main(String[] args){
        Graph g = new Graph();
        g.connect("A", "B");
        g.connect("A", "C");
        g.connect("A", "D");
        g.connect("C", "D");
        SeparableEnemySolver solver = new SeparableEnemySolver(g);
        System.out.println(solver.isSeparable());
    }
}
