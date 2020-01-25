package model;

import java.awt.Color;

public class Player {
	private String name;
	private Color color;
	private int x = 0;
	private int y = 0;
	
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
	}
	
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int[] getPosition() {
		int[] pos = new int[2];
		pos[0] = x;
		pos[1] = y;
		return pos;
	}
}
