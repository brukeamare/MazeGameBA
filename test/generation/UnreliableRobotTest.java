package generation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import generation.Order.Builder;
import gui.Control;
import gui.DistanceSensor;
import gui.Robot;
import gui.RobotDriver;
import gui.StateGenerating;
import gui.StatePlaying;
import gui.Constants.UserInput;
import gui.Robot.Direction;
import gui.Robot.Turn;

public class UnreliableRobotTest  {
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
    //    app.skillev= 0;
        if (!app.isDeterministic()) {
        	seed=  SingleRandom.getRandom().nextInt() ;
        }
        nextState.setSeed( seed);
        app.setState(nextState);
        DefaultOrder daorder= new DefaultOrder(3);
        nextState.factory.order(daorder);
        nextState.factory.waitTillDelivered();

        app.wallfollowerCrashedorLost= false;
		Robot robo= new UnreliableRobot();
		robo.setController(app);

		robo.setBatteryLevel(3500);
		RobotDriver robodriver= new WallFollower();
		robodriver.setRobot(robo);
		robodriver.setMaze(((StatePlaying)(app.currentState)).maze);
		app.setRobotAndDriver(robo, robodriver);
		
		boolean firstunreliablestarted=false;
		app.frontReliable= false;
		app.leftReliable= false;
		app.rightReliable= false;
		app.backReliable=false;
		try{
			for(Direction i: Direction.values()) {
	    		DistanceSensor sensosa= new UnreliableSensor();
	    		robo.addDistanceSensor(sensosa, i);
			}
			if (!app.leftReliable) {
				for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
					if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.LEFT) {		
						((UnreliableRobot)robo).startFailureAndRepairProcess(Direction.LEFT ,4000,2000);
						if (firstunreliablestarted) {
							Thread.sleep(1300);
						}
						firstunreliablestarted=true;
					}
				}
			}
			if (!app.rightReliable) {
				for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
					if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.RIGHT) {		
						((UnreliableRobot)robo).startFailureAndRepairProcess(Direction.RIGHT,4000,2000);
						if (firstunreliablestarted) {
							Thread.sleep(1300);
						}
						firstunreliablestarted=true;
					}
				}
			}
			if (!app.frontReliable) {
				for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
					if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.FORWARD) {		
						((UnreliableRobot)robo).startFailureAndRepairProcess(Direction.FORWARD,4000,2000);
						if (firstunreliablestarted) {
							Thread.sleep(1300);
						}
						firstunreliablestarted=true;
					}
				}
			}
			if (!app.backReliable) {
				for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
					if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.BACKWARD) {		
						((UnreliableRobot)robo).startFailureAndRepairProcess(Direction.BACKWARD,4000,2000);
						if (firstunreliablestarted) {
							Thread.sleep(1300);
						}
						firstunreliablestarted=true;
					}
				}
			}

		
		}catch(Exception e) {}	

		
		CardinalDirection cef= ((UnreliableRobot)robo).getCurrentDirection();
		
		((UnreliableRobot)robo).rotate(Turn.RIGHT);
		CardinalDirection cof= ((UnreliableRobot)robo).getCurrentDirection();
		
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

        app.wallfollowerCrashedorLost= false;
		Robot robo= new UnreliableRobot();
		robo.setController(app);

		robo.setBatteryLevel(3500);
		RobotDriver robodriver= new WallFollower();
		robodriver.setRobot(robo);
		robodriver.setMaze(((StatePlaying)(app.currentState)).maze);
		app.setRobotAndDriver(robo, robodriver);
		
		boolean firstunreliablestarted=false;
		app.frontReliable= false;
		app.leftReliable= false;
		app.rightReliable= false;
		app.backReliable=false;
		try{
			for(Direction i: Direction.values()) {
	    		DistanceSensor sensosa= new UnreliableSensor();
	    		robo.addDistanceSensor(sensosa, i);
			}
			if (!app.leftReliable) {
				for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
					if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.LEFT) {		
						((UnreliableRobot)robo).startFailureAndRepairProcess(Direction.LEFT ,4000,2000);
						if (firstunreliablestarted) {
							Thread.sleep(1300);
						}
						firstunreliablestarted=true;
					}
				}
			}
			if (!app.rightReliable) {
				for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
					if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.RIGHT) {		
						((UnreliableRobot)robo).startFailureAndRepairProcess(Direction.RIGHT,4000,2000);
						if (firstunreliablestarted) {
							Thread.sleep(1300);
						}
						firstunreliablestarted=true;
					}
				}
			}
			if (!app.frontReliable) {
				for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
					if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.FORWARD) {		
						((UnreliableRobot)robo).startFailureAndRepairProcess(Direction.FORWARD,4000,2000);
						if (firstunreliablestarted) {
							Thread.sleep(1300);
						}
						firstunreliablestarted=true;
					}
				}
			}
			if (!app.backReliable) {
				for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
					if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.BACKWARD) {		
						((UnreliableRobot)robo).startFailureAndRepairProcess(Direction.BACKWARD,4000,2000);
						if (firstunreliablestarted) {
							Thread.sleep(1300);
						}
						firstunreliablestarted=true;
					}
				}
			}

		
		}catch(Exception e) {}	
		
		
		
		
		
		
		
		
		int [] grot= new int [2];
		int [] grit= new int[2];
		
		int boo=robo.distanceToObstacle(Direction.FORWARD);
		while(boo<=0) {
			((UnreliableRobot)robo).rotate(Turn.LEFT);
			 boo=robo.distanceToObstacle(Direction.FORWARD);
		}
		try {
		grot= ((UnreliableRobot)robo).getCurrentPosition();
		((UnreliableRobot)robo).move(1);
		grit= ((UnreliableRobot)robo).getCurrentPosition();
		}catch(Exception e) {}
		
		assertTrue(!(grot[0] == grit[0]&&grot[1] == grit[1]));
	
	
	}


