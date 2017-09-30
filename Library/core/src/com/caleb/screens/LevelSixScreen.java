package com.caleb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.caleb.entities.ExitPortal;
import com.caleb.entities.Player;
import com.caleb.entities.Spikes;
import com.caleb.gspikes.GravitySpikesRevamped;
import com.caleb.tools.B2WorldCreator;
import com.caleb.tools.PreferenceManager;
import com.caleb.tools.WorldContactListener;

public class LevelSixScreen implements Screen {

	private GravitySpikesRevamped game;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera gamecam;
	private TiledMap levelSix;
	private Viewport gamePort;

	private World world;
	private Player player;

	private Stage stage;

	private Sound impaled;

	public LevelSixScreen(GravitySpikesRevamped game) {
		this.game = game;
		this.game.loadingSounds();
		this.game.soundManager.finishLoadingAsset("audio/sounds/impaled.ogg");

		impaled = this.game.soundManager.get("audio/sounds/impaled.ogg", Sound.class);
		levelSix = new TmxMapLoader().load("LevelSix.tmx");
		renderer = new OrthogonalTiledMapRenderer(levelSix, 1 / GravitySpikesRevamped.PPM);
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport((GravitySpikesRevamped.GAME_WIDTH) * 2 / GravitySpikesRevamped.PPM,
				(GravitySpikesRevamped.GAME_HEIGHT) * 2 / GravitySpikesRevamped.PPM, gamecam);

		world = new World(new Vector2(0, 2f), true);

		new B2WorldCreator(world, levelSix);

		player = new Player(world);

		world.setContactListener(new WorldContactListener());

		stage = new Stage(gamePort, game.batch);
	}

	public void update(float dt) {
		handleInput(dt);
		world.step(1 / 60f, 6, 2);
		player.update(dt);

		gamecam.position.x = player.playerBody.getPosition().x;
		gamecam.position.y = player.playerBody.getPosition().y;

		gamecam.update();
		renderer.setView(gamecam);
	}

	public void handleInput(float dt) {

		player.playerBody.setLinearVelocity(new Vector2(0, 7f));

		if (Gdx.input.isKeyPressed(Keys.RIGHT) && player.playerBody.getLinearVelocity().x <= 5f)
			player.playerBody.applyLinearImpulse(new Vector2(5f, 0), player.playerBody.getWorldCenter(), true);
		if (Gdx.input.isKeyPressed(Keys.LEFT) && player.playerBody.getLinearVelocity().x >= -5f)
			player.playerBody.applyLinearImpulse(new Vector2(-5f, 0), player.playerBody.getWorldCenter(), true);

		if (Spikes.dead == GravitySpikesRevamped.DEADPLAYER_BIT) {
			impaled.play();
			game.setScreen(new GameOverScreen(game));
			Spikes.setDeadPlayer(GravitySpikesRevamped.PLAYER_BIT);
		}

		if (ExitPortal.win == GravitySpikesRevamped.EXIT_BIT) {
			game.setScreen(new WinScreen(game));
			ExitPortal.setPlayerToWin(GravitySpikesRevamped.DEFAULT_BIT);
			new PreferenceManager().addPreferences("Six");
		}
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(.4f, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.render();

		game.batch.setProjectionMatrix(stage.getCamera().combined);
		game.batch.begin();
		player.draw(game.batch);
		game.batch.end();

		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);

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
		levelSix.dispose();
		renderer.dispose();
		stage.dispose();
		world.dispose();
		game.dispose();
		game.batch.dispose();
		impaled.dispose();
		game.soundManager.dispose();
	}

}
