package main;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map_input extends JPanel implements MouseListener {

	Rectangle import_button;
	public Map_input(){

		Main.window.add(this);
		
		Main.window.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2);
		Main.window.setLocationRelativeTo(null);
		Main.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main.window.setVisible(true);
		setup();
		this.addMouseListener(this);
		this.setBackground( new Color(10, 50, 100));
	}
	public void setup() {
		import_button = new Rectangle(Main.window.getWidth()/2 - 350/2, Main.window.getHeight()/2 - 200/2, 350, 200);
	}
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.drawString("IMPORT MAP", import_button.x+import_button.width/2, import_button.y+import_button.height/2);
		
		g2.draw(import_button);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(import_button.contains(e.getPoint())) {
			System.out.println();
			openFileDialog();
		}
		
	}
    public void openFileDialog() {
        try {
            FileDialog dialog = new FileDialog((Frame) null, "Open a File", FileDialog.LOAD);
            dialog.setFile("*.png");
            dialog.setVisible(true);

            String directory = dialog.getDirectory();
            String fileName  = dialog.getFile();

//            if (fileName != null) {
//                if (!fileName.toLowerCase().endsWith(".stl")) fileName += ".stl";
//                File file = new File(directory, fileName);
//                System.out.println("LOADED: " + file.getAbsolutePath());
//                //ADD where it goes here
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	

}
