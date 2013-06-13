package com.edsoft.moondefender;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author b
 * 
 * Projectile should start moving and such only after fire is called.
 * Initial state should be set by constructor.
 * update should make it move.
 * draw should make it show.
 * isAlive should return true until projectile should no longer be drawn.
 *
 */
public interface Projectile {

	public abstract void fire();
	public abstract void update();
	public abstract void draw(SpriteBatch batch);
	public abstract boolean isAlive();
		
}
