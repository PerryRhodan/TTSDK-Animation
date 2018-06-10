# TTSDK-Animation

Below is a small example highlighting the features of this library and how to use them.

## Plain image drawing
The library offers the DrawableImage interface to offer a more conevnient way to draw differnt types of images. Below the implementation of a simple plain image is demonstrated.

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
The Animation class implements the same interface as the PlainImage, and can there be used in the same way - while at the same time being an animation with changing images. The example below uses the same Canvas, GraphicsContext, Focus, and DrawingPosition as the example above.

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
A key functionaility is the Focus class, which basically acts as a global camera position. Additionally a drawing position can specifiy an individual images position and orientation. Again, the example below uses the same Canvas, GraphicsContext, Focus, and DrawingPosition as the first example.

```java
// To demonstrate the focus and drawing position 
// a bit more, lets set up a similar test
Thread thread_testfocus = new Thread(new Runnable() {
    @Override
    public void run() {
	int max_cycles = 50;
	int cycle_duration = 100;
	// let's reuse the image from earlier.
	try {
	    int cycle_count = 0;
	    while (cycle_count < max_cycles) {
	
		// here we can update the focus
		// and or the image position
		// This could be done for instance
		// through key or mouse input
		focus.AddTo_X(5); //move focus by 5 pixels
		focus.AddTo_Zoom(0.01); //zoom in a bit
				
		// For instance add some rotation
		target.AddTo_Rotation(1); //rotate by a degree

		// so basically by chaning the focus you would
		// draw ALL images differently,
		// while changing the target for one image would
		// only affect this single image.
		
		Platform.runLater(new Runnable() {
	    	    @Override public void run() {
			// we need to re-draw at the 
			// updated target and focus
			 pimg.Draw(gc, target, focus);
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
thread_testfocus.start();
```
