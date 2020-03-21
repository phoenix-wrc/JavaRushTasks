package com.javarush.games.NightmareRealm;

import com.javarush.engine.cell.*;

public class GameObject {
    public int x, y;
    private int turnCount = 1; // используется для мигания клетки
    public boolean isBlocked; // для заблокированных клеток
    public boolean isEmpty; // для пустых клеток
    public boolean isSelected; // для подсветки выбранной клетки
    public Color color;
    public String sign;


    GameObject(int x, int y, boolean isBlocked, boolean isEmpty)    {
        this.x = x;
        this.y = y;
        this.isEmpty = isEmpty;
        this.isBlocked = isBlocked;
        if (isBlocked)
            color = Color.BLACK;
        if(isEmpty)
            color = Color.WHITE;
        sign = "";
    }

    public void draw(Game game){
        if(isBlocked) {
            game.setCellValueEx(x,y, Color.BLACK,"",Color.NONE,75);
        } else {
            if(isSelected) {
                if(turnCount>0){
                    game.setCellValueEx(x, y, Color.values()[color.ordinal()+2], sign, Color.BLACK, 75);
                } else {
                    game.setCellValueEx(x, y, Color.values()[color.ordinal()+4], sign, Color.BLACK, 75);
                }
                turnCount *= -1;
            } else
                game.setCellValueEx(x,y, color,sign,Color.BLACK,75);
        }
    }

    public void getColor() {
        if(sign.equals("\uD83D\uDD25")) // огонь
            color = Color.RED;
        else if(sign.equals("\uD83C\uDF0A")) // волна
            color = Color.BLUE;
        else if(sign.equals("\uD83C\uDF24")) // солнце
            color = Color.YELLOW;
        else
            color = Color.BROWN;
    }
}