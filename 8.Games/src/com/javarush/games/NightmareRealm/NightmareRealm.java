package com.javarush.games.NightmareRealm;

import com.javarush.engine.cell.*;

public class NightmareRealm extends Game    {
    public static final int WIDTH = 5, HEIGHT = 5;
    private int turnDelay;
    private boolean isGameStopped, hasSelected;
    GameObject[][] gameField = new GameObject[WIDTH][HEIGHT];
    GameObject select;
    private String flameSign = "\uD83D\uDD25"; // огонь
    private String waterSign = "\uD83C\uDF0A"; // волна
    private String sunSign = "\uD83C\uDF24"; // солнце

    private void createGame()    {
        isGameStopped = false;
        hasSelected = false;
        select = null;
        // создаем поле целиком
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                gameField[i][j] = new GameObject(i,j,false,true);
            }
        }
        // блокируем клетки по шаблону
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if(j%2 == 0) {
                    if (i % 2 != 0) {
                        gameField[i][j].isBlocked = true;
                        gameField[i][j].isEmpty = false;
                    }
                }
            }
        }
        createSign(flameSign); // создаём игровые объекты огня
        createSign(waterSign); // создаём игровые объекты воды
        createSign(sunSign); // создаём игровые объекты солнца
        drawScene();
        turnDelay = 500;
        setTurnTimer(turnDelay);
    }

    private void createSign(String sign)   {
        int i = 0;
        while (i < 5)    {
            int x = getRandomNumber(WIDTH);
            int y = getRandomNumber(HEIGHT);
            if(gameField[x][y].isEmpty) {
                gameField[x][y].isEmpty = false;
                gameField[x][y].sign =  sign;
                gameField[x][y].getColor();
                i++;
            }
        }
    }

    private void drawScene() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                gameField[i][j].draw(this);
            }
        }
    }
    /*
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
    */

    private void getTile(int x, int y) {
    }

    private void win (){
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.LIGHTGREEN, "Ты попедил, продолжай после нажатия мыши", Color.BLUE, 20);
    }

    private void restart()  {
        isGameStopped = false;
        createGame();
    }

    @Override
    public void onMouseLeftClick(int x, int y)  {
        if(x < 5 && x >= 0 && y < 5 && y >= 0) {
            if (isGameStopped) {
                restart();
            } else {
                if (!hasSelected) {
                    if (!gameField[x][y].isEmpty) {
                        gameField[x][y].isSelected = true;
                        select = gameField[x][y];
                        hasSelected = true;
                    }
                } else {
                    if (select.x == x && select.y == y) {
                        gameField[x][y].isSelected = false;
                        select = null;
                        hasSelected = false;
                    } else {
                        if (!gameField[x][y].isBlocked && gameField[x][y].isEmpty) {
                            swap(x, y);
                        }
                    }
                }
            }
        }
    }

    private void swap(int x, int y) {
        if((select.x == x && select.y - 1 == y) ||
                (select.x == x && select.y + 1 == y ) ||
                (select.x +1 == x && select.y  == y ) ||
                (select.x -1 == x && select.y  == y )) {
            gameField[x][y].sign = select.sign;
            gameField[x][y].color = select.color;
            gameField[x][y].isEmpty = false;
            gameField[select.x][select.y].sign = "";
            gameField[select.x][select.y].color = Color.WHITE;
            gameField[select.x][select.y].isEmpty = true;
            gameField[select.x][select.y].isSelected = false;
            select = null;
            hasSelected = false;
            drawScene();
        }
    }

    @Override
    public void onTurn (int t) {
        if ((
                gameField[0][0].sign.equals(gameField[0][1].sign) &&
                        gameField[0][1].sign.equals(gameField[0][2].sign) &&
                        gameField[0][2].sign.equals(gameField[0][3].sign) &&
                        gameField[0][3].sign.equals(gameField[0][4].sign)) &&
                (gameField[2][0].sign.equals(gameField[2][1].sign) &&
                        gameField[2][1].sign.equals(gameField[2][2].sign) &&
                        gameField[2][2].sign.equals(gameField[2][3].sign) &&
                        gameField[2][3].sign.equals(gameField[2][4].sign))&&
                (gameField[4][0].sign.equals(gameField[4][1].sign) &&
                        gameField[4][1].sign.equals(gameField[4][2].sign) &&
                        gameField[4][2].sign.equals(gameField[4][3].sign) &&
                        gameField[4][3].sign.equals(gameField[4][4].sign))
        )
            win();
        drawScene();
    }

    @Override
    public void initialize( ){
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }
}