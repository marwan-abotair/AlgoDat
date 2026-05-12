import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BoardTest {

	// Konstruktor & Basisgrößen -----------------------------------------
	@Test
	void testBoard() {
		Board b = new Board(3);
		assertEquals(3, b.getN());
		assertEquals(9, b.nFreeFields());
	}

	@Test
	void testGetN() {
		Board b = new Board(5);
		assertEquals(5, b.getN());
	}

	// Frei‑Felder‑Zähler -------------------------------------------------
	@Test
	void testNFreeFields() {
		Board b = new Board(3);
		b.doMove(new Position(0, 0), 1);
		b.doMove(new Position(1, 1), -1);
		assertEquals(7, b.nFreeFields());
	}

	// getField / setField ----------------------------------------------
	@Test
	void testGetField() {
		Board b = new Board(3);
		b.setField(new Position(2, 2), 1);
		assertEquals(1, b.getField(new Position(2, 2)));
	}

	@Test
	void testSetField() {
		Board b = new Board(3);
		b.setField(new Position(1, 2), -1);
		assertEquals(-1, b.getField(new Position(1, 2)));
		assertEquals(8, b.nFreeFields());
	}

	// doMove / undoMove -------------------------------------------------
	@Test
	void testDoMove() {
		Board b = new Board(3);
		b.doMove(new Position(0, 1), 1);
		assertEquals(1, b.getField(new Position(0, 1)));
	}

	@Test
	void testUndoMove() {
		Board b = new Board(3);
		Position p = new Position(2, 0);
		b.doMove(p, -1);
		b.undoMove(p);
		assertEquals(0, b.getField(p));
		assertEquals(9, b.nFreeFields());
	}

	// Gewinnprüfung -----------------------------------------------------
	@Test
	void testIsGameWon() {
		Board b = new Board(3);
		b.doMove(new Position(0, 0), 1);
		b.doMove(new Position(0, 1), 1);
		b.doMove(new Position(0, 2), 1);
		assertTrue(b.isGameWon());
	}

	// gültige Züge ------------------------------------------------------
	@Test
	void testValidMoves() {
		Board b = new Board(3);
		b.doMove(new Position(0, 0), 1);
		int count = 0;
		for (Position p : b.validMoves()) count++;
		assertEquals(8, count);
	}

	// print() – nur aufrufen, keine Exception ---------------------------
	@Test
	void testPrint() {
		Board b = new Board(2);
		assertDoesNotThrow(b::print);
	}
}


