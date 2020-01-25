package gui;

import javax.swing.*;

import model.Model;

import java.awt.*;

public class View {
	
	public View(Model model, int width, int height) {
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

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // GamePanel displays the grid
        GamePanel gameGridPanel = new GamePanel(model);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, gameGridPanel);

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setVisible(true);

	}
}




