package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.KDTree;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    private  KDTree kdtree;
    private Map<Point, Node> p2n;
    private MyTrieSet trie;
    private Map<String, List<Node>> locations;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
        p2n = new HashMap<>();
         List<Node> nodes = this.getNodes();
         List<Point> points = new ArrayList<>();
         trie = new MyTrieSet();
         locations = new HashMap<>();

         for(Node node: nodes) {
             if(node.name() != null) {
                 trie.add(cleanString(node.name()));


                 if (locations.containsKey(cleanString(node.name()))) {
                     locations.get(cleanString(node.name())).add(node);
                 } else {
                     List<Node> node2 = new LinkedList<>();
                     node2.add(node);
                     locations.put(cleanString(node.name()), node2);
                 }
             }



             long id = node.id();
             if (!neighbors(id).isEmpty()) {    // only consider those nodes who have neighbors
                 Point point = new Point(node.lon(), node.lat());
                 points.add(point);
                 p2n.put(point, node);
             }
         }

         kdtree = new KDTree(points);
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        Point nearest = kdtree.nearest(lon, lat);
        return p2n.get(nearest).id();
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        return trie.keysWithPrefix(prefix);
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
            List<Map<String, Object>> result = new LinkedList<>();
            String locationname2 = cleanString(locationName);
            if(!locations.containsKey(locationname2)){
                return result;
            }
            for(Node node: locations.get(locationname2)){
                Map<String, Object> map = new HashMap<>();
                map.put("lat", node.lat());
                map.put("lon", node.lon());
                map.put("name", node.name());
                map.put("id", node.id());
                result.add(map);
            }
            return result;

    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
