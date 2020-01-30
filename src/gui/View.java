package gui;

import javax.swing.*;

import model.Model;
import model.Turn;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class View {
	
	private Model model;
	private JLabel lblInfo;
	private GamePanel gameGridPanel;
	private JFrame frame;
	
	public View(Model model) {
		this.model = model;
        //Creating the Frame
        frame = new JFrame("Isola");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set icon
        ImageIcon icon = new ImageIcon("res/images/construction.png");
        frame.setIconImage(icon.getImage());

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Test");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Hej");
        JMenuItem m22 = new JMenuItem("Hej1");
        m1.add(m11);
        m1.add(m22);

        //Creating the panel at bottom for message
        JPanel panel = new JPanel();
        lblInfo = new JLabel("Welcome to Isola");
        panel.add(lblInfo);   

        // GamePanel displays the grid
        gameGridPanel = new GamePanel(model);
        gameGridPanel.addMouseListener(getMouseListener(gameGridPanel));
        gameGridPanel.addComponentListener(new ResizeListener());
        
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, gameGridPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.pack();
        
        //Spawn the frame in middle of screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);

	}
	
	private MouseListener getMouseListener(final GamePanel grid) {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//this should maybe be in a separate controller class.
				int[] xy = grid.getGridPosition(e.getX(), e.getY());
				int x = xy[0];
				int y = xy[1];
				int[] max = model.getDim();
				// Ignore clicks outside the board.
				if (x < 0 || x >= max[0] || y < 0 || y >= max[1]) {
					return;
				}
				//Stops if someone win the game
				if (model.isWinner(model.getcurPlayer())) {
					return;
				}
				if (model.getTurn() == Turn.MOVE_PLAYER) {
					if(model.move(x, y)) {
						lblInfo.setText("Remove one tile");
					}else {
						lblInfo.setText("You can only move to active tiles");
					}
				}else { 
					if (model.removeTile(x, y)) {
						lblInfo.setText(model.getcurPlayer().getName() + "'s turn.");
						
						model.setNextPlayer();
						while(model.hasLost(model.getcurPlayer())) {
							lblInfo.setText(model.getcurPlayer().getName() + " has lost :(, this is so sad");
							model.setNextPlayer();
						}
						
						if (model.isWinner(model.getcurPlayer())) {
							System.out.printf("%s is the winner!!", model.getcurPlayer().getName());
						}
						
					}else {
						lblInfo.setText("That is not a valid tile you donkey.");
					}
				}
			}
		};
	}
	
	class ResizeListener extends ComponentAdapter {
        public void componentResized(ComponentEvent e) {
            gameGridPanel.updateSize(e.getComponent().getWidth(), e.getComponent().getHeight());
        }
}
}




