// Tetris 1.0
// Benson Musonda
// 31/05/2024
package com.mu.benson;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	
	GameWindow(String title) {
		super(title);
		
		Board board = new Board();
		SidePanel sidePanel = new SidePanel();
		
		this.add(board);
		//this.add(sidePanel);
		this.pack();
		this.setSize(495, 630);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new GameWindow("Tetris");
	}
}
