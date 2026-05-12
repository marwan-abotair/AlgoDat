// imports für optimalSequence() benötigt, um die Reihenfolge der Züge als List<Integer> zu speichern und zurückzugeben
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a game of Row of Bowls.
 * For the games rules see Blatt05. The goal is to find an optimal strategy.
 */
public class RowOfBowls {
    // Klassenvariablen für das Spiel
    private int[][] dp; // (dp: dynamische Programmierung) Array speichert die optimalen Gewinn-Differenzen für alle Teilintervalle
    private int[] values; // enthält die Anzahl der Murmeln in jeder Bowl
    private int n; // Anzahl der Bowls

    // leeerer Konstruktor
    public RowOfBowls() {

    }

    /**
     * Implements an optimal game using dynamic programming
     * @param values array of the number of marbles in each bowl
     * @return number of game points that the first player gets, provided both parties play optimally
     */
    public int maxGain(int[] values)
    {
        // TODO
        this.values = values;
        this.n = values.length;
        dp = new int[n][n]; // DP-Matrix dp[i][j] für alle Teilintervalle (i,j); Platzbedarf O(n^2)

        for (int i = 0; i < n; i++) {
            dp[i][i] = values[i];
        }
        // Fülle dp für alle Intervalle der len = [2,n]
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                // Entscheidung: linke oder rechte Bowl
                int pickLeft  = values[i] - dp[i+1][j]; // linke Bowl
                int pickRight = values[j] - dp[i][j-1]; // rechte Bowl
                dp[i][j] = Math.max(pickLeft, pickRight); // ich wähle den Zug der mir größere Differenz bringt
            }
        }
        return dp[0][n-1]; // Ergebnis für das volle Intervall
    }
    // rekursive Methode, die in maxGainRecursive aufgerufen wird
    private int rec(int i, int j) {
        if (i > j) return 0;
        if (i == j) return values[i];
        int pickLeft = values[i] - rec(i+1, j);
        int pickRight = values[j] - rec(i, j-1);
        return Math.max(pickLeft, pickRight);
    }

    /**
     * Implements an optimal game recursively.
     * @param values array of the number of marbles in each bowl
     * @return number of game points that the first player gets, provided both parties play optimally
     */

    public int maxGainRecursive(int[] values) {
        // TODO
        // Werte und Länge speichern
        this.values = values;
        this.n = values.length;
        return rec(0, n-1); // Rekursive Berechnung für das Intervall [i,j] unter optimalem Spiel gibt im return die maximale Gewinn-Differenz zurück
    }


    /**
     * Calculates an optimal sequence of bowls using the partial solutions found in maxGain(int values)
     * @return optimal sequence of chosen bowls (represented by the index in the values array)
     */
    public Iterable<Integer> optimalSequence() {
        // TODO
        // Liste zum Sammeln der gewählten Indizes (Position im array)
        List<Integer> seq = new ArrayList<>();
        int i = 0, j = n - 1; // Anfangsintervall
        while (i <= j) {
            // Abbruch, wenn nur eine Bowl übrig ist
            if (i == j) {
                seq.add(i);
                break;
            }
            int takeLeft  = values[i] - dp[i+1][j];
            int takeRight = values[j] - dp[i][j-1];
            // entscheidet, dann welche Bowl die größere Differenz hat
            if (takeLeft >= takeRight) {
                seq.add(i++); // Linke Bowl wählen und linken Zeiger vorziehen
            } else {
                seq.add(j--); // das gleiche für rechts
            }
        }
        return seq;
    }


    public static void main(String[] args)
    {
        // For Testing
        int[] beispiel = {4, 7, 2, 3};
        RowOfBowls spiel = new RowOfBowls();
        System.out.println("maxGainRecursive: " + spiel.maxGainRecursive(beispiel));
        System.out.println("maxGain:          " + spiel.maxGain(beispiel));
        System.out.println("sequence:         " + spiel.optimalSequence());


    }
}




