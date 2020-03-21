package com.javarush.games.flappybird;

import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.javarush.games.flappybird.FlappyBirdGame.HEIGHT;
import static com.javarush.games.flappybird.FlappyBirdGame.WIDTH;

public class GameObjectManager { // отдельно управляем всеми объектами НЕ птичками
	private List<GroundPart> ground = new ArrayList<>(); // поверхность
	private List<Colon> colons = new ArrayList<>(); // Колонны

	Random random = new Random();
	public void generateNewObjects(Game game)    {
		generateGround(game);
	}

	private void generateGround(Game game) {
		while (ground.size() < 101) {
			GroundPart lastPart = ground.get(ground.size() - 1);
			GroundPart ro = new GroundPart(lastPart.x + 1.5 * lastPart.speed, HEIGHT - 6);
			ground.add(ro);
		}
		//while (colons.size() < 0) {
			Colon lastColon = colons.get(colons.size()-1);
		//double y;
		//do {
		//	y = random.nextGaussian()  ;//* (HEIGHT-30);
		//} while(y < 1.5 && y > -1.5);
		//System.out.println(y);
			if (lastColon.x < ShapeMatrix.COLON[0].length * 2)
				colons.add(new Colon((double)WIDTH, random.nextInt(HEIGHT-15)));
		//}
	}
	// задаем первые объекты, землю и пару колонн
	public void initializeObject() {
		for (int i = 0; i < WIDTH; i++) {
			ground.add(new GroundPart(i,HEIGHT-6));
		}
		for (int i = 0; i < 3; i++) {
			colons.add(new Colon((double)(WIDTH/2) + ShapeMatrix.COLON[0].length * 2 * i,
					random.nextInt(HEIGHT-30)));
		}
	}
	//Удаляем ненужные объекты
	public void deletePassedObjects() {
		ground.removeIf(obj -> {
			//System.out.println(obj.x); // включать для проверки))
			return obj.x < (0 - obj.width);
		});
		colons.removeIf(obj -> {
			//System.out.println(obj.x); //Включать для проверки
			return obj.x < (0 - obj.width);
		});
	}

	// двигаем объекты, скорость уже задана в объектах,
	// потом вызываем удаление всех объектов
	public void move() {
		for (Colon colon : colons) {
			colon.move();
		}
		for (GroundPart groundPart : ground) {
			groundPart.move();
		}
		deletePassedObjects(); // удаляем излишек
		//System.out.println(ground.size());
		//System.out.println(colons.size());
	}
	//Отрисовываем всё
	public void draw(Game game) {
		for (Colon colon : colons) {
			colon.draw(game);
		}
		for (GroundPart groundPart : ground) {
			groundPart.draw(game);
		}
	}
	// На каждом шагу чекаем не врезалась ли птица в колонну
	// Как вариант исключить из чека незакрашенные ячейки
	public boolean checkCollision(Bird bird) {
		boolean answer = false;
		for (Colon colon : colons) {
			if(bird.x + bird.width > colon.x && bird.x < colon.x + colon.width){
				if (bird.y + bird.height > colon.y) {
					bird.dead();
					answer = true;
					break;
				}
			}
		}
		if(bird.y > HEIGHT-6- bird.height) {
			bird.y = HEIGHT - 6 - bird.height;
			bird.dead();
			answer = true;
		}
		return answer;
	}
}
