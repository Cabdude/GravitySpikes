package com.caleb.entities;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.caleb.gspikes.GravitySpikesRevamped;

public class Spikes extends InteractiveTileObject {

	public static short dead;

	public Spikes(World world, TiledMap map, Rectangle bounds) {
		super(world, map, bounds);

		fixture.setUserData(this);
		setCategoryFilter(GravitySpikesRevamped.SPIKE_BIT);
	}

	@Override
	public void onBodyHit() {
		setDeadPlayer(GravitySpikesRevamped.DEADPLAYER_BIT);

	}

	public static void setDeadPlayer(short deadplayerBit) {
		dead = deadplayerBit;
	}
}
