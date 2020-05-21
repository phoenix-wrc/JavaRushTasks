package com.javarush.games.spaceinvaders.gameobjects;
import com.javarush.games.spaceinvaders.*;

public class Bullet extends GameObject {
    private int dy ;
    
    public boolean isAlive = true;
    
    public void kill () {
        isAlive = false;
    }
    
    public Bullet (double x, double y, Direction direction)   {
        super(x, y);
        setMatrix(ShapeMatrix.BULLET);
        if (direction == Direction.UP)
            dy = -3;
        else
            dy = 1;
        
    }
    
    public void move () {
        this.y += dy;
    }
    
}