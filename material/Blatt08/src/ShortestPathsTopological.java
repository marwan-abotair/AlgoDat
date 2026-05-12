import java.util.Stack;

public class ShortestPathsTopological { // die Instanzvariablen dieser Klasse
    private int[] parent; //Ein Array, welches für jeden Knoten die Vorgänger-Knoten im Pfad speichert
    private int s; // die Nummer der Startknoten von dem aus der kürzeste Weg berechnet wird
    private double[] dist; // dist[v] ist die Länge des kürzesten bekannten Pfads von s(Startknoten) zu v (Zielknoten)

    public ShortestPathsTopological(WeightedDigraph G, int s) {
        // TODO
        final double INF = Double.MAX_VALUE;  // einmalig initialisiert

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
            for (DirectedEdge e : G.incident(v)) {// durchläuft jede ausgehende gerichtete Kante v → w mit ihrem Gewicht
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
        return parent[v] >= 0;// true genau dann, wenn dist[v] einen endlichen Wert hat (v vom Startknoten erreichbar)
    }

    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int w = v; w != s; w = parent[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
    }
}



