package com.javarush.games.spaceinvaders.gameobjects;
import com.javarush.engine.cell.*;

public class Star extends GameObject {
    private static final String STAR_SIGN = "\u2605";
    private Color color;
    
    public void draw(Game game)  {
        game.setCellValueEx((int)x, (int)y, Color.NONE, STAR_SIGN, color, 100);
    }
    
    private Color RandomColor() {
        Color answer = Color.BLACK;
        int i = (int)(Math.random() * 5);
        if(i == 0) answer = Color.RED;
        if(i == 1) answer = Color.PINK;
        if(i == 2) answer = Color.WHITE;
        if(i == 3) answer = Color.LIGHTBLUE;
        if(i == 4) answer = Color.YELLOW;
        return answer;
    }
    
    public Star (double x, double y) {
        super(x,y);
        this.color = RandomColor();
    }
}