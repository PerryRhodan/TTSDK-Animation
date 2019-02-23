/**
 * www.taleteller.de
 * 
 * TaletellerAnimation
 *   Target
 * 
 * Summary:
 *   Defines a target position for an image to be drawn at.
 * 
 * History:
 *   25.11.2017 - Cleaning of code
 *   
 * 
 * Ideas:
 *   
 * 
 * Stephan Hogrefe, Edinburgh, 2017
 */
package de.taleteller.animation;

/**
 * 
 */
public class DrawPosition {

	/* position parameters */
	/** x pixel screen coordinate */
	int x;
	/** y pixel screen coordinate */
	int y;
	/** pixel width */
	int w;
	/** pixel height */
	int h;
	/** rotation */
	double rotation;
	/* rotation center */
	/** rotation center x */
	int rotation_x;
	/** rotation center y */
	int rotation_y;
	/** alpha value */
	double alpha;
	
	public DrawPosition(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.rotation = 0.0;
		this.alpha = 1.0;
		// set default rotation center
		rotation_x = x + w/2;
		rotation_y = y + y/2;
	}
	
	public DrawPosition(int x, int y, int w, int h, double rot) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.rotation = rot;
		this.alpha = 1.0;
		// set default rotation center
		rotation_x = x + w/2;
		rotation_y = y + y/2;
	}
	
	public DrawPosition(int x, int y, int w, int h, double rot, double alpha) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.rotation = rot;
		this.alpha = alpha;
		// set default rotation center
		rotation_x = x + w / 2;
		rotation_y = y + y / 2;
	}
	
	public void AddTo_X(int amt) {
		this.x += amt;
	}
	
	public void AddTo_Y(int amt) {
		this.y += amt;
	}
	
	public void AddTo_W(int amt) {
		this.w += amt;
	}
	
	public void AddTo_H(int amt) {
		this.h += amt;
	}
	
	public void AddTo_Rotation(double amt) {
		this.rotation += amt;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public double getRotation() {
		return rotation;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setW(int w) {
		this.w = w;
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public int getRotation_x() {
		return rotation_x;
	}

	public int getRotation_y() {
		return rotation_y;
	}

	public void setRotation_x(int rotation_x) {
		this.rotation_x = rotation_x;
	}

	public void setRotation_y(int rotation_y) {
		this.rotation_y = rotation_y;
	}
	
}
