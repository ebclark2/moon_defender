package com.edsoft.moondefender;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;

public class Cannon implements InputProcessor {

	private static final Logger logger = new Logger("Cannon");
	
	private Texture texture;
	private Sprite sprite;
	
	public Cannon() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		float s = h/w;
		float hs = s/2f;

		texture = new Texture(Gdx.files.internal("data/cannon.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion cannonRegion = new TextureRegion(texture, 0, 0, 64, 128);
		sprite = new Sprite(cannonRegion);		
		sprite.setSize(.1f * sprite.getWidth() / sprite.getHeight(), .1f);
		sprite.setPosition(0 - (sprite.getWidth()/2f), -hs);
		sprite.setOrigin(sprite.getWidth()/2f, 0f);
		sprite.setRotation(45f);		
	}
	
	public void draw(SpriteBatch batch) {
		batch.enableBlending();
		sprite.draw(batch);
	}
	
	private void pointTo(int screenX, int screenY) {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		float s = h/w;
		float hs = s/2f;
		float x = (screenX / w) - .5f;
		float y = hs - ((screenY / h) * s);		
		Vector2 pos = new Vector2(sprite.getX() + sprite.getOriginX(), sprite.getY() + sprite.getOriginY());		
		Vector2 point = new Vector2(x, y);
		logger.error("Pos: " + pos + ", Point: " + point);
		Vector2 dir = point.sub(pos).nor();
		Vector2 up = new Vector2(0, 1);
		logger.error("Dir: " + dir + ", Up: " + up);
		double angle = Math.toDegrees(Math.acos(dir.dot(up)));		
		logger.error("Angle: " + angle);
		if(x > 0)
			angle = -angle;
		sprite.setRotation((float)angle);
		logger.error("Angle: " + angle);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		this.pointTo(screenX, screenY);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		this.pointTo(screenX, screenY);
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
