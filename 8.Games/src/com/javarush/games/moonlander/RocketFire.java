package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;
import java.util.List;

public class RocketFire extends GameObject {
	private List<int[][]> frames;
	private int frameIndex;
	private boolean isVisible;

	public void show()  {
		isVisible = true;
	}

	public void hide()  {
		isVisible = false;
	}

	@Override
	public void draw(Game game)  {
		if(isVisible) {
			nextFrame();
			super.draw(game);
		}
	}

	private void nextFrame ()   {
		frameIndex++;
		if(frameIndex >= frames.size())
			frameIndex = 0;
		matrix = frames.get(frameIndex);
	}

	public RocketFire (List<int[][]> framesList)    {
		super(0,0,framesList.get(0));
		frames = framesList;
		frameIndex  = 0;
		isVisible = false;
	}
}
