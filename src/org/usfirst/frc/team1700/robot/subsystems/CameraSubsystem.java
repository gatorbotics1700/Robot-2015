package org.usfirst.frc.team1700.robot.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.AxisCamera;

/**
 *
 */
public class CameraSubsystem extends Subsystem {
	int session;
    Image frame;
    AxisCamera camera;
	
    public CameraSubsystem(String IPAddress) {
    	frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        camera = new AxisCamera(IPAddress); // open the camera at the IP address assigned.
    }
    
    public void update() {
    	camera.getImage(frame);
        CameraServer.getInstance().setImage(frame);
    }

    public void initDefaultCommand() {
    }
}

