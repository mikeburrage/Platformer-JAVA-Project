import java.awt.Color;
import java.awt.Graphics;


public class CastleBlock extends Block {

	public CastleBlock(int x, int y) {
		
		super(x, y);
	}
	
	/*
	
	public void blockCollision(Player p) {
		
		super.blockCollision(p);
		
	}
	
	public void blockAction(Player p) {
		
		super.blockAction(p);
	}
	
	*/
	
	public void paint(Graphics g) {
		
		g.setColor(Color.YELLOW);
		g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
		super.paint(g);
	}
}
