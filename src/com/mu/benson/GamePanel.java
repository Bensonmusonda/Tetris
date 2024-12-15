package com.mu.benson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	//variables for x and y coordinates
	int x = 0;
	int y = 0;
	
	//int boxCounter = 1;	
	ArrayList<Box> boxes = new ArrayList<>();   //To store every box created
	Box current  = new Box(0, 0);   //The current box still moving
	
	Timer timer;   
	
	public GamePanel() {
		timer = new Timer(10, this);
		
		this.setFocusable(true);
		this.addKeyListener(this);
		
		this.setPreferredSize(new Dimension(495, 630));
		timer.start();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		setBackground(Color.BLACK);
		Graphics2D g2d = (Graphics2D) g;
		
		
		//drawing a rectangle for each box in boxes
		for(Box b: boxes) {
		   g2d.setColor(b.color);
		   g2d.fillRect(b.x, b.y, 60, 60); 
		}
		
		g2d.setColor(current.color);
		g2d.fillRect(current.x, current.y, 60, 60);
	}
	
	//count the number of boxes in a certain column
	public int iterateColumn(ArrayList<Box> b) {
		int n = 1;
		for(Box box: boxes) {
			if(box.x == current.x) {
				n++;
			}
		}
		return n;
	}
	@Override
	public void actionPerformed(ActionEvent t) {
		
		current.moveY(1);
		
		//incrementing why and repainting to move box on screen
		if(current.y > (getHeight() - 60*this.iterateColumn(boxes) )) {
			boxes.add(current);
			current = new Box(0, 0);
		}
		
		repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT) 
			if(current.x + 60 < getWidth())
			   current.moveX(60);		
		
		if(key == KeyEvent.VK_LEFT)
			if(current.x > 0)
			   current.moveX(-60);		
		
		if(key == KeyEvent.VK_DOWN) 
			current.setPosition(current.x, getHeight() - 60*this.iterateColumn(boxes));		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}

