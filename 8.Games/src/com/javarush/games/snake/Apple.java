package com.javarush.games.snake;
import com.javarush.engine.cell.*;
import java.util.Random;

public class Apple extends GameObject   {
    private static Random random = new Random();
    private String appleSign;
    public boolean isAlive;
    public boolean isBonus;
    public int count ;
    private Color color;

    Apple (int x, int y, boolean bonus)    {
        super (x,y);
        isBonus = bonus;
        isAlive = true;
        count = 20;
        appleSign = getAppleSign();
        color = getColor();
    }

    private Color getColor() {
        Color s = Color.RED; // яблоко
        switch (appleSign){
            case("\uD83C\uDF45"): s = Color.ORANGERED; // помидор
                break;
            case("\uD83C\uDF53"): s = Color.HOTPINK; // клубника
                break;
            case("\uD83C\uDF4D"): s = Color.GREEN; // ананас
                break;
            case("\uD83C\uDF4B"): s = Color.YELLOW; // лимон
                break;
            case("\uD83C\uDF47"): s = Color.DARKVIOLET; // виноград
                break;
            case("\uD83C\uDF4A"): s = Color.DARKORANGE; // мандарин
                break;
            case("\uD83C\uDF4F"): s = Color.DARKGREEN; //написано зеленое яблоко
                break;
        }
        return s;
    }

    private String getAppleSign () {
        String s = "\uD83C\uDF4E"; // яблоко
        int i = random.nextInt(8);
        switch (i){
            case(0): s = "\uD83C\uDF45"; // помидор
                break;
            case(1): s = "\uD83C\uDF53"; // клубника
                break;
            case(2): s = "\uD83C\uDF4D"; // ананас
                break;
            case(3): s = "\uD83C\uDF4B"; // лимон
                break;
            case(4): s = "\uD83C\uDF47"; // виноград
                break;
            case(5): s = "\uD83C\uDF4A"; // мандарин
                break;
            case(6): s = "\uD83C\uDF4F"; //написано зеленое яблоко
                break;
        }
        return s;
    }

    public void draw(Game game){
        if (isBonus) {
            /*if  (count == 1)
                game.setCellValueEx(x,y,Color.values()[],appleSign,color,75);
            else if(count == 2)
                game.setCellValueEx(x,y,Color.YELLOWGREEN,appleSign,color,75);
            else if(count == 3)
                game.setCellValueEx(x,y,Color.YELLOW,appleSign,color,75);
            else if(count == 4)
                game.setCellValueEx(x,y,Color.GREENYELLOW,appleSign,color,75);
            else */if(count%8 == 0)
                game.setCellValueEx(x,y,Color.values()[22],appleSign,color,75);
            else if(count%8 == 1)
                game.setCellValueEx(x,y,Color.values()[22],appleSign,color,75);
            else if(count%8 == 2)
                game.setCellValueEx(x,y,Color.values()[3],appleSign,color,75);
            else if(count%8 == 3)
                game.setCellValueEx(x,y,Color.values()[3],appleSign,color,75);
            else if(count%8 == 4)
                game.setCellValueEx(x,y,Color.values()[148],appleSign,color,75);
            else if(count%8 == 5)
                game.setCellValueEx(x,y,Color.values()[148],appleSign,color,75);
            else if(count%8 == 6)
                game.setCellValueEx(x,y,Color.values()[61],appleSign,color,75);
            else if(count%8 == 7)
                game.setCellValueEx(x,y,Color.values()[61],appleSign,color,75);
        }
        else
            game.setCellValueEx(x,y,Color.NONE, appleSign,color,75);
    }
}