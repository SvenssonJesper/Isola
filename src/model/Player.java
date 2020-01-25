package model;

import java.awt.Color;

public class Player implements Coordination {
	String name;
	Color color;
	int x;
	int y;
	
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
	}
	
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Color getColor() {
		return color;
	}
}
