package main;

import gui.View;
import model.Model;

public class main {
	public static void main(String[] args) {
		Model state = new Model(10, 10, 2);
		View gui = new View(state, 550, 650);
	}
}
