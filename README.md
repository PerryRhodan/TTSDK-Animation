# TTSDK-Animation

Below is a small example highlighting the features of this library and how to use them.

## Plain image drawing
```java
// We need a JavaFx Canvas first
Canvas can = new Canvas(500,  500);
GraphicsContext gc = can.getGraphicsContext2D();

/*** plain image ***/
// Load a plain image
PlainImage pimg = new PlainImage(new Image("file:res/testimage.JPG"));
		
// Focus, ie the "camera" position
Focus focus = Focus.createNew(0, 0);
		
// The target draw position we want the image to be drawn at
DrawPosition target = new DrawPosition(0, 0, 500, 500);
		
// Then simply call the image's Draw() method
// This will draw the image at given position with given focus.
// If either of the two changes, we need to call Draw()
// again to actually update the image
pimg.Draw(gc, target, focus);
```

## Animation
```java
// First load our images required for the animation
// This could either be a sprite sheet, ie a single image
// that is divided into rects, each of which making up one
// of the animation's images.
// Alternatively use the ImageSheet, which maintains
// an array of individually loaded images for the animation.
ImageSheet img_sheet = new ImageSheet(new Image("file:res/anim_testimg_0.jpg")
					, new Image("file:res/anim_testimg_1.jpg")
					, new Image("file:res/anim_testimg_2.jpg")
					, new Image("file:res/anim_testimg_3.jpg"));
// Create the actual animation
// - set time in ms for the full animation to exectue
// - set weather or not the animation shall repeat automatically
		// - and finally set the image set loaded above
Animation test_anim = new Animation(1000, true, img_sheet);

// To demonstrate how the animation works, let's set up a 
// quick thread that will continue to update the images
Thread thread_testanim = new Thread(new Runnable() {
	@Override
	public void run() {

	    // Let's run this for a few seconds to show
	    // how the animation works
	    int max_cycles = 50;
	    // set 100 ms per iteration
	    int cycle_duration = 100;

	    // Now let's start the animation.
	    test_anim.Start();

	    try {
		int cycle_count = 0;
		while (cycle_count < max_cycles) {
		    // Call Update() for the animation
		    // You can always call this, if the 
		    // animation is not yet started,
		    // nothing will happen
		    test_anim.Update(cycle_duration);

		    // And then call Draw(), which will draw
		    // the current image of the animation.
		    // (As usual with JavaFX, run like this in
		    // an extra Runnable ran later)
		    Platform.runLater(new Runnable() {
			@Override public void run() {
			    // Draw at given target position using
			    // given focus
			    test_anim.Draw(gc, target, focus);
			}
		    });

		    cycle_count++;
		    Thread.sleep(cycle_duration);
  		}
	    } catch (InterruptedException e) {
		 e.printStackTrace();
	    }
	}
});
thread_testanim.start();
```



## Focus and target draw position
