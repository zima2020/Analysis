import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BarrenLandAnalysisMain {
	// Global Field object.
	private static Field myField = new Field();
	public static void main(String[] args) throws IOException {
		// Create and initialize the field
		myField.zeroMatrix();
		
		// Gather and parse user input
		System.out.println("Please enter a set of four numbers, or enter 'q' to exit: ");
		// Gather input until we've received valid input.
		while(!gatherInput());
	
		// Calculate arable land sections
		myField.calculateArableLand();
		
		// Print results to the console.
		myField.printGrid();

		System.exit(0);
	}
	
	
	public static boolean gatherInput() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String stop  =  "q";
		if(s.equals(stop)){
			System.out.println("System will now exit.");
			System.exit(0);
		}
		String[] parts = s.split(",");
	    for(int i = 0; i < parts.length; i++){
	    	String[] numbers = parts[i].replaceAll("[^0-9]+", " ").trim().split(" ");
	    	if(numbers.length == 4){
	    		myField.setBarren(new Point(Integer.parseInt(numbers[0]),  Integer.parseInt(numbers[1])), 
	    						  new Point(Integer.parseInt(numbers[2]),  Integer.parseInt(numbers[3])));
	    	} else {
	    		System.out.println("ERROR: " + Arrays.toString(numbers) + " needs to have exactly 4 numbers." + '\n' +
	    							"Enter in a valid set of numbers, or enter 'q' to exit.");
	    		return false;
	    	}
	    }
	    return true;
	}		
}

