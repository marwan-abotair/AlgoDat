import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MatrixImage implements Image {
    public int[][] field;
    
    /**
     * A Constructor for the MatrixImage class
     * @param sx the size on the x-axis
     * @param sy the size on the y-axis
     */
    public MatrixImage(int sx, int sy) {
        field = new int[sx][sy];
    }

    /**
     * A deep dopy constructor for a given MatrixImage that
     * @param that the to be copied MatrixImage
     */
     public MatrixImage(MatrixImage that) {
        this(that.sizeX(), that.sizeY());
        for (int x = 0; x < sizeX(); x++) {
            field[x] = that.field[x].clone();
        }
    }
    
    /**
     * Initializes a MatrixImage from a given file
     * @param filename the file
     * @throws java.io.FileNotFoundException if there is no such file
     */
    public MatrixImage(String filename) throws java.io.FileNotFoundException {
        System.setIn(new FileInputStream(filename));
        Scanner in = new Scanner(System.in);
        int sx = in.nextInt();
        int sy = in.nextInt();
        field = new int[sx][sy];
        for (int y = 0; y < sy; y++) {
            for (int x = 0; x < sx; x++) {
                field[x][y] = in.nextInt();
            }
        }
    }
    
    /**
    * @return the size on the x-axis
    */
    @Override
    public int sizeX() {
        return field.length;
    }
    
    /**
    * @return the size on the y-axis
    */
    @Override
    public int sizeY() {
        return field[0].length;
    }
    
    /**
     * Calculates the contrast between two coordinates/nodes
     * @param p0 first coordinate
     * @param p1 second coordinate
     * @return the absolute value of the contrast
     * @throws InputMismatchException if the coordinates are not in the image
     */
    @Override
    public double contrast(Coordinate p0, Coordinate p1) throws InputMismatchException {
        // TODO
        int x0 = p0.x, y0 = p0.y;
        int x1 = p1.x, y1 = p1.y;
        // Überprüfen, ob p0 oder p1 außerhalb liegen
        if (x0 < 0 || x0 >= sizeX() || y0 < 0 || y0 >= sizeY()
                || x1 < 0 || x1 >= sizeX() || y1 < 0 || y1 >= sizeY()) {
            throw new InputMismatchException(
                    String.format("Koordinate außerhalb des Bildes: (%d,%d) oder (%d,%d)", x0, y0, x1, y1)); //Exception wie in der Aufgabenstellung gefordert
        }
        // Absoluter Differenzbetrag
        return Math.abs(field[x0][y0] - field[x1][y1]);
    }
    
    /**
     * Removes the given vertical path from the image.
     * Create a deep copy of the image with the correct new Matrix size.
     * @param path the do be deleted vertical path
     */
    @Override
    public void removeVPath(int[] path) {
        // TODO
        int W = sizeX();
        int H = sizeY();
        // Matrix mit veränderter Breite anlegen
        int[][] newField = new int[W - 1][H];
        for (int y = 0; y < H; y++) {
            int skipX = path[y]; // Spalte, die in dieser Zeile übersprungen wird
            int j = 0;
            for (int x = 0; x < W; x++) { // alle Spalten außer skipx kopieren
                if (x == skipX)
                    continue;
                newField[j++][y] = field[x][y];
            }
        }
        // Referenz auf die neue, kleinere Matrix setzen
        this.field = newField;
    }

    @Override
    public String toString() {
        String str = "";
        for (int y = 0; y < sizeY(); y++) {
            for (int x = 0; x < sizeX(); x++) {
                str += field[x][y] + " ";
            }
            str += "\n";
        }
        return str;
    }

    @Override
    public void render() {
        System.out.println(toString());
    }

}

