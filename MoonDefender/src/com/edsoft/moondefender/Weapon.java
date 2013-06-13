package com.edsoft.moondefender;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Weapon {
	
	private final Set<Projectile> projectiles = new HashSet<Projectile>();
	private final List<Projectile> expiredProjectiles = new LinkedList<Projectile>();

	public void fire(Projectile projectile) {
		this.projectiles.add(projectile);
		projectile.fire();
	}
	
	public void draw(SpriteBatch batch) {
		if(!this.expiredProjectiles.isEmpty()) {
			for(Projectile expiredProjectile : this.expiredProjectiles) {
				this.projectiles.remove(expiredProjectile);
			}
		}
		
		for(Projectile projectile : this.projectiles) {
			if(projectile.isAlive()) {
				projectile.update();
				projectile.draw(batch);
			} else {
				this.expiredProjectiles.add(projectile);
			}
		}
	}
	
	public int getProjectileCount() {
		return this.projectiles.size();
	}
}
