package org.usfirst.frc.team1700.robot;

import org.usfirst.frc.team1700.robot.commands.ChangeAlignerStateCommand;
import org.usfirst.frc.team1700.robot.commands.LifterToLevelCommand;
import org.usfirst.frc.team1700.robot.commands.ResetAlignerCommand;

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
	Button longAlignerChangeStateButton = new JoystickButton(driveJoystick, RobotMap.LONG_ALIGNER_STATE_CHANGE_BUTTON);
	Button shortAlignerChangeStateButton = new JoystickButton(driveJoystick, RobotMap.SHORT_ALIGNER_STATE_CHANGE_BUTTON);
	Button precisionMovePOV = new JoystickButton(driveJoystick, RobotMap.POV_INIT_NUMBER);
	Button resetAlignerButton = new JoystickButton(driveJoystick, RobotMap.RESET_ALIGNER_BUTTON);
	
	// control joystick and buttons
	public Joystick controlJoystick = new Joystick(RobotMap.CONTROL_JOYSTICK_PORT);
	Button lifterLevelZeroButton = new JoystickButton(controlJoystick, RobotMap.LIFTER_LEVEL_ZERO_BUTTON);
	Button lifterLevelOneButton = new JoystickButton(controlJoystick, RobotMap.LIFTER_LEVEL_ONE_BUTTON);
	Button lifterLevelTwoButton = new JoystickButton(controlJoystick, RobotMap.LIFTER_LEVEL_TWO_BUTTON);
	Button lifterLevelThreeButton = new JoystickButton(controlJoystick, RobotMap.LIFTER_LEVEL_THREE_BUTTON);

	public OI() {
		// tote aligner button bindings
		longAlignerChangeStateButton.whenPressed(new ChangeAlignerStateCommand(true)); // true = long
		shortAlignerChangeStateButton.whenPressed(new ChangeAlignerStateCommand(false)); // false = short
		resetAlignerButton.whenPressed(new ResetAlignerCommand());

		// chain lifter button bindings
		lifterLevelZeroButton.whileHeld(new LifterToLevelCommand(RobotMap.LEVEL_0));
		lifterLevelOneButton.whileHeld(new LifterToLevelCommand(RobotMap.LEVEL_1));
		lifterLevelTwoButton.whileHeld(new LifterToLevelCommand(RobotMap.LEVEL_2));
		lifterLevelThreeButton.whileHeld(new LifterToLevelCommand(RobotMap.LEVEL_3));
	}
	
}

