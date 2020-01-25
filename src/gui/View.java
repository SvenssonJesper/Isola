package gui;

import javax.swing.*;

import model.Model;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class View {
	
	private Model model;
	private JLabel lblInfo;
	
	public View(Model model, int width, int height) {
		this.model = model;
        //Creating the Frame
        JFrame frame = new JFrame("Isola");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);

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
        GamePanel gameGridPanel = new GamePanel(model);
        gameGridPanel.addMouseListener(getMouseListener(gameGridPanel));

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, gameGridPanel);

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setVisible(true);

	}
	
	private MouseListener getMouseListener(final GamePanel grid) {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int[] xy = grid.getGridPosition(e.getX(), e.getY());
				int x = xy[0];
				int y = xy[1];
				int[] max = model.getDim();
				// Ignore clicks outside the board.
				if (x < 0 || x >= max[0] || y < 0 || y >= max[1]) {
					return;
				}
				if (model.removeTile(x, y)){
					lblInfo.setText("Continue to next move");
				}else {
					lblInfo.setText("That is not a tile.");
				}
			}
		};
	}
}




