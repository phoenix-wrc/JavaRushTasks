package com.javarush.games.spaceinvaders.gameobjects;
import java.util.List;
import java.util.ArrayList;
import com.javarush.games.spaceinvaders.*;
import com.javarush.engine.cell.Game;

public class EnemyFleet {
    private static final int ROWS_COUNT = 3, COLUMNS_COUNT = 10, STEP = ShapeMatrix.ENEMY.length + 1;
    private List<EnemyShip> ships;
    private Direction direction = Direction.RIGHT;
    
    public int verifyHit(List<Bullet> bullets)  {
        if (bullets.size() == 0) return 0;
        int count = 0;
        for (Bullet b : bullets)    {
            for (EnemyShip s : ships)    {
                if(b.isCollision(s) && b.isAlive && s.isAlive)  {
                    b.kill();
                    s.kill();
                    count += s.score;
                }
            }
        }
        return count;
    }
    
    public EnemyFleet   ()  {
        createShips();
    }
    
    public double getBottomBorder ()    {
        double y = 0;
        for (Ship s : ships)    {
            if((s.y + s.height) > y)  
                y = s.y + s.height;
        }
        return y;
    }
    
    public int getShipsCount()  {
        return ships.size();
    }
    
    public void deleteHiddenShips() {
        for (int i = 0; i < ships.size(); i++)    {
            if(ships.get(i).isVisible() == false)
                ships.remove(i);
        }
    }
    /*
    public void verifyHit(List<Bullet> bullets) {
        for (Bullet b : bullets)    {
            for (Ship s : ships)    {
                if(b.isCollision(s) && b.isAlive && s.isAlive)  {
                    b.kill();
                    s.kill();
                }
            }
        }
    }
    */
    public Bullet fire (Game game)  {
        if (ships.size() == 0) return null;
        if(game.getRandomNumber (100/SpaceInvadersGame.COMPLEXITY) > 0)
            return null;
        else
            return ships.get(game.getRandomNumber(ships.size())).fire();
        
    }
    
    private double getSpeed ()  {
        double answer = 2.0;
        if (2.0 > (3.0/ships.size()))
            answer = 3.0/ships.size();
        return answer;
    }
    
    public void move () {
        if (ships.size() == 0) return;
        if ((direction == Direction.LEFT) && (getLeftBorder() < 0))    {
            direction = Direction.RIGHT;
            for(EnemyShip ship: ships)  
                ship.move(Direction.DOWN, getSpeed());
        }
        if ((direction == Direction.RIGHT) && (getRightBorder() > SpaceInvadersGame.WIDTH))  {
            direction = Direction.LEFT;
            for(EnemyShip ship: ships)  
                ship.move(Direction.DOWN, getSpeed());
        }
        for(EnemyShip ship: ships)  
            ship.move(direction, getSpeed());
    } 
    
    private double getLeftBorder() {
        double minX = Double.MAX_VALUE;
        for(EnemyShip ship: ships)  {
            if (ship.x < minX)
                minX = ship.x;
        }
        return minX;
    }
    
    private double getRightBorder() {
        double maxX = 0;
        for(EnemyShip ship: ships)  {
            if ((ship.x + ship.width) > maxX) 
                maxX = ship.x + ship.width;
        }
        return maxX;
    }
    
    public void draw (Game game) {
        for(EnemyShip ship: ships)
            ship.draw(game);
    }
    
    private void createShips()  {
        ships = new ArrayList<EnemyShip>();
        for (int i = 0; i < COLUMNS_COUNT; i++)    {
            for (int j = 0; j < ROWS_COUNT; j++)  {
                ships.add(new EnemyShip((double)i * STEP, (double) j * STEP+12));
            }
        }
        ships.add(new Boss(STEP * COLUMNS_COUNT/2 - ShapeMatrix.BOSS_ANIMATION_FIRST.length/2 -1 , 5));
    }
}