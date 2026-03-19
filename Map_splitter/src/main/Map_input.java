package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map_input extends JPanel {

	Rectangle import_button;
	public Map_input(){

		
		Main.window.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2);
		Main.window.setLocationRelativeTo(null);
		Main.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main.window.setVisible(true);
		setup();
		Main.window.add(this);
		this.setBackground(Color.cyan);
	}
	public void setup() {
		import_button = new Rectangle(Main.window.getWidth()/2 - 350/2, Main.window.getHeight()/2 - 200/2, 350, 200);
	}
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.draw(import_button);
		
	}

}
