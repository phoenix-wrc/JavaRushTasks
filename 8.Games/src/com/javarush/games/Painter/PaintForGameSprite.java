package com.javarush.games.Painter;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import java.io.*;
import java.util.stream.IntStream;


public class PaintForGameSprite extends Game {
	public static final int WIDTH = 25, INTERFACE_HEIGHT = Color.values().length/WIDTH + 1,
			HEIGHT = WIDTH - INTERFACE_HEIGHT;
	private Cell[][] palette = new Cell[WIDTH][INTERFACE_HEIGHT];
	private Cell[][] menu = new Cell[WIDTH][1];
	private Cell[][] cells = new Cell[WIDTH][HEIGHT+INTERFACE_HEIGHT];
	private Color currentColor = null;
	private String consoleLine = "В консоль";
	private String fileLine = "В файл";

	private void createCells () {
		for (int i = 0; i < cells[0].length; i++) {
			for (int j = 0; j < cells.length; j++) {
				cells[j][i] = new Cell(j, i, 0);
			}
		}
	}

	private void createMenu()    {
		for (int i = 0; i < menu.length; i++) {
			menu[i][0] = new Cell(i,INTERFACE_HEIGHT,107);
			if (i < WIDTH / 2) {
				if(i < consoleLine.length())
					menu[i][0].setNameMenu(consoleLine.substring(i, i+1));
				else
					menu[i][0].setNameMenu(" ");
			} else {
				if ((i - (WIDTH / 2)) < fileLine.length())
					menu[i][0].setNameMenu(fileLine.substring(i - (WIDTH / 2), i - (WIDTH / 2) + 1));
				else
					menu[i][0].setNameMenu(" ");
			}
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
		for (Cell[] cell : cells) {
			IntStream.range(0, cells[0].length).forEach(j -> cell[j].draw(this));
		}

		for (Cell[] value : palette) for (int j = palette[0].length - 1; j >= 0; j--) value[j].draw(this);

		for (Cell[] value : menu) {
			value[0].draw(this);
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
		if(x < WIDTH && x >= 0 && y <= HEIGHT + INTERFACE_HEIGHT && y >= 0) {
			if (y < INTERFACE_HEIGHT) {
				currentColor = Color.values()[palette[x][y].getColorIndex()];
			} else if( y == INTERFACE_HEIGHT) {
				if(x < WIDTH/2)
					printMatrixInConsole();
				else
					printMatrixInFile();
			} else {
				cells[x][y].setColorIndex(currentColor.ordinal());
			}
		}
	}

	private void printMatrixInConsole() {
		System.out.println('{');
		for (int i = 0; i < cells[0].length; i++) {
			System.out.print("{\t");
			for (Cell[] cell : cells) {
				if(cell[i].getColorIndex() < 100)
					System.out.print(cell[i].getColorIndex() + ",\t");
				else
					System.out.print(cell[i].getColorIndex() + ",");
			}
			System.out.println("}, ");
		}
		System.out.println("}");
	}
	private void printMatrixInFile() {
		System.out.println("Начинаем сохранение");
		try (FileWriter fw = new FileWriter("matrix.txt")) {
			fw.write("{\n");
			for (int i = 0; i < cells[0].length; i++) {
				fw.write("{\t");
				for (Cell[] cell : cells) {
					if(cell[i].getColorIndex() < 100)
						fw.write(cell[i].getColorIndex() + ",\t");
					else
						fw.write(cell[i].getColorIndex() + ",");
				}
				fw.write("}, \n");
			}
			fw.write("}");
		} catch(IOException ехс) {
			System.out.println("Ошибкa вывода: " + ехс);
		}
		System.out.println("Закочили");
	}

	public void initialize( ){
		setScreenSize(WIDTH, HEIGHT+INTERFACE_HEIGHT);
		createPaint();
	}
}
