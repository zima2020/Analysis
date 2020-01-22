import java.awt.Point;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Field {
	// Queue used to perform BFS
	private LinkedList<Point> queue = new LinkedList<Point>();
	// Horizontal bounds
	private int xLimit = 400;
	// Vertical bounds
	private int yLimit = 600;
	// 2D array representing the 400 X 600 grid.
	int landMap[][] = new int[xLimit][yLimit];
	// HashMap of arable land
	HashMap<Integer, Integer> arableLandMap = new HashMap<Integer, Integer>();
	
	public void zeroMatrix(){
		for(int i = 0; i < xLimit; i++){
			for(int j = 0; j < yLimit; j++){
				landMap[i][j] = 0;
			}
		}
	}
	public void setBarren(Point lowerLeft, Point upperRight){
		for(int i = lowerLeft.x; i <= upperRight.x; i++){
			for(int j = lowerLeft.y; j <= upperRight.y; j++){
				// Traverse the entire area of the rectangle, and mark it 1 for barren.
				landMap[i][j] = 1;
			}
		}
		
	}
	
	public void addAdjacent(int x, int y){
		if(x > 0){
			if(landMap[x-1][y] == 0){
				queue.add(new Point(x-1, y));
			}
		}
		if(x < (xLimit - 1)){
			if(landMap[x+1][y] == 0){
				queue.add(new Point(x+1, y));
			}
		}
		if(y > 0){
			if(landMap[x][y-1] == 0){
				queue.add(new Point(x, y-1));
			}
		}
		if(y < (yLimit - 1)){
			if(landMap[x][y+1] == 0){
				queue.add(new Point(x, y+1));
			}
		}
		
	}
	
	public void  calculateArableLand(){
		int arableLand = 1;
		int i = 0;
		int j = 0;
		
		while(i < xLimit && j < yLimit){
			if(queue.isEmpty()){
				Point coordinate = new Point(i,j);
				if(landMap[i][j] == 0){
					arableLand++;
					arableLandMap.put(arableLand, 0);
					queue.add(coordinate);
				}
				if(i == (xLimit - 1)){
					// Move to the next row
					i = 0;
					j++;
				} else {
					// Check the next x coordinate
					i++;
				}
			}
			if (!queue.isEmpty()){
				Point arrableCoordinate = queue.pop();
				int x = arrableCoordinate.x;
				int y = arrableCoordinate.y;
				
				if(landMap[x][y] == 0){
					// Add adjacent points to the queue to be checked.
					addAdjacent(x,y);
					// Mark this location as checked.
					landMap[x][y] = arableLand;
					// increment the value of this section of arable land.
					arableLandMap.put(arableLand, arableLandMap.get(arableLand) + 1);		
				}
			}
	
		}
	}
	
	public void printGrid(){
		int i = 0 ;
		int[] arableLandPieces = new int[arableLandMap.values().size()];
		for (HashMap.Entry<Integer, Integer> entry : arableLandMap.entrySet()){
			try{
				arableLandPieces[i] = entry.getValue();
				i++;
			} catch(ArrayIndexOutOfBoundsException e){
				// do nothing.
			}
		}
		Arrays.sort(arableLandPieces);
		String output = Arrays.toString(arableLandPieces).replaceAll("\\[|\\]|,", " ");
		System.out.println(output);
	}
		
}

