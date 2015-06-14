package rendering;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import core.Main;

public class AnimatedImage extends Image {
	
	private BufferedImage[] frames;
	private AnimatedImageThread animator;
	private Thread animatorThread;
	private boolean loading = true;
	
	public AnimatedImage(File gif, Main main) throws IOException {
		ImageReader imageReader = ImageIO.getImageReadersBySuffix("gif").next();
		imageReader.setInput(ImageIO.createImageInputStream(gif));
		frames = new BufferedImage[imageReader.getNumImages(true)];
		Iterator<IIOImage> iterator = imageReader.readAll(null);
		int index = 0;
		while (iterator.hasNext()) {
			RenderedImage image = iterator.next().getRenderedImage();
			ColorModel cm = image.getColorModel();
			int width = image.getWidth();
			int height = image.getHeight();
			WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
			boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
			Hashtable properties = new Hashtable();
			String[] keys = image.getPropertyNames();
			if (keys != null) {
				for (int i = 0; i < keys.length; i++) {
					properties.put(keys[i], image.getProperty(keys[i]));
				}
			}
			BufferedImage result = new BufferedImage(cm, raster, isAlphaPremultiplied, properties);
			image.copyData(raster);
			if (index > 0 && (frames[index - 1].getHeight() != height || frames[index - 1].getWidth() != width)) {
				throw new IllegalArgumentException("Frames not consistent.");
			}
			frames[index] = result;
			index++;
		}
		animator = new AnimatedImageThread(frames.length);
		animatorThread = new Thread(null, animator, "Animated image " + main.getRenderer().getAnimatedImageNum());
		animatorThread.start();
		loading = false;
	}
	
	public BufferedImage getFrame() {
		return frames[animator.frame()];
	}

	public BufferedImage getFrame(int frame) {
		return frames[frame];
	}
	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHeight(ImageObserver obs) {
		// TODO Auto-generated method stub
		if (loading) {
			return -1;
		}
		else {
			return frames[0].getHeight();
		}
	}

	@Override
	public Object getProperty(String arg0, ImageObserver arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageProducer getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth(ImageObserver obs) {
		// TODO Auto-generated method stub
		if (loading) {
			return -1;
		}
		else {
			return frames[0].getWidth();
		}
	}

}
