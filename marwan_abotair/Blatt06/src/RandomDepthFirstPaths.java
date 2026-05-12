import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class RandomDepthFirstPaths {
    private boolean[] marked; // marked[v] = is there an s-v path?
    private int[] edgeTo; // edgeTo[v] = last edge on s-v path
    private final int s; // source vertex

    /**
     * Computes a path between {@code s} and every other vertex in graph {@code G}.
     * 
     * @param G the graph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public RandomDepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
    }

    public void randomDFS(Graph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        randomDFS(G, s);
    }

    // depth first search from v
    /*
     * Unterschied zum „dfs“:
     * Hier wird die Nachbarsliste vor dem Durchlaufen mit Collections.shuffle(neighbours)
     * gemischt, um die Reihenfolge der rekursiven Besuche zufällig zu gestalten.
     */
    private void randomDFS(Graph G, int v) {
        // TODO
        marked[v] = true; // besucht

        // Nachbarn kopieren & mischen, damit G.adj(v) unverändert bleibt
        List<Integer> neighbours = (G.adj(v));
        Collections.shuffle(neighbours); // Randomisierung
        // marked[v] = true, sobald Knoten v besucht wurde, und edgeTo[w] = v speichert im DFS-Baum, von welchem Vorgänger v w entdeckt wurde.
        for (int w : neighbours) {
            if (!marked[w]) {
                edgeTo[w] = v;
                randomDFS(G, w); // Rekursion
            }
        }
       // Kein postorder.add(v), da hier nicht benötigt
    }

    /*
     * Anders als in nonrecursiveDFS wird hier die Nachbarsliste
     * mit Collections.shuffle(neighbours) gemischt, damit die DFS-
     * Reihenfolge zufällig ist und nicht immer gleich abläuft.
     */

    public void randomNonrecursiveDFS(Graph G) {
        // TODO
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        int V = G.V();

        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[V];
        for (int v = 0; v < G.V(); v++) {
            List<Integer> neighbours = (G.adj(v));
            Collections.shuffle(neighbours); // <- Unterschied: Randomisierung der Besuchsreihenfolge
            adj[v] = neighbours.iterator();
        }
        // Stackbasierte Tiefensuche
        Stack<Integer> stack = new Stack<>();
        marked[s] = true;
        stack.push(s);

        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (adj[v].hasNext()) {
                int w = adj[v].next();
                if (!marked[w]) {
                    marked[w]  = true;
                    edgeTo[w]  = v;
                    stack.push(w);
                }
            } else {
                stack.pop();
            }
        }
    }

    /**
     * Is there a path between the source vertex {@code s} and vertex {@code v}?
     * 
     * @param v the vertex
     * @return {@code true} if there is a path, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns a path between the vertex {@code v} and the source vertex {@code s},
     * or
     * {@code null} if no such path.
     * 
     * @param v the vertex
     * @return the sequence of vertices on a path between the vertex
     *         {@code v} and the source vertex {@code s}, as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * 
     */
    // wie 3.2, aber in dieser Klasse fehlt das LinkedList-Import, daher werden die Knoten mit einem Stack und Collections.reverse() gesammelt.
    public List<Integer> pathTo(int v) {
        // TODO
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();

        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        Collections.reverse(path);
        return path;
    }

    public int[] edge() {
        return edgeTo;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

}
