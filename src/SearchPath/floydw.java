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
        if (graph == null || start == null || end == null) return emptyPath();
        if (!graph.getVertexes().contains(start)) return emptyPath();
        if (start == end) return new ArrayList<Vertex>() {{ add(start); }};

        ArrayList<Vertex> vertexes = graph.getVertexes();
        if (!vertexes.contains(start) || !vertexes.contains(end)) return new ArrayList<>();

        ArrayList<Vertex> edges = graph.getEdges();
        if (!edges.contains(start) || !edges.contains(end)) return new ArrayList<>();

        HashMap<Vertex, HashMap<Vertex, Vertex>> predecessor = new HashMap<>();
        HashMap<Vertex, HashMap<Vertex, Integer>> cost = new HashMap<>();

        for (Vertex v1 : vertexes) {
            HashMap<Vertex, Vertex> inTableP = new HashMap<>();
            HashMap<Vertex, Integer> inTableC = new HashMap<>();

            for (Vertex v2 : vertexes) {
                inTableP.put(v2, null);
                inTableC.put(v2, null);
            }

            predecessor.put(v1, inTableP);
            cost.put(v1, inTableC);
        }

        for (int i = 0; i < edges.size(); i += 2) {
            Vertex source = edges.get(i);
            Vertex target = edges.get(i + 1);

            Integer actualCost = (source == target) ? 0 : graph.getValE(source, target, "cost");
            cost.get(source).put(target, actualCost);
        }

        for (int j = 0; j < vertexes.size(); j++) {
            for (int i = 0; i < vertexes.size(); i++) {
                for (int k = 0; k < vertexes.size(); k++) {
                    if (i != j && j != k) {
                        Vertex iv = vertexes.get(i);
                        Vertex jv = vertexes.get(j);
                        Vertex kv = vertexes.get(k);

                        Integer dik = cost.get(iv).get(kv);
                        Integer dij = cost.get(iv).get(jv);
                        Integer djk = cost.get(jv).get(kv);

                        if (dij != null && djk != null && (dik == null || dik > dij + djk)) {
                            cost.get(iv).put(kv, dij + djk);
                            predecessor.get(iv).put(kv, jv);
                        }
                    }
                }
                Integer dii = cost.get(vertexes.get(i)).get(vertexes.get(i));
                if (dii != null && dii < 0) return emptyPath();
            }
        }

        LinkedList<Vertex> path = new LinkedList<>();
        path.add(start);

        findPath(predecessor, start, end, path);

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

    public static long floydwarshallAcc(Graph graph, Vertex start, Vertex end) {
        long time = System.currentTimeMillis();
        floydwarshall(graph, start, end);
        return System.currentTimeMillis() - time;
    }

    private static ArrayList<Vertex> emptyPath() {
        return new ArrayList<>();
    }


    public static long floydwarshallRtm(Graph graph, Vertex start, Vertex end) {
        long acc = 0;

        if (graph == null || start == null || end == null) return acc;
        if (!graph.getVertexes().contains(start)) return acc;
        if (start == end) return acc;

        ArrayList<Vertex> vertexes = graph.getVertexes();
        if (!vertexes.contains(start) || !vertexes.contains(end)) return acc;

        ArrayList<Vertex> edges = graph.getEdges();
        if (!edges.contains(start) || !edges.contains(end)) return acc;

        HashMap<Vertex, HashMap<Vertex, Vertex>> predecessor = new HashMap<>();
        HashMap<Vertex, HashMap<Vertex, Integer>> cost = new HashMap<>();

        for (Vertex v1 : vertexes) {
            HashMap<Vertex, Vertex> inTableP = new HashMap<>();
            HashMap<Vertex, Integer> inTableC = new HashMap<>();

            for (Vertex v2 : vertexes) {
                inTableP.put(v2, null);
                inTableC.put(v2, null);
            }

            predecessor.put(v1, inTableP);
            cost.put(v1, inTableC);
        }

        for (int i = 0; i < edges.size(); i += 2) {
            Vertex source = edges.get(i); acc++;
            Vertex target = edges.get(i + 1); acc++;

            Integer actualCost = (source == target) ? 0 : graph.getValE(source, target, "cost"); acc++;
            cost.get(source).put(target, actualCost);
        }

        for (int j = 0; j < vertexes.size(); j++) {
            for (int i = 0; i < vertexes.size(); i++) {
                for (int k = 0; k < vertexes.size(); k++) {
                    if (i != j && j != k) {
                        Vertex iv = vertexes.get(i); acc++;
                        Vertex jv = vertexes.get(j); acc++;
                        Vertex kv = vertexes.get(k); acc++;

                        Integer dik = cost.get(iv).get(kv);
                        Integer dij = cost.get(iv).get(jv);
                        Integer djk = cost.get(jv).get(kv);

                        if (dij != null && djk != null && (dik == null || dik > dij + djk)) {
                            cost.get(iv).put(kv, dij + djk);
                            predecessor.get(iv).put(kv, jv);
                        }
                    }
                }
                Integer dii = cost.get(vertexes.get(i)).get(vertexes.get(i)); acc++;
                if (dii != null && dii < 0) return acc;
            }
        }

        LinkedList<Vertex> path = new LinkedList<>();
        path.add(start);

        findPath(predecessor, start, end, path);

        return acc;
    }
}
