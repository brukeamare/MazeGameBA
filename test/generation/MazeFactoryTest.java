package generation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import generation.Order.Builder;

public class MazeFactoryTest {
	
//	MazeFactory damaze= new MazeFactory();
	
	public boolean IsThereExit(Maze yeahmaze, Floorplan dafloor ) {
		
		int num=0;
		for( int y=0; y < yeahmaze.getHeight(); y++) {
			if (yeahmaze.hasWall(0, y, CardinalDirection.West)==false){
				num++;
			}
			if (yeahmaze.hasWall(yeahmaze.getWidth()-1, y, CardinalDirection.East)==false){
				num++;
			}
		}
		
		for(int x=0; x< yeahmaze.getWidth(); x++) {
			if (yeahmaze.hasWall(x, 0, CardinalDirection.North)==false) {
				num++;
			}
			if(yeahmaze.hasWall(x, yeahmaze.getHeight()-1, CardinalDirection.South)==false){
				num++;
			}
		}
		if (num == 1) {
			return true;
		
		}
		return false;
	}
	
	public boolean Wheredaexit(Maze yeahmaze, Floorplan dafloor ) {
		int width= yeahmaze.getWidth();
		int height= yeahmaze.getHeight();
		
		for( int y=0; y< height ; y++) {
			for( int x=0; x< width ; x++) {
				int num= yeahmaze.getDistanceToExit(x, y);
				int bell=0;
				int i=x;
				int j=y;
				for (int z=0; z<num; z++) {
					
					int [] result =yeahmaze.getNeighborCloserToExit(i,j);
					if (result!=null) {
						bell++;
						i= result[0];
						j= result[1];
					}
				}
				if (yeahmaze.getDistanceToExit(i, j)!=1) {
					return false;
				}
				if (num!=bell+1) {
					return false;
				}

				
				
			}
		}
		return true;
	}
	public boolean IsItPurr(Maze yeahmaze, Floorplan dafloor, DefaultOrder daorder ) {
		int width= yeahmaze.getWidth();
		int height= yeahmaze.getHeight();
		if(!daorder.isPerfect()) {
			for( int y=0; y< height ; y++) {
				for( int x=0; x< width ; x++) {
					if(yeahmaze.isInRoom(x,y)) {
						return false;
					}
				}
			}
		}
		int internalwallscalc= (2*width*height)-width-height-((width*height)-1);
		int internalwallscount=0;
		for( int y=0; y< height ; y++) {
			for( int x=0; x< width ; x++) {
				if (yeahmaze.hasWall(x,y,CardinalDirection.South)) {
					Wallboard dawallboard = new Wallboard(x,y, CardinalDirection.South);
					if(!dafloor.isPartOfBorder(dawallboard)){
						internalwallscount++;
					}
				}
				if (yeahmaze.hasWall(x,y,CardinalDirection.East)) {
					Wallboard dawallboard = new Wallboard(x,y, CardinalDirection.East);
					if(!dafloor.isPartOfBorder(dawallboard)){
						internalwallscount++;
					}
				}
			}
		}
		if (internalwallscalc==internalwallscount) {
			return true;
		}
		return false;
	}
	
	
	@Test
	final void TestDefaultMaze() {
		DefaultOrder daorder= new DefaultOrder();
		MazeFactory damaze= new MazeFactory();
		damaze.order(daorder);
		damaze.waitTillDelivered();
		Maze yeahmaze= daorder.getMaze();
		Floorplan dafloor= yeahmaze.getFloorplan();
		
		assertTrue(IsThereExit(yeahmaze,dafloor));
		assertTrue(Wheredaexit(yeahmaze,dafloor));
		assertTrue(IsItPurr(yeahmaze,dafloor,daorder));
	}
	@Test
	final void TestBiggerMaze() {
		DefaultOrder daorder= new DefaultOrder(3);
		MazeFactory damaze= new MazeFactory();
		damaze.order(daorder);
		damaze.waitTillDelivered();
		Maze yeahmaze= daorder.getMaze();
		Floorplan dafloor= yeahmaze.getFloorplan();
		
		assertTrue(IsThereExit(yeahmaze,dafloor));
		assertTrue(Wheredaexit(yeahmaze,dafloor));
		assertTrue(IsItPurr(yeahmaze,dafloor,daorder));
	}
	
	@Test
	final void TestBiggerPrimMaze() {
		DefaultOrder daorder= new DefaultOrder(0, Builder.Prim, false, 13);
		MazeFactory damaze= new MazeFactory();
		damaze.order(daorder);
		damaze.waitTillDelivered();
		Maze yeahmaze= daorder.getMaze();
		Floorplan dafloor= yeahmaze.getFloorplan();
		
		assertTrue(IsThereExit(yeahmaze,dafloor));
		assertTrue(Wheredaexit(yeahmaze,dafloor));
		assertTrue(IsItPurr(yeahmaze,dafloor,daorder));
	}
	@Test
	final void TestBiggerNonPerfectMaze() {
		DefaultOrder daorder= new DefaultOrder(3, Builder.DFS, false, 13);
		MazeFactory damaze= new MazeFactory();
		damaze.order(daorder);
		damaze.waitTillDelivered();
		Maze yeahmaze= daorder.getMaze();
		Floorplan dafloor= yeahmaze.getFloorplan();
		
		assertTrue(IsThereExit(yeahmaze,dafloor));
		assertTrue(Wheredaexit(yeahmaze,dafloor));
		assertFalse(IsItPurr(yeahmaze,dafloor,daorder));
	}

	

	
}


/* i make a defaultorder object
*  make a factory object
*  i but the defaultorder object as a parameter in mazefactory.order() to create the maze
*  I then get the maze from daufultorder with getMaze() 
*  i test the maze for an exit, by iterating through every boarder position and checking if it is an exit position
*  i test if i can get to the exit from anywhere on maze by iterating from every positon on the maze and finding the next neigbor 
*  until distance to exit is 0
*  i test if a perfect maze of n cells have (2*width*height)- (width-height)
*/
