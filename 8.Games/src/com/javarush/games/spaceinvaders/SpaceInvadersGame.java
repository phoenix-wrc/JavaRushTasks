package com.javarush.games.spaceinvaders;
import com.javarush.engine.cell.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import com.javarush.games.spaceinvaders.gameobjects.*;

public class SpaceInvadersGame extends Game {
    public static final int WIDTH = 64, HEIGHT = 64, COMPLEXITY = 5;
    private static final int PLAYER_BULLETS_MAX = 1;
    private EnemyFleet enemyFleet;
    private List<Star> stars;
    private List<Bullet> enemyBullets, playerBullets;
    private PlayerShip playerShip;
    private boolean isGameStopped;
    private int animationsCount, score;
    
    private void stopGameWithDelay()    {
        animationsCount ++; 
        if (animationsCount >= 10)
            stopGame(playerShip.isAlive);
    }
    
    private void stopGame (boolean isWin)   {
        isGameStopped = true;
        stopTurnTimer();
        if  (isWin) 
            showMessageDialog(Color.YELLOW, "ПОБЕДА, УРЯ", Color.GREEN, 45);
        else
            showMessageDialog(Color.YELLOW, "НЕ ПОБЕДА, НЕ УРЯ", Color.RED, 45);
    }
    
    private void check()    {
        playerShip.verifyHit(enemyBullets);
        score += enemyFleet.verifyHit(playerBullets);
        enemyFleet.deleteHiddenShips();
        if (enemyFleet.getBottomBorder() >= playerShip.y)
            playerShip.kill();
        if (enemyFleet.getShipsCount() == 0)    {
            playerShip.win();
            stopGameWithDelay();
        }
        removeDeadBullets();
        if (!playerShip.isAlive)
            stopGameWithDelay();    
    }
    
    private void removeDeadBullets()    {
        
        Iterator<Bullet> enemyBulletIterator = enemyBullets.listIterator();
        while (enemyBulletIterator.hasNext()) {
            Bullet tempBullet = enemyBulletIterator.next();
            if (tempBullet.y >= HEIGHT - 1)
                enemyBulletIterator.remove();//enemyBullets.remove(tempBullet);
            else if (tempBullet.isAlive==false)
                enemyBulletIterator.remove();
        }
        Iterator<Bullet> playerBulletIterator = playerBullets.listIterator();
        while (playerBulletIterator.hasNext()) {
            Bullet tempBullet = playerBulletIterator.next();
            if (tempBullet.y + tempBullet.height < 0)
                playerBulletIterator.remove();//enemyBullets.remove(tempBullet);
            else if (tempBullet.isAlive==false)
                playerBulletIterator.remove();
        }
        
        /*List<Bullet> deadBullets = new ArrayList<>();
        for (Bullet bullet : enemyBullets) {
            if (bullet.y >= HEIGHT - 1) deadBullets.add(bullet);
        }
        for (Bullet bullet : deadBullets) {
            enemyBullets.remove(bullet);
        }*/
        /*
        for(int i = 0 ; i < enemyBullets.size(); i ++)  {
            if (!enemyBullets.get(i).isAlive && enemyBullets.get(i).y >= (HEIGHT-1))
                enemyBullets.remove(i);
        }
        */
        //enemyBullets.removeIf(b -> !b.isAlive || b.y>=(HEIGHT-1));
        
        /*
        for (Bullet bullet : enemyBullets)   {
            if(!bullet.isAlive)
                enemyBullets.remove(enemyBullets.indexOf(bullet));
            else if (bullet.y >= (HEIGHT - 1))
                enemyBullets.remove(enemyBullets.indexOf(bullet));
        } */
    }
    
    private void moveSpaceObjects () {
        enemyFleet.move();
        for (Bullet b : enemyBullets)
            b.move();
        playerShip.move();
        for (Bullet p : playerBullets)
            p.move();
    }
    
    private void createStars () {
        stars = new ArrayList<Star>();
        for (int i = 0; i < 8; i ++)    {
            stars.add(new Star(Math.random() * (WIDTH-1), Math.random() * (HEIGHT-1)));
        }
    }
    
    private void drawField ()    {
        for (int y = 0 ; y < HEIGHT; y++)  {
            for (int x = 0; x < WIDTH; x++) {
                setCellValueEx(x, y, Color.BLACK, "");
            }
        }
        for (Star s : stars)
            s.draw(this);
    }
    
    private void drawScene ()   {
        drawField();
        playerShip.draw(this);
        for (Bullet b : enemyBullets)
            b.draw(this);
        enemyFleet.draw(this);
        for (Bullet p : playerBullets)
            p.draw(this);
    }
    
    private void createGame()   {
        createStars();
        enemyFleet = new EnemyFleet();
        setTurnTimer(40);
        enemyBullets = new ArrayList<Bullet>();
        playerShip = new PlayerShip();
        isGameStopped = false;
        animationsCount = 0;
        playerBullets = new ArrayList<Bullet>();
        score = 0;
        
        drawScene();
    }
    
    @Override
    
    public void setCellValueEx(int x, int y, Color color, String str)   {
        if  (x < 0 || x >= WIDTH || y >= HEIGHT || y < 0) return;
        else
            super.setCellValueEx(x, y, color, str);
    }
    
    public void onKeyReleased(Key key)  {
        if (key == Key.LEFT && playerShip.getDirection() == Direction.LEFT)
            playerShip.setDirection(Direction.UP);
        if (key == Key.RIGHT && playerShip.getDirection() == Direction.RIGHT)
            playerShip.setDirection(Direction.UP);
    }
    
    public void onKeyPress(Key key) {
        if (key == Key.SPACE && isGameStopped)
            createGame();
        if (key == Key.LEFT)
            playerShip.setDirection(Direction.LEFT);
        if (key == Key.RIGHT)
            playerShip.setDirection(Direction.RIGHT);
        if (key == Key.SPACE)    {
            Bullet b = playerShip.fire();
            if (b != null && playerBullets.size() < PLAYER_BULLETS_MAX)
                    playerBullets.add(b);
            
        }
    }
    
    public void initialize ()  {
        setScreenSize (WIDTH, HEIGHT);
        createGame();
    }
    
    public void onTurn (int time)    {
        moveSpaceObjects();
        check();
        Bullet b = enemyFleet.fire(this);
        if (b != null) enemyBullets.add(b);
        setScore(score);
        
        drawScene();
    }
}