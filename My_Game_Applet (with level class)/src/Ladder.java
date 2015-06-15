import java.awt.Color;
import java.awt.Graphics;

public class Ladder extends Block  {

	public Ladder(int x, int y) {
		
		super(x, y);
	}
	
	public void blockCollision(Player p) {
		
		super.blockCollision(p);
		
		//int playerX = p.getX();
		//int playerY = p.getY();
		//int playerWidth = p.getWidth();
		//int playerHeight = p.getHeight();
		
		
	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.RED);
		super.paint(g);
	}
}