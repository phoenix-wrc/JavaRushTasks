package com.javarush.games.snake;


public class GameObject {
    public int x, y;

    GameObject(int x, int y)    {
        this.x = x;
        this.y = y;
    }

    //public void draw(Game game){
    //    game.setCellValueEx(x,y, Color.NONE,"OPS",Color.RED,75);
    //}
}