
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.*;

public class GamePanel extends JPanel implements Observer{

	//Generated serialUID
	private static final long serialVersionUID = 5018457319560631120L;
	
	private final int UNIT_SIZE = 50;
	private Board board;
	private Player[] players;
	private Model model;
	
	
	public GamePanel(Model model) {
		this.model = model;
		this.model.addObserver(this);
		this.players = model.getPlayers();
		this.board = model.getBoard();
		this.board.addObserver(this);
		int[] dim = this.board.getDim();
		Dimension d = new Dimension(dim[0]*UNIT_SIZE+1, dim[1]*UNIT_SIZE+1);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setBackground(Color.BLACK);
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
				g.setColor(Color.BLACK);
				g.drawRect(x * UNIT_SIZE, y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
				
				Tile tile = board.getTile(x, y);
				
				if (tile == Tile.FILLED) {
					g.setColor(Color.YELLOW);
					g.fillRect(x * UNIT_SIZE + 1, y * UNIT_SIZE + 1, UNIT_SIZE - 2,
							UNIT_SIZE - 2);
				}
				if (tile == Tile.START) {
					g.setColor(Color.GREEN);
					g.fillRect(x * UNIT_SIZE + 1, y * UNIT_SIZE + 1, UNIT_SIZE - 2,
							UNIT_SIZE - 2);
					g.setColor(Color.BLACK);
					g.drawOval(x * UNIT_SIZE + 1, y * UNIT_SIZE + 1, UNIT_SIZE - 2,
							UNIT_SIZE - 2);
				}
			}
		}
		
		for (int i = 0; i < players.length; i++) {
			
			g.setColor(players[i].getColor());
			int[] pos = players[i].getPosition();
			g.fillOval(pos[0] * UNIT_SIZE + 1, pos[1] * UNIT_SIZE + 1, UNIT_SIZE - 2,
					UNIT_SIZE - 2);
			//boarder
			g.setColor(Color.BLACK);
			g.drawOval(pos[0] * UNIT_SIZE + 1, pos[1] * UNIT_SIZE + 1, UNIT_SIZE - 2,
					UNIT_SIZE - 2);
		}
		
	}
	
}