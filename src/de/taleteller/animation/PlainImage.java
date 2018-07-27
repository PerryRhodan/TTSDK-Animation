/**
 * www.taleteller.de
 * 
 * TaletellerAnimation
 *   PlainImage
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
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

/**
 * 
 */
public class PlainImage implements DrawableImage {

	/** Actual image */
	Image image;
	
	/**
	 * Create new instance with given image data.
	 * @param image
	 */
	public PlainImage(Image image) {
		this.image = image;
	}

	@Override
	public void Draw(GraphicsContext context, DrawPosition target, Focus focus) {
		context.save();
		// apply rotation
		Rotate r = new Rotate(focus.getRotation() + target.getRotation()
				, focus.getRotation_x() + target.getRotation_x() * focus.getZoom() + focus.getX()
				, focus.getRotation_y() + target.getRotation_y() * focus.getZoom() + focus.getY());
        	context.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
		context.setGlobalAlpha(target.getAlpha());
        	// draw image
		context.drawImage(image
				, target.getX() * focus.getZoom() + focus.getX()
				, target.getY() * focus.getZoom() + focus.getY()
				, target.getW() * focus.getZoom()
				, target.getH() * focus.getZoom());
		context.restore();
	}

}
