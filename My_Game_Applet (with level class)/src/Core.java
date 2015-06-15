import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Core extends Applet implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	private Graphics gBuffer;
	private Image i;
	private Player p;
	private GameLevels levels; //level class variable
	
	//--- Scrolling variables ---
	private int scrollSpeedX = 0;
	private int xpos;
	private int platX; //scrolling for the platforms
	
	private ArrayList<Block> blockArray = new ArrayList<Block>();
	private int lvlCounter = 1;
	
	
	public void init() {
		
		//readMap();	//Calls the method that reads from a text file
		
		setFocusable(true);
		addKeyListener(this);
		setSize(980, 601);
	}
	
	public void start() {
		
		levels = new GameLevels(blockArray, lvlCounter); //creates a game level variable and creates the level
		p = new Player(20, 100);

		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
	
		while(true) {
			
			p.update(this);
			
			/*
			 * 
			 * goes through the block arraylist and checks for collisions
			 * 
			 */
			
			if(!blockArray.isEmpty()) {
			
				for(int i = 0; i < blockArray.size(); i++) {
					
					blockArray.get(i).update(p);
				}
			}
			
			if(scrollSpeedX != 0) {
				
				scrollGameX();
			}
			
			repaint();
			
			try {
				
				Thread.sleep(15);
			
			} catch (InterruptedException e) { 
				
			}
		}
	}

	public void paint(Graphics g) {
		
		if(p.getAlive()) {
				
			/*
			 * 
			 * goes through the block arraylist and paints the blocks
			 * 
			 */
			
			if(!blockArray.isEmpty()) {
			
				for(int i = 0; i < blockArray.size(); i++) {
				
					blockArray.get(i).paint(g);
				}
			}
			
			p.paint(g);	
		}
		else {
			
			Font font = new Font("TimesRoman", Font.BOLD, 30);
			
			g.setFont(font);
			g.drawString("Game Over", 450, 250);
		}
	}
	
	/*
     * 
     * moves the x coordinates of the image
     * 
     */
	
	public void scrollGameX() {
			
		if(!blockArray.isEmpty()) {	
				
			for(int i = 0; i < blockArray.size(); i++) {
				
				platX = blockArray.get(i).getX();
				
				platX += scrollSpeedX;
				
				blockArray.get(i).setX(platX);
			}
		}
	}
	
	public void rightSideXScrolling() {
		
		xpos = p.getX(); 

		if(xpos > 700) {
		
			scrollSpeedX = -7;
		}
	}

	public void leftSideXScrolling() {
		
		xpos = p.getX(); 

		if(xpos < 280) {
					 
			 scrollSpeedX = 7;
		}
	}
	
	public void releaseXScrolling() {
		
		scrollSpeedX = 0;
	}
	
	public void update(Graphics g) {
		
		if(i == null) {
			
			i = createImage(this.getSize().width, this.getSize().height);
			gBuffer = i.getGraphics();
		}
		
		gBuffer.setColor(getBackground());
		gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		gBuffer.setColor(getForeground());
		paint(gBuffer);
		
		g.drawImage(i, 0, 0, this);
	}

	public void keyPressed(KeyEvent pressEvent) {
		
		switch(pressEvent.getKeyCode()) {
		
			case KeyEvent.VK_LEFT:
				
				p.moveLeft();
				leftSideXScrolling();
				
				break;
			
			case KeyEvent.VK_RIGHT:
				
				p.moveRight();
				rightSideXScrolling();
				
				break;
				
			case KeyEvent.VK_A:
				
			if(p.isOnGround()) {
					
				p.playerJump();
				break;
			}
		}
	}
	
	public void keyReleased(KeyEvent releaseEvent) {
		
		switch(releaseEvent.getKeyCode()) {
		
		case KeyEvent.VK_LEFT:
			
			p.releaseLeft();
			releaseXScrolling();
			
			break;
		
		case KeyEvent.VK_RIGHT:
			
			p.releaseRight();
			releaseXScrolling();
			
			break;
			
		case KeyEvent.VK_A:
			
			p.releaseUp();
			break;
		}
	}

	public void keyTyped(KeyEvent typedEvent) {
		
	}
	
	/*
	 * 
	 * The method that reads from the text file and creates
	 * the Block objects and adds them to the Arraylist
	 * 
	 */
	  
	public void readMap() {
		
		String line;
		String currentLine = "";
		ArrayList<String> lines = new ArrayList<String>();	
		
		int row = 1;
		int col = 1;
		
		/*
		 * 
		 * reads in the file and adds each line into an Arraylist
		 * 
		 */
	
		try {
	
			FileInputStream reader = new FileInputStream("maps/map1.txt");
			DataInputStream in = new DataInputStream(reader);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			while((line = br.readLine()) != null) {
				
				lines.add(line);
			}
			
			in.close(); //closes the file
		}
		catch(Exception e) {

		}
		
		/*
		 * 
		 * section that handles the types of blocks from the file
		 * 
		 * i is the counter for the rows
		 * k is the counter for the columns
		 * 
		 */
	
		for(int i = 0; i < lines.size(); i++) {
			
			currentLine = lines.get(i);
			col = 0;
			
			for(int k = 0; k < currentLine.length(); k++) {
				
				/*
				 * 
				 * if a section of the current line matches an "A"
				 * it will create an new Block object and add it to
				 * the block Arraylist
				 * 
				 */

				if(currentLine.substring(k, k+1).matches("A")) {
					
					blockArray.add(new GroundBlock(col*30, row*30));
				}

				if(currentLine.substring(k, k+1).matches("C")) {
					
					blockArray.add(new CastleBlock(col*30, row*30));
				}
				
				if(currentLine.substring(k, k+1).matches("E")) {
					
					blockArray.add(new ExitBlock(col*30, row*30));
				}
				col++;
			}
			row++;
		}
	}
}