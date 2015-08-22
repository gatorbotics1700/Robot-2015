package org.usfirst.frc.team1700.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// drive joystick axes and buttons
	public static final int DRIVE_JOYSTICK_PORT = 0,
						    // axes
						    MOVE_X = 4, // right x
						    MOVE_Y = 5, // right y
						    ROTATE_X = 0, // left x
						    ROTATE_Y = 1, // left y
						    
						    // strafing triggers
						    STRAFE_LEFT = 2,
						    STRAFE_RIGHT = 3,
						    
						    // buttons
						    POV_INIT_NUMBER = 0,
						    DEBUGGING_BUTTON = 1, // A
						    RESET_ALIGNER_BUTTON = 2, // B
						    SENSOR_BUTTON = 3, // X
						    Y_BUTTON = 4, // Y
						    SHORT_ALIGNER_STATE_CHANGE_BUTTON = 5,
						    LONG_ALIGNER_STATE_CHANGE_BUTTON = 6;
							
	// control joystick buttons and ports
	public static final int CONTROL_JOYSTICK_PORT = 1,
							LIFTER_LEVEL_ZERO_BUTTON = 7,
							LIFTER_LEVEL_ONE_BUTTON = 6,
							LIFTER_LEVEL_TWO_BUTTON = 10,
							LIFTER_LEVEL_THREE_BUTTON = 11,
							ZERO_LIFTER_BUTTON = 9,
							LIFTER_UNCHECKED_MOVE_BUTTON = 8;
							
	// Talons
	public static final int TALON_DRIVE_FL_ID = 2,
							TALON_DRIVE_FR_ID = 3,
							TALON_DRIVE_BL_ID = 1, 
							TALON_DRIVE_BR_ID = 4, 
							LIFTER_TALON_1_ID = 9,
							LIFTER_TALON_2_ID = 10,
							SHORT_ALIGNMENT_TALON_1_ID = 8, //6,
							LONG_ALIGNMENT_TALON_1_ID = 7;
	
	// sensor ports
	public static final int LIFTER_LIMIT_SWITCH_LEFT = 0,
							LIFTER_LIMIT_SWITCH_RIGHT = 5,
							AUTONOMOUS_SWITCH = 1;
		
	// Aligner Encoder Values
	public static final int LONG_ALIGNER_VERTICAL_STATE = -50, 
							SHORT_ALIGNER_VERTICAL_STATE = -125,
							LONG_ALIGNER_HORIZONTAL_STATE = -1820,
							SHORT_ALIGNER_HORIZONTAL_STATE = -1900;
	
	//Lifter Levels
	public static final int LEVEL_0 = -50,
							LEVEL_1 = 45000,
							LEVEL_2 = 75200,
							LEVEL_3 = 155900; // little bit less than soft limit 154946.0

	// autonomous driving
	public static final double AUTONOMOUS_DRIVE_SPEED = 0.6;
	public static final int AUTONOMOUS_QUARTER_TURN_DIST = 6000,
							AUTONOMOUS_HALF_TURN_DIST = 12000,
							AUTONOMOUS_FORWARD_FLAT_DISTANCE = 70000,
							AUTONOMOUS_FORWARD_PLATFORM_DISTANCE = 71000,
							AUTONOMOUS_BACKWARD_FLAT_DISTANCE = -50000; // same for platform distance


	public static final int PROGRAMMING_WORKSHOP_TEST = 10;
        public static final int PROGRAMMING_TEST = 2;
}

