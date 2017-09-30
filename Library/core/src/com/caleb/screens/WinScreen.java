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

public class WinScreen implements Screen {

	private static final int TITLE_WIDTH = 240;
	private static final int TITLE_HEIGHT = 240;

	private static final int PLAY_WIDTH = 200;
	private static final int PLAY_HEIGHT = 100;

	private static final int TITLE_Y = 400;
	private static final int PLAY_Y = 150;

	private GravitySpikesRevamped game;
	private Texture winTitle;
	private Texture wonScreen;
	private Texture nextLevelActive;
	private Texture nextLevelInactive;

	private Viewport winPort;
	private Stage stage;
	
	

	public WinScreen(GravitySpikesRevamped game) {
		this.game = game;
		winTitle = new Texture("WinTitle.png");
		wonScreen = new Texture("WonScreen.png");
		nextLevelActive = new Texture("NextLevelActive.png");
		nextLevelInactive = new Texture("NextLevelInactive.png");

		winPort = new FitViewport(GravitySpikesRevamped.GAME_WIDTH / GravitySpikesRevamped.PPM,
				GravitySpikesRevamped.GAME_HEIGHT / GravitySpikesRevamped.PPM, new OrthographicCamera());

		stage = new Stage(winPort, game.batch);
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 0);

		game.batch.begin();
		game.batch.draw(wonScreen, 0, 0, GravitySpikesRevamped.GAME_WIDTH / GravitySpikesRevamped.PPM,
				GravitySpikesRevamped.GAME_HEIGHT / GravitySpikesRevamped.PPM);

		game.batch.draw(winTitle,
				GravitySpikesRevamped.GAME_WIDTH / 2 / GravitySpikesRevamped.PPM
						- TITLE_WIDTH / 2 / GravitySpikesRevamped.PPM,
				TITLE_Y / GravitySpikesRevamped.PPM, TITLE_WIDTH / GravitySpikesRevamped.PPM,
				TITLE_HEIGHT / GravitySpikesRevamped.PPM);

		handleInput();

		game.batch.end();

		stage.draw();
	}

	public void handleInput() {
		int playX = GravitySpikesRevamped.GAME_WIDTH / 2 - PLAY_WIDTH / 2;

		if (Gdx.input.getX() / GravitySpikesRevamped.PPM < (playX + PLAY_WIDTH) / GravitySpikesRevamped.PPM
				&& Gdx.input.getX() / GravitySpikesRevamped.PPM > (playX) / GravitySpikesRevamped.PPM
				&& (GravitySpikesRevamped.GAME_HEIGHT - Gdx.input.getY())
						/ GravitySpikesRevamped.PPM < (PLAY_Y + PLAY_HEIGHT) / GravitySpikesRevamped.PPM
				&& (GravitySpikesRevamped.GAME_HEIGHT - Gdx.input.getY()) / GravitySpikesRevamped.PPM > (PLAY_Y)
						/ GravitySpikesRevamped.PPM) {

			game.batch.draw(nextLevelActive,
					GravitySpikesRevamped.GAME_WIDTH / 2 / GravitySpikesRevamped.PPM
							- PLAY_WIDTH / 2 / GravitySpikesRevamped.PPM,
					PLAY_Y / GravitySpikesRevamped.PPM, PLAY_WIDTH / GravitySpikesRevamped.PPM,
					PLAY_HEIGHT / GravitySpikesRevamped.PPM);

			if (Gdx.input.isTouched()) {
				game.setScreen(new LevelSelectScreen(game));
			}
		} else {
			game.batch.draw(nextLevelInactive,
					GravitySpikesRevamped.GAME_WIDTH / 2 / GravitySpikesRevamped.PPM
							- PLAY_WIDTH / 2 / GravitySpikesRevamped.PPM,
					PLAY_Y / GravitySpikesRevamped.PPM, PLAY_WIDTH / GravitySpikesRevamped.PPM,
					PLAY_HEIGHT / GravitySpikesRevamped.PPM);
		}
	}

	@Override
	public void resize(int width, int height) {
		winPort.update(width, height);

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
		winTitle.dispose();
		nextLevelActive.dispose();
		nextLevelInactive.dispose();
		wonScreen.dispose();
		game.batch.dispose();
		game.dispose();
		stage.dispose();

	}

}
