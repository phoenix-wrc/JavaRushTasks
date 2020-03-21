package com.javarush.games.Painter;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;


public class PaintForGameSprite extends Game {
	public static final int WIDTH = 15, HEIGHT = 15, INTERFACE_HEIGHT = 10;
	private Cell palette[][] = new Cell[WIDTH][INTERFACE_HEIGHT];
	private Cell menu[][] = new Cell[WIDTH][1];
	private Cell cells[][] = new Cell[WIDTH][HEIGHT+INTERFACE_HEIGHT];
	private Color currentColor;

	private void createCells () {
		for (int i = 0; i < cells[0].length; i++) {
			for (int j = 0; j < cells.length; j++) {
				cells[j][i] = new Cell(j, i, 0);
			}
		}
	}

	private void createMenu()    {
		for (int i = 0; i < menu.length; i++) {
			menu[i][0] = new Cell(i,INTERFACE_HEIGHT,2);
		}
	}
	private void createPalette ()    {
		int count = 0;
		for (int i = 0; i < palette[0].length; i++) {
			for (int j = 0; j < palette.length; j++) {
				count++;
				palette[j][i] = new Cell(j, i, count);
			}
		}
	}

	private void drawScene() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				cells[i][j].draw(this);
			}
		}

		for (int i = 0; i < palette.length; i++) {
			for (int j = 0; j < palette[0].length; j++) {
				palette[i][j].draw(this);
			}
		}

		for (int i = 0; i < menu.length; i++) {
			menu[i][0].draw(this);
		}
	}

	private void createPaint ()    {
		createPalette();
		createMenu();
		createCells();

		setTurnTimer(300);
		drawScene ();
	}

	@Override

	public void onTurn(int t)    {
		drawScene();
	}

	public void onMouseLeftClick(int x, int y) {
		if(x < WIDTH && x >= 0 && y <= HEIGHT + INTERFACE_HEIGHT && y >= 0)   {
			if (y < INTERFACE_HEIGHT)
				currentColor = Color.values()[palette[x][y].getColorIndex()];
			else if( y == INTERFACE_HEIGHT)
				;//cells[x][y].setColorIndex();
			else
				cells[x][y].setColorIndex(currentColor.ordinal());
		}
	}

	public void initialize( ){
		setScreenSize(WIDTH, HEIGHT+INTERFACE_HEIGHT);
		createPaint();
	}
}