/**
 * tests if the distance to obstacle returns a number, regardless of which 
 * sensor is used for one cardinal direction.
 * does this by testing two sensors for an object in the same cardinal direction
 * then asserting if both values are the same.
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
   //     app.skillev= 0;
        if (!app.isDeterministic()) {
        	seed=  SingleRandom.getRandom().nextInt() ;
        }
        nextState.setSeed( seed);
        app.setState(nextState);
        DefaultOrder daorder= new DefaultOrder(3);
        nextState.factory.order(daorder);
        nextState.factory.waitTillDelivered();

        app.wallfollowerCrashedorLost= false;
		Robot robo= new UnreliableRobot();
		robo.setController(app);

		robo.setBatteryLevel(3500);
		RobotDriver robodriver= new WallFollower();
		robodriver.setRobot(robo);
		robodriver.setMaze(((StatePlaying)(app.currentState)).maze);
		app.setRobotAndDriver(robo, robodriver);
		
		boolean firstunreliablestarted=false;
		app.frontReliable= false;
		app.leftReliable= false;
		app.rightReliable= false;
		app.backReliable=false;
		try{
			for(Direction i: Direction.values()) {
	    		DistanceSensor sensosa= new UnreliableSensor();
	    		robo.addDistanceSensor(sensosa, i);
			}
			if (!app.leftReliable) {
				for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
					if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.LEFT) {		
						((UnreliableRobot)robo).startFailureAndRepairProcess(Direction.LEFT ,4000,2000);
						if (firstunreliablestarted) {
							Thread.sleep(1300);
						}
						firstunreliablestarted=true;
					}
				}
			}
			if (!app.rightReliable) {
				for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
					if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.RIGHT) {		
						((UnreliableRobot)robo).startFailureAndRepairProcess(Direction.RIGHT,4000,2000);
						if (firstunreliablestarted) {
							Thread.sleep(1300);
						}
						firstunreliablestarted=true;
					}
				}
			}
			if (!app.frontReliable) {
				for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
					if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.FORWARD) {		
						((UnreliableRobot)robo).startFailureAndRepairProcess(Direction.FORWARD,4000,2000);
						if (firstunreliablestarted) {
							Thread.sleep(1300);
						}
						firstunreliablestarted=true;
					}
				}
			}
			if (!app.backReliable) {
				for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
					if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.BACKWARD) {		
						((UnreliableRobot)robo).startFailureAndRepairProcess(Direction.BACKWARD,4000,2000);
						if (firstunreliablestarted) {
							Thread.sleep(1300);
						}
						firstunreliablestarted=true;
					}
				}
			}

		
		}catch(Exception e) {}	
		
		int boo=((UnreliableRobot)robo).distanceToObstacle(Direction.LEFT);
		if (boo==-1) {
		while(boo==-1) {
			((UnreliableRobot)robo).rotate(Turn.LEFT);
			boo=((UnreliableRobot)robo).distanceToObstacle(Direction.FORWARD);
			if(boo==-1) {
				((UnreliableRobot)robo).rotate(Turn.LEFT);
				boo=((UnreliableRobot)robo).distanceToObstacle(Direction.RIGHT);
				if (boo==-1) {
					((UnreliableRobot)robo).rotate(Turn.LEFT);
					boo=((UnreliableRobot)robo).distanceToObstacle(Direction.BACKWARD);
					if (boo==-1) {
						((UnreliableRobot)robo).rotate(Turn.LEFT);
						boo=((UnreliableRobot)robo).distanceToObstacle(Direction.LEFT);
						continue;
					}
					((UnreliableRobot)robo).rotate(Turn.LEFT);
					continue;
				}
				((UnreliableRobot)robo).rotate(Turn.AROUND);
				continue;
			}
			((UnreliableRobot)robo).rotate(Turn.RIGHT);
			}
		}
		
		((UnreliableRobot)robo).rotate(Turn.LEFT);
		
		int bea=((UnreliableRobot)robo).distanceToObstacle(Direction.FORWARD);
		if (bea==-1) {
		while(bea==-1) {
			((UnreliableRobot)robo).rotate(Turn.LEFT);
			bea=((UnreliableRobot)robo).distanceToObstacle(Direction.RIGHT);
			if(bea==-1) {
				((UnreliableRobot)robo).rotate(Turn.LEFT);
				bea=((UnreliableRobot)robo).distanceToObstacle(Direction.BACKWARD);
				if (bea==-1) {
					((UnreliableRobot)robo).rotate(Turn.LEFT);
					bea=((UnreliableRobot)robo).distanceToObstacle(Direction.LEFT);
					if (bea==-1) {
						((UnreliableRobot)robo).rotate(Turn.LEFT);
						bea=((UnreliableRobot)robo).distanceToObstacle(Direction.FORWARD);
						continue;
					}
					((UnreliableRobot)robo).rotate(Turn.LEFT);
					continue;
				}
				((UnreliableRobot)robo).rotate(Turn.AROUND);
				continue;
			}
			((UnreliableRobot)robo).rotate(Turn.RIGHT);
			}
		}
		
		assertTrue(boo==bea);
		
	}

	/**
	 * tests if the method works when not facing an exit and if it returns false.
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

        app.wallfollowerCrashedorLost= false;
		Robot robo= new UnreliableRobot();
		robo.setController(app);

		robo.setBatteryLevel(3500);
		RobotDriver robodriver= new WallFollower();
		robodriver.setRobot(robo);
		robodriver.setMaze(((StatePlaying)(app.currentState)).maze);
		app.setRobotAndDriver(robo, robodriver);
		
		boolean firstunreliablestarted=false;
		app.frontReliable= false;
		app.leftReliable= false;
		app.rightReliable= false;
		app.backReliable=false;
		try{
			for(Direction i: Direction.values()) {
	    		DistanceSensor sensosa= new UnreliableSensor();
	    		robo.addDistanceSensor(sensosa, i);
			}
			if (!app.leftReliable) {
				for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
					if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.LEFT) {		
						((UnreliableRobot)robo).startFailureAndRepairProcess(Direction.LEFT ,4000,2000);
						if (firstunreliablestarted) {
							Thread.sleep(1300);
						}
						firstunreliablestarted=true;
					}
				}
			}
			if (!app.rightReliable) {
				for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
					if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.RIGHT) {		
						((UnreliableRobot)robo).startFailureAndRepairProcess(Direction.RIGHT,4000,2000);
						if (firstunreliablestarted) {
							Thread.sleep(1300);
						}
						firstunreliablestarted=true;
					}
				}
			}
			if (!app.frontReliable) {
				for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
					if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.FORWARD) {		
						((UnreliableRobot)robo).startFailureAndRepairProcess(Direction.FORWARD,4000,2000);
						if (firstunreliablestarted) {
							Thread.sleep(1300);
						}
						firstunreliablestarted=true;
					}
				}
			}
			if (!app.backReliable) {
				for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
					if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.BACKWARD) {		
						((UnreliableRobot)robo).startFailureAndRepairProcess(Direction.BACKWARD,4000,2000);
						if (firstunreliablestarted) {
							Thread.sleep(1300);
						}
						firstunreliablestarted=true;
					}
				}
			}
			
		
		}catch(Exception e) {}	
		
		boolean num=robo.canSeeThroughTheExitIntoEternity(Direction.BACKWARD);
		
		
		
		
		assertTrue(num==false);
	
	}
	
	/**
	 * Tests of the thread for one sensor is started from the robot class
	 */
	@Test
	final void startFailureAndRepairProcess(){
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
      //  app.skillev= 0;
        if (!app.isDeterministic()) {
        	seed=  SingleRandom.getRandom().nextInt() ;
        }
        nextState.setSeed( seed);
        app.setState(nextState);
        DefaultOrder daorder= new DefaultOrder(3);
        nextState.factory.order(daorder);
        nextState.factory.waitTillDelivered();

        app.wallfollowerCrashedorLost= false;
		Robot robo= new UnreliableRobot();
		robo.setController(app);

		robo.setBatteryLevel(3500);
		RobotDriver robodriver= new WallFollower();
		robodriver.setRobot(robo);
		robodriver.setMaze(((StatePlaying)(app.currentState)).maze);
		app.setRobotAndDriver(robo, robodriver);
		

		app.frontReliable= false;
		app.leftReliable= false;
		app.rightReliable= false;
		app.backReliable=false;
		DistanceSensor f= new UnreliableSensor();
		try{
			for(Direction i: Direction.values()) {
	    		DistanceSensor sensosa= new UnreliableSensor();
	    		robo.addDistanceSensor(sensosa, i);
			}
			if (!app.leftReliable) {
				for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
					if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.LEFT) {	
						f=((UnreliableRobot)robo).sensorarrays.get(i);
						((UnreliableRobot)robo).startFailureAndRepairProcess(Direction.LEFT ,4000,2000);
						while(!((UnreliableSensor)f).started) {
							
						}
					}
				}
			}
			
		}catch(Exception e) {}
		boolean excuse=((UnreliableSensor)f).started;
		
		assertTrue(((UnreliableSensor)f).started);
		
	}
	
	
	/**
	 * Tests of the thread for one sensor is stopped from the robot class.
	 */
	@Test
	final void stopFailureAndRepairProcess(){
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
   //     app.skillev= 0;
        if (!app.isDeterministic()) {
        	seed=  SingleRandom.getRandom().nextInt() ;
        }
        nextState.setSeed( seed);
        app.setState(nextState);
        DefaultOrder daorder= new DefaultOrder(3);
        nextState.factory.order(daorder);
        nextState.factory.waitTillDelivered();

        app.wallfollowerCrashedorLost= false;
		Robot robo= new UnreliableRobot();
		robo.setController(app);

		robo.setBatteryLevel(3500);
		RobotDriver robodriver= new WallFollower();
		robodriver.setRobot(robo);
		robodriver.setMaze(((StatePlaying)(app.currentState)).maze);
		app.setRobotAndDriver(robo, robodriver);
		

		app.frontReliable= false;
		app.leftReliable= false;
		app.rightReliable= false;
		app.backReliable=false;
		DistanceSensor f= new UnreliableSensor();
		try{
			for(Direction i: Direction.values()) {
	    		DistanceSensor sensosa= new UnreliableSensor();
	    		robo.addDistanceSensor(sensosa, i);
			}
			if (!app.leftReliable) {
				for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
					if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.LEFT) {	
						f=((UnreliableRobot)robo).sensorarrays.get(i);
						((UnreliableRobot)robo).startFailureAndRepairProcess(Direction.LEFT ,4000,2000);
						while(!((UnreliableSensor)f).started) {
							
						}
						((UnreliableRobot)robo).stopFailureAndRepairProcess(Direction.LEFT);
					}
				}
			}
			
		}catch(Exception e) {}
		
		boolean excuse=((UnreliableSensor)f).pleasestop;
		boolean um=((UnreliableSensor)f).Operational;
		
		assertTrue(((UnreliableSensor)f).pleasestop);
		assertTrue(((UnreliableSensor)f).Operational);
		
	}



}


