import java.util.InputMismatchException;
import java.util.Stack;

import static java.lang.Math.abs;
/**
 * This class represents a generic TicTacToe game board.
 */
public class Board {
    private int n;
    //TODO
    private int[][] board;
    private int freeFields;

    /**
     *  Creates Board object, am game board of size n * n with 1<=n<=10.
     */
    public Board(int n)
    {
        // TODO
        if (n < 1 || n > 10)
            throw new InputMismatchException("Board size must be 1–10.");
        this.n = n;
        board = new int[n][n];          // alles 0 (frei)
        freeFields = n * n;
    }

    /**
     *  @return     length/width of the Board object
     */
    public int getN() { return n; }

    /**
     *  @return     number of currently free fields
     */
    public int nFreeFields() {
        // TODO
        return freeFields;
    }

    /**
     *  @return     token at position pos
     */
    public int getField(Position pos) throws InputMismatchException
    {
        // TODO
        if (pos == null) throw new InputMismatchException("Position is null.");
        int r = pos.x, c = pos.y;
        if (r < 0 || r >= n || c < 0 || c >= n)
            throw new InputMismatchException("Position out of bounds.");
        return board[r][c];
    }

    /**
     *  Sets the specified token at Position pos.
     */
    public void setField(Position pos, int token) throws InputMismatchException
    {
        // TODO
        if (token != -1 && token != 0 && token != 1)
            throw new InputMismatchException("Token must be -1, 0 or 1.");
        if (pos == null) throw new InputMismatchException("Position is null.");
        int r = pos.x, c = pos.y;
        if (r < 0 || r >= n || c < 0 || c >= n)
            throw new InputMismatchException("Position out of bounds.");

        int old = board[r][c];
        if (old == 0 && token != 0) freeFields--;
        if (old != 0 && token == 0) freeFields++;
        board[r][c] = token;
    }

    /**
     *  Places the token of a player at Position pos.
     */
    public void doMove(Position pos, int player)
    {
        // TODO
        if (player != -1 && player != 1)
            throw new InputMismatchException("Player must be 1 or -1.");
        if (getField(pos) != 0)
            throw new InputMismatchException("Field already occupied.");
        setField(pos, player);
    }

    /**
     *  Clears board at Position pos.
     */
    public void undoMove(Position pos)
    {
        // TODO
        if (getField(pos) == 0)
            throw new InputMismatchException("Field already empty.");
        setField(pos, 0);
    }

    /**
     *  @return     true if game is won, false if not
     */
    // r durchläuft alle rows und c alle columns.  In jedem Durchgang addieren wir den aktuellen Feldwert zu sumRow bzw. sumCol.
    // Liegen in einer Zeile oder Spalte nur identische Steine, ist |Summe| = n; → erkennt sofort den Gewinn
    public boolean isGameWon() {
        // TODO
        for (int r = 0; r < n; r++) {
            int sumRow = 0, sumCol = 0;
            for (int c = 0; c < n; c++) {
                sumRow += board[r][c];
                sumCol += board[c][r];
            }
            if (abs(sumRow) == n || abs(sumCol) == n) return true; // abs(…) == n bedeutet: alle n Felder tragen dasselbe Vorzeichen (entweder +1 → x oder −1 → o)
        }
        int d1 = 0, d2 = 0;
        for (int i = 0; i < n; i++) {
            d1 += board[i][i];
            d2 += board[i][n - 1 - i];
        }
        return abs(d1) == n || abs(d2) == n;
    }

    /**
     *  @return     set of all free fields as some Iterable object
     */
    public Iterable<Position> validMoves()
    {
        // TODO
        Stack<Position> moves = new Stack<>(); // Sucht Zeile für Zeile nach Feldern, deren Wert 0 ist, legt jede freie Koordinate als Position-Objekt in einen Stack und gibt diesen Stack zurück.  Da Stack Iterable implementiert, kann man das Ergebnis direkt in einer foreach-Schleife durchlaufen.

        for (int r = 0; r < n; r++)
            for (int c = 0; c < n; c++)
                if (board[r][c] == 0)
                    moves.push(new Position(r, c));
        return moves;
    }

    /**
     *  Outputs current state representation of the Board object.
     *  Practical for debugging.
     */
    public void print()
    {
        // TODO
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                char ch = board[r][c] == 1  ? 'x'
                        : board[r][c] == -1 ? 'o'
                        : '.';
                System.out.print(ch + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}



