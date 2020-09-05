package com.javarush.games.Painter;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

public class Cell {
	int x;
	int y;
	public boolean isSelected;
	int colorIndex;
	private String nameMenu = "";

	public void draw (Game game)    {
		if(nameMenu.isEmpty())
			game.setCellValueEx(x, y, Color.values()[colorIndex],
					Integer.toString(colorIndex), getColorText(10));
		else
			game.setCellValueEx(x, y, Color.values()[colorIndex],
					nameMenu, getColorText(10));

	}

	private Color getColorText (int scale) {
		Color color;
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
		isSelected = false;
	}

	public void setNameMenu(String name) {
		nameMenu = name;
	}
}