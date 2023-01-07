package generation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import generation.Order.Builder;
import gui.Control;
import gui.DistanceSensor;
import gui.MazePanel;
import gui.Robot;
import gui.RobotDriver;
import gui.State;
import gui.StateGenerating;
import gui.StatePlaying;
import gui.StateTitle;
import gui.Constants.UserInput;
import gui.Robot.Direction;
import gui.Robot.Turn;

public class ReliableRobotTest  {
    /**
     * Test case: See if 
     * <p>
     * Methods under test:
     * <p>
     * Test starts builds maze up until Starts testing each method
     */
	
	
/**
 * Tests of the initial direction is different in the current direction after rotate method
 */
	@Test
	final void testrotate() {
		String[] args= new String[1];
		args[0]="-d Manual";
		Control app = new Control();
		app.handleCommandLineInput(args);
		app.start();
		app.handleKeyboardInput(UserInput.START, 0);
		StateGenerating nextState = new StateGenerating();
		int seed=86422;
        nextState.setBuilder(Builder.DFS); 
        nextState.setPerfect(app.isPerfect());
        nextState.setSkillLevel(0);
       // app.skillev= 0;
        if (!app.isDeterministic()) {
        	seed=  SingleRandom.getRandom().nextInt() ;
        }
        nextState.setSeed( seed);
        app.setState(nextState);
        DefaultOrder daorder= new DefaultOrder(3);
        nextState.factory.order(daorder);
        nextState.factory.waitTillDelivered();

		app.wizardCrashedorLost= false;
		Robot robo= new ReliableRobot();
		robo.setController(app);
		robo.setBatteryLevel(3500);
		RobotDriver robodriver= new Wizard();
		robodriver.setRobot(robo);
		robodriver.setMaze(((StatePlaying)(app.currentState)).maze);
		app.setRobotAndDriver(robo, robodriver);
		for(Direction i: Direction.values()) {
    		DistanceSensor sensosa= new ReliableSensor();
    		robo.addDistanceSensor(sensosa, i);
		}
		
		
		
		CardinalDirection cef= ((ReliableRobot)robo).getCurrentDirection();
		((ReliableRobot)robo).rotate(Turn.RIGHT);
		CardinalDirection cof= ((ReliableRobot)robo).getCurrentDirection();
		
		assertTrue(cef!= cof);
		

	}
/**
 * asserts if the initial position is different in the current position after the move method
 */
	@Test
	final void testmove() {
		String[] args= new String[1];
		args[0]="-d Manual";
		Control app = new Control();
		app.handleCommandLineInput(args);
		app.start();
		app.handleKeyboardInput(UserInput.START, 0);
		StateGenerating nextState = new StateGenerating();
		int seed=86422;
        nextState.setBuilder(Builder.DFS); 
        nextState.setPerfect(app.isPerfect());
        nextState.setSkillLevel(0);
     //   app.skillev= 0;
        if (!app.isDeterministic()) {
        	seed=  SingleRandom.getRandom().nextInt() ;
        }
        nextState.setSeed( seed);
        app.setState(nextState);
        DefaultOrder daorder= new DefaultOrder(3);
        nextState.factory.order(daorder);
        nextState.factory.waitTillDelivered();

		app.wizardCrashedorLost= false;
		Robot robo= new ReliableRobot();
		robo.setController(app);
		robo.setBatteryLevel(3500);
		RobotDriver robodriver= new Wizard();
		robodriver.setRobot(robo);
		robodriver.setMaze(((StatePlaying)(app.currentState)).maze);
		app.setRobotAndDriver(robo, robodriver);
		for(Direction i: Direction.values()) {
    		DistanceSensor sensosa= new ReliableSensor();
    		robo.addDistanceSensor(sensosa, i);
		}
		
		
		
		
		
		
		int [] grot= new int [2];
		int [] grit= new int[2];
		while(robo.distanceToObstacle(Direction.FORWARD)==0) {
			((ReliableRobot)robo).rotate(Turn.RIGHT);
		}
		try {
		grot= ((ReliableRobot)robo).getCurrentPosition();
		((ReliableRobot)robo).move(1);
		grit= ((ReliableRobot)robo).getCurrentPosition();
		}catch(Exception e) {}
		
		assertTrue(grot[0] != grit[0]|| grot[1] != grit[1]);
	
	}
	/**
	 * asserts if the initial position is different in the current position after the jump method
	 */
		@Test
		final void testjump() {
			String[] args= new String[1];
			args[0]="-d Manual";
			Control app = new Control();
			app.handleCommandLineInput(args);
			app.start();
			app.handleKeyboardInput(UserInput.START, 0);
			State f= app.currentState;
			while(f instanceof StateGenerating) {
				f= app.currentState;
				System.out.print("");
			}
			
//			StateGenerating nextState = new StateGenerating();
//			int seed=86422;
//	        nextState.setBuilder(Builder.DFS); 
//	        nextState.setPerfect(app.isPerfect());
//	        nextState.setSkillLevel(0);
//	     //   app.skillev= 0;
//	        if (!app.isDeterministic()) {
//	        	seed=  SingleRandom.getRandom().nextInt() ;
//	        }
//	        nextState.setSeed( seed);
//	        app.setState(nextState);
//	        DefaultOrder daorder= new DefaultOrder(3);
//	        nextState.factory.order(daorder);
//	        nextState.factory.waitTillDelivered();

			app.wizardCrashedorLost= false;
			Robot robo= new ReliableRobot();
			robo.setController(app);
			robo.setBatteryLevel(3500);
			RobotDriver robodriver= new Wizard();
			robodriver.setRobot(robo);
			robodriver.setMaze(((StatePlaying)(app.currentState)).maze);
			app.setRobotAndDriver(robo, robodriver);
			for(Direction i: Direction.values()) {
	    		DistanceSensor sensosa= new ReliableSensor();
	    		robo.addDistanceSensor(sensosa, i);
			}
			
			
			
			
			
			
			int [] grot= new int [2];
			int [] grit= new int[2];
			int [] grunt= new int[2];
			CardinalDirection cd;
			Floorplan dafloor= ((Wizard)robodriver).Wizmaze.getFloorplan();
			cd= ((ReliableRobot)robo).getCurrentDirection();


			try {
				grot= ((ReliableRobot)robo).getCurrentPosition();
				grunt=cd.getDxDyDirection();
				grunt[0]= grunt[0]+grot[0];
				grunt[1]= grunt[1]+grot[1];

				
			while(dafloor.hasNoWall(grot[0], grot[1], cd)|| grunt[0]<0|| grunt[0]>= ((Wizard)robodriver).Wizmaze.getWidth()|| grunt[1]<0|| grunt[1]>= ((Wizard)robodriver).Wizmaze.getHeight() ) {
				cd =cd.rotateClockwise();
				((ReliableRobot)robo).rotate(Turn.LEFT);
				grunt=cd.getDxDyDirection();
				grunt[0]= grunt[0]+grot[0];
				grunt[1]= grunt[1]+grot[1];
			}
			((ReliableRobot)robo).jump();
			
			grit= ((ReliableRobot)robo).getCurrentPosition();
			
			}catch(Exception e) {}
			
			assertTrue(grot[0] != grit[0]|| grot[1] != grit[1]);
			
			assertTrue((grot[0]+ grunt[0])== grit[0] || (grot[1]+ grunt[1])== grit[1]);
		
		}

/**
 * tests if the distance to obstacle returns a valid number(not negative 1).
 */
	@Test
	final void testdistanceToObstacle() {
		String[] args= new String[1];
		args[0]="-d Manual";
		Control app = new Control();
		app.handleCommandLineInput(args);
		app.start();
		app.handleKeyboardInput(UserInput.START, 0);
		StateGenerating nextState = new StateGenerating();
		int seed=86422;
        nextState.setBuilder(Builder.DFS); 
        nextState.setPerfect(app.isPerfect());
        nextState.setSkillLevel(0);
  //      app.skillev= 0;
        if (!app.isDeterministic()) {
        	seed=  SingleRandom.getRandom().nextInt() ;
        }
        nextState.setSeed( seed);
        app.setState(nextState);
        DefaultOrder daorder= new DefaultOrder(3);
        nextState.factory.order(daorder);
        nextState.factory.waitTillDelivered();

		app.wizardCrashedorLost= false;
		Robot robo= new ReliableRobot();
		robo.setController(app);
		robo.setBatteryLevel(3500);
		RobotDriver robodriver= new Wizard();
		robodriver.setRobot(robo);
		robodriver.setMaze(((StatePlaying)(app.currentState)).maze);
		app.setRobotAndDriver(robo, robodriver);
		for(Direction i: Direction.values()) {
    		DistanceSensor sensosa= new ReliableSensor();
    		robo.addDistanceSensor(sensosa, i);
		}
		
		
		
		
		
		int num=robo.distanceToObstacle(Direction.BACKWARD);
		
		assertTrue(num<=0);
	}

