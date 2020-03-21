package com.javarush.games.spaceinvaders.gameobjects;
import com.javarush.games.spaceinvaders.*;

public class EnemyShip extends Ship {
    public int score = 15;
    
    public EnemyShip(double x, double y)   {
        super(x, y);
        setStaticView(ShapeMatrix.ENEMY);
    }
    
    
    public void kill () {
        if(!isAlive) return;
        isAlive = false;
        super.setAnimatedView(false, ShapeMatrix.KILL_ENEMY_ANIMATION_FIRST,
                            ShapeMatrix.KILL_ENEMY_ANIMATION_SECOND, 
                            ShapeMatrix.KILL_ENEMY_ANIMATION_THIRD);
    }
    
    public void move (Direction direction, double speed) {
        if (direction == Direction.RIGHT)   {
            x += speed;
        }
        if (direction == Direction.LEFT)   {
            x -= speed;
        }
        if (direction == Direction.DOWN)   {
            y += 2;
        }
    }
    
    public Bullet fire ()   {
        return new Bullet(this.x + 1, this.y + height, Direction.DOWN);
    }
}