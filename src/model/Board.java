package model;

import java.awt.Color;
import java.util.Observable;

public class Board extends Observable {
	private Coordination[][] game;
	private int width;
	private int height;
	
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		this.setupBoard();
	}
	
	private void setupBoard() {
		game = new Coordination[width][height];
		for (int x=0; x<game.length; x++) {
			for (int y=0; y<game[x].length; y++) {
				game[x][y] = new Tile(TileState.FILLED);
			}
		}
		game[Math.floorDiv(width, 2)][0] = new Player("test", Color.RED);
		game[Math.floorDiv(width, 2) - 1][game[game.length - 1].length - 1] = new Player("test", Color.GREEN);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public int[] getDim() {
		int[] dim = new int[2];
		dim[0] = width;
		dim[1] = height;
		return dim;
	}
	
	public Coordination getLocation(int x, int y) {
		return game[x][y];
	}
}
