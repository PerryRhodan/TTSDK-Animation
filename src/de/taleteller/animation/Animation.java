/**
 * www.taleteller.de
 * 
 * TaletellerAnimation
 *   Animation
 * 
 * Summary:
 *   
 * 
 * History:
 *   25.11.2017 - Cleaning of code
 *   
 * 
 * Ideas:
 *   - TODO isRunning public getter
 *   - TODO DrawWhenRequired() function
 *   - TODO Only draw in Draw() method when 
 *   		the animation is actually running.
 * 
 * Stephan Hogrefe, Edinburgh, 2017
 */
package de.taleteller.animation;

import de.taleteller.animation.focus.Focus;
import javafx.scene.canvas.GraphicsContext;


/**
 *
 */
public class Animation implements DrawableImage, Updating {

	/** General run flag for this animation */
	boolean flag_running;
	/** Paused flag */
	boolean flag_paused;
	/** A submode of pause. If set, the pause is limited to a given duration. */
	boolean flag_paused_duration;
	/** The duration to pause left in milli-seconds,
	 *  if flag_paused_duration is used. */
	int time_pausing;
	/** Repeat flag */
	boolean flag_repeat;
	/** current time accumulated through the update function. (milli-seconds) */
	int current_time;
	/** total time of the animation to finish. (milli-seconds) */
	int total_time;
	/** the ImageSet with the images to draw. */
	ImageSet images;
	/** Current index of image out of the image set to be drawn. 
	 *  Is basically current_time/total_time * max number of images */
	int current_index;
	
	/** flag which is set to true when the animation has finished
	 *  Will remain false when Animation is set to repeat. */
	boolean finished;
	
	
	/** flag to indicate that the animation has a new
	 *  index, and needs to be redrawn */
	boolean flag_hasnewimage;

	/**
	 * Create new Animiation with given parameters
	 * @param time total time in milli-seconds
	 * @param repeat
	 * @param images
	 */
	public Animation(int time, boolean repeat, ImageSet images) {
		this.images = images;
		this.time_pausing = 0;
		this.total_time = time;
		this.flag_repeat = repeat;
	}

	@Override
	public void Update(long delta) {
		/* set this to false in the beginning,
		 * to make sure it is false when the anim
		 * has been stopped or paused. */
		flag_hasnewimage = false;
		
		if(!flag_running)
			return;

		if(flag_paused) {
			if(flag_paused_duration) {
				time_pausing -= delta;
				if(time_pausing < 0)
					flag_paused = false;
				else
					return;
			} else
				return;
		}
		
		current_time += delta;
		if(flag_repeat)
			current_time%=total_time;
		
		if(current_time > total_time) {
			flag_running = false;
			current_index = images.getSize() -1;
			finished = true;
			return;
		}
		
		
		int prev_index = current_index;
		current_index = (int) (images.getSize() * (double)current_time/(double)total_time);
		
		if(prev_index != current_index)
			flag_hasnewimage = true;
		else
			flag_hasnewimage = false;
	}
	
	@Override
	public void Draw(GraphicsContext context, DrawPosition target, Focus focus) {
		images.Draw(context, current_index, target, focus);
	}

	/**
	 * Starts the animation.
	 */
	public void Start() {
		current_time = 0;
		flag_running = true;
		flag_paused = false;
	}

	/**
	 * Stops the animation.
	 */
	public void Stop() {
		flag_running = false;
	}

	/**
	 * Calls Stop() and then Start()
	 */
	public void Restart() {
		Stop();
		Start();
	}

	/**
	 * Sets pause flags to false.
	 */
	public void Resume() {
		flag_paused = false;
		flag_paused_duration = false;
	}

	/**
	 * Pauses until Resume is called.
	 */
	public void Pause() {
		flag_paused = true;
		flag_paused_duration = false;
		time_pausing = 0;
	}

	/**
	 * Pauses for given duration in milli-seconds.
	 * @param duration
	 */
	public void Pause(int duration) {
		flag_paused = true;
		flag_paused_duration = true;
		time_pausing = duration;
	}

	/**
	 * Set animation to repeat.
	 * @param flag
	 */
	public void setRepeat(boolean flag) {
		flag_repeat = flag;
	}

	/**
	 * Returns whether or not if animation
	 * is repeating.
	 * @return
	 */
	public boolean isRepeating() {
		return flag_repeat;
	}
	
	/**
	 * 
	 * @param index
	 */
	public void setIndex(int index) {
		this.current_index = index;
	}

	/**
	 * Returns whether or not this animations has finished 
	 * after it was running.
	 * Will always be false for repeating animations.
	 * @return
	 */
	public boolean isFinished() {
		return finished;
	}
	
	/**
	 * Indicates whether or not the animation has updated the 
	 * current index, ie if the animation needs to be redrawn
	 * in order to display the latest image.
	 * @return
	 */
	public boolean hasNewImage() {
		return flag_hasnewimage;
	}
}
