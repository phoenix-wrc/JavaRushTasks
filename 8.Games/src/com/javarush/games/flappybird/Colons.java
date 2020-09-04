package com.javarush.games.flappybird;
import com.javarush.engine.cell.Game;

public class Colons extends GameObject {
	private Colon upColon;
	private Colon dwnColon;
	//Вообще не уверен что так норм. Но пока работает + можно менять что-то.
	public Colons(double x, double y) {
		super(x,y);
		upColon = new Colon(x, y - 18 - ShapeMatrix.COLON.length, ShapeMatrix.COLON);
		dwnColon = new Colon(x, y, ShapeMatrix.COLON);
	}

	public void move() {
		super.move();
		upColon.move();
		dwnColon.move();
	}

	public void draw(Game game) {
		upColon.draw(game);
		dwnColon.draw(game);
	}
	// по сути нам нужно просто переносить изменения во внутренние объекты и всё будет ок
	public double getX() {
		return (upColon.x + dwnColon.x)/2;
	}
	public void setX(double x) {
		this.x = x;
		upColon.x = x;
		dwnColon.x = x;
	}
	public void setY (double y) {
		upColon.y = y - 18 - ShapeMatrix.COLON.length;
		dwnColon.y = y;
	}

	//чекаем колижин
	public boolean checkCollision(Bird bird) {
		if(bird.x + bird.width > upColon.x && bird.x < upColon.x + upColon.width){
			if (bird.y < upColon.y + upColon.height ||
					bird.y + bird.height > dwnColon.y) {
				bird.dead();
				return true;
			}
		}
		return false;
	}

	private class Colon extends GameObject {
		public Colon(double x, double y, int[][] matrix) {
			super(x, y, matrix);
		}
		public void move() {
			x -= speed;
		}
	}
}
