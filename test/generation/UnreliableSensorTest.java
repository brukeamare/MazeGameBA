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

public class UnreliableSensorTest {
    /**
     * Test case: See if 
     * <p>
     * Methods under test:
     * <p>
     * Test starts builds maze up until Starts testing each method
     */


	

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
		
		

		
		int num=robo.distanceToObstacle(Direction.BACKWARD);
		
		
		assertTrue(num<=0);


	}
	
	/**
	 * Tests of the thread for one sensor is started from in the sensor class.
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
		try{
			for(Direction i: Direction.values()) {
	    		DistanceSensor sensosa= new UnreliableSensor();
	    		robo.addDistanceSensor(sensosa, i);
			}

		}catch(Exception e) {}
		DistanceSensor f= new UnreliableSensor();
		for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
			if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.LEFT) {		
				f= ((UnreliableRobot)robo).sensorarrays.get(i);
				((UnreliableSensor)f).pleasestop=true;
				f.startFailureAndRepairProcess(4000,2000);
				
			}
		}
		
		
		assertTrue(((UnreliableSensor)f).started);

		
	}
	
	
	/**
	 * Tests of the thread for one sensor is stopped from in the sensor class.
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
		

		app.frontReliable= false;
		app.leftReliable= false;
		app.rightReliable= false;
		app.backReliable=false;
		try{
			for(Direction i: Direction.values()) {
	    		DistanceSensor sensosa= new UnreliableSensor();
	    		robo.addDistanceSensor(sensosa, i);
			}

		}catch(Exception e) {}
		
		DistanceSensor f= new UnreliableSensor();
		for (int i=0; i<((UnreliableRobot)robo).sensorarrays.size(); i++) {
			if (((UnreliableRobot)robo).sensorarrays.get(i).dadirection== Direction.LEFT) {		
				f= ((UnreliableRobot)robo).sensorarrays.get(i);
				((UnreliableSensor)f).op.start();
				while(!((UnreliableSensor)f).started) {
					
				}
				f.stopFailureAndRepairProcess();
			}
		}
		
		
		assertTrue(((UnreliableSensor)f).pleasestop);
		assertTrue(((UnreliableSensor)f).Operational);
	}
	
}


