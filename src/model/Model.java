package model;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Model{
	private Player[] players;
	private Player curPlayer;
	private int curPlayerIndex;
	private Turn turn;
	private Color[] colors = {Color.BLUE, Color.RED};
	private int[][] startingPos;
	private Board b;
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
	
	public Model(int width, int height, int numPlayers) {
		this.initStartingPos(width, height);
		this.b = new Board(width, height);
		this.createPlayers(numPlayers);
		this.turn = Turn.MOVE_PLAYER;
		
	}
	
	private void initStartingPos(int width, int height) {
		startingPos = new int[2][2];
		if (width % 2 == 0) {
			startingPos[0] = new int[] {width/2 -1, height-1};
			startingPos[1] = new int[] {width/2, 0};
		}else {
			startingPos[0] = new int[] {width/2, height-1};
			startingPos[1] = new int[] {width/2, 0};
		}
	}
	
	private void createPlayers(int numPlayers) {
		players = new Player[numPlayers];
		for (int i=0; i<numPlayers; i++) {
			players[i] = new Player("Player " + Integer.toString(i+1), colors[i]);
			int[] stPos = startingPos[i];
			int[] oldPos = players[i].getPosition();
			players[i].move(stPos[0], stPos[1]);
			b.setStartTiles(stPos[0], stPos[1]);
			changes.firePropertyChange("Player moved", oldPos, players[i].getPosition());
		}
		curPlayer = players[0];
	}
	
	private void nextPlayer() {
		curPlayerIndex = curPlayerIndex == players.length -1 ? 0 : curPlayerIndex + 1;
		curPlayer = players[curPlayerIndex];
	}
	
	public boolean move(int x, int y) {
		if (turn == Turn.MOVE_PLAYER) {
			if (b.getTile(x, y) == Tile.FILLED || b.getTile(x, y) == Tile.START) {
				int[] oldPos = curPlayer.getPosition();
				curPlayer.move(x, y);
				turn = Turn.REMOVE_TILE;
				changes.firePropertyChange("Player moved", oldPos, curPlayer.getPosition());
				return true;
			}
		}
		return false;
	}
	
	public Turn getTurn() {
		return turn;
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	public Player getcurPlayer() {
		return curPlayer;
	}
	
	public Board getBoard() {
		return b;
	}
	
	public int[] getDim() {
		return b.getDim();
	}
	
	public boolean removeTile(int x, int y) {
		if (turn == Turn.REMOVE_TILE) {
			if (b.removeTile(x, y)) {
				turn = Turn.MOVE_PLAYER;
				this.nextPlayer();
				return true;
			}
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
