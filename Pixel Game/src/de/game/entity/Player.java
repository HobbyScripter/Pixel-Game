package de.game.entity;

import org.lwjgl.input.Keyboard;

import de.game.world.Level;

public class Player extends Entity {
	  public Player(Level level)
	  {
	    super(level);
	    this.heightOffset = 1.62F;
	  }
	  
	  public void tick()
	  {
	    this.xo = this.x;
	    this.yo = this.y;
	    this.zo = this.z;
	    float xa = 0.0F;
	    float ya = 0.0F;
	    if (Keyboard.isKeyDown(19)) {
	      resetPos();
	    }
	    if ((Keyboard.isKeyDown(200)) || (Keyboard.isKeyDown(17))) {
	      ya -= 1.0F;
	    }
	    if ((Keyboard.isKeyDown(208)) || (Keyboard.isKeyDown(31))) {
	      ya += 1.0F;
	    }
	    if ((Keyboard.isKeyDown(203)) || (Keyboard.isKeyDown(30))) {
	      xa -= 1.0F;
	    }
	    if ((Keyboard.isKeyDown(205)) || (Keyboard.isKeyDown(32))) {
	      xa += 1.0F;
	    }
	    if ((Keyboard.isKeyDown(57)) || (Keyboard.isKeyDown(219))) {
	      if (this.onGround) {
	        this.yd = 0.12F;
	      }
	    }
	    moveRelative(xa, ya, this.onGround ? 0.02F : 0.005F);
	    
	    this.yd = ((float)(this.yd - 0.005D));
	    move(this.xd, this.yd, this.zd);
	    this.xd *= 0.91F;
	    this.yd *= 0.98F;
	    this.zd *= 0.91F;
	    if (this.onGround)
	    {
	      this.xd *= 0.8F;
	      this.zd *= 0.8F;
	    }
	  }
}
