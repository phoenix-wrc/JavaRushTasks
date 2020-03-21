package com.javarush.games.spaceinvaders.gameobjects;
import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

public class Ship extends GameObject    {
    public boolean isAlive = true;
    private List<int[][]> frames;
    private int frameIndex;
    private boolean loopAnimation = false; 
    
    public void setAnimatedView(boolean isLoopAnimation, int [][] ... viewFrames)  {
        loopAnimation = isLoopAnimation;
        super.setMatrix(viewFrames[0]);
        frames = Arrays.asList(viewFrames);
        frameIndex = 0;
    }
    
    public boolean isVisible()  {
        if(!isAlive && frameIndex >= frames.size())
            return false;
        else
            return true;
    }
    public void nextFrame() {
        frameIndex++;
        if (loopAnimation && frameIndex == frames.size()) {
            frameIndex = 0;
        }
        if (frameIndex < frames.size()) {
            matrix = frames.get(frameIndex);
        }
    }
    /*
    public void nextFrame() {
        if(frameIndex >= frames.size() && loopAnimation == false) return;
        frameIndex += 1;
        if (frameIndex >= frames.size() && loopAnimation == true)
            frameIndex=0;
        if (!(frameIndex >= frames.size())) 
            matrix = frames.get(frameIndex);
    }
    */
    public void draw(Game game) {
        super.draw(game);
        nextFrame();
    }
    /*
    public void setAnimatedView(int[][]... viewFrames) {
        super.setMatrix(viewFrames[0]);
        frames = Arrays.asList(viewFrames);
        frameIndex = 0;
        
    }
    */
    public void kill()  {
        isAlive = false;
    }
    
    public Ship(double x, double y)   {
        super(x, y);
    }
    
    public void setStaticView(int[][] viewFrame)    {
        super.setMatrix(viewFrame);
        frames = new ArrayList<int[][]>();
        frames.add(viewFrame);
        frameIndex = 0;
    }
    
    public Bullet fire ()  {
        return null;
    }
}