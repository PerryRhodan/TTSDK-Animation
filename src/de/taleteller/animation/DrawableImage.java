/**
 * www.taleteller.de
 * 
 * TaletellerAnimation
 *   DrawableImage
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
package de.taleteller.animation;

import de.taleteller.animation.focus.Focus;
import javafx.scene.canvas.GraphicsContext;


/**
 * 
 */
public interface DrawableImage {

	/**
	 * Draws image in given context with given target position 
	 * and focus.
	 * @param context
	 * @param target
	 * @param focus
	 */
	public void Draw(GraphicsContext context, DrawPosition target, Focus focus);
	

}
