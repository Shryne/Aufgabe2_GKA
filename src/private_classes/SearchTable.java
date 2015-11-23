package private_classes;

import ADTGraph.Vertex;

import java.util.Hashtable;

/**
 * Created by remen on 20.11.15.
 */
public class SearchTable {
    // ##########################################
    // vars
    // ##########################################
    private static final Integer INITIAL_VALUE = Integer.MAX_VALUE;

    private Hashtable<Vertex, Tupel<Integer, Vertex>> table;

    // ##########################################
    // methods
    // ##########################################
    private SearchTable(Vertex... vertex) {
        table = new Hashtable<>(vertex.length);

        for (Vertex v : vertex)
            table.put(v, Tupel.of(INITIAL_VALUE, null));
    }

    public static SearchTable of(Vertex... vertex) {
        return new SearchTable(vertex);
    }

    public void setCost(Vertex vertex, int cost) {
        //table.replace(vertex, cost);
    }

    public void setPredecessor(int pos, Vertex predecessor) {

    }

    public void set(int pos, int cost, Vertex predecessor) {
        //setCost(pos, cost);
        setPredecessor(pos, predecessor);
    }
    // ##########################################
    // invisible
    // ##########################################

    // ##########################################
    // alias
    // ##########################################
    private void throwException(String text) {
        throw new IllegalArgumentException(text);
    }
}
