import java.util.*;

/**
 * The class {@code Bettelmann} simulated the card game 'Bettelmann'. You can construct objects
 * either by providing the piles of cards of the two players, or by requesting a shuffled
 * distribution of cards.
 */
public class Bettelmann {
    private Deque<Card> closedPile1;
    private Deque<Card> closedPile2;
    private int winner = -1;

    /**
     * Constructor which initializes both players with empty piles.
     */
    public Bettelmann() {
        closedPile1 = new LinkedList<>();
        closedPile2 = new LinkedList<>();
    }

    /**
     * Constructor which initializes both players with the provided piles of cards.
     *
     * @param pile1 pile of cards of player 1.
     * @param pile2 pile of cards of player 2.
     */
    public Bettelmann(Deque<Card> pile1, Deque<Card> pile2) {
        closedPile1 = pile1;
        closedPile2 = pile2;
    }

    /**
     * Returns the closed pile of player 1 (required for the tests).
     *
     * @return The closed pile of player 1.
     */
    public Deque<Card> getClosedPile1() {
        return closedPile1;
    }

    /**
     * Returns the closed pile of player 2 (required for the tests).
     *
     * @return The closed pile of player 2.
     */
    public Deque<Card> getClosedPile2() {
        return closedPile2;
    }

    /**
     * Play one round of the game. This includes drawing more cards, when both players
     * have drawn cards of the same rank. At the end of the round, the player with the
     * higher ranked card wins the trick, so all drawn cards from that round are added
     * to the bottom of her/his closed pile of cards.
     */

    public void playRound() {
        // TODO implement this method
        if (winner != -1) return; //Spiel bereits beendet

        if (closedPile1.isEmpty() && closedPile2.isEmpty()) {
            winner = 0; //Unentschieden
            return;
        }
        if (closedPile1.isEmpty()) {
            winner = 2;
            return;
        }
        if (closedPile2.isEmpty()) {
            winner = 1;
            return;
        }

        //FIFO für offene Karten
        Deque<Card> open1 = new LinkedList<>();
        Deque<Card> open2 = new LinkedList<>();

        /*Die Schleife beginnt bewusst mit while (true), weil man vor dem Ziehen nicht wissen kann, wie viele „Kriege“ (Gleichstände) nacheinander auftreten.
        Anstatt eine komplizierte Abbruchbedingung in den Schleifenkopf zu schreiben, lässt man die Schleife endlos laufen und beendet sie nur dann,
        wenn im Rumpf entweder ein return ausgeführt wird (das gesamte Spiel ist sofort entschieden,
        weil ein Stapel leer ist) oder ein break erreicht wird (die aktuelle Runde ist entschieden, weil endlich eine höhere Karte gefallen ist).
        Auf diese Weise bleibt der Code kompakt, und dennoch wird die Schleife garantiert verlassen, sobald das Ergebnis feststeht.*/

        while (true) {
            // Vor dem Ziehen überprüfen, ob Spieler aufgeben muss
            if (closedPile1.isEmpty() && closedPile2.isEmpty()) {
                winner = 0;
                return;
            }
            if (closedPile1.isEmpty()) {
                winner = 2;
                return;
            }
            if (closedPile2.isEmpty()) {
                winner = 1;
                return;
            }

            // Karten ziehen und auf offene Stapel legen
            Card card1 = closedPile1.pollFirst();
            Card card2 = closedPile2.pollFirst();
            open1.addLast(card1);
            open2.addLast(card2);

            int cmp = card1.compareTo(card2);
            if (cmp == 0) {
                //Gleichstand –> neue Karten ziehen
                continue;
            }

            /*Deque<Card> allCards = new LinkedList<>();
            allCards.addAll(open1);
            allCards.addAll(open2);*/
            //wenn cmp größer als 0 ist, bedeutet das, dass Spieler 1 die höhere Karte hat und den Stich gewinnt
            if (cmp > 0) {
                for (Card c : open1) closedPile1.addLast(c); //eigene offenen Karten
                for (Card c : open2) closedPile1.addLast(c); //dann die des Gegners
            }
            //Spieler 2 gewinnt den Stich
            else {
                for (Card c : open2) closedPile2.addLast(c);
                for (Card c : open1) closedPile2.addLast(c);
            }
            break; //verlässt die Schleife, wenn ein Gewinner dieser Runde feststeht (kein Gleichstand mehr)
        }

        //Siegprüfung
        if (closedPile1.isEmpty() && closedPile2.isEmpty()) {
            winner = 0;
        } else if (closedPile1.isEmpty()) {
            winner = 2;
        } else if (closedPile2.isEmpty()) {
            winner = 1;
        }
    }



    /**
     * Returns the winner of the game after the end, or -1 during the game.
     *
     * @return the winner of game (1 or 2), or -1 while the game is ongoing.
     */
    public int getWinner() {
        return winner;
    }

    /**
     * Deal the given deck of cards alternately to the two players.
     * Side effect: The deck is empty after calling this method.
     *
     * @param deck The deck of cards that is distributed to the players.
     */
    public void distributeCards(Stack<Card> deck) {
        closedPile1.clear();
        closedPile2.clear();
        // use addFirst() because the last distributed card should be drawn first
        while (!deck.isEmpty()) {
            Card card = deck.pop();
            closedPile1.addFirst(card);
            if (!deck.isEmpty()) {
                card = deck.pop();
                closedPile2.addFirst(card);
            }
        }
    }

    /**
     * Shuffle a deck of cards and distribute it evenly to the two players.
     */
    public void distributeCards() {
        Stack<Card> deck = new Stack<>();
        for (int i = 0; i < Card.nCards; i++){
            deck.add(new Card(i));
        }
        Collections.shuffle(deck);
        distributeCards(deck);
    }

    /**
     * Returns a String representation of closed piles of cards of the two players.
     *
     * @return String representation of the state of the game.
     */
    @Override
    public String toString() {
        return "Player 1: " + closedPile1 + "\nPlayer 2: " + closedPile2;
    }

    public static void main(String[] args) {
/*
        // Game with a complete, shuffled deck
        Bettelmann game = new Bettelmann();
        game.distributeCards();
*/

        // For testing, you may also use specific distribtions and a small number of cards like this:
        int[] deckArray = {28, 30, 6, 23, 17, 14};
        Stack<Card> deck = new Stack<>();
        for (int id : deckArray) {
            deck.push(new Card(id));
        }
        Bettelmann game = new Bettelmann();
        game.distributeCards(deck);

        // This part is the same for both of the above variants
        System.out.println("Initial situation (top card first):\n" + game);
        int round = 0;
        while (round < 1000000 && game.getWinner()<0) {
            round++;
            game.playRound();
            System.out.println("State after round " + round + ":\n" + game);
        }
    }
}

