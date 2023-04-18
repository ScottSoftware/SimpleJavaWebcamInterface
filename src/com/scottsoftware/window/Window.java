package com.scottsoftware.window;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;

public class Window {
	/*
	 * This class is used to put together a window and visualize the information we're processing
	 */
	
	private CanvasFrame canvas;
	
	// Set stuff up here
	public void init() {
		canvas = new CanvasFrame("SimpleJavaWebcamInterface");
		//This makes sure the program stops when the frame is closed, though it's less graceful
		canvas.setDefaultCloseOperation(CanvasFrame.EXIT_ON_CLOSE);
	}
	
	// Update our window with the new frame here
	public void update(Frame frame) {
		canvas.showImage(frame);
	}
}
