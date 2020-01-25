
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.*;

public class GamePanel extends JPanel implements Observer{

	private final int UNIT_SIZE = 50;
	private Board board;
	
	
	public GamePanel(Board board) {
		this.board = board;
		this.board.addObserver(this);
		int[] dim = this.board.getDim();
		Dimension d = new Dimension(dim[0]*UNIT_SIZE+1, dim[1]*UNIT_SIZE+1);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setBackground(Color.WHITE);
	}

	
	public int[] getGridPosition(int x, int y) {
		int[] xy = new int[2];
		xy[0] = x / UNIT_SIZE;
		xy[1] = y / UNIT_SIZE;
		return xy;
	}
	
	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int[] dim = board.getDim();
		for (int x = 0; x < dim[0]; x++) {
			for (int y = 0; y < dim[1]; y++) {
				int startx = x * UNIT_SIZE;
				int starty = y * UNIT_SIZE;
				g.setColor(Color.BLACK);
				g.drawRect(startx, starty, UNIT_SIZE, UNIT_SIZE);
				
				Coordination coord = board.getLocation(x, y);
				
				if (coord instanceof Player) {
					g.setColor(((Player) coord).getColor());
					g.fillOval(startx + 1, starty + 1, UNIT_SIZE - 2,
							UNIT_SIZE - 2);
				} else if (coord instanceof Tile) {
					if (((Tile) coord).getState() == TileState.FILLED) {
						g.setColor(Color.BLACK);
						g.fillRect(startx + 1, starty + 1, UNIT_SIZE - 2,
								UNIT_SIZE - 2);
					}
					
				}
			}
		}
		
	}
	
}