package com.caleb.entities;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.World;

public class EnterPortal extends EnterPortalTileObject {

	public EnterPortal(World world, TiledMap map, Ellipse elip) {
		super(world, map, elip);
	}

}