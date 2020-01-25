package model;

import java.util.Observable;

public class Board extends Observable {
	private Tile[][] game;
	private int width;
	private int height;
	
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		this.setupBoard();
	}
	
	private void setupBoard() {
		game = new Tile[width][height];
		for (int x=0; x<game.length; x++) {
			for (int y=0; y<game[x].length; y++) {
				game[x][y] = Tile.FILLED;
			}
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public int[] getDim() {
		int[] dim = new int[2];
		dim[0] = width;
		dim[1] = height;
		return dim;
	}
	
	public void setStartTiles(int x, int y) {
		game[x][y] = Tile.START;
		this.setChanged();
		this.notifyObservers();
	}
	
	public Tile getTile(int x, int y) {
		return game[x][y];
	}
	
	public boolean removeTile(int x, int y) {
		if (game[x][y] == Tile.FILLED) {
			game[x][y] = Tile.EMPTY;
			this.setChanged();
			this.notifyObservers();
			return true;
		}
		return false;
	}
}
