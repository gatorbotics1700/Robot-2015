package org.usfirst.frc.team1700.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// drive joystick axes and buttons
	public static final int MOVE_X = 4, // right x
						    MOVE_Y = 5, // right y
						    ROTATE_X = 0, // left x
						    ROTATE_Y = 1, // left y
						    DEBUGGING_BUTTON = 1, // A
						    B_BUTTON = 2, // B
						    X_BUTTON = 3, // X
						    Y_BUTTON = 4, // Y
							DRIVE_JOYSTICK_PORT = 0;
	
	//Control joystick buttons and ports
	public static final int CONTROL_JOYSTICK_PORT = 1,
							LIFTER_UP_BUTTON = 0,
							LIFTER_DOWN_BUTTON = 2,
							LONG_ALIGNER_STATE_CHANGE_BUTTON = 3,
							SHORT_ALIGNER_STATE_CHANGE_BUTTON = 4;

	//Talons
	public static final int TALON_FL_ID = 2,
							TALON_FR_ID = 1,
							TALON_BL_ID = 6, 
							TALON_BR_ID = 4, 
							LIFTER_TALON_1_ID = 3,
							LIFTER_TALON_2_ID = 0,
							SHORT_ALIGNMENT_TALON_1_ID = 5,
							SHORT_ALIGNMENT_TALON_2_ID = 7,
							LONG_ALIGNMENT_TALON_1_ID = 8,
							LONG_ALIGNMENT_TALON_2_ID = 9;
	
	//SensorsS
	public static final int LIFTER_ENCODER_PORT_1 = 1,
 							LIFTER_ENCODER_PORT_2 = 2,
							LIFTER_LIMIT_SWITCH_TOP_PORT = 3,
							LIFTER_LIMIT_SWITCH_BOTTOM_PORT = 4,
							LIFTER_POTENTIOMETER_PORT = 5;
	
	

}

