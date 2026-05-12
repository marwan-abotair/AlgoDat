public class notizen extends RuntimeException {
    public notizen(String message) {

      super(message);
      /*
import java.util.Stack;

public class ShortestPathsTopological { // die Instanzvariablen dieser Klasse
    private static final double INF = Double.MAX_VALUE; // INF initialisiert


    private int[] parent; //Ein Array, welches für jeden Knoten die Vorgänger-Knoten im Pfad speichert
    private int s; // die Nummer der Startknoten von dem aus der kürzeste Weg berechnet wird
    private double[] dist; // dist[v] ist die Länge des kürzesten bekannten Pfads von s(Startknoten) zu v (Zielknoten)

    public ShortestPathsTopological(WeightedDigraph G, int s) {
        // TODO
        int V = G.V(); // Knoten
        this.s = s; // Startknoten speichern
        parent = new int[V]; // Vorgängerknoten initialisieren
        dist   = new double[V]; // Distanz initialisisren

        // Initialisierung aller Distanzen und Eltern
        for (int v = 0; v < V; v++) {
            dist[v] = INF;
            parent[v] = -1; // es sind noch keine Vorgänger bekannt, daher dist und paren noch im Start-Zustand
        }
        dist[s] = 0.0;
        parent[s] = s;

        Stack<Integer> order = new TopologicalWD(G).order(); // topologosche Sortierung (umgekehrt)

        // entsprechend dem TopologicalWD ist das Stack in LIFO geordnet
        while (!order.isEmpty()) {
            int v = order.pop();
            for (DirectedEdge e : G.adj(v)) { // durchläuft jede einzelne dieser Kanten – jede Kante repräsentiert ein Pfeil v → w mit einem Gewicht.
                relax(e);
            }
        }
    }

    public void relax(DirectedEdge e) {
        // TODO
        // Prüft, ob der Weg von s über e.from() → e.to() kürzer ist als der bisher bekannte und aktualisiert in diesem Fall dist[e.to()] sowie parent[e.to()].
        int v = e.from();
        int w = e.to();
        double wt = e.weight();
        if (dist[v] + wt < dist[w]) {
            dist[w] = dist[v] + wt;
            parent[w] = v;
        }
    }

    public boolean hasPathTo(int v) {
        return parent[v] >= 0;
    }

    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int w = v; w != s; w = parent[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
    }
}
       */
    }
}

/*
import java.util.Stack;

public class ShortestPathsTopological {
    private int[] parent;
    private int s;
    private double[] dist;

    public ShortestPathsTopological(WeightedDigraph G, int s) {
        this.s = s;
        int V = G.V();
        dist   = new double[V];
        parent = new int[V];

        // 1) Initialisierung
        for (int v = 0; v < V; v++) {
            dist[v]   = Double.POSITIVE_INFINITY;
            parent[v] = -1;
        }
        dist[s]    = 0.0;
        parent[s]  = s;  // Quelle zeigt auf sich selbst

        // 2) Topologische Sortierung des DAG
        TopologicalWD topo   = new TopologicalWD(G);
        Stack<Integer> order = topo.order();  // umgekehrte Hauptordnung

        // 3) Knoten in Topo-Reihenfolge durchlaufen
        while (!order.isEmpty()) {
            int v = order.pop();
            // 4) Relaxiere alle ausgehenden Kanten direkt über incident(v)
            for (DirectedEdge e : G.incident(v)) {
                relax(e);
            }
        }
    }


    public void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        double weight = e.weight();
        if (dist[v] + weight < dist[w]) {
            dist[w] = dist[v] + weight;
            parent[w] = v;
        }
    }

    public boolean hasPathTo(int v) {
        return parent[v] >= 0;
    }

    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int w = v; w != s; w = parent[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
    }
}
 */


