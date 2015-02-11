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
    // drive joystick and buttons
	public Joystick driveJoystick = new Joystick(RobotMap.DRIVE_JOYSTICK_PORT);
	//Button alignToToteButton = new JoystickButton(driveJoystick, RobotMap.ALIGN_TO_TOTE_BUTTON);
	//Button sensorButton = new JoystickButton(driveJoystick, RobotMap.SENSOR_BUTTON);
	
	// control joystick and buttons
	public Joystick controlJoystick = new Joystick(RobotMap.CONTROL_JOYSTICK_PORT);
	Button longAlignerChangeStateButton = new JoystickButton(controlJoystick, RobotMap.LONG_ALIGNER_STATE_CHANGE_BUTTON);
	Button shortAlignerChangeStateButton = new JoystickButton(controlJoystick, RobotMap.SHORT_ALIGNER_STATE_CHANGE_BUTTON);
	Button lifterLevelZeroButton = new JoystickButton(controlJoystick, RobotMap.LIFTER_LEVEL_ZERO_BUTTON);
	Button lifterLevelOneButton = new JoystickButton(controlJoystick, RobotMap.LIFTER_LEVEL_ONE_BUTTON);
	//Button lifterLevelTwoButton = new JoystickButton(controlJoystick, RobotMap.LIFTER_LEVEL_TWO_BUTTON);
	//Button lifterLevelThreeButton = new JoystickButton(controlJoystick, RobotMap.LIFTER_LEVEL_THREE_BUTTON);

	public OI() {
		// tote aligner button bindings
		longAlignerChangeStateButton.whenPressed(new ChangeAlignerStateCommand(true)); // true = long
		shortAlignerChangeStateButton.whenPressed(new ChangeAlignerStateCommand(false)); // false = short
		
		// proximity sensor aligner button bindings
//		alignToToteButton.whileHeld(new AlignToToteCommand());
		//sensorButton.whileHeld(new ReadProximitySensorCommand());

		// chain lifter button bindings
		lifterLevelZeroButton.whileHeld(new LifterToLevelCommand(RobotMap.LEVEL_0));
		lifterLevelOneButton.whileHeld(new LifterToLevelCommand(RobotMap.LEVEL_1));
		//lifterLevelTwoButton.whenPressed(new LifterToLevelCommand(2));
		//lifterLevelThreeButton.whenPressed(new LifterToLevelCommand(3));
	}
	
}

