package SearchPath;

import ADTGraph.Graph;
import ADTGraph.Vertex;

import java.util.*;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by remen on 20.11.15.
 */
public class floydw {
    public static ArrayList<Vertex> floydwarshall(Graph graph, Vertex start, Vertex end) {
        ArrayList<Vertex> vertexes = graph.getVertexes();
        if (!vertexes.contains(start) || !vertexes.contains(end)) return new ArrayList<>();

        ArrayList<Vertex> edges = graph.getEdges();
        if (!edges.contains(start) || !edges.contains(end)) return new ArrayList<>();

        HashMap<Vertex, HashMap<Vertex, Vertex>> predecessor = new HashMap<>();
        HashMap<Vertex, HashMap<Vertex, Integer>> table = new HashMap<>();

        for (Vertex v1 : vertexes) {
            HashMap<Vertex, Vertex> inTableP = new HashMap<>();
            HashMap<Vertex, Integer> inTableC = new HashMap<>();

            for (Vertex v2 : vertexes) {
                Integer costs = (v1 == v2) ? 0 : graph.getValE(v1, v2, "cost");

                inTableC.put(v2, costs);
                inTableP.put(v2, null);
            }

            table.put(v1, inTableC);
            predecessor.put(v1, inTableP);
        }

        for (int i = 0; i < edges.size() - 1; i += 2) {
            Vertex source = edges.get(i);
            Vertex target = edges.get(i + 1);

            table.get(source).put(target, graph.getValE(source, target, "cost"));
        }

        for (int j = 0; j < vertexes.size(); j++) {
            for (int i = 0; i < vertexes.size(); i++) {
                for (int k = 0; k < vertexes.size(); k++) {
                    if (i != j && j != k) {
                        Vertex iv = vertexes.get(i);
                        Vertex jv = vertexes.get(j);
                        Vertex kv = vertexes.get(k);

                        Integer firstCost = table.get(iv).get(jv);
                        Integer secondCost = table.get(iv).get(kv);
                        Integer thirdCost = table.get(jv).get(kv);

                        if (greaterThanNull(firstCost, secondCost, thirdCost)) {
                            table.get(iv).put(kv, secondCost + thirdCost);
                            predecessor.get(iv).replace(kv, jv);
                        }

                        if (greaterThanNull(0, table.get(iv).get(iv), 0)) return new ArrayList<>();
                    }
                }
            }
        }
        LinkedList<Vertex> path = new LinkedList<>();
        path.add(start);

        System.out.println(predecessor);
        findPath(predecessor, start, end, path);
        //Collections.reverse(path);

        return new ArrayList<>(path);
    }

    public static LinkedList<Vertex> findPath(HashMap<Vertex, HashMap<Vertex, Vertex>> predecessor, Vertex start, Vertex target, LinkedList<Vertex> list) {
        Vertex interim = predecessor.get(start).get(target);
        if (interim != null) {
            findPath(predecessor, start, interim, list);
            findPath(predecessor, interim, target, list);
        } else {
            list.add(target);
        }
        return list;
    }

    private static boolean greaterThanNull(Integer compareWith, Integer summand1, Integer summand2) {
        if (compareWith == null || summand1 == null || summand2 == null) return false;
        return (compareWith > summand1 + summand2);
    }

    private static boolean compareInvinitySave(Integer compareWith, Integer summand1, Integer summand2) {
        if (compareWith == null && summand1 != null && summand2 != null) return true;
        if (compareWith == null || summand1 == null || summand2 == null) return false;
        return (compareWith > summand1 + summand2);
    }
}
