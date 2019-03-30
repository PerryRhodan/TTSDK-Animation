/**
 * www.taleteller.de
 * 
 * TaletellerCanvas
 *   TileImageData
 * 
 * Summary:
 *   
 * 
 * History:
 *   09.06.2018 - Cleaning of code and comments
 *   
 * 
 * Ideas:
 *   - TODO not pre-defined colours, but
 *       allow for all possible colours
 *   - TODO all setters and getters
 *   - TODO also have setters for normal 
 *       Image types for convenience
 * 
 * Stephan Hogrefe, Edinburgh, 2018
 */
package de.taleteller.animation;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.image.Image;

/**
 * Convenience class holding various image data
 * so they can be easily loaded from an image
 * directory containing the various colored
 * images for (hex-)tiles.
 */
public class TileImageData {

	/** default DrawableImage */
	DrawableImage img_default;
	/** selected */
	DrawableImage img_selected;
	/** hover */
	DrawableImage img_hover;
	
	/* borders */
	DrawableImage img_border_top;
	DrawableImage img_border_topright;
	DrawableImage img_border_bottomright;
	DrawableImage img_border_bottom;
	DrawableImage img_border_bottomleft;
	DrawableImage img_border_topleft;
	

	//////////////////////////////////////////////////////
	
	/** state images, ie the images used for draw states as
	 *  implemented in TTSDK-Grid */
	ArrayList<DrawableImage> state_images;
	
	//TODO other colors
	//TODO all setters and getters
	//TODO also have setters for normal Image type for convenience
	
	public TileImageData(DrawableImage img_default) {
		this.img_default = img_default;
		
		state_images = new ArrayList<>();
	}

	public TileImageData(Image image) {
		this.img_default = new PlainImage(image);
		state_images = new ArrayList<>();
	}

	public TileImageData(String path) {
		// load
		/** default */
		if(Files.exists(Paths.get(path + "/base/img_default.png")))
			img_default = new PlainImage(
					new Image("file:" + path + "/base/img_default.png"));
		/** selected */
		if(Files.exists(Paths.get(path + "/base/img_selected.png")))
			img_selected = new PlainImage(
					new Image("file:" + path + "/base/img_selected.png"));
		/** hover */
		if(Files.exists(Paths.get(path + "/base/img_hover.png")))
			img_hover = new PlainImage(
					new Image("file:" + path + "/base/img_hover.png"));
		
		/* border images */
		if(Files.exists(Paths.get(path + "/base/img_border_top.png")))
			img_border_top = new PlainImage(
					new Image("file:" + path + "/base/img_border_top.png"));
		if(Files.exists(Paths.get(path + "/base/img_border_topright.png")))
			img_border_topright = new PlainImage(
					new Image("file:" + path + "/base/img_border_topright.png"));
		if(Files.exists(Paths.get(path + "/base/img_border_bottomright.png")))
			img_border_bottomright = new PlainImage(
					new Image("file:" + path + "/base/img_border_bottomright.png"));
		if(Files.exists(Paths.get(path + "/base/img_border_bottom.png")))
			img_border_bottom = new PlainImage(
					new Image("file:" + path + "/base/img_border_bottom.png"));
		if(Files.exists(Paths.get(path + "/base/img_border_bottomleft.png")))
			img_border_bottomleft = new PlainImage(
					new Image("file:" + path + "/base/img_border_bottomleft.png"));
		if(Files.exists(Paths.get(path + "/base/img_border_topleft.png")))
			img_border_topleft = new PlainImage(
					new Image("file:" + path + "/base/img_border_topleft.png"));
		
		// load state images
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		Arrays.sort(listOfFiles);
		
		state_images = new ArrayList<>();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
			  // load files
			  Image img = new Image("file:" + listOfFiles[i].getPath());
			  System.out.println("TileImageData:: " + listOfFiles[i].getPath());
			  state_images.add(new PlainImage(img));
		  }
		}
		
	}
	
	//////////////////////////////////////////////////////

	public DrawableImage getImg_default() {
		return img_default;
	}

	public DrawableImage getImg_hover() {
		if(img_hover != null)
			return img_hover;
		else
			return img_default;
	}
	
	public DrawableImage getImg_selected() {
		if(img_selected != null)
			return img_selected;
		else
			return img_default;
	}
	
	public DrawableImage getFromValue(double value, double max) {
		if(state_images.size() <= 0)
			return img_default;
		int index
			= Math.min(state_images.size()-1, Math.max(0
					, (int)(value/max * state_images.size())));
		return state_images.get(index);
	}

	public ArrayList<DrawableImage> getState_images() {
		return state_images;
	}

	public void setImg_default(DrawableImage img_default) {
		this.img_default = img_default;
	}

	public void setImg_selected(DrawableImage img_selected) {
		this.img_selected = img_selected;
	}

	public void setImg_hover(DrawableImage img_hover) {
		this.img_hover = img_hover;
	}

	public void setState_images(DrawableImage... state_images) {
		this.state_images = new ArrayList<>(Arrays.asList(state_images));
	}

	public DrawableImage getImg_border_top() {
		return img_border_top;
	}

	public DrawableImage getImg_border_topright() {
		return img_border_topright;
	}

	public DrawableImage getImg_border_bottomright() {
		return img_border_bottomright;
	}

	public DrawableImage getImg_border_bottom() {
		return img_border_bottom;
	}

	public DrawableImage getImg_border_bottomleft() {
		return img_border_bottomleft;
	}

	public DrawableImage getImg_border_topleft() {
		return img_border_topleft;
	}

	public void setImg_border_top(DrawableImage img_border_top) {
		this.img_border_top = img_border_top;
	}

	public void setImg_border_topright(DrawableImage img_border_topright) {
		this.img_border_topright = img_border_topright;
	}

	public void setImg_border_bottomright(DrawableImage img_border_bottomright) {
		this.img_border_bottomright = img_border_bottomright;
	}

	public void setImg_border_bottom(DrawableImage img_border_bottom) {
		this.img_border_bottom = img_border_bottom;
	}

	public void setImg_border_bottomleft(DrawableImage img_border_bottomleft) {
		this.img_border_bottomleft = img_border_bottomleft;
	}

	public void setImg_border_topleft(DrawableImage img_border_topleft) {
		this.img_border_topleft = img_border_topleft;
	}
	
}
