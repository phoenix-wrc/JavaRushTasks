package com.javarush.games.Painter;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

//import java.awt.*;

public class Cell {
	int x;
	int y;
	public boolean isSelested;
	int colorIndex;

	public void draw (Game game)    {
		//game.setCellColor(x, y, Color.values()[colorIndex]);
		game.setCellValueEx(x,y,Color.values()[colorIndex], Integer.toString(colorIndex),getColorText(10));
	}

	private Color getColorText (int scale) {
		Color color = Color.NONE;
		if (colorIndex < 148 - scale)
			color = Color.values()[colorIndex+10];
		else
			color = Color.values()[149 + scale - colorIndex];
		return color;
	}

	public int getColorIndex() {
		return colorIndex;
	}

	public void setColorIndex(int colorIndex) {
		if (colorIndex > 148 || colorIndex < 0)
			colorIndex = 0;
		this.colorIndex = colorIndex;
	}

	public Cell (int x, int y, int colorNum) {
		this.x = x;
		this.y = y;
		setColorIndex(colorNum);
		isSelested = false;
	}
}