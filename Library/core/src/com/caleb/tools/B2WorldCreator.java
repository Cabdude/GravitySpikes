package com.caleb.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.caleb.entities.EnterPortal;
import com.caleb.entities.ExitPortal;
import com.caleb.entities.Ground;
import com.caleb.entities.Spikes;

public class B2WorldCreator {

	public B2WorldCreator(World world, TiledMap levels) {
		// Ground Collision

		for (MapObject object : levels.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();

			new Ground(world, levels, rect);
		}

		// Spike Collision

		for (MapObject object : levels.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Spikes(world, levels, rect);
		}

		// Entrance Portal

		for (MapObject object : levels.getLayers().get(4).getObjects().getByType(EllipseMapObject.class)) {
			Ellipse elip = ((EllipseMapObject) object).getEllipse();
			new EnterPortal(world, levels, elip);
		}

		// Exit portal

		for (MapObject object : levels.getLayers().get(5).getObjects().getByType(EllipseMapObject.class)) {
			Ellipse elip = ((EllipseMapObject) object).getEllipse();
			new ExitPortal(world, levels, elip);
		}

	}

}
