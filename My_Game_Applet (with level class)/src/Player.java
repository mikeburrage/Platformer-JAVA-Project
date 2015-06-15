import java.awt.Color;
import java.awt.Graphics;

public class Player {

	private int x, y;
	private int width = 30;
	private int height = 30;
	
	private boolean alive = true;
	private boolean isOnGround;
	
	private double vx = 0;
	private double vy = 0;
	private double xAcc = 0;
	private double yAcc = 0;
	private double friction;
	private double gravity = 0.8;
	private double jumpForce = -16;
	private double runningSpeed = 7;
	private double acc = 1.5;
	
	public Player(int startingX, int startingY) {
		
		x = startingX;
		y = startingY;
	}
	
	public void update(Core c) {
		
		playerMovement();
		checkFrame(c);	
	}
	
	/*
	 * 
	 * Player movement section
	 * 
	 */
	
	public void moveRight() {
		
		friction = 1;
		xAcc = acc;
	}
	
	public void moveLeft() {

		friction = 1;
		xAcc = -acc;
	}
	
	public void playerJump() {
		
		yAcc = jumpForce;
		isOnGround = false;
	}
	
	public void releaseLeft() {
		
		friction = 0.60;
		xAcc = 0;
		
		if(vx < 0.1) {
			
			vx = 0;
		}
	}

	public void releaseRight() {
		
		friction = 0.60;
		xAcc = 0;
	}

	public void releaseUp() {
		
		yAcc = 0;
		
		if(vy < 0.1) {
			
			vy = 0;
		}
	}

	/*
	 * 
	 * Other methods such as frame collision and painting
	 * 
	 */
	public void checkFrame(Core c) {
			
		/*
		 * Stops the player sprite from going off the
		 * left hand side of the screen
		 * Stops the player sprite from going off the
		 * right hand side of the screen
		 */
		if(x < 0) {
			
			vx = 0;
			x = 0;
		}
		else if(x + width > c.getWidth()) {
			
			vx = 0;
			x = c.getWidth() - width - 1;
		}
		
		/*
		 * Stops the player sprite from going past the
		 * bottom part of the screen
		 * Stops the player sprite from going higher than
		 * the top part of the screen
		 */
		if(y < 0) {
			
			vy = 0;
			y = 0;
		}
		else if(y + height > c.getHeight() + 30) {
		
		//else if(y + height > c.getHeight() -1) {	
		
			//vy = 0;
			//y = c.getHeight() - height - 1;
			//isOnGround = true;
			
			setAlive(false);
		}
	}
	
	public void playerMovement() {
		
		vx += xAcc;
		
		if(vx > runningSpeed) {
					
			vx = runningSpeed;
		}
				
		if(vx < -runningSpeed) {
			
			vx = -runningSpeed;
		}
				
		vy += yAcc;

		if(vy > runningSpeed *3) {
					
			vy = runningSpeed *3;
		}
				
		if(isOnGround) {
					
			vx *= friction;
		}
		 
		vy += gravity;
		
		x += vx;
		y += vy;
				
		if(!isOnGround) {
					
			yAcc = 0;
		}
				
		if(vy >= 0) {
					
			isOnGround = false;
		}
	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		g.drawRect(x, y, width, height);
	}
	
	/*
	 * 
	 * The setters and getters section
	 * 
	 */
	
	/*
	 * 
	 * The setters 
	 * 
	 */
	
	public void setX(int x) {
		
		this.x = x;
	}

	public void setY(int y) {
		
		this.y = y;
	}
	
	public void setWidth(int width) {
		
		this.width = width;
	}

	public void setHeight(int height) {

		this.height = height;
	}

	public void setAlive(boolean alive) {
	
		this.alive = alive;
	}

	public void setOnGround(boolean isOnGround) {
	
		this.isOnGround = isOnGround;
	}

	public void setVx(double vx) {
	
		this.vx = vx;
	}

	public void setVy(double vy) {
	
		this.vy = vy;
	}

	public void setxAcc(double xAcc) {
	
		this.xAcc = xAcc;
	}

	public void setyAcc(double yAcc) {
	
		this.yAcc = yAcc;
	}

	public void setFriction(double friction) {
	
		this.friction = friction;
	}

	public void setGravity(double gravity) {
	
		this.gravity = gravity;
	}

	public void setJumpForce(double jumpForce) {
	
		this.jumpForce = jumpForce;
	}

	public void setRunningSpeed(double runningSpeed) {
	
		this.runningSpeed = runningSpeed;
	}

	public void setAcc(double acc) {
	
		this.acc = acc;
	}

	/*
	 * 
	 * The getters 
	 * 
	 */

	public int getX() {
		
		return x;
	}
	
	public int getY() {
		
		return y;
	}

	public int getWidth() {
		
		return width;
	}
	
	public int getHeight() {
		
		return height;
	}
	
	public boolean getAlive() {
		
		return alive;
	}
	
	public boolean isOnGround() {
		
		return isOnGround;
	}
	
	public double getVx() {
		
		return vx;
	}

	public double getVy() {
		
		return vy;
	}

	public double getxAcc() {
		
		return xAcc;
	}

	public double getyAcc() {
		
		return yAcc;
	}

	public double getFriction() {
		
		return friction;
	}

	public double getGravity() {
		
		return gravity;
	}

	public double getJumpForce() {
		
		return jumpForce;
	}

	public double getRunningSpeed() {
		
		return runningSpeed;
	}

	public double getAcc() {
		
		return acc;
	}
}