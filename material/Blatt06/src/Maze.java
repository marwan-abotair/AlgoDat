import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
/**
 * Class that represents a maze with N*N junctions.
 * 
 * @author Vera Röhr
 */
public class Maze{
    private final int N;
    private Graph M;    //Maze
    public int startnode;
        
	public Maze(int N, int startnode) {
		
        if (N < 0) throw new IllegalArgumentException("Number of vertices in a row must be nonnegative");
        this.N = N;
        this.M= new Graph(N*N);
        this.startnode= startnode;
        buildMaze();
	}
	
    public Maze (In in) {
    	this.M = new Graph(in);
    	this.N= (int) Math.sqrt(M.V());
    	this.startnode=0;
    }

	
    /**
     * Adds the undirected edge v-w to the graph M.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {
		// TODO
        M.addEdge(v, w); // Fügt eine Kante zwischen Knoten v und w in den Maze-Graphen ein
    }
    
    /**
     * Returns true if there is an edge between 'v' and 'w'
     * @param v one vertex
     * @param w another vertex
     * @return true or false
     */

    public boolean hasEdge( int v, int w){

        if (v == w) return true; // Aufgabenstellung sagt reflexive Kanten sind immer vorhanden
        // Liste der Nachbarn einmal zwischenspeichern
        List<Integer> neighbours = M.adj(v);
        // Klassischer for-Loop über die Indizes
        for (int i = 0; i < neighbours.size(); i++) {
            if (neighbours.get(i) == w) {
                return true;
            }
        }
        return false;
    }

/**
     * Builds a grid as a graph.
     * @return Graph G -- Basic grid on which the Maze is built
     */
    public Graph mazegrid() {
		// TODO
        int total = N * N; // Gesamtzahl der Knoten
        Graph G = new Graph(total); // Neuerstellung des Graphen G mit n^2 Knoten

        // Iteriere über alle Zeilen (r) und Spalten (c) des Gitters
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int v = r * N + c;
                // Kante zum rechten Nachbarn (falls nicht am rechten Rand); horizontal
                if (c < N - 1) {
                    G.addEdge(v, v + 1);
                }
                // Kante zum unteren Nachbarn (falls nicht in der letzten Zeile); vertikal
                if (r < N - 1) {
                    G.addEdge(v, v + N);
                }
            }
        }
        return G;
    }
    
    /**
     * Builds a random maze as a graph.
     * The maze is build with a randomized DFS as the Graph M.
     */
    private void buildMaze() {
		// TODO
        Graph grid = mazegrid(); // ruft mazegrid() auf (quadratische Gitter mit N×N Knoten und den Basis-Kanten)
        RandomDepthFirstPaths randomDFS = new RandomDepthFirstPaths(grid, startnode); // randomDFS-Objekt initialisieren
        randomDFS.randomNonrecursiveDFS(grid);
        for (int w = 0; w < grid.V(); w++) { // for-Schleife iteriert über alle Knoten des Gitters
            if (w == startnode) continue; // continue, da noch keine Kante
            int v = randomDFS.edge()[w]; // Hier ist v immer der Parent von w im DFS-Baum, niemals ein Nachfolger
            M.addEdge(v, w); // Kante (v,w) in das Labyrinth einfügen
        }
    }

    /**
     * Find a path from node v to w
     * @param v start node
     * @param w end node
     * @return List<Integer> -- a list of nodes on the path from v to w (both included) in the right order.
     */
    public List<Integer> findWay(int v, int w){
		// TODO
    }
    
    /**
     * @return Graph M
     */
    public Graph M() {
    	return M;

    }

    public static void main(String[] args) {
		// FOR TESTING
    }


}

