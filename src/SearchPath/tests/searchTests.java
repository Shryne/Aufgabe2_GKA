package SearchPath.tests;

import ADTGraph.Graph;
import ADTGraph.Vertex;
import SearchPath.bellf;
import SearchPath.floydw;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static ADTGraph.Vertex.createV;
import static org.junit.Assert.assertEquals;
import static ADTGraph.Graph.*;

/**
 * Created by remen on 20.11.15.
 */
public class searchTests {
    private static final String BASIC_FILENAME = "graphs/graph_";

    private SearchAlgo algo[] = {
            //(g, start, end) -> bellf.bellford(g, start, end),
            (g, start, end) -> floydw.floydwarshall(g, start, end)
    };
    /*
    @Test
    public void testExtern() {
        for (SearchAlgo a : algo) {
            assertSearch(a, getGraph("03"), v("v"), v("x"), asList(v("v"), v("y"), v("s"), v("x")));


            // fail - negative cycle
            assertSearch(a, getGraph("04"), v("v4"), v("v1"), asList());
            assertSearch(a, getGraph("05"), v("v4"), v("v1"), asList());
        }
    }*/

    @Test
    public void testOwn() {
        //getGraph("08");

        for (SearchAlgo a : algo) {
            assertSearch(a, getGraph("own_1"), v("s"), v("t"), asList(v("s"), v("C"), v("A"), v("B"), v("t")));
            //assertSearch(a, getGraph("own_2"), v("v1"), v("v2"), asList());
        }
    }
    /*
    @Test
    public void testSpecials() {
        for (SearchAlgo a : algo) {
            assertSearch(a, getGraph("01"), v("x"), v("x"), asList());
            assertSearch(a, getGraph("01"), v("Augsburg"), v("Augsburg"), asList(v("Augsburg")));
            assertSearch(a, getGraph("01"), v("Augsburg"), v("x"), asList());

            // fail
            assertSearch(a, getGraph("01"), null, v("Augsburg"), asList());
            assertSearch(a, getGraph("01"), v("Augsburg"), null, asList());
            assertSearch(a, null, v("Bonn"), v("Augsburg"), asList());
        }
    }*/

    // ##########################################
    // private helper
    // ##########################################
    @FunctionalInterface
    private interface SearchAlgo {
        ArrayList<Vertex> search(Graph g, Vertex start, Vertex end);
    }

    private void assertSearch(SearchAlgo searchAlgo, Graph g, Vertex start, Vertex end, ArrayList<Vertex> result) {
        assertEquals(result, searchAlgo.search(g, start, end));
    }

    // ##########################################
    // alias
    // ##########################################
    private Graph getGraph(String num) {
        return importG(BASIC_FILENAME + num);
    }

    private Vertex v(String name) {
        return createV(name);
    }

    private ArrayList<Vertex> asList(Vertex... vertex) {
        return new ArrayList<>(Arrays.asList(vertex));
    }
}
