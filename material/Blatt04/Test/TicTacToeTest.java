import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TicTacToeTest {

	@Test
	void testAlphaBetaBoardInt() {
		// leeres 2×2‑Brett – Spieler 1 kann mit optimalem Spiel in 2 Zügen gewinnen
		Board b = new Board(2);
		int score = TicTacToe.alphaBeta(b, 1);
		assertEquals(2, score);   // p = 1 freies Feld nach Gewinn  →  p+1 = 2
	}

	@Test
	void testEvaluatePossibleMoves() {
		Board b = new Board(2);
		assertDoesNotThrow(() -> TicTacToe.evaluatePossibleMoves(b, 1));
	}
}


