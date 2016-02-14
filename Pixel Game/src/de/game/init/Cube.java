package de.game.init;

import org.lwjgl.opengl.GL11;

public class Cube {

	int i;
	
	public Cube(int num){
		i = num;
	}

	public void Render(int textureID){
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glNormal3f(0.0f, 0.0f, 1.0f);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(-1.0f, -1.0f, 1.0f);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex3f(1.0f, -1.0f, 1.0f);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(1.0f, 1.0f, 1.0f);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
		
		GL11.glNormal3f(0.0f, 0.0f, -0.0f);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(-1.0f, 1.0f, -1.0f);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(1.0f, 1.0f, -1.0f);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(1.0f, -1.0f, -1.0f);
		
		GL11.glNormal3f(0.0f, 1.0f, 0.0f);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(-1.0f, 1.0f, -1.0f);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex3f(1.0f, 1.0f, 1.0f);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(1.0f, 1.0f, -1.0f);
		
		GL11.glNormal3f(0.0f, -1.0f, 0.0f);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(1.0f, -1.0f, -1.0f);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(1.0f, -1.0f, 1.0f);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex3f(-1.0f, -1.0f, 1.0f);

		GL11.glNormal3f(1.0f, 0.0f, 0.0f);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex3f(1.0f, -1.0f, -1.0f);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(1.0f, 1.0f, -1.0f);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(1.0f, 1.0f, 1.0f);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(1.0f, -1.0f, 1.0f);

		GL11.glNormal3f(-1.0f, 0.0f, 0.0f);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex3f(-1.0f, -1.0f, 1.0f);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(-1.0f, 1.0f, -1.0f);
		GL11.glEnd();
	}
}
