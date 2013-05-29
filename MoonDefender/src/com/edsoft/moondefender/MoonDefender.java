package com.edsoft.moondefender;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Logger;

public class MoonDefender implements ApplicationListener {
	
	private static final Logger logger = new Logger("MoonDefender");
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	
	private Texture cannonTexture;
	private Sprite cannonSprite;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		float s = h/w;
		float hs = s/2f;
		
		camera = new OrthographicCamera(1, s);
		batch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("data/background.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region = new TextureRegion(texture, 0, 0, 1024, 1024);
		
		sprite = new Sprite(region);
		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		
		cannonTexture = new Texture(Gdx.files.internal("data/cannon.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion cannonRegion = new TextureRegion(cannonTexture, 0, 0, 64, 128);
		cannonSprite = new Sprite(cannonRegion);
		cannonSprite.setSize(.1f * cannonSprite.getWidth() / cannonSprite.getHeight(), .1f);
		cannonSprite.setPosition(0, -hs);
				
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.draw(batch);
		cannonSprite.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
