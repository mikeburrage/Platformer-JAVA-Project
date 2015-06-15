import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameLevels {

	private int currentLvl;
	private String lvl;
	
	public GameLevels(ArrayList<Block> blockArray, int lvlCounter) {
		
		String line;
		String currentLine = "";
		ArrayList<String> lines = new ArrayList<String>();	
		
		int row = 1;
		int col = 1;
		
		try {
			/*
			 * 
			 * reads in the file and adds each line into an Arraylist
			 * 
			 */
			
			switch(lvlCounter) {
			
				case 1:
				
					currentLvl = 1;
					break;
			
				case 2:
				
					currentLvl = 2;
					break;	
			}
			
			lvl = "map"+ currentLvl + ".txt"; //creates the map name used when searching for a level
			
			FileInputStream reader = new FileInputStream("maps/"+lvl);
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
