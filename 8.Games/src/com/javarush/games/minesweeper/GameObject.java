package com.javarush.games.minesweeper;

public class GameObject {
    public int x, y;
    public boolean isMine, isOpen, isFlag;
    public int countMineNeighbors;

    public GameObject (int x, int y, boolean isMine)    {
        this.x = x;
        this.y = y;
        this.isMine = isMine;
        this.isOpen = false;
        this.isFlag = false;
    }
}
