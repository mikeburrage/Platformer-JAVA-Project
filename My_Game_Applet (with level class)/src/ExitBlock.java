import java.awt.Color;
import java.awt.Graphics;

public class ExitBlock extends Block {

	public ExitBlock(int x, int y) {
		
		super(x, y);
	}
	
	public void blockCollision(Player p) {
		
		super.blockCollision(p);
		
		int playerX = p.getX();
		int playerY = p.getY();
		//int playerWidth = p.getWidth();
		//int playerHeight = p.getHeight();
		
		if(playerY == super.getY()) {
			
			if(playerX == super.getX()) {
				
				System.out.print("Exit level");
			}
		}
	}
	
	//public void blockAction(Player p) {
		
		//super.blockAction(p);
	//}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
		super.paint(g);
	}
}
