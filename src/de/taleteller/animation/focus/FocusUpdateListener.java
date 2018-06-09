/**
 * www.taleteller.de
 * 
 * TaletellerAnimation
 *   FocusUpdateListener
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
public interface FocusUpdateListener {

	/**
	 * Triggered when the focus updates, ie
	 * the "camera" has moved/zoomed/etc.
	 */
	public void onFocusUpdate();
	
}
