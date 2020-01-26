
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.*;

public class GamePanel extends JPanel implements Observer{

	//Generated serialUID
	private static final long serialVersionUID = 5018457319560631120L;
	
	private int tileSize;
	private Board board;
	private Player[] players;
	private Model model;
	private int[] dim;
	
	
	public GamePanel(Model model) {
		this.model = model;
		this.model.addObserver(this);
		this.players = model.getPlayers();
		this.board = model.getBoard();
		this.board.addObserver(this);
		this.dim = this.board.getDim();
		
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		this.tileSize = Math.min(screenDim.width/dim[0]/2, screenDim.height/dim[1]/2);
		
		Dimension d = new Dimension(dim[0]*tileSize+1, dim[1]*tileSize+1);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setBackground(Color.BLACK);
	}

	public int[] getGridPosition(int x, int y) {
		int[] xy = new int[2];
		xy[0] = x / tileSize;
		xy[1] = y / tileSize;
		return xy;
	}
	
	public void updateSize(int width, int height) {
		int h = height / dim[1];
		int w = width / dim[0];
		tileSize = Math.min(w, h);
		
		this.repaint();
	}
	
	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.drawTiles(g);
		this.drawPlayers(g);
		
	}
	
	private void drawTiles(Graphics g) {
		for (int x = 0; x < dim[0]; x++) {
			for (int y = 0; y < dim[1]; y++) {
				g.setColor(Color.BLACK);
				g.drawRect(x * tileSize, y * tileSize, tileSize, tileSize);
				
				Tile tile = board.getTile(x, y);
				
				if (tile == Tile.FILLED) {
					g.setColor(Color.YELLOW);
					g.fillRect(x * tileSize + 1, y * tileSize + 1, tileSize - 2,
							tileSize - 2);
				}
				if (tile == Tile.START) {
					g.setColor(Color.GREEN);
					g.fillRect(x * tileSize + 1, y * tileSize + 1, tileSize - 2,
							tileSize - 2);
					g.setColor(Color.BLACK);
					g.drawOval(x * tileSize + 1, y * tileSize + 1, tileSize - 2,
							tileSize - 2);
				}
			}
		}
	}
	
	private void drawPlayers(Graphics g) {
		for (int i = 0; i < players.length; i++) {	
			g.setColor(players[i].getColor());
			int[] pos = players[i].getPosition();
			g.fillOval(pos[0] * tileSize + 1, pos[1] * tileSize + 1, tileSize - 2,
					tileSize - 2);
			//boarder
			g.setColor(Color.BLACK);
			g.drawOval(pos[0] * tileSize + 1, pos[1] * tileSize + 1, tileSize - 2,
					tileSize - 2);
		}
	}
	
}