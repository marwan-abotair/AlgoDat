
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StringGenomeTest {

	@Test
	public void testAddNucleotide() {
		StringGenome genome = new StringGenome();
		genome.addNucleotide('A');
		assertEquals(genome.toString(),"A");
		try {
			genome.addNucleotide('X');
			assertTrue(false);
		}
		catch(RuntimeException e) {
			assertTrue(true);
		}
		genome.addNucleotide('C');
		assertEquals(genome.toString(),"AC");
		
		}

	@Test
	public void testNucleotideAt() {
		StringGenome genome = new StringGenome();
		genome.addNucleotide('A');
		genome.addNucleotide('G');
		genome.addNucleotide('C');
		genome.addNucleotide('C');
		genome.addNucleotide('T');
		assertEquals(genome.nucleotideAt(2),'C');
		assertEquals(genome.nucleotideAt(4),'T');
		try {
			genome.nucleotideAt(6);
			assertTrue(false);
		}
		catch(RuntimeException e) {
			assertTrue(true);
		}
	
	}

	@Test
	public void testLength() {
		StringGenome genome = new StringGenome();
		genome.addNucleotide('A');
		genome.addNucleotide('G');
		genome.addNucleotide('C');
		genome.addNucleotide('C');
		assertEquals(genome.length(), 4);
		genome.addNucleotide('G');
		assertEquals(genome.length(), 5);
	}

	@Test
	public void testToString() {
		StringGenome genome = new StringGenome();
		genome.addNucleotide('A');
		genome.addNucleotide('C');
		genome.addNucleotide('T');
		
		assertEquals(genome.toString(),"ACT");
	}

	@Test
	public void testEqualsObject() {
		StringGenome genome = new StringGenome();
		genome.addNucleotide('G');
		StringGenome genome1 = new StringGenome();
		genome1.addNucleotide('G');
		assertTrue(genome.equals(genome1));
	}

}
