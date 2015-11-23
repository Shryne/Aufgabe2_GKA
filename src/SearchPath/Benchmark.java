package SearchPath;

import ADTGraph.Graph;
import ADTGraph.Vertex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static ADTGraph.Graph.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by remen on 22.11.15.
 */
public class Benchmark {
    private static SearchAlgo algoRtm[] = {
            (graph, s, e) -> bellf.bellfordRtm(graph, s, e),
            (graph, s, e) -> floydw.floydwarshallRtm(graph, s, e)
    };

    private static SearchAlgo algoAcc[] = {
            (graph, s, e) -> bellf.bellfordAcc(graph, s, e),
            (graph, s, e) -> floydw.floydwarshallAcc(graph, s, e)
    };

    private static final int EASY_GRAPH_AMOUNT = 12;
    private static String easyGraphName[] = new String[EASY_GRAPH_AMOUNT];
    static {
        for (int i = 1; i <= 9 ; i++)
            easyGraphName[i] = "graph_0" + i;

        for (int i = 10; i < easyGraphName.length; i++)
            easyGraphName[i] = "graph_" + i;
    }

    private static Graph easyGraph[] = new Graph[EASY_GRAPH_AMOUNT];
    static {
        for (int i = 0; i < easyGraphName.length; i++)
            easyGraph[i] = importG(easyGraphName[i]);
    }


    private static Graph graph13 = importG("graph_13");
    private static Graph graph14 = importG("graph_14");

    private static String DELIMETER = ";";
    private static String FILENAME = "benchmark.csv";

    public static void main(String args[]) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < easyGraph.length; i++) {
            Graph g = easyGraph[i];
            ArrayList<Vertex> vertexes = g.getVertexes();

            long rtm[] = new long[algoRtm.length];
            long acc[] = new long[algoAcc.length];

            for (Vertex source : vertexes) {
                for (Vertex target : vertexes) {
                    for (int j = 0; j < algoRtm.length; j++) {
                        rtm[j] += search(algoRtm[j], g, source, target);
                        acc[j] += search(algoAcc[j], g, source, target);
                    }
                }
            }

            output.append("Bellmann Ford - Zeit\n");
            output.append(easyGraphName[i] + DELIMETER + rtm[0]);

            output.append("Floyd Warshall - Zeit\n");
            output.append(easyGraphName[i] + DELIMETER + rtm[1]);



            output.append("Bellmann Ford - Zugriffe\n");
            output.append(easyGraphName[i] + DELIMETER + acc[0]);

            output.append("Floyd Warshall - Zugriffe\n");
            output.append(easyGraphName[i] + DELIMETER + acc[1]);
        }

        writeFile(FILENAME, output.toString());
    }

    @FunctionalInterface
    private interface SearchAlgo {
        long search(Graph g, Vertex start, Vertex end);
    }

    private static long search(SearchAlgo searchAlgo, Graph g, Vertex start, Vertex end) {
        return searchAlgo.search(g, start, end);
    }

    private static void writeFile(String filename, String output) {
        try {
            Files.write(Paths.get(filename), output.getBytes());
        } catch (IOException e) {}
    }
}
