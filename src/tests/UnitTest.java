package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import model.*;
import java.awt.Color;

public class UnitTest {
	@Test
	public void createPlayer() {
		String name = "Test";
		Color color = Color.BLACK;
		Player p = new Player(name, color);
		
		assertEquals(p.getName(), name);
		assertEquals(p.getColor(), color);
	}
	
	@Test
	public void movePlayer() {
		String name = "Test";
		Color color = Color.BLACK;
		Player p = new Player(name, color);
		
		p.move(1, 1);
		assertArrayEquals(p.getPosition(), new int[] {1,1});
		
		p.move(5, 7);
		assertArrayEquals(p.getPosition(), new int[] {5,7});
		
		
		int[] t = p.getPosition();
		t[0]++;
		assertNotSame(p.getPosition(), t);
	}
	
	@Test
	public void createBoard() {
		Board b = new Board(5,5);
		
		assertArrayEquals(b.getDim(), new int[] {5,5});
	}

	@Test
	public void createBoard_WithNegativeValues_ThrowsException() {
		Board b = new Board(-2,-1);
		
		
	}
}
