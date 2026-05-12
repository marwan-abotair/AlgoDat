/**
 * This class implements and evaluates game situations of a TicTacToe game.
 */
public class TicTacToe {

    /**
     * Returns an evaluation for player at the current board state.
     * Alpha-Beta-Pruning; Bewertungs­skala siehe Aufgabenblatt 4.
     */
    public static int alphaBeta(Board board, int player)
    {
        class AB {
            // Gewinner:  1 = x,  -1 = o,  0 = noch offen
            /**
             * Prüft nach jedem Zug, ob bereits eine vollständige Gewinnlinie
             * existiert.
             * Alle Zeilen, Spalten sowie beide Hauptdiagonalen
             * aufsummiert; erreicht der Betrag einer Summe die Brettgröße *n*,
             * liegt eine durchgehende Linie desselben Spielers vor und die
             * Methode liefert +1 bzw. -1. Wird keine solche
             * Linie gefunden, gibt sie 0 zurück, d. h. das Spiel ist noch offen.
             */
            int winner(Board b) {
                int n = b.getN();

                for (int r = 0; r < n; r++) {
                    int row = 0, col = 0;
                    for (int c = 0; c < n; c++) {
                        row += b.getField(new Position(r, c));
                        col += b.getField(new Position(c, r));
                    }
                    if (Math.abs(row) == n) return row > 0 ? 1 : -1;
                    if (Math.abs(col) == n) return col > 0 ? 1 : -1;
                }

                // Die Diagonalen
                int d1 = 0, d2 = 0;
                for (int i = 0; i < n; i++) {
                    d1 += b.getField(new Position(i, i));
                    d2 += b.getField(new Position(i, n - 1 - i));
                }
                if (Math.abs(d1) == n) return d1 > 0 ? 1 : -1;
                if (Math.abs(d2) == n) return d2 > 0 ? 1 : -1;

                return 0;
            }

            // rekursive Suche
            int search(Board b, int turn, int perspective, int alpha, int beta) {

                // ,wenn entweder das Spielfeld voll ist oder es einen Sieger gibt
                int win = winner(b);
                if (b.nFreeFields() == 0 || win != 0) {
                    if (win == 0) return 0;                         // Remis / offen
                    int p = b.nFreeFields() + 1;                    // p freie Felder
                    return win == perspective ?  p : -p;            // ±(p+1)
                }

                if (turn == perspective) {                          // MAX-Knoten
                    int value = Integer.MIN_VALUE;
                    for (Position m : b.validMoves()) {
                        b.doMove(m, turn);
                        value = Math.max(value, search(b, -turn, perspective, alpha, beta));
                        b.undoMove(m);
                        alpha = Math.max(alpha, value);
                        if (alpha >= beta) break;                   // Beta-Cut
                    }
                    return value;
                } else {                                            // MIN-Knoten
                    int value = Integer.MAX_VALUE;
                    for (Position m : b.validMoves()) {
                        b.doMove(m, turn);
                        value = Math.min(value, search(b, -turn, perspective, alpha, beta));
                        b.undoMove(m);
                        beta = Math.min(beta, value);
                        if (beta <= alpha) break;                   // Alpha-Cut
                    }
                    return value;
                }
            }
        }
        return new AB().search(board, player, player,
                Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Prints a rating for every currently possible move, from player's perspective.
     * Format: jede Zeile / Spalte durch Leerzeichen getrennt, besetzte Felder als x / o.
     */

    /**
     * Erzeugt eine tabellarische Auswertung aller Felder: Für jedes freie Feld
     * führt die Methode eine Alpha-Beta-Bewertung durch
     * und gibt das Ergebnis (Stein-Symbol) formatiert aus.
     */

    public static void evaluatePossibleMoves(Board board, int player) {
        char pChar = player == 1 ? 'x' : 'o';
        System.out.println("Evaluation for player '" + pChar + "':");

        int n = board.getN();
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                Position pos = new Position(x, y);
                int tok = board.getField(pos);
                String cell;

                if (tok == 1) {
                    cell = String.format("%3c", 'x');      // "  x"
                } else if (tok == -1) {
                    cell = String.format("%3c", 'o');      // "  o"
                } else {                                   // freies Feld: Bewertung ausgeben
                    board.doMove(pos, player);
                    int score = -alphaBeta(board, -player);
                    board.undoMove(pos);
                    cell = String.format("%3d", score);    // also "  0", " -2", "  3", es sind immer zwei Leerzeichen vor der Bewertung (insgesamt ist String 3-lang)
                }

                System.out.print(cell);
                if (x < n - 1) System.out.print(' ');
            }
            System.out.println();
        }
    }

    public static void main(String[] args) { }
}