package com.caleb.entities;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.caleb.gspikes.GravitySpikesRevamped;

public class Ground extends InteractiveTileObject {

	public Ground(World world, TiledMap levels, Rectangle rect) {

		super(world, levels, rect);
		fixture.setUserData(this);
		setCategoryFilter(GravitySpikesRevamped.GROUND_BIT);
	}

	@Override
	public void onBodyHit() {

	}
}
