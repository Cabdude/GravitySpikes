package com.caleb.entities;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.caleb.gspikes.GravitySpikesRevamped;

public abstract class ExitPortalTileObject {

	protected World world;
	protected TiledMap map;
	protected Ellipse elipse;
	protected Body body;
	protected Fixture fixture;

	public ExitPortalTileObject(World world, TiledMap map, Ellipse elip) {
		this.world = world;
		this.map = map;
		this.elipse = elip;

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		bdef.type = BodyDef.BodyType.StaticBody;
		bdef.position.set((elipse.x + elipse.width / 2) / GravitySpikesRevamped.PPM,
				(elipse.y + elipse.height / 2) / GravitySpikesRevamped.PPM);

		body = world.createBody(bdef);
		shape.setRadius(elipse.width / 2 / GravitySpikesRevamped.PPM);

		fdef.shape = shape;
		fdef.isSensor = true;
		body.createFixture(fdef);

		fixture = body.createFixture(fdef);

	}

	public abstract void onBodyHit();

}
