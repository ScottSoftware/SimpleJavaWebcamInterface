package com.scottsoftware.main;

import com.scottsoftware.cam.CameraInterface;
import com.scottsoftware.window.Window;

public class Main {
	
	public static void main(String[] args) {
		CameraInterface cam = new CameraInterface();
		Window w = new Window();
		
		cam.init(0);
		w.init();
		
		while(!cam.cameraFail) {
			//Update the camera first, so we have fresh info
			cam.update();
			
			//Update other processes or image modifications
			
			//Update the window last to show our changes
			w.update(cam.frame);
		}
		// Clean everything up and ensure it's fully shut down
		cam.exit();
		System.exit(0);
	}
}
