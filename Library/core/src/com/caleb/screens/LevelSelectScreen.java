package com.caleb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.caleb.gspikes.GravitySpikesRevamped;
import com.caleb.tools.PreferenceManager;

public class LevelSelectScreen implements Screen {

	private static final int BUTTON_WIDTH = 145;
	private static final int BUTTON_HEIGHT = 145;
	private static final int SPACING = 60;
	private static final float ROW_ONE_BUTTON_Y = GravitySpikesRevamped.GAME_HEIGHT - BUTTON_HEIGHT - SPACING;

	private static final float ROW_TWO_BUTTON_Y = GravitySpikesRevamped.GAME_HEIGHT - BUTTON_HEIGHT * 2.2f - SPACING;
	private static final float ROW_THREE_BUTTON_Y = GravitySpikesRevamped.GAME_HEIGHT - BUTTON_HEIGHT * 3.4f - SPACING;
	private static final float ROW_FOUR_BUTTON_Y = GravitySpikesRevamped.GAME_HEIGHT - BUTTON_HEIGHT * 4.6f - SPACING;

	private GravitySpikesRevamped game;

	private Viewport levelSelectPort;
	private Stage stage;

	private PreferenceManager preferences;

	private Screen levelOneScreen;
	private Screen levelTwoScreen;
	private Screen levelThreeScreen;
	private Screen levelFourScreen;
	private Screen levelFiveScreen;
	private Screen levelSixScreen;
	private Screen levelSevenScreen;
	private Screen levelEightScreen;

	public LevelSelectScreen(GravitySpikesRevamped game) {
		this.game = game;
		this.game.loadingTextures();
		this.game.textureManager.finishLoadingAsset("LevelSelectBackground.png");
		this.game.textureManager.finishLoadingAsset("LoadingScreen.png");

		preferences = new PreferenceManager();

		levelOneScreen = new LevelOneScreen(game);
		if (preferences.getPreferences("One").equals("One"))
			levelTwoScreen = new LevelTwoScreen(game);
		if (preferences.getPreferences("Two").equals("Two"))
			levelThreeScreen = new LevelThreeScreen(game);
		if (preferences.getPreferences("Three").equals("Three"))
			levelFourScreen = new LevelFourScreen(game);
		if (preferences.getPreferences("Four").equals("Four"))
			levelFiveScreen = new LevelFiveScreen(game);
		if (preferences.getPreferences("Five").equals("Five"))
			levelSixScreen = new LevelSixScreen(game);
		if (preferences.getPreferences("Six").equals("Six"))
			levelSevenScreen = new LevelSevenScreen(game);
		if (preferences.getPreferences("Seven").equals("Seven"))
			levelEightScreen = new LevelEightScreen(game);

		levelSelectPort = new FitViewport(GravitySpikesRevamped.GAME_WIDTH / GravitySpikesRevamped.PPM,
				GravitySpikesRevamped.GAME_HEIGHT / GravitySpikesRevamped.PPM, new OrthographicCamera());

		stage = new Stage(levelSelectPort, game.batch);

	}

