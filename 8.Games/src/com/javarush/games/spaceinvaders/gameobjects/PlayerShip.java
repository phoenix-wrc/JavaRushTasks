package com.javarush.games.spaceinvaders.gameobjects;
import com.javarush.games.spaceinvaders.*;
import java.util.List;
import java.util.ArrayList;

public class PlayerShip extends Ship    {
    private Direction direction = Direction.UP;
    
    public void win()   {
        setStaticView(ShapeMatrix.WIN_PLAYER);
    }
    
    public Bullet fire ()  {
        if (!isAlive) return null;
        Bullet b = new Bullet(x + 2, y - ShapeMatrix.BULLET.length, Direction.UP);
        return b;
    }
    
    public void move () {
        if (!isAlive) return;
        if(direction == Direction.LEFT)
            this.x --;
        else if (direction == Direction.RIGHT)
            this.x ++;
        if (this.x < 0)
            this.x = 0;
        if ((this.x + width) > SpaceInvadersGame.WIDTH)
            this.x = SpaceInvadersGame.WIDTH  - width;
    }
    
    public void setDirection (Direction newDirection)   {
        if (newDirection != Direction.DOWN)
            direction = newDirection;
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public void kill () {
        if (!this.isAlive) return;
        isAlive = false;
        setAnimatedView(false, ShapeMatrix.KILL_PLAYER_ANIMATION_FIRST,
                        ShapeMatrix.KILL_PLAYER_ANIMATION_SECOND,
                        ShapeMatrix.KILL_PLAYER_ANIMATION_THIRD,
                        ShapeMatrix.DEAD_PLAYER);
    }
    
    public void verifyHit (List<Bullet> bullets)  {
       if(bullets.size()!=0){
           for (Bullet bullet: bullets) {
               if(isAlive && bullet.isAlive){
                   if( isCollision(bullet)){
                       kill();
                       bullet.kill();
                   }
               }
           }
       }
    /*
         if (bullets.size() == 0) return;
         if (isAlive)   {
             for(Bullet b: bullets) {
                 if (this.isCollision(b))   {
                     kill();
                     b.kill();
                 }
             }
         }*/
    }
    public PlayerShip ()    {
        super(SpaceInvadersGame.WIDTH/2.0, 
                SpaceInvadersGame.HEIGHT - ShapeMatrix.PLAYER.length - 1.0);
        setStaticView(ShapeMatrix.PLAYER);
    }
}