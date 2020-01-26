package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Board{
	private Tile[][] game;
	private int width;
	private int height;
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
	
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
		//might need to change "old game" here later on.
		changes.firePropertyChange("BoardModified", "old game", game);
	}
	
	public int[] getDim() {
		int[] dim = new int[2];
		dim[0] = width;
		dim[1] = height;
		return dim;
	}
	
	public void setStartTiles(int x, int y) {
		changes.firePropertyChange("BoardModified", game, game[x][y] = Tile.START);
	}
	
	public Tile getTile(int x, int y) {
		return game[x][y];
	}
	
	public boolean removeTile(int x, int y) {
		if (game[x][y] == Tile.FILLED) {
			changes.firePropertyChange("BoardModified", game, game[x][y] = Tile.EMPTY);
			return true;
		}
		return false;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener l) {
	        changes.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
	        changes.removePropertyChangeListener(l);
    }
}
