package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.commands.GetCameraCommand;

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
	
    public CameraSubsystem() {
    	frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        camera = new AxisCamera("10.17.0.22"); // open the camera at the IP address assigned.
    }
    
    public void update() {
//    	NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);
    	camera.getImage(frame);
//        NIVision.imaqDrawShapeOnImage(frame, frame, rect,
//                DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);

        CameraServer.getInstance().setImage(frame);
    }

    public void initDefaultCommand() {
//        setDefaultCommand(new GetCameraCommand());
    }
}

