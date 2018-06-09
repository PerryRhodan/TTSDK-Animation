/**
 * www.taleteller.de
 * 
 * TaletellerAnimation
 *   DetailedFocusUpdateListener
 * 
 * Summary:
 *   
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

/**
 * 
 */
public interface DetailedFocusUpdateListener {

	/**
	 * Triggered when the focus updates, ie
	 * the "camera" has moved/zoomed/etc.
	 * @param change_x Change in x position
	 * @param change_y Change in y position
	 * @param change_zoom Change in zoom
	 * @param change_rotation Change in rotation (degrees)
	 */
	public void onFocusUpdate(int change_x, int change_y
			, double change_zoom, double change_rotation);
	
}
