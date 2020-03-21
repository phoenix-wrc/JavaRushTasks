package com.javarush.games.spaceinvaders.gameobjects;
import com.javarush.games.spaceinvaders.*;


public class Boss extends EnemyShip {
    private int frameCount = 0;
    public int score = 100;
    
    public Boss (double x, double y)    {
        super(x, y);
        setAnimatedView(true, ShapeMatrix.BOSS_ANIMATION_FIRST,
                        ShapeMatrix.BOSS_ANIMATION_SECOND);
    }
    
    
    @Override
    
    public void kill()  {
        if (!isAlive) return;
        isAlive = false;
        setAnimatedView(false, ShapeMatrix.KILL_BOSS_ANIMATION_FIRST, 
                        ShapeMatrix.KILL_BOSS_ANIMATION_SECOND, 
                        ShapeMatrix.KILL_BOSS_ANIMATION_THIRD);
    }
    
    public Bullet fire()    {
        if (!isAlive) return null;
        Bullet b = null;
        if(matrix == ShapeMatrix.BOSS_ANIMATION_FIRST)
            b = new Bullet(x+6, y + height, Direction.DOWN);
        else if(matrix != ShapeMatrix.BOSS_ANIMATION_FIRST)
            b = new Bullet(x, y + height, Direction.DOWN);
        return b;
    }
    
    public void nextFrame() {
        frameCount++;
        if((frameCount%10 == 0) || !this.isAlive)
            super.nextFrame();
    }
}