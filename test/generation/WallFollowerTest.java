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

public class WallFollowerTest {
	
    /**
     * Test case: See if 
     * <p>
     * Methods under test:
     * <p>
     * Test starts builds maze up until Starts testing each method
     */
	

/**
 * tests if the test drives out of the maze
 * @throws Exception 
 */

	@Test
	final void testdrive2Exit() throws Exception  {
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

			wizardry=robodriver.drive2Exit();
		
		}catch(Exception e) {}	
		
		assertTrue(wizardry);
	}
/**
 * tests if the the method takes one step towards the exit
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

