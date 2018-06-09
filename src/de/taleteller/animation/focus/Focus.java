/**
 * www.taleteller.de
 * 
 * TaletellerAnimation
 *   Focus
 * 
 * Summary:
 *   Focus ie "camera", containing x, y offset
 *   and zoom.
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
package de.taleteller.animation.focus;

import java.util.ArrayList;
import java.util.List;


/**
 * Focus ie "camera", containing x, y offset
 * and zoom.
 */
public class Focus {

	/**
	 * List of listeners that will be informed when this focus changes.
	 */
	List<FocusUpdateListener> listeners;
	/**
	 * List of listeners that will be informed when this focus changes.
	 * This time the detailed listeners.
	 */
	List<DetailedFocusUpdateListener> listeners_detailed;
	
	/** x value of the focus.
	 *  Increases when moving the view to the right.*/
	private int x;
	/** x value of the focus.
	 *  Increases when moving the view to the up.*/
	private int y;
	/** Current zoom value.
	 */
	private double zoom;
	
	/** rotation */
	double rotation;
	/* rotation center */
	/** rotation center x */
	int rotation_x;
	/** rotation center y */
	int rotation_y;
	
	/* the dimensions of the window, used when center zoom is active */
	/** flag to activate/deactivate center zoom */
	boolean useCenterZoom;
	/** width of the window */
	int window_width;
	/** height of the window */
	int window_height;
	double adjust_x;
	double adjust_y;
	
	/* ################################################# */
	/* ################## Constructors ################# */
	/* ################################################# */

	/**
	 * Creates a new instance of a focus
	 * @return new instance of default focus
	 */
	public static Focus createNew() {
		return new Focus(0, 0, 1.0, 0.0, 100, 100);
	}
	/**
	 * Creates a new instance of a focus
	 * with given params
	 * @return new instances
	 * @param x
	 * @param y
	 * @return new instance
	 */
	public static Focus createNew(int x, int y) {
		return new Focus(x, y, 1.0, 0.0, 100, 100);
	}
	
	/**
	 * Creates a new instance of a focus
	 * with given params
	 * @param x
	 * @param y
	 * @param zoom
	 * @return new instance
	 */
	public static Focus createNew(int x, int y, double zoom) {
		return new Focus(x, y, zoom, 0.0, 100, 100);
	}
	
	/**
	 * Creates a new instance of a focus
	 * with given params
	 * @return new instances
	 * @param x
	 * @param y
	 * @param zoom
	 * @param rot_x
	 * @param rot_y
	 * @return new instance
	 */
	public static Focus createNew(int x, int y, double zoom, int rot_x, int rot_y) {
		return new Focus(x, y, zoom, 0.0, rot_x, rot_y);
	}
	
	private Focus(int x, int y, double zoom, double rot, int rot_x, int rot_y) {
		this.x = x;
		this.y = y;
		this.zoom = zoom;
		
		listeners = new ArrayList<>();
		listeners_detailed = new ArrayList<>();
		
		rotation = rot;
		rotation_x = rot_x;
		rotation_y = rot_y;
	}
	
	/* ################################################# */
	/* ################# Functionality ################# */
	/* ################################################# */
	
	public void setUseCenterZoom(boolean useCenterZoom, int win_width, int win_height) {
		this.useCenterZoom = useCenterZoom;
		this.window_width = win_width;
		this.window_height = win_height;
	}
	
	public void AddTo_X(int amt_x) {
		this.x += amt_x;
		handleCenterZoomAdjustments();
		notifyListeners(amt_x, 0, 0.0, 0.0);
	}
	
	public void AddTo_Y(int amt_y) {
		this.y += amt_y;
		handleCenterZoomAdjustments();
		notifyListeners(0, amt_y, 0.0, 0.0);
	}
	
	public void AddTo_Zoom(double amt_zoom) {
		this.zoom += amt_zoom;
		handleCenterZoomAdjustments();
		notifyListeners(0, 0, amt_zoom, 0.0);
	}
	
	public void AddTo_Rotation(double amt_rot) {
		this.rotation += amt_rot;
		notifyListeners(0, 0, 0.0, amt_rot);
	}
	
	private void handleCenterZoomAdjustments() {
		if(useCenterZoom) {	

			// remove old adjustments
			x -= adjust_x;
			y -= adjust_y;
			// adjust x and y to keep the same center
			double fct = 1.0 - zoom;
			adjust_x = (int) (window_width * 0.5 * fct)
					- (x * (1.0 - zoom));
			adjust_y = (int) (window_height * 0.5 * fct)
					- (y * (1.0 - zoom));
			// add new adjustments
			x += adjust_x;
			y += adjust_y;
		}
	}
	
	
	/* ################################################# */
	/* ################### Listeners ################### */
	/* ################################################# */
	
	public void register(FocusUpdateListener listener) {
		if(listener != null && !listeners.contains(listener))
			listeners.add(listener);
	}
	
	public void register(DetailedFocusUpdateListener listener) {
		if(listener != null && !listeners_detailed.contains(listener))
			listeners_detailed.add(listener);
	}

	public void unregister(FocusUpdateListener listener) {
		if(listener != null && listeners.contains(listener))
			listeners.remove(listener);
	}
	
	public void unregister(DetailedFocusUpdateListener listener) {
		if(listener != null && listeners_detailed.contains(listener))
			listeners_detailed.remove(listener);
	}
	
	private void notifyListeners(int change_x, int change_y
			, double change_zoom, double change_rotation) {
		for (FocusUpdateListener listener : listeners) {
			listener.onFocusUpdate();
		}
		for (DetailedFocusUpdateListener listener : listeners_detailed) {
			listener.onFocusUpdate(change_x, change_y, change_zoom, change_rotation);
		}
	}
	
	/* ################################################# */
	/* ############### Getters / Setters ############### */
	/* ################################################# */
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getZoom() {
		return zoom;
	}

	public void setX(int x) {
		this.x = x;
		notifyListeners(x - this.x, 0, 0.0, 0.0);
	}

	public void setY(int y) {
		this.y = y;
		notifyListeners(0, y - this.y, 0.0, 0.0);
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
		notifyListeners(0, 0, zoom - this.zoom, 0.0);
	}
	
	public void setRotation(double rotation) {
		this.rotation = rotation;
		notifyListeners(0, 0, 0.0, rotation - this.rotation);
	}
	
	public double getRotation() {
		return rotation;
	}

	public int getRotation_x() {
		return rotation_x;
	}

	public int getRotation_y() {
		return rotation_y;
	}

	public void setRotation_x(int rotation_x) {
		this.rotation_x = rotation_x;
		notifyListeners(0, 0, 0.0, 0.0);
	}

	public void setRotation_y(int rotation_y) {
		this.rotation_y = rotation_y;
		notifyListeners(0, 0, 0.0, 0.0);
	}

}