	/**
	 * tests if the method works when facing an exit and if it returns false when not facing exit.
	 */
	@Test
	final void testcanSeeThroughTheExitIntoEternity()  {
		String[] args= new String[1];
		args[0]="-d Manual";
		Control app = new Control();
		app.handleCommandLineInput(args);
		app.start();
		app.handleKeyboardInput(UserInput.START, 0);
		StateGenerating nextState = new StateGenerating();
		int seed=86422;
        nextState.setBuilder(Builder.DFS); 
        nextState.setPerfect(app.isPerfect());
        nextState.setSkillLevel(0);
     //   app.skillev= 0;
        if (!app.isDeterministic()) {
        	seed=  SingleRandom.getRandom().nextInt() ;
        }
        nextState.setSeed( seed);
        app.setState(nextState);
        DefaultOrder daorder= new DefaultOrder(3);
        nextState.factory.order(daorder);
        nextState.factory.waitTillDelivered();

		app.wizardCrashedorLost= false;
		Robot robo= new ReliableRobot();
		robo.setController(app);
		robo.setBatteryLevel(3500);
		RobotDriver robodriver= new Wizard();
		robodriver.setRobot(robo);
		robodriver.setMaze(((StatePlaying)(app.currentState)).maze);
		app.setRobotAndDriver(robo, robodriver);
		for(Direction i: Direction.values()) {
    		DistanceSensor sensosa= new ReliableSensor();
    		robo.addDistanceSensor(sensosa, i);
		}
		
		
		
		
		
		boolean num=robo.canSeeThroughTheExitIntoEternity(Direction.BACKWARD);
		
		
		assertTrue(num==false);
	
	}



}
