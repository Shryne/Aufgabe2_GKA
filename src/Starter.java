import ADTGraph.Graph;
import ADTGraph.Vertex;
import SearchPath.bellf;
import SearchPath.floydw;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by remen on 20.11.15.
 */
public class Starter {

    public static void main(String args[]) {
        Vertex vs = v("s"), va = v("A"), vb = v("B"), vc = v("C"), vd = v("D"), vt = v("t");
        Graph g = Graph.createG(vs).addVertex(va).addVertex(vb).addVertex(vc).addVertex(vd).addVertex(vt);

        g.addEdge(vs, va).addEdge(vs, vc).addEdge(vc, vd).addEdge(vc, va).addEdge(va, vb).addEdge(vb, vc).addEdge(vb, vt).addEdge(vb, vd).addEdge(vd, vt);
        g.setAtE(vs, va, "cost", 5).setAtE(vs, vc, "cost", 1).setAtE(vc, va, "cost", 2)
                .setAtE(va, vb, "cost", 1).setAtE(vc, vd, "cost", 3).setAtE(vb, vc, "cost", 2)
                .setAtE(vb, vd, "cost", 7).setAtE(vb, vt, "cost", 3).setAtE(vd, vt, "cost", 10);

        System.out.println(bellf.bellford(g, vs, vt));
        System.out.println(floydw.floydwarshall(g, vs, vt));
        // \u000d System.out.println("This Comment Executed!");
    }

    private static Vertex v(String name) {
        return Vertex.createV(name);
    }
}
