package com.mu.benson;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Board extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	Tetromino currentTetromino = new Tetromino();
	Box[][] boxes = new Box[10][8];
	
	Board() {
		timer = new Timer(10, this);	// Initializing the timer
		this.addKeyListener(this);
		
		this.setFocusable(true);	
		this.setPreferredSize(new Dimension(495, 630));
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		setBackground(Color.BLACK);
		Graphics2D g2d = (Graphics2D) g;
		
		for(Box b: currentTetromino.boxes) {
			
			g2d.setColor(b.color);
			g2d.fillRect(b.x, b.y, 60, 60);
		}
		
		for(int row = 0; row < 10; row++) {
			
			for(int column = 0; column < 8; column++) {
				
				if(boxes[row][column] != null) {
					
					Box b = boxes[row][column];				
					g2d.setColor(b.color);
					g2d.fillRect(b.x, b.y, 60, 60);
				}
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Moving the currently falling tetromino down if there is no collision
		if(!this.checkCollision(currentTetromino, "down")) {
			
			currentTetromino.moveTetrominoY(2);
		} else {
			//Adding each box in the last tetromino to @boxes
			for(Box b: currentTetromino.boxes) {
				boxes[Math.floorDiv(b.x, 60)][Math.floorDiv(b.y, 60) - 1] = b;
			}
			
			currentTetromino = new Tetromino();
		}
		/*
		for(Box b: currentTetromino.boxes) {
			if(b.y >= 0) {
				boxes[Math.floorDiv(b.x, 60)][Math.floorDiv(b.y, 60)] = b;
			}
		}
		*/
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();

	    if (key == KeyEvent.VK_RIGHT) {
	    	if(!this.checkCollision(currentTetromino, "right"))
	    		currentTetromino.moveTetrominoX(60);
	    }

	    if (key == KeyEvent.VK_LEFT) {
	    	if(!this.checkCollision(currentTetromino, "left"))
	    		currentTetromino.moveTetrominoX(-60);
	    }

	    if (key == KeyEvent.VK_UP) {
	        this.rotate();
	    }
	    
	    if (key == KeyEvent.VK_DOWN) {
	        currentTetromino.moveTetrominoY(1);
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	void rotate() {
		// Use the center of the tetromino as the pivot point
	    int pivotX = currentTetromino.boxes[1].x;
	    int pivotY = currentTetromino.boxes[1].y;

	    for (Box b : currentTetromino.boxes) {
	        // Translate box to origin (pivot point)
	        int translatedX = b.x - pivotX;
	        int translatedY = b.y - pivotY;

	        // Rotate 90 degrees clockwise: (x, y) -> (y, -x)
	        int rotatedX = translatedY;
	        int rotatedY = -translatedX;

	        // Translate box back to original position
	        b.x = rotatedX + pivotX;
	        b.y = rotatedY + pivotY;
	    }
	    
	    int checkOffset = -60;
	    for(Box b: currentTetromino.boxes) {
	    	if(b.x == checkOffset) {
	    		currentTetromino.moveTetrominoX(+60);
	    		checkOffset = checkOffset - 60;
	    	}
	    }
	}
	
	boolean checkCollision(Tetromino tet, String direction) {
		
		switch (direction) {
		case "left":
			for(Box b: currentTetromino.boxes)
				if(b.x - 60 < 0)
					return true;
			
			for(Box b: currentTetromino.boxes)
				if(Math.floorDiv(b.y, 60) - 1 >= 0)
					if(boxes[Math.floorDiv(b.x - 60, 60)][Math.floorDiv(b.y + 4, 60) - 1] != null)				
						return true;
			break;
			
		case "right":
			for(Box b: currentTetromino.boxes)
				if(b.x + 60 > getWidth())
					return true;
			
			for(Box b: currentTetromino.boxes)
				if(Math.floorDiv(b.y, 60) - 1 >= 0)
					if(boxes[Math.floorDiv(b.x + 60, 60)][Math.floorDiv(b.y + 4, 60) - 1] != null)				
						return true;
			break;
			
		case "down":
			
			for(Box b: currentTetromino.boxes)
				if(b.y > getHeight() - 60)
					return true;
			
			for(Box b: currentTetromino.boxes)
				if(Math.floorDiv(b.y, 60) - 1 >= 0)
					if(boxes[Math.floorDiv(b.x, 60)][Math.floorDiv(b.y + 4, 60) - 1] != null)
						return true;		
		}
		
		
		return false;
	}
	
	//boolean isLineFull()
}
