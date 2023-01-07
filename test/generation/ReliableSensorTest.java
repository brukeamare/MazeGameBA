package generation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import generation.Order.Builder;
import gui.Control;
import gui.DistanceSensor;
import gui.MazePanel;
import gui.Robot;
import gui.RobotDriver;
import gui.StateGenerating;
import gui.StatePlaying;
import gui.Constants.UserInput;
import gui.Robot.Direction;



public class ReliableSensorTest {
    /**
     * Test case: See if 
     * <p>
     * Methods under test:
     * <p>
     * Test starts builds maze up until Starts testing each method
     */


	

/**
 * tests to see if the method in sensor returns an integer
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

}
