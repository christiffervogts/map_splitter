package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Grid_maker {

	int starting_x, starting_y, offsetX, offsetY, total_Grid_Height, total_Grid_Width, number_of_maps, image_width, colloms, rows, max_width, max_height, image_height;
	int grid_box_x, grid_box_y;
	Rectangle[] mini_maps;
	
	
	public void starting_stuff(int starting_x, int starting_y, int number_of_maps, int image_width, int image_height) {
		this.starting_x = starting_x;
		this.starting_y = starting_y;
		this.number_of_maps = number_of_maps;
		this.image_height = image_height;
		this.image_width = image_width;
		
		grid_box_x = image_width/number_of_maps;
		grid_box_y = image_height/number_of_maps;
		colloms = (int)Math.ceil(Math.sqrt(number_of_maps));
		rows = (int)Math.ceil((double)number_of_maps/colloms);
		total_Grid_Width = grid_box_x * colloms;
	    total_Grid_Height = grid_box_y * rows;
		max_width = grid_box_x * colloms;
		max_height = grid_box_y * rows;
		offsetX = starting_x - total_Grid_Width / 2;
		offsetY = starting_y - total_Grid_Height / 2;
		mini_maps = new Rectangle[number_of_maps*number_of_maps];
		
	}
	public void starting_location(Graphics2D g2) {
				g2.setColor(Color.RED);
		g2.fillRect(starting_x-5, starting_y-5, 10, 10);
	}
public void fix_lines(Graphics2D g2) {
    // Calculate the number of columns and rows
    int cols = (int) Math.ceil(Math.sqrt(number_of_maps));  // Start by assuming a square layout
    int rows = (int) Math.ceil((double) number_of_maps / cols);  // Adjust the number of rows based on the number of boxes

    // Calculate the width and height of each box to fill the screen
    int boxWidth = image_width / cols;
    int boxHeight = image_height / rows;

    // Total width and height of the grid
    int totalGridWidth = cols * boxWidth;
    int totalGridHeight = rows * boxHeight;

    // Adjust the starting position to center the grid around (starting_x, starting_y)
    int offsetX = starting_x - totalGridWidth / 2;
    int offsetY = starting_y - totalGridHeight / 2;

    mini_maps = new Rectangle[number_of_maps];

    int index = 0;
    
    // Draw the grid of boxes, wrapping as needed
    for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
            if (index >= number_of_maps) break;  // Stop if we've reached the desired number of maps

            // Calculate the position of each box
            int x = offsetX + col * boxWidth;
            int y = offsetY + row * boxHeight;

            // Store the rectangle for later use
            mini_maps[index] = new Rectangle(x, y, boxWidth, boxHeight);

            // Draw the box
            g2.setColor(Color.RED);
            g2.draw(mini_maps[index]);

            index++;
        }
    }
}	
	public void draw() {
		
	}
	
}
