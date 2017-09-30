package com.caleb.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.caleb.entities.ExitPortalTileObject;
import com.caleb.entities.InteractiveTileObject;

public class WorldContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();

		if (fixA.getUserData() == "circleBody" || fixB.getUserData() == "circleBody") {
			Fixture circleBody = fixA.getUserData() == "circleBody" ? fixA : fixB;
			Fixture object = circleBody == fixA ? fixB : fixA;

			if (object.getUserData() instanceof InteractiveTileObject) {
				((InteractiveTileObject) object.getUserData()).onBodyHit();
			} else if (object.getUserData() instanceof ExitPortalTileObject) {
				((ExitPortalTileObject) object.getUserData()).onBodyHit();
			}
		}
	}

	@Override
	public void endContact(Contact contact) {

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

}
