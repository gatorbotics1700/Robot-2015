package org.usfirst.frc.team1700.robot;

import org.usfirst.frc.team1700.robot.commands.ChangeAlignerStateCommand;
import org.usfirst.frc.team1700.robot.commands.LifterToLevelCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	public Joystick driveJoystick = new Joystick(RobotMap.DRIVE_JOYSTICK_PORT);
	public Joystick controlJoystick = new Joystick(RobotMap.CONTROL_JOYSTICK_PORT);
	Button longAlignerChangeStateButton = new JoystickButton(controlJoystick, RobotMap.LONG_ALIGNER_STATE_CHANGE_BUTTON);
	Button shortAlignerChangeStateButton = new JoystickButton(controlJoystick, RobotMap.SHORT_ALIGNER_STATE_CHANGE_BUTTON);
	Button lifterLevelZeroButton = new JoystickButton(controlJoystick, RobotMap.LIFTER_LEVEL_ZERO_BUTTON);
	Button lifterLevelOneButton = new JoystickButton(controlJoystick, RobotMap.LIFTER_LEVEL_ONE_BUTTON);
	Button lifterLevelTwoButton = new JoystickButton(controlJoystick, RobotMap.LIFTER_LEVEL_TWO_BUTTON);
	Button lifterLevelThreeButton = new JoystickButton(controlJoystick, RobotMap.LIFTER_LEVEL_THREE_BUTTON);

	public OI() {
		longAlignerChangeStateButton.whenPressed(new ChangeAlignerStateCommand(true));
		shortAlignerChangeStateButton.whenPressed(new ChangeAlignerStateCommand(false));
		lifterLevelZeroButton.whenPressed(new LifterToLevelCommand(0));
		lifterLevelOneButton.whenPressed(new LifterToLevelCommand(1));
		lifterLevelTwoButton.whenPressed(new LifterToLevelCommand(2));
		lifterLevelThreeButton.whenPressed(new LifterToLevelCommand(3));



	}
	
}

