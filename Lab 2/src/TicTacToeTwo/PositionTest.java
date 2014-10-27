package TicTacToeTwo;

import static org.junit.Assert.*;

import javax.crypto.AEADBadTagException;

import org.junit.Test;

public class PositionTest {

	//Board is just 9 empty spaces!!
	
	@Test
	public void testNew() {
		Position p = new Position();
		assertEquals("         ", p.toString());
		assertEquals('x', p.turn);
	}

	@Test
	public void testMove() throws Exception{
		Position p = new Position().move(1);
		assertEquals(" x       ", p.toString());
		assertEquals('o', p.turn);
	}
	
	@Test
	public void testPossibleMove() throws Exception{
		Position p =new Position().move(1).move(3).move(4);
		assertArrayEquals(new Integer[]{0,2,5,6,7,8}, p.possibleMoves());
	}
	
	@Test
	public void testWin() throws Exception{
		assertFalse(new Position().win('x'));
		assertTrue(new Position("xxx       ").win('x'));
		assertTrue(new Position("   ooo   ").win('o'));
		assertTrue(new Position("x  x  x  ").win('x'));
		assertTrue(new Position("x   x   x").win('x'));
		assertTrue(new Position("  x x x  ").win('x'));
	}
}
