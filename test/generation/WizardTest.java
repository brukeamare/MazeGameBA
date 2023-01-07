package generation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import generation.Order.Builder;
import gui.Constants.UserInput;
import gui.Robot.Direction;
import gui.Control;
import gui.DistanceSensor;
import gui.MazePanel;
import gui.Robot;
import gui.RobotDriver;
import gui.StateGenerating;
import gui.StatePlaying;

public class WizardTest {
	
	
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
      //  app.skillev= 0;
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
		
		
		boolean wizardry=false;
		try{wizardry=robodriver.drive2Exit();
		}catch(Exception e) {
			
		}
		
		assertTrue(wizardry);
	}
/**
 * tests if the the method runs one step towards the exit
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
		
		
		boolean wizardry=false;
		try{wizardry=robodriver.drive1Step2Exit();
		}catch(Exception e) {
			
		}
		
		
		assertTrue(wizardry);
	}



}
