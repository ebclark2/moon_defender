package com.edsoft.moondefender;

import com.badlogic.gdx.Gdx;

public class ScreenUtil {
	
	private static ScreenUtil INSTANCE = null;
	
	private float w;
	private float h;
	private float s;
	private float hs;
	
	//scaled width and heights
	private float sw;
	private float hsw;
	private float sh;
	private float hsh;
	
	private ScreenUtil() {
		this.w = Gdx.graphics.getWidth();
		this.h = Gdx.graphics.getHeight();
		this.s = h/w;
		this.hs = s/2f;
		
		this.sw = 1f;
		this.hsw = .5f;
		this.sh = this.s;
		this.hsh = this.hs;
	}
	
	public static ScreenUtil instance() {
		if(INSTANCE == null) {
			ScreenUtil.refresh();
		}
		return ScreenUtil.INSTANCE;
	}
	
	public static void refresh() {
		ScreenUtil.INSTANCE = new ScreenUtil();
	}
	
	public boolean isOnScreen(float x, float y) {
		return x <= this.hsw && x >= -this.hsw &&
				y <= this.hsh && y >= -this.hsh;
	}


}
