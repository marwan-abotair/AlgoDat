import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PermutationTest {
	PermutationVariation p1;
	PermutationVariation p2;
	public int n1;
	public int n2;
	int cases = 0;  // 0 = Permutation, 1 = Permutation1

	void initialize() {
		n1 = 4;
		n2 = 6;
		Cases c = new Cases();

		p1 = c.switchforTesting(cases, n1);
		p2 = c.switchforTesting(cases, n2);
	}

	public void fixConstructor() {
		// falls im Konstruktor etwas nicht korrekt initialisiert wurde
		p1.allDerangements = new LinkedList<int[]>();
		for (int i = 0; i < n1; i++) {
			p1.original[i] = 2 * i + 1;
		}
		p2.allDerangements = new LinkedList<int[]>();
		for (int i = 0; i < n2; i++) {
			p2.original[i] = i + 1;
		}
	}

	@Test
	void testPermutation() {
		initialize();
		// TODO
		// original darf nicht null sein und muss richtige Länge haben
		assertNotNull(p1.original, "original von p1 sollte initialisiert sein");
		assertEquals(n1, p1.original.length, "original.length muss n1 sein");
		assertNotNull(p2.original, "original von p2 sollte initialisiert sein");
		assertEquals(n2, p2.original.length, "original.length muss n2 sein");

		//keine Duplikate im original (verschachtelte Schleifen)
		for (int i = 0; i < p1.original.length; i++) {
			for (int j = i + 1; j < p1.original.length; j++) {
				assertNotEquals(p1.original[i], p1.original[j],
						"Duplikat im original von p1: " + p1.original[i]);
			}
		}
		for (int i = 0; i < p2.original.length; i++) {
			for (int j = i + 1; j < p2.original.length; j++) {
				assertNotEquals(p2.original[i], p2.original[j],
						"Duplikat im original von p2: " + p2.original[i]);
			}
		}

		//allDerangements muss vor Berechnung leer sein
		assertNotNull(p1.allDerangements, "allDerangements von p1 sollte initialisiert sein");
		assertTrue(p1.allDerangements.isEmpty(), "allDerangements von p1 muss vor Aufruf leer sein");
		assertNotNull(p2.allDerangements, "allDerangements von p2 sollte initialisiert sein");
		assertTrue(p2.allDerangements.isEmpty(), "allDerangements von p2 muss vor Aufruf leer sein");
	}

	@Test
	void testDerangements() {
		initialize();
		// falls im Konstruktor etwas nicht korrekt initialisiert wurde
		fixConstructor();
		// Math.pow(a, b) berechnet a^b als double – hier nutzen wir Math.pow(-1, k), um abwechselnd +1 und -1 zu erhalten
		// Math.round(x) rundet den double-Wert x auf den nächsten long – so erhalten wir eine ganzzahlige Anzahl von Derangements
		// TODO
		p1.derangements();
		p2.derangements();

		long expected1;
		{
			double sum = 0;
			for (int k = 0; k <= n1; k++) {
				long fk = 1;
				for (int i = 1; i <= k; i++) fk *= i;
				sum += Math.pow(-1, k) / fk;
			}
			long fact = 1;
			for (int i = 1; i <= n1; i++) fact *= i;
			expected1 = Math.round(fact * sum);
		}
		long expected2;
		{
			double sum = 0;
			for (int k = 0; k <= n2; k++) {
				long fk = 1;
				for (int i = 1; i <= k; i++) fk *= i;
				sum += Math.pow(-1, k) / fk;
			}
			long fact = 1;
			for (int i = 1; i <= n2; i++) fact *= i;
			expected2 = Math.round(fact * sum);
		}
		assertEquals(expected1, p1.allDerangements.size(), "Anzahl Derangements für n1=" + n1);
		assertEquals(expected2, p2.allDerangements.size(), "Anzahl Derangements für n2=" + n2);

		//Fixpunktfreiheit prüfen
		for (int[] arr : p1.allDerangements) {
			assertEquals(n1, arr.length, "Jedes Derangement von p1 muss Länge n1 haben");
			for (int i = 0; i < n1; i++) {
				assertNotEquals(p1.original[i], arr[i],
						"Fixpunkt an Position " + i + " in einem Derangement von p1");
			}
		}
		for (int[] arr : p2.allDerangements) {
			assertEquals(n2, arr.length, "Jedes Derangement von p2 muss Länge n2 haben");
			for (int i = 0; i < n2; i++) {
				assertNotEquals(p2.original[i], arr[i],
						"Fixpunkt an Position " + i + " in einem Derangement von p2");
			}
		}
	}

	@Test
	void testsameElements() {
		initialize();
		// falls im Konstruktor etwas nicht korrekt initialisiert wurde
		fixConstructor();
		// TODO

		p1.derangements();
		p2.derangements();

		//sortierte Kopien des Originals zum Vergleich
		int[] orig1 = java.util.Arrays.copyOf(p1.original, n1);
		int[] orig2 = java.util.Arrays.copyOf(p2.original, n2);
		java.util.Arrays.sort(orig1);
		java.util.Arrays.sort(orig2);

		assertFalse(p1.allDerangements.isEmpty(), "Keine Derangements für p1 erzeugt!");
		assertFalse(p2.allDerangements.isEmpty(), "Keine Derangements für p2 erzeugt!");

		//In jedem Derangement dieselbe Multimenge
		for (int[] arr : p1.allDerangements) {
			int[] tmp = java.util.Arrays.copyOf(arr, arr.length);
			java.util.Arrays.sort(tmp);
			assertArrayEquals(orig1, tmp,
					"Element-Multimenge stimmt nicht mit original von p1 überein");
		}
		for (int[] arr : p2.allDerangements) {
			int[] tmp = java.util.Arrays.copyOf(arr, arr.length);
			java.util.Arrays.sort(tmp);
			assertArrayEquals(orig2, tmp,
					"Element-Multimenge stimmt nicht mit original von p2 überein");
		}
	}

	void setCases(int c) {
		this.cases = c;
	}
}



