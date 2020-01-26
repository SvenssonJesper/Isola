package model;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Model{
	private Player[] players;
	private int numPlayers;
	private Color[] colors = {Color.BLUE, Color.RED};
	private int[][] startingPos;
	private Board b;
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
	
	public Model(int width, int height, int numPlayers) {
		this.numPlayers = numPlayers;
		this.initStartingPos(width, height);
		this.b = new Board(width, height);
		this.createPlayers();
		
	}
	
	private void initStartingPos(int width, int height) {
		startingPos = new int[2][2];
		if (width % 2 == 0) {
			startingPos[0] = new int[] {width/2, 0};
			startingPos[1] = new int[] {width/2 -1, height-1};
		}else {
			startingPos[0] = new int[] {width/2, 0};
			startingPos[1] = new int[] {width/2, height-1};
		}
	}
	
	private void createPlayers() {
		players = new Player[numPlayers];
		for (int i=0; i<numPlayers; i++) {
			players[i] = new Player("Player " + Integer.toString(i), colors[i]);
			int[] stPos = startingPos[i];
			int[] oldPos = players[i].getPosition();
			players[i].move(stPos[0], stPos[1]);
			b.setStartTiles(stPos[0], stPos[1]);
			changes.firePropertyChange("Player moved", oldPos, players[i].getPosition());
		}
		
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	public Board getBoard() {
		return b;
	}
	
	public int[] getDim() {
		return b.getDim();
	}
	
	public boolean removeTile(int x, int y) {
		return b.removeTile(x, y);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
	        changes.removePropertyChangeListener(l);
	}
}
