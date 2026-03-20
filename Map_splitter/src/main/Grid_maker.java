package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Grid_maker {

	int starting_x, starting_y, number_of_maps, image_width, image_height;
	Rectangle[] mini_maps;
	
	
	public void starting_stuff(int starting_x, int starting_y, int number_of_maps, int image_width, int image_height) {
		this.starting_x = starting_x;
		this.starting_y = starting_y;
		this.number_of_maps = number_of_maps;
		this.image_height = image_height;
		this.image_width = image_width;
		
		mini_maps = new Rectangle[number_of_maps];
		
	}
	public void starting_location(Graphics2D g2) {
				
		g2.setColor(Color.RED);
		g2.fillRect(starting_x-5, starting_y-5, 10, 10);
		
	}
	public void fix_lines(Graphics2D g2) {
		double scale = Math.sqrt(1.0 / number_of_maps);

		int grid_width = (int)(image_width * scale);
		int grid_height = (int)(image_height * scale);

		g2.setColor(Color.RED);

		int screen_w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screen_h = Toolkit.getDefaultToolkit().getScreenSize().height;

		int offset_x = (int)((starting_x - grid_width / 2.0) % grid_width);
		if (offset_x > 0) offset_x -= grid_width;

		int offset_y = (int)((starting_y - grid_height / 2.0) % grid_height);
		if (offset_y > 0) offset_y -= grid_height;

		for (int y = offset_y; y < screen_h; y += grid_height) {
		    for (int x = offset_x; x < screen_w; x += grid_width) {
		        g2.drawRect(x, y, grid_width, grid_height);
		    }
		}
		}	
	public File[] split(BufferedImage map, File outputFolder) {
	    if (map == null) return null;

	    int rows = (int)Math.sqrt(number_of_maps);
	    int cols = rows;

	    int cellW = image_width / cols;
	    int cellH = image_height / rows;

	    File[] outputs = new File[number_of_maps];
	    int index = 0;

	    try {
	        for (int row = 0; row < rows; row++) {
	            for (int col = 0; col < cols; col++) {

	                int x = col * cellW;
	                int y = row * cellH;

	                // Crop the mini‑map
	                BufferedImage sub = map.getSubimage(x, y, cellW, cellH);

	                // Output file
	                File out = new File(outputFolder, "mini_map_" + index + ".png");
	                ImageIO.write(sub, "png", out);

	                outputs[index] = out;
	                index++;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return outputs;
	}
	
}
