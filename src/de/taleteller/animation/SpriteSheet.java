/**
 * www.taleteller.de
 * 
 * TaletellerAnimation
 *   SpriteSheet
 * 
 * Summary:
 *   Index definitions:
 *   Example for 3 columns, 5 rows
 *   00 05 10
 *   01 06 11
 *   02 07 12
 *   03 08 13
 *   04 09 14
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

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import de.taleteller.animation.focus.Focus;


/**
 * 
 */
public class SpriteSheet implements ImageSet {
	
	/* sprite sheet */
	Image sheet;
	
	/* single element dimensions in PIXELS */
	int element_width;
	int element_height;
	/* spite sheet dimensions in ELEMENTS */
	int rows;
	int colums;

	public SpriteSheet(Image sheet, int rows, int colums,
			int element_width, int element_height) {
		this.sheet = sheet;
		this.rows = rows;
		this.colums = colums;
		this.element_width = element_width;
		this.element_height = element_height;

	}
	
	@Override
	public void Draw(GraphicsContext context, int index, DrawPosition target, Focus focus) {
		context.save();
		// apply rotation
		Rotate r = new Rotate(target.getRotation()
				, target.getRotation_x() * focus.getZoom() + focus.getX()
				, target.getRotation_y() * focus.getZoom() + focus.getY());
        context.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
        // draw image
		context.drawImage(sheet
				, index/rows * element_width
				, index%rows * element_height
				, element_width, element_height
				, target.getX() * focus.getZoom() + focus.getX()
				, target.getY() * focus.getZoom() + focus.getY()
				, target.getW(), target.getH());
		context.restore();
	}

	@Override
	public int getSize() {
		return rows * colums;
	}

}
