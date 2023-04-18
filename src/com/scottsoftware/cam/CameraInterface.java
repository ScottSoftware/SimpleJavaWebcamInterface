package com.scottsoftware.cam;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.IplImage;

public class CameraInterface {
	/*
	 * This class will be used to access the camera, as well as update the current
	 * frame and output the results for use in other classes
	 */

	// FrameGrabber Object helps us grab frames from the webcam
	private FrameGrabber g;
	private OpenCVFrameConverter.ToIplImage c;
	
	public Frame frame;
	public boolean cameraFail = false;
	
	// Load up the stuff we need here, called once (also passing in which device to use)
	public void init(int device) {
		g = new OpenCVFrameGrabber(device);
		c = new OpenCVFrameConverter.ToIplImage();
		try {
			g.start();
		} catch (Exception e) {
			// Catch any exceptions generated when we try to access the camera device
			cameraFail = true;
			System.err.println("Failed to start capture on camera device: " + device);
			e.printStackTrace();
		}
	}

	public void update() {
		//No reason to update if the camera isn't connected
		if(!cameraFail) {
			try {
				frame = g.grab();
			} catch (Exception e) {
				System.err.println("Failed to grab frame on current update.");
				// cameraFail can always be changed by other parts of the program later
				cameraFail = true;
				e.printStackTrace();
			}
		}
	}
	
	// Free our resources back up down here
	public void exit() {
		try {
			g.close();
			c.close();
		} catch (Exception e) {
			System.err.println("Failed to close the camera grabber.");
			e.printStackTrace();
		}
		frame.close();
	}
	
	// Method to grab our current frame for viewing or processing
	public Frame getCurrentFrame() {
		return frame;
	}
	
	// Method to save the current frame to an Image
	public void saveCurrentFrame() {
		IplImage img = c.convert(frame);
		opencv_imgcodecs.cvSaveImage("latest.jpg", img);
	}
}
