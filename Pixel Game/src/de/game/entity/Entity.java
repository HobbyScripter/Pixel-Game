package de.game.entity;

import java.util.List;

import de.game.physics.AABB;
import de.game.world.Level;

public class Entity {
	  private Level level;
	  public float xo;
	  public float yo;
	  public float zo;
	  public float x;
	  public float y;
	  public float z;
	  public float xd;
	  public float yd;
	  public float zd;
	  public float yRot;
	  public float xRot;
	  public AABB bb;
	  public boolean onGround = false;
	  protected float heightOffset = 0.0F;
	  
	  public Entity(Level level)
	  {
	    this.level = level;
	    resetPos();
	  }
	  
	  protected void resetPos()
	  {
	    float x = (float)Math.random() * this.level.width;
	    float y = this.level.depth + 10;
	    float z = (float)Math.random() * this.level.height;
	    setPos(x, y, z);
	  }
	  
	  private void setPos(float x, float y, float z)
	  {
	    this.x = x;
	    this.y = y;
	    this.z = z;
	    float w = 0.3F;
	    float h = 0.9F;
	    this.bb = new AABB(x - w, y - h, z - w, x + w, y + h, z + w);
	  }
	  
	  public void turn(float xo, float yo)
	  {
	    this.yRot = ((float)(this.yRot + xo * 0.15D));
	    this.xRot = ((float)(this.xRot - yo * 0.15D));
	    if (this.xRot < -90.0F) {
	      this.xRot = -90.0F;
	    }
	    if (this.xRot > 90.0F) {
	      this.xRot = 90.0F;
	    }
	  }
	  
	  public void tick()
	  {
	    this.xo = this.x;
	    this.yo = this.y;
	    this.zo = this.z;
	  }
	  
	  public void move(float xa, float ya, float za)
	  {
	    float xaOrg = xa;
	    float yaOrg = ya;
	    float zaOrg = za;
	    
	    List<AABB> aABBs = this.level.getCubes(this.bb.expand(xa, ya, za));
	    for (int i = 0; i < aABBs.size(); i++) {
	      ya = ((AABB)aABBs.get(i)).clipYCollide(this.bb, ya);
	    }
	    this.bb.move(0.0F, ya, 0.0F);
	    for (int i = 0; i < aABBs.size(); i++) {
	      xa = ((AABB)aABBs.get(i)).clipXCollide(this.bb, xa);
	    }
	    this.bb.move(xa, 0.0F, 0.0F);
	    for (int i = 0; i < aABBs.size(); i++) {
	      za = ((AABB)aABBs.get(i)).clipZCollide(this.bb, za);
	    }
	    this.bb.move(0.0F, 0.0F, za);
	    
	    this.onGround = ((yaOrg != ya) && (yaOrg < 0.0F));
	    if (xaOrg != xa) {
	      this.xd = 0.0F;
	    }
	    if (yaOrg != ya) {
	      this.yd = 0.0F;
	    }
	    if (zaOrg != za) {
	      this.zd = 0.0F;
	    }
	    this.x = ((this.bb.x0 + this.bb.x1) / 2.0F);
	    this.y = (this.bb.y0 + this.heightOffset);
	    this.z = ((this.bb.z0 + this.bb.z1) / 2.0F);
	  }
	  
	  public void moveRelative(float xa, float za, float speed)
	  {
	    float dist = xa * xa + za * za;
	    if (dist < 0.01F) {
	      return;
	    }
	    dist = speed / (float)Math.sqrt(dist);
	    xa *= dist;
	    za *= dist;
	    
	    float sin = (float)Math.sin(this.yRot * 3.141592653589793D / 180.0D);
	    float cos = (float)Math.cos(this.yRot * 3.141592653589793D / 180.0D);
	    
	    this.xd += xa * cos - za * sin;
	    this.zd += za * cos + xa * sin;
	  }
}
