package SearchPath;

import ADTGraph.Graph;
import ADTGraph.Vertex;

import java.beans.VetoableChangeListener;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by remen on 20.11.15.
 */
public class bellf {

    public static ArrayList<Vertex> bellford(Graph graph, Vertex v1, Vertex v2) {
        if (graph == null || v1 == null || v2 == null) return emptyPath();
        if (!graph.getVertexes().contains(v1)) return emptyPath();
        if (v1 == v2) return new ArrayList<Vertex>() {{ add(v1); }};

        ArrayList<Vertex> vertexes = graph.getVertexes();
        ArrayList<Vertex> edges = graph.getEdges();

        Hashtable<Vertex, Integer> cost = new Hashtable<>();
        Hashtable<Vertex, Vertex> predecessor = new Hashtable<>();

        cost.put(v1, 0);

        for (int z = 0; z < vertexes.size() - 1; z++) {
            for (int i = 0; i < edges.size() - 1; i += 2) {
                Vertex source = edges.get(i);
                Vertex target = edges.get(i + 1);

                Integer firstCost = cost.get(target);
                Integer secondCost = cost.get(source);
                Integer thirdCost = graph.getValE(source, target, "cost");

                if (compareInvinitySave(firstCost, secondCost, thirdCost)) {
                    cost.put(target, cost.get(source) + graph.getValE(source, target, "cost"));
                    predecessor.put(target, source);
                }
            }
        }
        /*
        System.out.println(graph.getValE(Vertex.createV("v2"), Vertex.createV("v3"), "cost"));
        System.out.println(graph.getValE(Vertex.createV("v3"), Vertex.createV("v2"), "cost"));
        System.out.println(cost.get(Vertex.createV("v2")));
        System.out.println(cost.get(Vertex.createV("v3")));
        */
        //System.out.println(cost);
        for (int i = 0; i < edges.size() - 1; i += 2) {
            Vertex source = edges.get(i);
            Vertex target = edges.get(i + 1);

            Integer firstCost = cost.get(target);
            Integer secondCost = cost.get(source);
            Integer thirdCost = graph.getValE(source, target, "cost");
            //System.out.print(" | " + source + " _ " + target + " ___ " + firstCost + " _ " + secondCost + " _ " + thirdCost);
            if (firstCost == null || secondCost == null || thirdCost == null) continue;
            if (firstCost > secondCost + thirdCost) return emptyPath();
        }


        LinkedList<Vertex> path = new LinkedList<>();
        path.add(v2);

        Vertex pred = predecessor.get(v2);
        path.add(pred);

        //System.out.println(predecessor);
        while (pred != v1) {
            if (pred == null) return emptyPath();
            pred = predecessor.get(path.getLast());

            path.add(pred);
        }
        Collections.reverse(path);

        return new ArrayList<>(path);
    }

    public long bellfordRtm(Graph graph, String filename, Vertex v1, Vertex v2){

    }

    public long bellfordAcc(Graph graph, String filename, Vertex v1, Vertex v2){

    }

    private static boolean compareInvinitySave(Integer compareWith, Integer summand1, Integer summand2) {
        if (compareWith == null && summand1 != null && summand2 != null) return true;
        if (compareWith == null || summand1 == null || summand2 == null) return false;
        return (compareWith > summand1 + summand2);
    }

    private static ArrayList<Vertex> emptyPath() {
        return new ArrayList<>();
    }
}
