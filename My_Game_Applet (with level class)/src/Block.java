import java.awt.Graphics;

public class Block {

	private int y;
	private int x;
	private int width;
	private int height;
	
	public Block(int x, int y) {
		
		setX(x);
		setY(y);
		setWidth(30);
		setHeight(30);
	}
	
	public void setY(int y) {
		
		this.y = y;
	}

	public int getY() {
		
		return y;
	}

	public void setX(int x) {
		
		this.x = x;
	}
	
	public int getX() {
		
		return x;
	}

	public void setHeight(int height) {
		
		this.height = height;
	}
	
	public int getHeight() {
		
		return height;
	}
	
	public void setWidth(int width) {
		
		this.width = width;
	}
	
	public int getWidth() {
		
		return width;
	}
	
	public void update(Player p) {
		
		blockCollision(p);
		//blockAction(p);
	}
	
	public void blockCollision(Player p) {
		
	}
	
	//public void blockAction(Player p) { 
	
	//}
	
	public void paint(Graphics g) {
		
		g.drawRect(x, y, width, height);
	}
}