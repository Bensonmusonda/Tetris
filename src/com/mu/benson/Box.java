package com.mu.benson;

import java.awt.Color;
import java.util.Random;

public class Box {
	int x;
	int y;
	Color color;
	
	//constructor for the boxes with
	//arguments x and y specify the position on creation
	Box(int x, int y){
		this.setPosition(x, y);
		this.color = this.getRandomColor();
	}
	Box(int x, int y, Color color) {
		this.setPosition(x, y);
		this.color = color;
	}
	//to set the position of the box at the passed coordinates
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	//move the box along the box horizontally by an offset of c
	public void moveX(int c) {
		x = x + c;
	}
	
	public void moveY(int c) {
		y = y + c;
	}

	private Color getRandomColor() {
        Random rand = new Random();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        return new Color(r, g, b);
    }
	
	public void rotateY() {}
	
	public void pullDown() {}
	
}
