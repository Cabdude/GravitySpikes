package com.caleb.entities;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.World;
import com.caleb.gspikes.GravitySpikesRevamped;

public class ExitPortal extends ExitPortalTileObject {

	public static short win;

	public ExitPortal(World world, TiledMap map, Ellipse elip) {
		super(world, map, elip);
		fixture.setUserData(this);

	}

	@Override
	public void onBodyHit() {
		setPlayerToWin(GravitySpikesRevamped.EXIT_BIT);
	}

	public static void setPlayerToWin(short winBit) {
		win = winBit;
	}

}
