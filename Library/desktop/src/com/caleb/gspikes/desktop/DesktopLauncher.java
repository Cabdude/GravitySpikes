package com.caleb.gspikes.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.caleb.gspikes.GravitySpikesRevamped;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GravitySpikesRevamped.GAME_WIDTH;
		config.height = GravitySpikesRevamped.GAME_HEIGHT;
		config.resizable = false;
		config.foregroundFPS = 60;
		config.useGL30 = true;
		
		new LwjglApplication(new GravitySpikesRevamped(), config);
	}
}
