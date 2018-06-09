/**
 * www.taleteller.de
 * 
 * TaletellerAnimation
 *   ImageSet
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
public interface ImageSet {

	public void Draw(GraphicsContext context, int index, DrawPosition target, Focus focus);
	
	/**
	 * Returns the number if images in this set.
	 * @return
	 */
	public int getSize();
}
