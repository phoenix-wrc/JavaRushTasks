package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private static final String MINE = "\uD83D\uDCA3", FLAG = "\uD83D\uDEA9";
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField, countFlags, score, countClosedTiles = SIDE*SIDE;
    private boolean isGameStopped;

    private void openTile (int x, int y)    {
        if (gameField[y][x].isOpen || gameField[y][x].isFlag || isGameStopped) return;
        gameField[y][x].isOpen = true;
        if(!gameField[y][x].isMine)
            score += 5;
        countClosedTiles--;
        if(!gameField[y][x].isMine && countClosedTiles == countMinesOnField)
            win();
        if (gameField[y][x].isMine) {
            //setCellValue(x, y, MINE);
            //setCellColor(x, y, Color.RED);
            setCellValueEx(x, y, Color.RED, MINE);
            gameOver();
        }
        else    {
            if(gameField[y][x].countMineNeighbors == 0) {
                List<GameObject> list = getNeighbors(gameField[y][x]);
                for (GameObject tile : list) {
                    if(!tile.isOpen && !tile.isFlag)
                        openTile(tile.x, tile.y);
                }
                setCellValue(x, y, "");
            }
            else
            setCellNumber(x, y, gameField[y][x].countMineNeighbors);
            setCellColor(x, y, Color.GREEN);
        }
        setScore(score);
    }

    private void markTile(int x, int y) {
        if(gameField[y][x].isOpen || isGameStopped) return;
        if(!gameField[y][x].isFlag && countFlags == 0) return;
        if(!gameField[y][x].isFlag )  {
            gameField[y][x].isFlag = true;
            countFlags --;
            setCellValue(x, y, FLAG);
            setCellColor(x, y, Color.YELLOW);
        }
        else if (gameField[y][x].isFlag)    {
            gameField[y][x].isFlag = false;
            countFlags ++;
            setCellValue(x, y, "");
            setCellColor(x, y, Color.ORANGE);
        }
    }

    private void restart()  {
        isGameStopped = false;
        score =0;
        setScore(score);
        countClosedTiles = SIDE*SIDE;
        countMinesOnField = 0;
        createGame();
    }

    private void gameOver () {
        isGameStopped = true;
        showMessageDialog(Color.DARKRED, "Не победа",
                Color.YELLOWGREEN, 48);
    }

    private void win () {
        isGameStopped = true;
        showMessageDialog(Color.SEAGREEN, "Победа, урЯ",
                Color.MEDIUMVIOLETRED, 60);
    }

    private void countMineNeighbors ()  {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                if(!gameField[y][x].isMine) {
                    List<GameObject> list = getNeighbors(gameField[y][x]);
                    for (GameObject gameObject : list) {
                        if(gameObject.isMine)
                            gameField[y][x].countMineNeighbors += 1;
                    }
                }
            }
        }
    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.ORANGE);
                setCellValue(x, y, "");
            }
        }
        countMineNeighbors ();
        countFlags = countMinesOnField;
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    @Override

    public void onMouseLeftClick(int x, int y)  {
        if(isGameStopped)   {
            restart();
        }
        else
            openTile(x, y);
    }

    public void onMouseRightClick (int x, int y)    {
        markTile(x, y);
    }

    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

}