	public void update(float dt) {

	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(.4f, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();

		if (game.textureManager.update()) {
			game.batch.draw(game.textureManager.get("LevelSelectBackground.png", Texture.class), 0, 0,
					GravitySpikesRevamped.GAME_WIDTH / GravitySpikesRevamped.PPM,
					GravitySpikesRevamped.GAME_HEIGHT / GravitySpikesRevamped.PPM);
			if (game.textureManager.isLoaded("LoadingScreen.png"))
				game.textureManager.unload("LoadingScreen.png");

			handleInput();
		} else
			game.batch.draw(game.textureManager.get("LoadingScreen.png", Texture.class), 0, 0,
					GravitySpikesRevamped.GAME_WIDTH / GravitySpikesRevamped.PPM,
					GravitySpikesRevamped.GAME_HEIGHT / GravitySpikesRevamped.PPM);

		game.batch.end();

		stage.draw();

	}

	public void handleInput() {
		float firstX = BUTTON_WIDTH / 2;
		float secondX = BUTTON_WIDTH * 2;

		game.textureManager.finishLoadingAsset("LevelOneButtonActive.png");
		game.textureManager.finishLoadingAsset("LevelOneButtonInactive.png");
		levelManager(firstX, ROW_ONE_BUTTON_Y, levelOneScreen,
				game.textureManager.get("LevelOneButtonActive.png", Texture.class),
				game.textureManager.get("LevelOneButtonInactive.png", Texture.class), 1);

		/* Level Two */
		if (preferences.getPreferences("One").equals("One")) {
			game.textureManager.finishLoadingAsset("LevelTwoButtonActive.png");
			game.textureManager.finishLoadingAsset("LevelTwoButtonInactive.png");

			if (game.textureManager.isLoaded("LevelTwoButtonLocked.png")) {
				game.textureManager.unload("LevelTwoButtonLocked.png");
			}

			levelManager(secondX, ROW_ONE_BUTTON_Y, levelTwoScreen,
					game.textureManager.get("LevelTwoButtonActive.png", Texture.class),
					game.textureManager.get("LevelTwoButtonInactive.png", Texture.class), 2);
		} else {

			if (game.textureManager.isLoaded("LevelTwoButtonActive.png")
					&& game.textureManager.isLoaded("LevelTwoButtonInactive.png")) {
				game.textureManager.unload("LevelTwoButtonActive.png");
				game.textureManager.unload("LevelTwoButtonInactive.png");
			}

			game.textureManager.finishLoadingAsset("LevelTwoButtonLocked.png");
			game.batch.draw(game.textureManager.get("LevelTwoButtonLocked.png", Texture.class),
					BUTTON_WIDTH * 2 / GravitySpikesRevamped.PPM, ROW_ONE_BUTTON_Y / GravitySpikesRevamped.PPM,
					BUTTON_WIDTH / GravitySpikesRevamped.PPM, BUTTON_HEIGHT / GravitySpikesRevamped.PPM);
		}
		/* Level Three */
		if (preferences.getPreferences("Two").equals("Two")) {
			game.textureManager.finishLoadingAsset("LevelThreeButtonActive.png");
			game.textureManager.finishLoadingAsset("LevelThreeButtonInactive.png");

			if (game.textureManager.isLoaded("LevelThreeButtonLocked.png")) {
				game.textureManager.unload("LevelThreeButtonLocked.png");
			}

			levelManager(firstX, ROW_TWO_BUTTON_Y, levelThreeScreen,
					game.textureManager.get("LevelThreeButtonActive.png", Texture.class),
					game.textureManager.get("LevelThreeButtonInactive.png", Texture.class), 1);
		} else {

			if (game.textureManager.isLoaded("LevelThreeButtonActive.png")
					&& game.textureManager.isLoaded("LevelThreeButtonInactive.png")) {
				game.textureManager.unload("LevelThreeButtonActive.png");
				game.textureManager.unload("LevelThreeButtonInactive.png");
			}

			game.textureManager.finishLoadingAsset("LevelThreeButtonLocked.png");
			game.batch.draw(game.textureManager.get("LevelThreeButtonLocked.png", Texture.class),
					BUTTON_WIDTH / 2 / GravitySpikesRevamped.PPM, ROW_TWO_BUTTON_Y / GravitySpikesRevamped.PPM,
					BUTTON_WIDTH / GravitySpikesRevamped.PPM, BUTTON_HEIGHT / GravitySpikesRevamped.PPM);
		}
		/* Level Four */
		if (preferences.getPreferences("Three").equals("Three")) {
			game.textureManager.finishLoadingAsset("LevelFourButtonActive.png");
			game.textureManager.finishLoadingAsset("LevelFourButtonInactive.png");

			if (game.textureManager.isLoaded("LevelFourButtonLocked.png")) {
				game.textureManager.unload("LevelFourButtonLocked.png");
			}

			levelManager(secondX, ROW_TWO_BUTTON_Y, levelFourScreen,
					game.textureManager.get("LevelFourButtonActive.png", Texture.class),
					game.textureManager.get("LevelFourButtonInactive.png", Texture.class), 2);
		} else {

			if (game.textureManager.isLoaded("LevelFourButtonActive.png")
					&& game.textureManager.isLoaded("LevelFourButtonInactive.png")) {
				game.textureManager.unload("LevelFourButtonActive.png");
				game.textureManager.unload("LevelFourButtonInactive.png");
			}

			game.textureManager.finishLoadingAsset("LevelFourButtonLocked.png");
			game.batch.draw(game.textureManager.get("LevelFourButtonLocked.png", Texture.class),
					BUTTON_WIDTH * 2 / GravitySpikesRevamped.PPM, ROW_TWO_BUTTON_Y / GravitySpikesRevamped.PPM,
					BUTTON_WIDTH / GravitySpikesRevamped.PPM, BUTTON_HEIGHT / GravitySpikesRevamped.PPM);
		}
		/* Level Five */

		if (preferences.getPreferences("Four").equals("Four")) {

			game.textureManager.finishLoadingAsset("LevelFiveButtonActive.png");
			game.textureManager.finishLoadingAsset("LevelFiveButtonInactive.png");

			if (game.textureManager.isLoaded("LevelFiveButtonLocked.png")) {
				game.textureManager.unload("LevelFiveButtonLocked.png");
			}
			levelManager(firstX, ROW_THREE_BUTTON_Y, levelFiveScreen,
					game.textureManager.get("LevelFiveButtonActive.png", Texture.class),
					game.textureManager.get("LevelFiveButtonInactive.png", Texture.class), 1);
		} else {

			if (game.textureManager.isLoaded("LevelFiveButtonActive.png")
					&& game.textureManager.isLoaded("LevelFiveButtonInactive.png")) {
				game.textureManager.unload("LevelFiveButtonActive.png");
				game.textureManager.unload("LevelFiveButtonInactive.png");
			}
			game.textureManager.finishLoadingAsset("LevelFiveButtonLocked.png");
			game.batch.draw(game.textureManager.get("LevelFiveButtonLocked.png", Texture.class),
					BUTTON_WIDTH / 2 / GravitySpikesRevamped.PPM, ROW_THREE_BUTTON_Y / GravitySpikesRevamped.PPM,
					BUTTON_WIDTH / GravitySpikesRevamped.PPM, BUTTON_HEIGHT / GravitySpikesRevamped.PPM);
		}
		/* Level Six */
		if (preferences.getPreferences("Five").equals("Five")) {
			game.textureManager.finishLoadingAsset("LevelSixButtonActive.png");
			game.textureManager.finishLoadingAsset("LevelSixButtonInactive.png");

			if (game.textureManager.isLoaded("LevelSixButtonLocked.png")) {
				game.textureManager.unload("LevelSixButtonLocked.png");
			}

			levelManager(secondX, ROW_THREE_BUTTON_Y, levelSixScreen,
					game.textureManager.get("LevelSixButtonActive.png", Texture.class),
					game.textureManager.get("LevelSixButtonInactive.png", Texture.class), 2);
		} else {

			if (game.textureManager.isLoaded("LevelSixButtonActive.png")
					&& game.textureManager.isLoaded("LevelSixButtonInactive.png")) {
				game.textureManager.unload("LevelSixButtonActive.png");
				game.textureManager.unload("LevelSixButtonInactive.png");
			}

			game.textureManager.finishLoadingAsset("LevelSixButtonLocked.png");
			game.batch.draw(game.textureManager.get("LevelSixButtonLocked.png", Texture.class),
					BUTTON_WIDTH * 2 / GravitySpikesRevamped.PPM, ROW_THREE_BUTTON_Y / GravitySpikesRevamped.PPM,
					BUTTON_WIDTH / GravitySpikesRevamped.PPM, BUTTON_HEIGHT / GravitySpikesRevamped.PPM);
		}
		/* Level Seven */

		if (preferences.getPreferences("Six").equals("Six")) {
			game.textureManager.finishLoadingAsset("LevelSevenButtonActive.png");
			game.textureManager.finishLoadingAsset("LevelSevenButtonInactive.png");

			if (game.textureManager.isLoaded("LevelSevenButtonLocked.png")) {
				game.textureManager.unload("LevelSevenButtonLocked.png");
			}
			levelManager(firstX, ROW_FOUR_BUTTON_Y, levelSevenScreen,
					game.textureManager.get("LevelSevenButtonActive.png", Texture.class),
					game.textureManager.get("LevelSevenButtonInactive.png", Texture.class), 1);
		} else {
			if (game.textureManager.isLoaded("LevelSevenButtonActive.png")
					&& game.textureManager.isLoaded("LevelSevenButtonInactive.png")) {
				game.textureManager.unload("LevelSevenButtonActive.png");
				game.textureManager.unload("LevelSevenButtonInactive.png");
			}
			game.textureManager.finishLoadingAsset("LevelSevenButtonLocked.png");
			game.batch.draw(game.textureManager.get("LevelSevenButtonLocked.png", Texture.class),
					BUTTON_WIDTH / 2 / GravitySpikesRevamped.PPM, ROW_FOUR_BUTTON_Y / GravitySpikesRevamped.PPM,
					BUTTON_WIDTH / GravitySpikesRevamped.PPM, BUTTON_HEIGHT / GravitySpikesRevamped.PPM);
		}
		/* Level Eight */
		if (preferences.getPreferences("Seven").equals("Seven")) {
			game.textureManager.finishLoadingAsset("LevelEightButtonActive.png");
			game.textureManager.finishLoadingAsset("LevelEightButtonInactive.png");

			if (game.textureManager.isLoaded("LevelEightButtonLocked.png")) {
				game.textureManager.unload("LevelEightButtonLocked.png");
			}

			levelManager(secondX, ROW_FOUR_BUTTON_Y, levelEightScreen,
					game.textureManager.get("LevelEightButtonActive.png", Texture.class),
					game.textureManager.get("LevelEightButtonInactive.png", Texture.class), 2);
		} else {

			if (game.textureManager.isLoaded("LevelEightButtonActive.png")
					&& game.textureManager.isLoaded("LevelEightButtonInactive.png")) {
				game.textureManager.unload("LevelEightButtonActive.png");
				game.textureManager.unload("LevelEightButtonInactive.png");
			}
			game.textureManager.finishLoadingAsset("LevelEightButtonLocked.png");
			game.batch.draw(game.textureManager.get("LevelEightButtonLocked.png", Texture.class),
					BUTTON_WIDTH * 2 / GravitySpikesRevamped.PPM, ROW_FOUR_BUTTON_Y / GravitySpikesRevamped.PPM,
					BUTTON_WIDTH / GravitySpikesRevamped.PPM, BUTTON_HEIGHT / GravitySpikesRevamped.PPM);
		}
	}

	public void levelManager(float rowX, float rowY, Screen setScreen, Texture drawActive, Texture drawInactive,
			float multiplier) {
		if (Gdx.input.getX() / GravitySpikesRevamped.PPM < (rowX + BUTTON_WIDTH) / GravitySpikesRevamped.PPM
				&& Gdx.input.getX() / GravitySpikesRevamped.PPM > rowX / GravitySpikesRevamped.PPM
				&& (GravitySpikesRevamped.GAME_HEIGHT - Gdx.input.getY())
						/ GravitySpikesRevamped.PPM < (rowY + BUTTON_HEIGHT) / GravitySpikesRevamped.PPM
				&& (GravitySpikesRevamped.GAME_HEIGHT - Gdx.input.getY()) / GravitySpikesRevamped.PPM > rowY
						/ GravitySpikesRevamped.PPM) {

			if (multiplier == 1) {
				game.batch.draw(drawActive, BUTTON_WIDTH / 2 / GravitySpikesRevamped.PPM,
						rowY / GravitySpikesRevamped.PPM, BUTTON_WIDTH / GravitySpikesRevamped.PPM,
						BUTTON_HEIGHT / GravitySpikesRevamped.PPM);
			} else if (multiplier == 2) {
				game.batch.draw(drawActive, BUTTON_WIDTH * 2 / GravitySpikesRevamped.PPM,
						rowY / GravitySpikesRevamped.PPM, BUTTON_WIDTH / GravitySpikesRevamped.PPM,
						BUTTON_HEIGHT / GravitySpikesRevamped.PPM);
			} else if (multiplier == 3) {
				game.batch.draw(drawActive, BUTTON_WIDTH * 3.5f / GravitySpikesRevamped.PPM,
						rowY / GravitySpikesRevamped.PPM, BUTTON_WIDTH / GravitySpikesRevamped.PPM,
						BUTTON_HEIGHT / GravitySpikesRevamped.PPM);
			}

			if (Gdx.input.isTouched()) {
				game.setScreen(setScreen);
			}
		} else {

			if (multiplier == 1) {
				game.batch.draw(drawInactive, BUTTON_WIDTH / 2 / GravitySpikesRevamped.PPM,
						rowY / GravitySpikesRevamped.PPM, BUTTON_WIDTH / GravitySpikesRevamped.PPM,
						BUTTON_HEIGHT / GravitySpikesRevamped.PPM);
			} else if (multiplier == 2) {
				game.batch.draw(drawInactive, BUTTON_WIDTH * 2 / GravitySpikesRevamped.PPM,
						rowY / GravitySpikesRevamped.PPM, BUTTON_WIDTH / GravitySpikesRevamped.PPM,
						BUTTON_HEIGHT / GravitySpikesRevamped.PPM);
			} else if (multiplier == 3) {
				game.batch.draw(drawInactive, BUTTON_WIDTH * 3.5f / GravitySpikesRevamped.PPM,
						rowY / GravitySpikesRevamped.PPM, BUTTON_WIDTH / GravitySpikesRevamped.PPM,
						BUTTON_HEIGHT / GravitySpikesRevamped.PPM);
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		levelSelectPort.update(width, height);

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {

		game.textureManager.dispose();
		stage.dispose();
		game.dispose();
		game.batch.dispose();
		levelOneScreen.dispose();
		levelTwoScreen.dispose();
		levelThreeScreen.dispose();
		levelFourScreen.dispose();
		levelFiveScreen.dispose();
		levelSixScreen.dispose();
		levelSevenScreen.dispose();
		levelEightScreen.dispose();

	}

}
