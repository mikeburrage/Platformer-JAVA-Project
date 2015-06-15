import java.awt.Color;
import java.awt.Graphics;

public class GroundBlock extends Block  {

	public GroundBlock(int x, int y) {
		
		super(x, y);
	}
	
	public void blockCollision(Player p) {
		
		super.blockCollision(p);
		
		int playerX = p.getX();
		int playerY = p.getY();
		int playerWidth = p.getWidth();
		int playerHeight = p.getHeight();
		
		if(playerY + playerHeight > super.getY() && playerY + playerHeight < super.getY() + super.getHeight()) {
			
			if(playerX >= super.getX() - playerWidth && playerX < super.getX() + super.getWidth()) {
				
				p.setVy(0);
				p.setY((super.getY() - playerHeight));
				p.setOnGround(true);
			}
		}
	}
	
	//public void blockAction(Player p) {
		
		//super.blockAction(p);
	//}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.GREEN);
		g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
		super.paint(g);
	}
}