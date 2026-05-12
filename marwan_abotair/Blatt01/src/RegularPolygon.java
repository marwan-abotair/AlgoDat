// Diese Klasse implementiert nur *zentrierte* reguläre Polygone, also mit midpoint = (0, 0).

public class RegularPolygon extends ConvexPolygon {
    private int N;
    private double radius;

    public RegularPolygon(int N, double radius) {
    super();
    //this verweist auf die Attribute der Klasse RegularPolygon. Wenn man kein this schreibt, greift Java auf den lokalen Parameter oder die lokale Variable zu
    this.N = N;
    this.radius = radius;
    this.vertices = new Vector2D[N];
    resize(radius);
    }
//Copy-Konstruktor erstellt die Kopie des polygon-Objekts
    public RegularPolygon(RegularPolygon polygon) {
        this(polygon.N, polygon.radius);
    }

    public void resize(double newradius) {
        this.radius = newradius;
        Vector2D[] newvertices = new Vector2D[N];
        for (int i = 0; i < N; i++) {
// Berechnet den Winkel für die i-te Ecke (i läuft von 0 bis N-1)
            double angle = 2 * Math.PI * i / N;
            double x = newradius * Math.cos(angle);
            double y = newradius * Math.sin(angle);
            newvertices[i] = new Vector2D(x, y);
        }
        this.vertices = newvertices;
    }

    @Override
    public String toString() {
        return "RegularPolygon{N=" + N + ", radius=" + radius + "}";
    }

    public static void main(String[] args) {
        RegularPolygon pentagon = new RegularPolygon(5, 1);
        System.out.println("Der Flächeninhalt des " + pentagon + " beträgt " + pentagon.area() + " LE^2.");
//        RegularPolygon otherpentagon = pentagon;      // Dies funktioniert nicht!
        RegularPolygon otherpentagon = new RegularPolygon(pentagon);
        pentagon.resize(10);
        System.out.println("Nach Vergrößerung: " + pentagon + " mit Fläche " + pentagon.area() + " LE^2.");
        System.out.println("Die Kopie: " + otherpentagon + " mit Fläche " + otherpentagon.area() + " LE^2.");
        /*
        Die erwartete Ausgabe ist:
Der Flächeninhalt des RegularPolygon{N=5, radius=1.0} beträgt 2.377641290737883 LE^2.
Nach Vergrößerung: RegularPolygon{N=5, radius=10.0} mit Fläche 237.7641290737884 LE^2.
Die Kopie: RegularPolygon{N=5, radius=1.0} mit Fläche 2.377641290737883 LE^2.
         */
    }
}

