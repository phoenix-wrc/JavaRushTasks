package com.javarush.games.SortVisio;

import java.io.File;
import java.util.Random;

public class Sounds {
	String pathChoose = "8.Games/src/Sound/ChooseSound";
	String pathSwap = "8.Games/src/Sound/SwapSound";
	Random rnd = new Random();

	File folderChoose = new File(pathChoose);
	File folderSwap = new File(pathSwap);
	String[] filesChoose = folderChoose.list((folder1, name) ->
			name.endsWith(".wav"));
	String[] filesSwap = folderSwap.list((folder1, name) ->
			name.endsWith(".wav"));

	Sound soundChoose[] = initializeSounds(pathChoose, filesChoose);
	Sound soundSwap[] = initializeSounds(pathSwap, filesSwap);

	Sound[] initializeSounds(String folder, String[] files) {
		Sound sounds[] = new Sound[files.length];
		for (int i = 0; i < files.length; i++) {
			sounds[i] = new Sound(new File(
					folder + "/" + files[i]));
		}
		return sounds;
	}

	public void playSwap() {
		soundSwap[rnd.nextInt(soundSwap.length)].play();
	}

	public void playChose() {
		soundChoose[rnd.nextInt(soundChoose.length)].play();
	}
}
