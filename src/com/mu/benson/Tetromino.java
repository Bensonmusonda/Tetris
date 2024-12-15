package com.mu.benson;

import java.awt.Color;
import java.util.Random;


enum TetrominoType {
	
	T1, T2, T3, T4, T5, T6, T7;
}

public class Tetromino {
	
	Box b1;
	Box b2;
	Box b3;
	Box b4;
	Box[] boxes = new Box[4];
	
	TetrominoType[] tetTypes = TetrominoType.values();
	TetrominoType type;
	Color tetColor;
	
	Tetromino()  {
		
		this.type = setType();
		this.setBoxes(type);
		boxes = new Box[]{b1, b2, b3, b4};
		
	}
	
	TetrominoType setType() {
		
		Random rand = new Random();
		int tetType = rand.nextInt(tetTypes.length);
		
		return tetTypes[tetType];
	}
	
	void setBoxes(TetrominoType type) {
		
		switch(type) {
		
		case T1:
			this.tetColor = Color.RED;
			
			b1 = new Box(120, 0, tetColor);
			b2 = new Box(180, 0, tetColor);
			b3 = new Box(240, 0, tetColor);
			b4 = new Box(300, 0, tetColor);
			
			break;
			
		case T2:
			this.tetColor = Color.DARK_GRAY;
			
			b1 = new Box(180, -60, tetColor);
			b2 = new Box(240, -60, tetColor);
			b3 = new Box(180, 0, tetColor);
			b4 = new Box(240, 0, tetColor);
			
			break;
			
		case T3:
			this.tetColor = Color.YELLOW;
			
			b1 = new Box(240, -60, tetColor);
			b2 = new Box(180, 0, tetColor);
			b3 = new Box(240, 0, tetColor);
			b4 = new Box(300, 0, tetColor);
			
			break;
			
		case T4:
			this.tetColor = Color.ORANGE;
			
			b1 = new Box(180, -60, tetColor);
			b2 = new Box(240, -60, tetColor);
			b3 = new Box(240, 0, tetColor);
			b4 = new Box(300, 0, tetColor);
			
			break;
			
		case T5:
			this.tetColor = Color.BLUE;
			
			b1 = new Box(240, -60, tetColor);
			b2 = new Box(300, -60, tetColor);
			b3 = new Box(180, 0, tetColor);
			b4 = new Box(240, 0, tetColor);
			
			break;
			
		case T6:
			this.tetColor = Color.GREEN;
			
			b1 = new Box(180, -120, tetColor);
			b2 = new Box(180, -60, tetColor);
			b3 = new Box(180, 0, tetColor);
			b4 = new Box(240, 0, tetColor);

			break;
			
		case T7:
			this.tetColor = Color.PINK;
			
			b1 = new Box(240, -120, tetColor);
			b2 = new Box(240, -60, tetColor);
			b3 = new Box(240, 0, tetColor);
			b4 = new Box(180, 0, tetColor);
			
			break;
		}
	}
	

	void moveTetrominoX(int c) {
		
		b1.moveX(c);
		b2.moveX(c);
		b3.moveX(c);
		b4.moveX(c);
	}
	
	void moveTetrominoY(int c) {
		
		b1.moveY(c);
		b2.moveY(c);
		b3.moveY(c);
		b4.moveY(c);
	}
	
	
}
