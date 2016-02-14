package de.game.init;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import de.game.entity.Player;
import de.game.entity.Zombie;
import de.game.physics.HitResult;
import de.game.world.Level;
import de.game.world.LevelRenderer;

public class pixel implements Runnable {
	  @SuppressWarnings("unused")
	private static final boolean FULLSCREEN_MODE = false;
	  private static int width;
	  private static int height;
	  private static FloatBuffer fogColor = BufferUtils.createFloatBuffer(4);
	  @SuppressWarnings("unused")
	private static Timer timer = new Timer(60.0F);
	  private static Level level;
	  private static LevelRenderer levelRenderer;
	  private static Player player;
	  @SuppressWarnings({ "unchecked", "rawtypes" })
	private static ArrayList<Zombie> zombies = new ArrayList();
	  
	  public static void init()
	    throws LWJGLException, IOException
	  {
	    int col = 920330;
	    float fr = 0.5F;
	    float fg = 0.8F;
	    float fb = 1.0F;
	    fogColor.put(new float[] { (col >> 16 & 0xFF) / 255.0F, (col >> 8 & 0xFF) / 255.0F, (col & 0xFF) / 255.0F, 1.0F });
	    fogColor.flip();
	    
	    Display.setDisplayMode(new DisplayMode(1024, 768));
	    
	    Display.create();
	    Keyboard.create();
	    Mouse.create();
	    
	    width = Display.getDisplayMode().getWidth();
	    height = Display.getDisplayMode().getHeight();
	    
	    GL11.glEnable(3553);
	    GL11.glShadeModel(7425);
	    GL11.glClearColor(fr, fg, fb, 0.0F);
	    GL11.glClearDepth(1.0D);
	    GL11.glEnable(2929);
	    GL11.glDepthFunc(515);
	    
	    GL11.glMatrixMode(5889);
	    GL11.glLoadIdentity();
	    GL11.glMatrixMode(5888);
	    
	    level = new Level(256, 256, 64);
	    levelRenderer = new LevelRenderer(level);
	    player = new Player(level);
	    
	    Mouse.setGrabbed(true);
	    for (int i = 0; i < 100; i++) {
	      zombies.add(new Zombie(level, 128.0F, 0.0F, 128.0F));
	    }
	  }
	  
	  public void destroy()
	  {
	    level.save();
	    
	    Mouse.destroy();
	    Keyboard.destroy();
	    Display.destroy();
	  }
	  
	  public void run(){}
	  
	  public void tick()
	  {
	    for (int i = 0; i < zombies.size(); i++) {
	      ((Zombie)zombies.get(i)).tick();
	    }
	    player.tick();
	  }
	  
	  private void moveCameraToPlayer(float a)
	  {
	    GL11.glTranslatef(0.0F, 0.0F, -0.3F);
	    GL11.glRotatef(player.xRot, 1.0F, 0.0F, 0.0F);
	    GL11.glRotatef(player.yRot, 0.0F, 1.0F, 0.0F);
	    
	    float x = player.xo + (player.x - player.xo) * a;
	    float y = player.yo + (player.y - player.yo) * a;
	    float z = player.zo + (player.z - player.zo) * a;
	    GL11.glTranslatef(-x, -y, -z);
	  }
	  
	  private void setupCamera(float a)
	  {
	    GL11.glMatrixMode(5889);
	    GL11.glLoadIdentity();
	    GLU.gluPerspective(70.0F, width / height, 0.05F, 1000.0F);
	    GL11.glMatrixMode(5888);
	    GL11.glLoadIdentity();
	    moveCameraToPlayer(a);
	  }
	  
	  private IntBuffer viewportBuffer = BufferUtils.createIntBuffer(16);
	  
	  private void setupPickCamera(float a, int x, int y)
	  {
	    GL11.glMatrixMode(5889);
	    GL11.glLoadIdentity();
	    this.viewportBuffer.clear();
	    GL11.glGetInteger(2978, this.viewportBuffer);
	    this.viewportBuffer.flip();
	    this.viewportBuffer.limit(16);
	    GLU.gluPickMatrix(x, y, 5.0F, 5.0F, this.viewportBuffer);
	    GLU.gluPerspective(70.0F, width / height, 0.05F, 1000.0F);
	    GL11.glMatrixMode(5888);
	    GL11.glLoadIdentity();
	    moveCameraToPlayer(a);
	  }
	  
