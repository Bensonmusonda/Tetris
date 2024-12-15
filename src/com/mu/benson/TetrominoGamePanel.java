package com.mu.benson;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

public class TetrominoGamePanel extends JPanel implements ActionListener, KeyListener {
	
	Timer timer;
	Tetromino currentTetromino = new Tetromino();
	ArrayList<Box> boxes = new ArrayList<>();	// Array that will store all the boxes from all the tetrominoes
	
	public TetrominoGamePanel() {
		
		timer = new Timer(20, this);	// Initializing the timer
		this.addKeyListener(this);
		
		this.setFocusable(true);	
		this.setPreferredSize(new Dimension(495, 630));
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		setBackground(Color.black);
		Graphics2D g2d = (Graphics2D) g;
		
		// Drawing the Tetromino that is currently falling
		for(Box b: currentTetromino.boxes) {
			g2d.setColor(b.color);
			g2d.fillRect(b.x, b.y, 60, 60);
		}
		
		// Drawing all the boxes stored in the boxes array
		for(Box b: boxes) {
			g2d.setColor(b.color);
			g2d.fillRect(b.x, b.y, 60, 60);
		}
	}
	
	Box[] getLowerBoxes() {
		
		Box[] lower = new Box[4];
		int highestY = currentTetromino.b1.y;
		
		for(Box b: currentTetromino.boxes) {
			if(b.y > highestY) {
				highestY = b.y;
			}
		}
		
		for(int i = 0;i < 4;i++) {
			if(currentTetromino.boxes[i].y == highestY) {
				lower[i] = currentTetromino.boxes[i];
			}
		}
		
		return lower;
	}
	
	Box[] getLeftMostBoxes() {
		
		Box[] leftMost = new Box[4];
		int lowestX = currentTetromino.b1.x;
		
		for(Box b: currentTetromino.boxes) {
			if(b.x < lowestX) {
				lowestX = b.x;
			}
		}
		
		for(int i = 0;i < 4;i++) {
			if(currentTetromino.boxes[i].x == lowestX) {
				leftMost[i] = currentTetromino.boxes[i];
			}
		}
		
		return leftMost;
	}
	
	Box[] getRightMostBoxes() {
		
		Box[] rightMost = new Box[4];
		int highestX = currentTetromino.b1.x;
		
		for(Box b: currentTetromino.boxes) {
			if(b.x > highestX) {
				highestX = b.x;
			}
		}
		
		for(int i = 0;i < 4;i++) {
			if(currentTetromino.boxes[i].x == highestX) {
				rightMost[i] = currentTetromino.boxes[i];
			}
		}
		
		return rightMost;
	}
	
	void rotateTetromino() {
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
	}

	
	
	// To check if there are no boxes on the left of the current Tetromino
	boolean isMovableLeft() {
		for(Box a: this.getLeftMostBoxes()) {	//	checking through all the boxes on the leftmost in the tetromino
			if(a != null) {
				for(Box b: boxes)
					if((b.y - a.y) > 0 && (b.y - a.y) < 60 && (a.x - 60) == (b.x))
						return false;	// Return force to avoid collision with a box on the left
			}
		}
		
		return true;
	}
	
	// To check if there are no boxes on the right of the current Tetromino
	boolean isMovableRight() {
		
		for(Box a: this.getRightMostBoxes()) {
			if(a != null) {
				for(Box b: boxes)
					if((b.y - a.y) > 0 && (b.y - a.y) < 60 && (a.x + 60) == (b.x))
						return false;
			}
		}
		
		return true;
	}
	
	//  To check if there is a box just below the current Tetromino
	boolean isMovableDown() {
		
		for(Box a: this.getLowerBoxes()) {
			if(a != null) {
				for(Box b: boxes)
					if((a.x == b.x) && (a.y + 60) == (b.y))	// Checking if there is no box below the tetromino
						return false;
			}
		}
		
		return true;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		for(Box b: currentTetromino.boxes) {
			boxes.add(b);
		}
		
		if (this.isMovableDown() && currentTetromino.b4.y < getHeight() - 60) {
			
			currentTetromino.moveTetrominoY(2);
		} else {
			
			currentTetromino = new Tetromino();
		}
		
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	    int key = e.getKeyCode();

	    if (key == KeyEvent.VK_RIGHT) {
	        if (this.isMovableRight() && currentTetromino.b4.x + 60 < getWidth())
	            currentTetromino.moveTetrominoX(60);
	    }

	    if (key == KeyEvent.VK_LEFT) {
	        if (this.isMovableLeft() && currentTetromino.b1.x > 0)
	            currentTetromino.moveTetrominoX(-60);
	    }

	    if (key == KeyEvent.VK_UP) {
	        this.rotateTetromino();
	    }
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
