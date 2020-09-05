package com.javarush.games.flappybird;

import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.javarush.games.flappybird.FlappyBirdGame.*;


public class GameObjectManager { // отдельно управляем всеми объектами НЕ птичками
	private List<GroundPart> ground = new ArrayList<>(); // поверхность
	private List<Colons> colons = new ArrayList<>(); // Колонны
	private List<GameObject> clouds = new ArrayList<>(); // Облака
	private double deltaForGround = 1.0; // для подбора правильного смещения поверхности при переносе земли
	private int securitySpace = 6;
	public static final int X_FOR_COLONS = 254 - ShapeMatrix.COLON[0].length; //место появление последней колонны минус ширина, подобрал примерно

	Random random = new Random(); // тут придется еще один поток создать

	// создаем все нужные объекты
	public void initializeObject() {
		for (int i = 0; i <= WIDTH; i++) {
			ground.add(new GroundPart(i,HEIGHT-6));
		}
		for (int i = 0; i < 5; i++) {
			colons.add(new Colons((double)(WIDTH/2) + ShapeMatrix.COLON[0].length * 3 * i,
					random.nextInt(HEIGHT-18) + 12));
		}
		clouds.add(new GameObject(WIDTH, HEIGHT/2, ShapeMatrix.CLOUD_1));
	}
	//переносим сбежавшие объекты
	public void replacePassedObjects() {
		for (GroundPart ground: ground) {
			if (ground.x < (0 - ground.width)) {
				ground.x = WIDTH;// + ground.speed;// * ground.speed;
				//при изменнии скорости нужно играть с коифициэнтами, тут тольк магия поможет
				ground.reCreateMatrix();
			}
		}
		// теперь колнны, я сказал колонны))
		for (Colons cln: colons) {
			if (cln.x < ( - ShapeMatrix.COLON[0].length)) {
				cln.setX(X_FOR_COLONS);
				cln.setY(random.nextInt(HEIGHT - (securitySpace * 6)) + securitySpace * 4); // на самом деле на глаз подбирается)))
			}
		}
		for (GameObject cld: clouds) {
			if (cld.x < ( - ShapeMatrix.CLOUD_1[0].length)) {
				cld.x = WIDTH;
				cld.y = random.nextInt(HEIGHT - (securitySpace * 6)) + securitySpace * 4; // на самом деле на глаз подбирается)))
			}
		}
	}

	// двигаем объекты, скорость уже задана в объектах
	public void move() {
		for (Colons colon : colons) {
			colon.move();
		}
		for (GroundPart groundPart : ground) {
			groundPart.move();
		}
		for (GameObject cld: clouds) {
			cld.move();
		}
		replacePassedObjects(); // переносим ушедшее после хода
	}
	//Отрисовываем всё
	public void draw(Game game) {
		for (GameObject cld: clouds) {
			cld.draw(game);
		}
		for (Colons colon : colons) {
			colon.draw(game);
		}
		for (GroundPart groundPart : ground) {
			groundPart.draw(game);
		}
	}
	// На каждом шагу чекаем не врезалась ли птица в колонну
	// Как вариант исключить из чека незакрашенные ячейки
	public boolean checkCollision(Bird bird) {
		if(bird.y > HEIGHT - 6 - bird.height) { //сначало чекаем столкновение с землей, и не даём падать вниз
			bird.y = HEIGHT - 6 - bird.height;
			bird.dead();
			return true;
		}
		for (Colons colon : colons) { // проверяем колонны на персечение
			if(colon.checkCollision(bird))
				return true;
		}
		return false;
	}
}