	  private IntBuffer selectBuffer = BufferUtils.createIntBuffer(2000);
	  private HitResult hitResult = null;
	  
	  private void pick(float a)
	  {
	    this.selectBuffer.clear();
	    GL11.glSelectBuffer(this.selectBuffer);
	    GL11.glRenderMode(7170);
	    setupPickCamera(a, width / 2, height / 2);
	    levelRenderer.pick(player);
	    int hits = GL11.glRenderMode(7168);
	    this.selectBuffer.flip();
	    this.selectBuffer.limit(this.selectBuffer.capacity());
	    
	    long closest = 0L;
	    int[] names = new int[10];
	    int hitNameCount = 0;
	    for (int i = 0; i < hits; i++)
	    {
	      int nameCount = this.selectBuffer.get();
	      long minZ = this.selectBuffer.get();
	      this.selectBuffer.get();
	      
	      long dist = minZ;
	      if ((dist < closest) || (i == 0))
	      {
	        closest = dist;
	        hitNameCount = nameCount;
	        for (int j = 0; j < nameCount; j++) {
	          names[j] = this.selectBuffer.get();
	        }
	      }
	      else
	      {
	        for (int j = 0; j < nameCount; j++) {
	          this.selectBuffer.get();
	        }
	      }
	    }
	    if (hitNameCount > 0) {
	      this.hitResult = new HitResult(names[0], names[1], names[2], names[3], names[4]);
	    } else {
	      this.hitResult = null;
	    }
	  }
	  
	  public void render(float a)
	  {
	    float xo = Mouse.getDX();
	    float yo = Mouse.getDY();
	    player.turn(xo, yo);
	    pick(a);
	    while (Mouse.next())
	    {
	      if ((Mouse.getEventButton() == 1) && (Mouse.getEventButtonState())) {
	        if (this.hitResult != null) {
	          level.setTile(this.hitResult.x, this.hitResult.y, this.hitResult.z, 0);
	        }
	      }
	      if ((Mouse.getEventButton() == 0) && (Mouse.getEventButtonState())) {
	        if (this.hitResult != null)
	        {
	          int x = this.hitResult.x;
	          int y = this.hitResult.y;
	          int z = this.hitResult.z;
	          if (this.hitResult.f == 0) {
	            y--;
	          }
	          if (this.hitResult.f == 1) {
	            y++;
	          }
	          if (this.hitResult.f == 2) {
	            z--;
	          }
	          if (this.hitResult.f == 3) {
	            z++;
	          }
	          if (this.hitResult.f == 4) {
	            x--;
	          }
	          if (this.hitResult.f == 5) {
	            x++;
	          }
	          level.setTile(x, y, z, 1);
	        }
	      }
	    }
	    while (Keyboard.next()) {
	      if ((Keyboard.getEventKey() == 28) && (Keyboard.getEventKeyState())) {
	        level.save();
	      }
	    }
	    GL11.glClear(16640);
	    setupCamera(a);
	    
	    GL11.glEnable(2884);
	    GL11.glEnable(2912);
	    GL11.glFogi(2917, 2048);
	    GL11.glFogf(2914, 0.2F);
	    GL11.glFog(2918, fogColor);
	    
	    GL11.glDisable(2912);
	    levelRenderer.render(player, 0);
	    for (int i = 0; i < zombies.size(); i++) {
	      ((Zombie)zombies.get(i)).render(a);
	    }
	    GL11.glEnable(2912);
	    levelRenderer.render(player, 1);
	    GL11.glDisable(3553);
	    if (this.hitResult != null) {
	      levelRenderer.renderHit(hitResult);
	    }
	    new Cube(0, 0);
	    GL11.glDisable(2912);
	    
	    Display.update();
	  }
	  
	  public static void checkError()
	  {
	    int e = GL11.glGetError();
	    if (e != 0) {
	      throw new IllegalStateException(GLU.gluErrorString(e));
	    }
	  }
	  
	  public static void main(String[] args)
	    throws LWJGLException, IOException
	  {
	    init();
	  }
}
