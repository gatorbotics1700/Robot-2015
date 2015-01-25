package org.usfirst.frc.team1700.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;

public class ProximitySensorSubsystem {
	private AnalogInput sensor;
	private static final int NUM_READINGS_TO_AVERAGE = 10;
	private int[] rollingAverage;
	private double sum, average = 0;
	private int index = 0; 
	
	public ProximitySensorSubsystem(int port) {
		rollingAverage = new int[NUM_READINGS_TO_AVERAGE]; //will all be zero to start with
		sensor = new AnalogInput(port);
		
		sum = 0; 
		index = 0; 
	}
	
	// the first 9 updates of this method are invalid
	public void updateRollingAverage() {
		int currVal = sensor.getValue();
		int oldVal = rollingAverage[index]; 
		rollingAverage[index] = currVal; 
		
		sum += (currVal - oldVal);
		average = sum / rollingAverage.length;
		
		index = (index +1) % rollingAverage.length;
	}
	
	// returns distance in centimeters
	public double getDistance() {
		return (2043 * Math.exp(-0.1231 * average) + 683.2);
	}
	
	public void printDistance() {
		System.out.println(getDistance());
	}
}
