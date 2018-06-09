/**
 * www.taleteller.de
 * 
 * TaletellerAnimation
 *   ImageSheet
 * 
 * Summary:
 *   
 * 
 * History:
 *   25.11.2017 - Cleaning of code
 *   
 * 
 * Ideas:
 *   Offer load method, which takes a path to a set of 
 *   images that are named in a convenient way to load 
 *   them as in as the image sheet.
 * 
 * Stephan Hogrefe, Edinburgh, 2017
 */
package de.taleteller.animation;

import java.util.ArrayList;

import de.taleteller.animation.focus.Focus;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


/**
 * 
 */
public class ImageSheet implements ImageSet {

	/** list of images to be drawn
	 *  in order, based on a given index */
	ArrayList<PlainImage> images;
	
	/** 
	 * Creates new ImageSheet with given images.
	 * @param images
	 */
	public ImageSheet(Image... images) {
		this.images = new ArrayList<>(images.length);
		for (int i = 0; i < images.length; i++) {
			this.images.add(new PlainImage(images[i]));
		}
	}
	
	/** 
	 * Creates new ImageSheet with given images.
	 * @param images
	 */
	public ImageSheet(PlainImage... images) {
		this.images = new ArrayList<>(images.length);
		for (int i = 0; i < images.length; i++) {
			this.images.add(images[i]);
		}
	}
	
	@Override
	public void Draw(GraphicsContext context, int index, DrawPosition target, Focus focus) {
		images.get(index).Draw(context, target, focus);
	}

	@Override
	public int getSize() {
		return images.size();
	}

	
}
