package main;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map_input extends JPanel implements MouseListener, KeyListener {

	Rectangle import_button;
	BufferedImage map;
	String fileName;
	Grid_maker gm = new Grid_maker();
	
	boolean step_1, step_2, step_3 = false;
	
	public Map_input(){

		Main.window.add(this);
		Main.window.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2);
		Main.window.setLocationRelativeTo(null);
		Main.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main.window.setVisible(true);
		setup();
		this.addMouseListener(this);
		Main.window.addKeyListener(this);
		this.setBackground( new Color(10, 50, 100));
		step_1 = true;
	}
	public void setup() {
		import_button = new Rectangle(Main.window.getWidth()/2 - 350/2, Main.window.getHeight()/2 - 200/2, 350, 200);
	}
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		
		if(map != null) {
			Main.window.setExtendedState(JFrame.MAXIMIZED_BOTH);
			g2.drawImage(map, 0, 0, Main.window.getWidth(), Main.window.getHeight(), null);
			if(step_2) {
				gm.starting_location(g2);
				gm.fix_lines(g2);
			}
		}
		else {			
			g2.drawString("IMPORT MAP", import_button.x+import_button.width/2, import_button.y+import_button.height/2);
			g2.draw(import_button);
		}
		repaint();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(import_button.contains(e.getPoint()) && map == null) {
			System.out.println();
			openFileDialog();
		}
		if(map != null && !step_1) {
			gm.starting_stuff(e.getPoint().x, e.getPoint().y, 70, Main.window.getWidth(), Main.window.getHeight());
			step_2 = true;
			
			repaint();
		}
		
	}
    public void openFileDialog() {
        try {
            FileDialog dialog = new FileDialog(Main.window, "Open a File", FileDialog.LOAD);
            dialog.setFile("*.png");
            dialog.setVisible(true);

            String directory = dialog.getDirectory();
            fileName  = dialog.getFile();

            if (fileName != null) {
                File file = new File(directory, fileName);
                System.out.println("LOADED: " + file.getAbsolutePath());

                if (!file.exists()) {
                	System.out.println("Directory: " + directory);
                	System.out.println("FileName: " + fileName);
                    System.out.println("File does NOT exist!");
                    return;
                }

                map = ImageIO.read(file);

                if (map == null) {
                    System.out.println("ImageIO.read returned null (unsupported format?)");
                    return;
                }

                repaint();
                step_1 = false;                
            }
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
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_ENTER) {
            FileDialog dialog = new FileDialog(Main.window, "Save a folder", FileDialog.SAVE);

            dialog.setVisible(true);
            String Folder = dialog.getFile();
            String path = dialog.getDirectory();

            File newFolder = new File(path + "Maps_split_"+ fileName);
            boolean created = newFolder.mkdir();
            
            if(created) {
            	gm.split(map, newFolder);
            }

		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	

}
