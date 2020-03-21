package com.javarush.games.snake;
import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.ListIterator;


public class SnakeGame extends Game{
    public static final int WIDTH = 15, HEIGHT =15;
    private static final int GOAL = 18;
    private int turnDelay;
    private int score;
    private int round;
    private ArrayList<Apple> apples;
    private ArrayList<Enemy> enemys;
    private Snake snake;
    private int snakeLives;
    private boolean isGameStopped = false;
    private boolean isWinLevel = false;

    public int getScore() {
        return score;
    }

    public int getSnakeLives() {
        return snakeLives;
    }

    public void initialize( ){
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame()    {
        snake = new Snake(WIDTH/2, HEIGHT/2);
        snakeLives = 3;
        apples = new ArrayList<>();
        enemys = new ArrayList<>();
        apples.add(createNewApple(false));
        enemys.add(createNewEnemy());
        isGameStopped = false;
        round = 1;
        score = 0;
        setScore(score);
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
    }

    private void createNextLevel()    {
        snake = new Snake(WIDTH/2, HEIGHT/2);
        apples.clear();
        enemys.clear();
        apples.add(createNewApple(false));
        for (int i = 0; i < round; i++) {
            enemys.add(createNewEnemy());
        }
        isGameStopped = false;
        isWinLevel = false;
        setScore(score);
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
    }

    private void drawScene() {
        for (int x = 0; x<WIDTH;x++){    
            for (int y = 0; y< HEIGHT; y++  ){
                setCellValueEx(x,y,Color.values()[42],"");
            }
        }
        snake.draw(this, getSnakeLives(), getScore(), isWinLevel);

        for (Apple object : apples) {
            object.draw(this);
        }
        for (Enemy object : enemys) {
            object.draw(this);
        }
    }

    private Enemy createNewEnemy(){
        Enemy enemy;
        for(;;)   {
            int x = getRandomNumber(WIDTH);
            int y = getRandomNumber(HEIGHT);
            enemy = new Enemy (x,y);
            if (!snake.checkCollision(enemy))
                break;
        }
        return enemy;
    }

    private Apple createNewApple (boolean bonus){
        Apple apple;
        for(;;)   {
        int x = getRandomNumber(WIDTH);
        int y = getRandomNumber(HEIGHT);
        apple = new Apple (x,y, bonus);
        if (!snake.checkCollision(apple))
            break;
        }
        return apple;
    }
    
    public void onKeyPress(Key key) {
        if (key == Key.LEFT && !isGameStopped)
            snake.setDirection(Direction.LEFT);
        else if (key == Key.RIGHT && !isGameStopped)
            snake.setDirection(Direction.RIGHT);
        else if (key == Key.UP && !isGameStopped)
            snake.setDirection(Direction.UP);
        else if (key == Key.DOWN && !isGameStopped)
            snake.setDirection(Direction.DOWN);
        else if (key == Key.SPACE && isWinLevel && isGameStopped)
            createNextLevel();
        else if (key == Key.SPACE && isGameStopped)
            createGame();
    }

    private void gameOver (){
        stopTurnTimer();
        isGameStopped = true;
        String message = " You scored " + score + " points, reached level " + round + ". " +
                "\n good luck in next time)) " +
                "\n Press SPACE for new game ";
        showMessageDialog(Color.RED, message, Color.YELLOW, 20);
    }

    private void win (){
        stopTurnTimer();
        isWinLevel = true;
        isGameStopped = true;
        showMessageDialog(Color.LIGHTGREEN, getLevelMessage(), Color.BLUE, 20);
    }

    private String getLevelMessage ()   {
        String message = "";
        switch (getRandomNumber(5)) {
            case(0): { message = " WOW!!! You born for this! \n" +
                    " Your score is " + score +"! \n" +
                    " Press SPACE for next level! ";
                break;
            }
            case(1): { message = " That's was GREAT! \n" +
                    " Your score is " + score + "! \n" +
                    " Press SPACE for next level! ";
                break;
            }
            case(2): { message = " Awesome! Level " + round + " is PASS! \n" +
                    " Score is " + score + " !!! \n" +
                    " Press SPACE for next level! ";
                break;
            }
            case(3): { message = " Lord of the SNAKES!!! \n" +
                    " Already " + score + "! \n" +
                    " Press SPACE for next level! ";
                break;
            }
            case(4): { message = " You on right WAY! \n" +
                    " Your score is " + score + "! \n" +
                    " Press SPACE for next level! ";
                break;
            }
        }
        return message;
    }

    @Override

    public void onTurn (int t) {
        snake.move(apples, enemys);
        ListIterator<Apple> iterApples = apples.listIterator();
        while(iterApples.hasNext())   {
            Apple iterObject = iterApples.next();
            if (!iterObject.isAlive ) {
                if(iterObject.isBonus){
                    iterApples.remove();
                    score += (9+round);
                    snakeLives++;
                } else {
                    iterApples.remove();
                    score += 5;
                    iterApples.add(createNewApple(false));
                    if(getRandomNumber(10) < round)
                        iterApples.add(createNewApple(true));
                }
                setScore(score);
                turnDelay -=7+(0.5*round);
                setTurnTimer(turnDelay);
            } else {
                if(iterObject.isBonus) {
                    iterObject.count--;
                    if (iterObject.count == 0) {
                        iterApples.remove();
                    }
                }
            }
        }
        ListIterator<Enemy> iterEnemys = enemys.listIterator();
        while(iterEnemys.hasNext())   {
            Enemy iterEnemy = iterEnemys.next();
            if(iterEnemy.isAlive) {
                iterEnemy.count--;
                if (iterEnemy.count == 0) {
                    iterEnemys.remove();
                    iterEnemys.add(createNewEnemy());
                }
            } else {
                snakeLives--;
                if(snakeLives == 0)
                    snake.isAlive = false;
                iterEnemys.remove();
                iterEnemys.add(createNewEnemy());
                if(getRandomNumber(2) == 0)
                    iterEnemys.add(createNewEnemy());
            }
        }
        if (!snake.isAlive)
            gameOver();
        if (GOAL + (round * 2) < snake.getLength()) {
            win();
            round++;
        }
        drawScene();
    }
}