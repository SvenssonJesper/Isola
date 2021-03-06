package model;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.ArrayList;

public class Model{
	private List<Player> players;
	private Player curPlayer;
	private Turn turn;
	private Color[] colors = {Color.CYAN, Color.MAGENTA, Color.DARK_GRAY, Color.ORANGE};
	private int[][] startingPos;
	private Board b;
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
	private int width, height, numPlayers;
	
	public Model(int width, int height, int numPlayers) {
		this.newGame(width, height, numPlayers);
	}
	
	public void restartGame() {
		//creates a new game with same variables as before.
		newGame(this.width, this.height, this.numPlayers);
	}
	
	public void newGame(int width, int height, int numPlayers) {
		//Set new variables for later use
		this.width = width;
		this.height = height;
		this.numPlayers = numPlayers;
		
		this.initStartingPos();
		this.b = new Board(width, height);
		this.createPlayers();
		this.turn = Turn.MOVE_PLAYER;
	}
	
	private void initStartingPos() {
		startingPos = new int[4][2];
		
		//Set starting pos depending on number of squares
		startingPos[0] = new int[] {width % 2 == 0 ? width/2 -1 : width/2, height-1};
		startingPos[1] = new int[] {width/2, 0};
		startingPos[2] = new int[] {width-1, height/2};
		startingPos[3] = new int[] {0, height % 2 == 0 ? height/2 -1 : height/2};
	}
	
	private void createPlayers() {
		players = new ArrayList<Player>();
		for (int i=0; i<numPlayers; i++) {
			players.add(new Player("Player " + Integer.toString(i+1), colors[i]));
			int[] stPos = startingPos[i];
			players.get(i).move(stPos[0], stPos[1]);
			b.setStartTiles(stPos[0], stPos[1]);
		}
		if (curPlayer != null) {
			this.setNextPlayer();
		}else {
			curPlayer = players.get(0);
		}
	}
	
	public void setNextPlayer() {
		Player oldPlayer = curPlayer;
		curPlayer = getNextPlayer(players.indexOf(curPlayer));
		changes.firePropertyChange("NewPlayer", oldPlayer, curPlayer);
	}
	
	private Player getNextPlayer(int index) {
		int nextIndex = players.size() > index + 1 ? index + 1 : 0;
		return players.get(nextIndex).hasLost() ? getNextPlayer(nextIndex) : players.get(nextIndex);
	}
	
	public boolean move(int x, int y) {
		if (turn == Turn.MOVE_PLAYER) {
			for (int[] move : getValidMoves(curPlayer)) {
				if (move[0] == x && move[1] == y){
					int[] oldPos = curPlayer.getPosition();
					curPlayer.move(x, y);
					turn = Turn.REMOVE_TILE;
					changes.firePropertyChange("PlayerMoved", oldPos, curPlayer.getPosition());
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isWinner(Player p) {
		if (curPlayer.equals(p) && getNextPlayer(players.indexOf(p)).equals(p)) {
			changes.firePropertyChange("PlayerWon", "", p);
			return true;
		}
		return false;
	}
	
	public boolean hasLost(Player p) {
		if (getValidMoves(p).isEmpty()) {
			p.setLost(true);
			return true;
		}
		return false;
	}
	
	public List<int[]> getValidMoves(Player p){
		List<int[]> moves = new ArrayList<int[]>();
		int[] pos = p.getPosition();
		int x = pos[0];
		int y = pos[1];
		int[][] directions = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
		for (int[] d : directions){
			if(validMove(x + d[0], y + d[1], p)) {
				moves.add(new int[] {x+d[0], y+ d[1]});
			}
		}
		
		return moves;
	}
	
	private boolean containsPlayer(int x, int y) {
		for (Player p: players) {
			int[] pos = p.getPosition();
			int x0 = pos[0];
			int y0 = pos[1];
			if (x0 == x && y0 == y) {
				return true;
			}
		}
		return false;
	}
	
	private boolean validMove(int x, int y, Player p) {
		if(x >= 0 && x < b.getDim()[0] && y >= 0 && y < b.getDim()[1]) {
			if (b.getTile(x, y) == Tile.FILLED || b.getTile(x, y) == Tile.START) {
				if (!containsPlayer(x,y)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Turn getTurn() {
		return turn;
	}
	
	public List <Player> getPlayers() {
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
			if (!containsPlayer(x, y)) {
				if (b.removeTile(x, y)) {
					turn = Turn.MOVE_PLAYER;
					changes.firePropertyChange("TileRemoved", Tile.FILLED, Tile.EMPTY);
					return true;
				}
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
