
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.*;

public class GamePanel extends JPanel implements PropertyChangeListener{

	//Generated serialUID
	private static final long serialVersionUID = 5018457319560631120L;
	
	private int tileSize;
	private Model model;
	private int[] dim;
	private List<int[]> moves = new ArrayList<int[]>();
	
	
	public GamePanel(Model model) {
		this.model = model;
		this.model.addPropertyChangeListener(this);
		this.dim = this.model.getBoard().getDim();
		
		this.moves = model.getValidMoves(model.getcurPlayer());
		
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
	
	
	 public void propertyChange(PropertyChangeEvent evt) {
		 if (evt.getPropertyName().equals("NewPlayer")) {
			 this.moves = model.getValidMoves(model.getcurPlayer());
		 }
		 if (evt.getPropertyName().equals("PlayerMoved")) {
			 this.moves.clear();
		 }
		 
		 this.repaint();
		 
		 if (evt.getPropertyName().equals("PlayerWon")) {
			 //do something.
		 }
	}
	 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.drawTiles(g);
		this.drawMoves(g);
		this.drawPlayers(g);
	}
	
	
	private void drawMoves(Graphics g) {
		for (int[] move: moves) {
			drawTile(g, move[0], move[1], new Color(255, 255, 161));
		}
	}
	
	private void drawTiles(Graphics g) {
		for (int x = 0; x < dim[0]; x++) {
			for (int y = 0; y < dim[1]; y++) {
				Tile tile = model.getBoard().getTile(x, y);
				
				if (tile == Tile.FILLED) {
					drawTile(g, x, y, Color.YELLOW);
				}
				if (tile == Tile.START) {
					drawTile(g, x, y, Color.GREEN);
				}
			}
		}
	}
	
	private void drawTile(Graphics g, int x, int y, Color c) {
		g.setColor(c);
		g.fillRect(x * tileSize + 1, y * tileSize + 1, tileSize - 2,
				tileSize - 2);
	}
	
	private void drawPlayers(Graphics g) {
		for (Player p : model.getPlayers()) {
			g.setColor(!p.hasLost() ? p.getColor() : Color.BLACK);
			int[] pos = p.getPosition();
			g.fillOval(pos[0] * tileSize + 1, pos[1] * tileSize + 1, tileSize - 2,
					tileSize - 2);
			//boarder
			if (model.getcurPlayer().equals(p)) {
				g.setColor(Color.WHITE);
			}else {
				g.setColor(Color.BLACK);
			}
			g.drawOval(pos[0] * tileSize + 1, pos[1] * tileSize + 1, tileSize - 2,
					tileSize - 2);
			
			if (p.hasLost()) {
				g.setColor(Color.WHITE);
			}
			
			//Draw name in middle of players
			Graphics2D g2 = (Graphics2D)g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	        RenderingHints.VALUE_ANTIALIAS_ON);
	        
	        int charSize = 3;
	        g2.drawString(p.getName(), pos[0] * tileSize + (tileSize/2 - charSize*p.getName().length()), pos[1] * tileSize + tileSize/2);
		}
	}
	
}