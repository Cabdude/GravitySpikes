package com.caleb.gspikes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.caleb.screens.MainMenuScreen;

public class GravitySpikesRevamped extends Game {

	public static final int GAME_HEIGHT = 720;
	public static final int GAME_WIDTH = 480;
	public static final float PPM = 100;

	public static final short DEADPLAYER_BIT = 2;
	public static final short GROUND_BIT = 4;
	public static final short SPIKE_BIT = 8;
	public static final short EXIT_BIT = 16;
	public static final short ENTER_BIT = 32;
	public static final short DEFAULT_BIT = 1;
	public static final short PLAYER_BIT = 64;

	public SpriteBatch batch;
	Texture img;

	public AssetManager textureManager;
	public AssetManager musicManager;

	public AssetManager soundManager;

	@Override
	public void create() {
		batch = new SpriteBatch();
		textureManager = new AssetManager();
		musicManager = new AssetManager();
		soundManager = new AssetManager();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	public void loadingTextures() {

		textureManager.load("LevelSelectBackground.png", Texture.class);
		textureManager.load("LevelOneButtonInactive.png", Texture.class);
		textureManager.load("LevelOneButtonActive.png", Texture.class);

		textureManager.load("LevelTwoButtonInactive.png", Texture.class);
		textureManager.load("LevelTwoButtonActive.png", Texture.class);
		textureManager.load("LevelTwoButtonLocked.png", Texture.class);

		textureManager.load("LevelThreeButtonInactive.png", Texture.class);
		textureManager.load("LevelThreeButtonActive.png", Texture.class);
		textureManager.load("LevelThreeButtonLocked.png", Texture.class);

		textureManager.load("LevelFourButtonInactive.png", Texture.class);
		textureManager.load("LevelFourButtonActive.png", Texture.class);
		textureManager.load("LevelFourButtonLocked.png", Texture.class);

		textureManager.load("LevelFiveButtonInactive.png", Texture.class);
		textureManager.load("LevelFiveButtonActive.png", Texture.class);
		textureManager.load("LevelFiveButtonLocked.png", Texture.class);

		textureManager.load("LevelSixButtonInactive.png", Texture.class);
		textureManager.load("LevelSixButtonActive.png", Texture.class);
		textureManager.load("LevelSixButtonLocked.png", Texture.class);

		textureManager.load("LevelSevenButtonInactive.png", Texture.class);
		textureManager.load("LevelSevenButtonActive.png", Texture.class);
		textureManager.load("LevelSevenButtonLocked.png", Texture.class);

		textureManager.load("LevelEightButtonInactive.png", Texture.class);
		textureManager.load("LevelEightButtonActive.png", Texture.class);
		textureManager.load("LevelEightButtonLocked.png", Texture.class);

		textureManager.load("LoadingScreen.png", Texture.class);
	}

	public void loadingMusic() {
		musicManager.load("audio/music/technosound.ogg", Music.class);
	}

	public void loadingSounds() {
		soundManager.load("audio/sounds/impaled.ogg", Sound.class);

	}

}
