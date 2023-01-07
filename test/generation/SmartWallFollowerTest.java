package generation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import generation.Order.Builder;
import gui.Control;
import gui.DistanceSensor;
import gui.Robot;
import gui.RobotDriver;
import gui.State;
import gui.StateGenerating;
import gui.StatePlaying;
import gui.Constants.UserInput;
import gui.Robot.Direction;

public class SmartWallFollowerTest {
	
    /**
     * Test case: See if 
     * <p>
     * Methods under test:
     * <p>
     * Test starts builds maze up until Starts testing each method
     */
	

/**
 * tests if the test drives out of the maze in an non perfect maze that 
 * would normally cause the regular wallfollower algorithm robot to go in an infinite cycle.
 * @throws Exception 
 */

	@Test
	final void testdrive2Exit()  {
		boolean wizardry1=false;

	//	try {
		String[] args= new String[1];
		args[0]="-d Manual";
		Control app = new Control();
		app.handleCommandLineInput(args);
		app.start();
		app.handleKeyboardInput(UserInput.START, 1);
		State f= app.currentState;
		while(f instanceof StateGenerating) {
			f= app.currentState;
			System.out.print("");
		}

//		StateGenerating nextState = new StateGenerating();
//		int seed=86422;
//        nextState.setBuilder(Builder.DFS); 
//        nextState.setPerfect(app.isPerfect());
//        nextState.setSkillLevel(1);
//        //app.skillev= 1;
//       // app.deterministic= true;
////        if (!app.isDeterministic()) {
////        	seed=  SingleRandom.getRandom().nextInt() ;
////        }
//        nextState.setSeed( seed);
//        app.setState(nextState);
//        DefaultOrder daorder= new DefaultOrder(1);
//        nextState.factory.order(daorder);
//        nextState.factory.waitTillDelivered();

        app.wallfollowerCrashedorLost= false;
		Robot robo= new UnreliableRobot();
		robo.setController(app);

		robo.setBatteryLevel(3500);
		RobotDriver robodriver= new SmartWallFollower();
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

			wizardry1=robodriver.drive2Exit();}catch(Exception e) {}
		
		

		
		assertTrue(wizardry1);
		
	}
/**
 * tests if the the method takes one step closer to the exit
 */
	@Test
	final void testdrive1Step2Exit() {
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
        app.deterministic= true;
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
		RobotDriver robodriver= new SmartWallFollower();
		robodriver.setRobot(robo);
		robodriver.setMaze(((StatePlaying)(app.currentState)).maze);
		app.setRobotAndDriver(robo, robodriver);
		
		boolean firstunreliablestarted=false;
		boolean wizardry=false;
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

			wizardry=robodriver.drive1Step2Exit();
		
		}catch(Exception e) {}	
		
		assertTrue(wizardry);
	}



}


