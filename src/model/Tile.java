package model;

public class Tile implements Coordination {
	private TileState state;
	
	public Tile(TileState state) {
		this.state = state;
	}
	
	public TileState getState() {
		return state;
	}
}
