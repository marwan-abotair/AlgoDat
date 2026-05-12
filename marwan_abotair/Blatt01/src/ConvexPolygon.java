import java.util.Arrays;


public class ConvexPolygon extends Polygon {

    public ConvexPolygon(Vector2D [] vertices) {
        super(); //ruft den Konstruktor der Oberklasse auf
        this.vertices = vertices; //speichert die übergebenen vertices
    }

    //leere Konstruktor wird für Untergruppen gebraucht
    public ConvexPolygon(){

    }
//Umfang
public double perimeter() {
    double perimeter = 0;
    for (int i = 0; i < vertices.length; i++) {
        Vector2D current = vertices[i];
        Vector2D next = vertices[(i + 1) % vertices.length]; //verbindet auch den letzten Punkt mit dem ersten (geschlossenes Polygon)
        double dx = next.getX() - current.getX();
        double dy = next.getY() - current.getY();
        perimeter += Math.sqrt(dx * dx + dy * dy); //euklidische Distanz
    }
    return perimeter;
}
    //Flächeninhalt
    public double area(){
        double totalArea=0.0; //Area beginnt mit dem Wert 0.0
        //fixedVertex : einzelner Eckpunkt
        Vector2D fixedVertex = vertices[vertices.length - 1];

        //Bei n Punkten hat ein konvexes Polygon n - 2 Dreiecke und die Schleife geht von i = 0 bis i < n - 2.
        for (int i = 0; i < vertices.length - 2; i++) { //i geht bis 3, das heisst es gibt 4 Dreicke
            Triangle triangle = new Triangle(fixedVertex, vertices[i], vertices[i + 1]);
            totalArea += triangle.area();
        }
        return totalArea;
    }
    public static double totalArea(Polygon[] polygons){
        double totalArea=0;
        for(int i = 0; i<polygons.length; i++){
            totalArea += polygons[i].area();
        }
        return totalArea;
    }
//Eckpunkte der Vielfachecke
    public static Polygon[] somePolygons(){

        Triangle triangle = new Triangle(
                new Vector2D(0, 0),
                new Vector2D(10, 0),
                new Vector2D(5, 5)
        );

        Tetragon tetragon = new Tetragon(
                new Vector2D(0, 0),
                new Vector2D(10, -5),
                new Vector2D(12, 2),
                new Vector2D(3, 17)
        );

        RegularPolygon pentagon = new RegularPolygon(5, 1);

        RegularPolygon hexagon = new RegularPolygon(6, 1);

        return new Polygon[] {triangle, tetragon, pentagon, hexagon};
    };

    @Override
    //toString() liefert eine benutzerfreundliche Textdarstellung
    public String toString() {
        return "ConvexPolygon(" + Arrays.toString(vertices) + ")";
    }

}

