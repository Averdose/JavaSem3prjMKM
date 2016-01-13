import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FaderShader {
	private int maxWidth, maxHeight;
	private int direction;
	private boolean blackOnWhite;
	FaderShader(boolean _blackOnWhite, int _direction) {
		blackOnWhite = _blackOnWhite;
		direction = _direction;
	}
	private int getGradient(int currentWidth, int currentHeight){
		switch(direction) {
		case 0 :
			float ratio = (float) currentWidth / maxWidth;
			if(blackOnWhite)
				return (int) (ratio * 255);
		case 1 :
			int area = maxWidth * maxHeight;
			int currentArea = currentWidth * currentHeight;
			ratio = (float) currentArea / area;
			return (int) (ratio * 255);
		case 2 :
			ratio = (float) currentHeight / maxHeight;
			return (int) (ratio * 255);
		case 3 :
			area = maxWidth * maxHeight;
			currentArea = (maxWidth - currentWidth) * currentHeight;
			ratio = (float) currentArea / area;
			return (int) (ratio * 255);
		case 4 :
			int width = maxWidth - currentWidth;
			ratio = (float) width / maxWidth;
			return (int) (ratio * 255);
		case 5 :
			area = maxWidth * maxHeight;
			currentArea = (maxWidth - currentWidth) * (maxHeight - currentHeight);
			ratio = (float) currentArea / area;
			return (int) (ratio * 255);
		case 6 :
			int height = maxHeight - currentHeight;
			ratio = (float) height / maxHeight;
			return (int) (ratio * 255);
		case 7 :
			area = maxWidth * maxHeight;
			currentArea = currentWidth * (maxHeight - currentHeight); //0 = minHeight
			ratio = (float) currentArea / area;
			return (int) (ratio * 255);
		}
		return -1;
	}
	
	public BufferedImage shadeImage(BufferedImage image) throws IOException {
		maxWidth = image.getWidth();
		maxHeight = image.getHeight();
		for(int h = 0; h < maxHeight; h++) {
			for(int w = 0; w < maxWidth; w++) {
				int pixel = image.getRGB(w, h);
				int gradient = getGradient(w ,h);
				Color baseColor = new Color(pixel);
				Color newColor = null;
				if(blackOnWhite) {
					float g = (float) gradient / 255;
					gradient = (int) g * 200;
					newColor = new Color(Math.min(baseColor.getRed() + gradient, 200),
							Math.min(baseColor.getGreen() + gradient, 200),
							Math.min(baseColor.getBlue() + gradient, 200));
				}
				else {
					newColor = new Color(Math.max(baseColor.getRed() - gradient, 0),
							Math.max(baseColor.getGreen() - gradient, 0),
							Math.max(baseColor.getBlue() - gradient, 0));
				}
				image.setRGB(w,  h, newColor.getRGB());
			}
		}
		// only for tests //
		//ImageIO.write(image, "BMP", new File("shadeImage.bmp"));
		return image;
	}
	
	public BufferedImage fadeImage(BufferedImage image) throws IOException {
		maxWidth = image.getWidth();
		maxHeight = image.getHeight();
		for(int h = 0; h < maxHeight; h++) {
			for(int w = 0; w < maxWidth; w++) {
				int pixel = image.getRGB(w, h);
				int gradient = getGradient(w ,h);
				Color baseColor = new Color(pixel);
				Color newColor = null;
				if(blackOnWhite) {
					newColor = new Color(Math.min(baseColor.getRed() + gradient, 254),
							Math.min(baseColor.getGreen() + gradient, 254),
							Math.min(baseColor.getBlue() + gradient, 254));
				}
				else {
					float g = (float) gradient / 255;
					gradient = (int) g * 200;
					newColor = new Color(Math.max(baseColor.getRed() - gradient, 55),
							Math.max(baseColor.getGreen() - gradient, 55),
							Math.max(baseColor.getBlue() - gradient, 55));
						
				}
				image.setRGB(w,  h, newColor.getRGB());
			}
		}
		// only for tests //
		//ImageIO.write(image, "PNG", new File("fadeImage.png"));
		return image;
	}
}
