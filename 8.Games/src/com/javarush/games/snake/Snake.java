package com.javarush.games.snake;
import com.javarush.engine.cell.*;
import java.util.*;

public class Snake {
    private ArrayList<GameObject> snakeParts = new ArrayList<>();
    private static final String HEAD_SIGN = "\uD83E\uDD16"; // робоголова
	private static final String BODY_SIGN = "\u25A9"; //плетеный квадрат
    //private static final String HEAD_SIGN = "\u1F432"; /
    //private static final String BODY_SIGN = "\u25EF"; // круг
    //private static final String BODY_SIGN = "\u2609"; // солнце
    //private static final String BODY_SIGN = "\u25A2"; //скругленный квадрат

    public boolean isAlive = true;
    private Direction direction = Direction.DOWN;
    
    Snake(int x, int y) {
        snakeParts.add(0,new GameObject(x,y));
        snakeParts.add(new GameObject(x+1,y));
        snakeParts.add(new GameObject(x+1,y-1));
    }
    
    public void draw(Game game, int lives, int score, boolean isWinLvl) {
        for(int i = 0, l = lives; i < snakeParts.size(); i++, l--){
            String scr = Integer.toString(score);
            GameObject snk = snakeParts.get(i);
            if(isAlive) {
                if (!isWinLvl)  {
                    if(l > 0) {
                        if(i == 0)
                            game.setCellValueEx(snk.x, snk.y,Color.ORANGERED,HEAD_SIGN,Color.BLACK,75);
                        else
                            game.setCellValueEx(snk.x, snk.y,Color.ORANGERED,BODY_SIGN,Color.BLACK,75);
                    }   else {
                        if (i == 0)
                            game.setCellValueEx(snk.x, snk.y, Color.ORANGE, HEAD_SIGN, Color.BLACK, 75);
                        else
                            game.setCellValueEx(snk.x, snk.y, Color.ORANGE, BODY_SIGN, Color.BLACK, 75);
                    }
                } else  {
                    if(l > 0) {
                        if(i == 0)
                            game.setCellValueEx(snk.x, snk.y,Color.ORANGERED,HEAD_SIGN,Color.BLACK,75);
                        else
                            game.setCellValueEx(snk.x, snk.y,Color.ORANGERED,getChar(scr,i),Color.BLACK,75);
                    }   else {
                        if (i == 0)
                            game.setCellValueEx(snk.x, snk.y, Color.ORANGE, HEAD_SIGN, Color.BLACK, 75);
                        else
                            game.setCellValueEx(snk.x, snk.y, Color.ORANGE, getChar(scr,i), Color.BLACK, 75);
                    }

                }
            } else  {
                if(i == 0)
            game.setCellValueEx(snk.x, snk.y,Color.BLACK,HEAD_SIGN,Color.RED,70);
                else
            game.setCellValueEx(snk.x, snk.y,Color.BLACK,getChar(scr,i),Color.RED,70);
            }
        }
        System.out.println(snakeParts.size());
    }

    private String getChar(String s, int i)  {
        String c = BODY_SIGN;
        if(i <= s.length())
            c = s.substring(i - 1,i);
        return c;
    }
    
    public void setDirection(Direction direction){
        GameObject snk1 = snakeParts.get(0);
        GameObject snk2 = snakeParts.get(1);
        if (this.direction==Direction.LEFT && snk1.x==snk2.x)
        ;
        else if (this.direction==Direction.RIGHT && snk1.x==snk2.x)
        ;
        else if (this.direction==Direction.UP && snk1.y==snk2.y)
        ;
        else if (this.direction==Direction.DOWN && snk1.y==snk2.y)
        ;
        else if (direction==Direction.RIGHT && this.direction!=Direction.LEFT)
        this.direction = direction;
        else if (direction==Direction.LEFT && this.direction!=Direction.RIGHT)
        this.direction = direction;
        else if (direction==Direction.UP && this.direction!=Direction.DOWN)
        this.direction = direction;
        else if (direction==Direction.DOWN && this.direction!=Direction.UP)
        this.direction = direction;
    }
    
    public void move(ArrayList<Apple> apples, ArrayList<Enemy> enemys) {
        GameObject newHead = createNewHead();
        if(newHead.x > SnakeGame.WIDTH - 1)
            newHead.x = 0;
        if (newHead.x < 0)
            newHead.x = SnakeGame.WIDTH - 1;
        if (newHead.y < 0)
            newHead.y = SnakeGame.HEIGHT-1;
        if (newHead.y>SnakeGame.HEIGHT-1)
            newHead.y = 0;
        boolean isNeedRemove = true;
        if (checkCollision(newHead))    {
            isAlive = false;
        }   else {
            snakeParts.add(0, newHead);
            for (Apple gameObject : apples) {
                if (gameObject.isAlive) {
                    if (newHead.x == gameObject.x && newHead.y == gameObject.y) {
                        gameObject.isAlive = false;
                        isNeedRemove = false;
                    }
                }
            }
            for (Enemy enemy : enemys) {
                if (newHead.x == enemy.x && newHead.y == enemy.y) {
                    enemy.isAlive = false;
                    if (snakeParts.size()>3)
                        snakeParts.remove(0);
                }
            }
        }
        if(isNeedRemove)
            removeTail();
    }
    
    public GameObject createNewHead() {
        GameObject head;
        System.out.println(snakeParts.size());
        head = snakeParts.get(0);
        GameObject createdNewHead;
        if (direction==Direction.LEFT) {
            createdNewHead = new GameObject(head.x-1,head.y);
        } else if (direction==Direction.RIGHT) {
            createdNewHead = new GameObject(head.x+1,head.y);
        } else if (direction == Direction.UP) {
            createdNewHead = new GameObject(head.x,head.y-1);
        } else {
            createdNewHead = new GameObject(head.x,head.y+1);
        }
        
        return createdNewHead;
    }
    
    public void removeTail()   {
        int i = snakeParts.size();
        snakeParts.remove(i - 1);
    }
    
    public boolean checkCollision(GameObject gameObject) {
        boolean check = true;
        for(GameObject snk : snakeParts)  {
            if (snk.x == gameObject.x && snk.y == gameObject.y) {
                check = true;
                break;
            }
            else    {
                check = false;
            }
        }
        return check;
    }
    
    public int getLength() {
        return snakeParts.size();
    }
}