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
						    Y_BUTTON = 4; // Y
	
	//Control joystick buttons and ports
	public static final int CONTROL_JOYSTICK_PORT = 0;
	
	//Victors
	public static final int VICTOR_FL = 2,
							VICTOR_FR = 1,
							VICTOR_BL = 6, 
							VICTOR_BR = 4, 
							LIFTER_VICTOR_PORT = 3;
	
	//Sensors
	public static final int LIFTER_ENCODER_PORT_1 = 1,
 							LIFTER_ENCODER_PORT_2 = 2;

}

