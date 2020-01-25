package model;

import java.awt.Color;

public class Model {
	private Player[] players;
	private int numPlayers;
	private Coordination[][] game;
	private Color[] colors = {Color.black, Color.red};
	private Board b;
	
	public Model(int width, int height, int numPlayers) {
		this.b = new Board(width, height);
	}
	
	private void createPlayers() {
		this.players = new Player[this.numPlayers];
		for (int i=0; i<this.numPlayers; i++) {
			this.players[i] = new Player("Player " + Integer.toString(i), colors[i]);
		}
	}
	
	private void placePlayer(int i, int j, Player player) {
		game[i][j] = player;
	}
	
	public Board getBoard() {
		return this.b;
	}
}
