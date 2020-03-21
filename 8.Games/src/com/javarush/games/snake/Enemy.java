package com.javarush.games.snake;
import com.javarush.engine.cell.*;
import java.util.Random;

public class Enemy extends GameObject {
	private static Random random = new Random();
	private  String enemySign;
	public boolean isAlive;
	public int count;
	private Color color;

	public Enemy (int x, int y)    {
		super (x,y);
		count = 15;
		isAlive = true;
		enemySign = getEnemySign();
		color = getColor();
	}

	private Color getColor() {
		if (enemySign.equals("\uD83C\uDF21")) // вроде термометр
			return Color.RED;
		else if (enemySign.equals("\u2622")) // радиация
			return Color.YELLOWGREEN;
		else if (enemySign.equals("\u262D")) // серп и молот
			return Color.DARKRED;
		else if (enemySign.equals("\u26A1")) // молния
			return Color.YELLOW;
		else if (enemySign.equals("\u2623")) // Биоопасность
			return Color.DARKBLUE;
		else if (enemySign.equals("\uD83D\uDD25")) // Вроде огонь
			return Color.LIGHTYELLOW;
		else if (enemySign.equals("\u2620")) // череп с костями
			return Color.GHOSTWHITE;
		else if (enemySign.equals("\u2668")) //что похожее на джаву
			return Color.BROWN;
		else
			return Color.WHITE;
	}

	private String getEnemySign () {
		String s = "\uD83C\uDF21"; // вроде термометр
		int i = random.nextInt(8);
		switch (i){
			case(0): s = "\u2622"; // радиация
				break;
			case(1): s = "\u262D"; // серп и молот
				break;
			case(2): s = "\u26A1"; // молния
				break;
			case(3): s = "\u2623"; // Биоопасность
				break;
			case(4): s = "\uD83D\uDD25"; // Вроде огонь
				break;
			case(5): s = "\u2620"; // череп с костями
				break;
			case(6): s = "\u2668"; //что похожее на джаву
				break;
		}
		return s;
	}


	public void draw(Game game){
		if  (count == 1)
			game.setCellValueEx(x,y,Color.LIGHTCORAL,enemySign,color,75);
		else if(count == 2)
			game.setCellValueEx(x,y,Color.CORAL,enemySign,color,75);
		else if(count == 3)
			game.setCellValueEx(x,y,Color.RED,enemySign,color,75);
		else if(count == 4)
			game.setCellValueEx(x,y,Color.PINK,enemySign,color,75);
		else if(count%4 == 0)
			game.setCellValueEx(x,y,Color.NONE,enemySign,color,75);
			//game.setCellValueEx(x,y,Color.WHITESMOKE,enemySign,color,75);
		else if(count%4 == 1)
			game.setCellValueEx(x,y,Color.NONE,enemySign,color,75);
			//game.setCellValueEx(x,y,Color.DARKSLATEGREY,enemySign,color,75);
		else if(count%4 == 2)
			game.setCellValueEx(x,y,Color.DARKSLATEGREY,enemySign,color,75);
			//game.setCellValueEx(x,y,Color.WHITESMOKE,enemySign,color,75);
		else if(count%4 == 3)
			game.setCellValueEx(x,y,Color.DARKSLATEGREY,enemySign,color,75);
	}

}
