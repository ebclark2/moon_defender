package com.edsoft.moondefender.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.edsoft.moondefender.Projectile;
import com.edsoft.moondefender.ScreenUtil;

public class Rocket implements Projectile {

	private static final float SIZE = .04f;
	
	private Texture texture;
	private Sprite sprite;
	
	private float xspeed;
	private float yspeed;
	private long lastUpdateTime;
	private float speed = .3f; //Percentage of screen per second

	public Rocket(float x, float y, float direction) {
		double rdir = Math.toRadians(direction+90f);
		this.xspeed = (float)Math.cos(rdir);
		this.yspeed = (float)Math.sin(rdir);
		
		this.texture = new Texture(Gdx.files.internal("data/Missile.png"));
		this.texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion cannonRegion = new TextureRegion(texture, 0, 0, 64, 64);
		this.sprite = new Sprite(cannonRegion);		
		this.sprite.setSize(SIZE * sprite.getWidth() / sprite.getHeight(), SIZE);
		this.sprite.setPosition(x - (sprite.getWidth()/2f), y);
		this.sprite.setOrigin(sprite.getWidth()/2f, 0f);
		this.sprite.setRotation(direction);		
	}

	@Override
	public void fire() {
		this.lastUpdateTime = System.currentTimeMillis();
	}

	@Override
	public void update() {		
		long now = System.currentTimeMillis();
		long delta = now - lastUpdateTime;
		if(delta > 0)
		{
			lastUpdateTime = now;
			float s = delta / 1000.0f;
			float x = this.sprite.getX() + (this.xspeed * this.speed * s);
			float y = this.sprite.getY() + (this.yspeed * this.speed * s);
			this.sprite.setPosition(x, y);
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		this.sprite.draw(batch);
	}

	@Override
	public boolean isAlive() {
		return ScreenUtil.instance().isOnScreen(this.sprite.getX(), this.sprite.getY());
	}
}
