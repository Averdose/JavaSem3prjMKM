import java.awt.Color;
import java.awt.image.BufferedImage;

/**FaderShader class is responsible for shading and fading images during the merging process*/
public class FaderShader {
	private int maxWidth, maxHeight;
	/**direction variable holds the int value of direction of shading/merging :
	 * 0 - from left to right, 1 - from top left to bottom right, 2 - from top to bottom
	 * 3 - from top right to bottom left, 4 - from right to left, 5 - from bottom right to top left
	 * 6 - from bottom to top, 7 - from bottom left to top right, 8 - from centre outwards
	 */
	private int direction;
	/**blackOnWhite variable holds the boolean value :
	 * 1 - image has black colour and is placed on white background
	 * 2 - image has white colour and is placed on black background
	 */
	private boolean blackOnWhite;
	FaderShader(boolean _blackOnWhite, int _direction) {
		blackOnWhite = _blackOnWhite;
		direction = _direction;
	}
	/**Function getGradient() calculates the value of shade/fade gradient by which 
	 *  RGB value of pixel has to be increased/decreased.
	 *  The gradient calculation is based on ratio of already coloured part of image
	 *  to the total surface of image.
	 *  For top to bottom/left to right and reverse shading/fading operations, the function
	 *  calculates ratio of current width/height to total width/height.
	 *  Otherwise the function calculates the ratio of current shaded/faded surface of image
	 *  to the total surface.
	 *  boolean value grayShade, changes the ratio interval to [0, 200] if the image is meant to have
	 *  gray shade, otherwise the ratio interval is standard RGB [0, 255]
	 */
	private int getGradient(int currentWidth, int currentHeight, boolean grayShade){
		switch(direction) {
		case 0 :
			float ratio = (float) currentWidth / maxWidth;
			if(grayShade)
				return (int) (ratio * 200);
			return (int) (ratio * 255);
		case 1 :
			int area = maxWidth * maxHeight;
			int currentArea = currentWidth * currentHeight;
			ratio = (float) currentArea / area;
			if(grayShade)
				return (int) (ratio * 200);
			return (int) (ratio * 255);
		case 2 :
			ratio = (float) currentHeight / maxHeight;
			if(grayShade)
				return (int) (ratio * 200);
			return (int) (ratio * 255);
		case 3 :
			area = maxWidth * maxHeight;
			currentArea = (maxWidth - currentWidth) * currentHeight;
			ratio = (float) currentArea / area;
			if(grayShade)
				return (int) (ratio * 200);
			return (int) (ratio * 255);
		case 4 :
			int width = maxWidth - currentWidth;
			ratio = (float) width / maxWidth;
			if(grayShade)
				return (int) (ratio * 200);
			return (int) (ratio * 255);
		case 5 :
			area = maxWidth * maxHeight;
			currentArea = (maxWidth - currentWidth) * (maxHeight - currentHeight);
			ratio = (float) currentArea / area;
			if(grayShade)
				return (int) (ratio * 200);
			return (int) (ratio * 255);
		case 6 :
			int height = maxHeight - currentHeight;
			ratio = (float) height / maxHeight;
			if(grayShade)
				return (int) (ratio * 200);
			return (int) (ratio * 255);
		case 7 :
			area = maxWidth * maxHeight;
			currentArea = currentWidth * (maxHeight - currentHeight); 
			ratio = (float) currentArea / area;
			if(grayShade)
				return (int) (ratio * 200);
			return (int) (ratio * 255);
		case 8 :
			area = (maxWidth / 2) + (maxHeight / 2);
			currentArea = Math.abs((maxWidth / 2) - currentWidth) + Math.abs((maxHeight / 2) - currentHeight);
			ratio = (float) currentArea / area;
			if(grayShade)
				return (int) (ratio * 200);
			return (int) (ratio * 255);
		}
		return -1;
	}
	/**function shadeImage darkens the pixels of the image down to the value 55(dark gray)
	 *  on RGB scale, if blackOnWhite = 1
	 *  lightens the pixel of the image up to the value 254(white) on RGB scale if blackOnWhite = 0
	 *  the function iterates over every pixel of image changing its RGB value
	 */
	public BufferedImage shadeImage(BufferedImage image) {
		maxWidth = image.getWidth();
		maxHeight = image.getHeight();
		for(int h = 0; h < maxHeight; h++) {
			for(int w = 0; w < maxWidth; w++) {
				int pixel = image.getRGB(w, h);
				Color baseColor = new Color(pixel);
				Color newColor = null;
				if(!blackOnWhite) {
					int gradient = getGradient(w , h, false);
					newColor = new Color(Math.min(baseColor.getRed() + gradient, 254),
							Math.min(baseColor.getGreen() + gradient, 254),
							Math.min(baseColor.getBlue() + gradient, 254));
				}
				else {
					int gradient = getGradient(w , h, true);
					if(baseColor.getRed() >= 55)
						newColor = new Color(Math.max(baseColor.getRed() - gradient, 55),
								Math.max(baseColor.getGreen() - gradient, 55),
								Math.max(baseColor.getBlue() - gradient, 55));
					else
						newColor = new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue());
				}
				image.setRGB(w,  h, newColor.getRGB());
			}
		}
		
		return image;
	}
	/**function fadeImage darkens the pixels of the image down to the value 55(dark gray)
	 *  on RGB scale, if blackOnWhite = 0
	 *  lightens the pixel of the image up to the value 254(white) on RGB scale if blackOnWhite = 1
	 *  the function iterates over every pixel of image changing its RGB value
	 */
	public BufferedImage fadeImage(BufferedImage image) {
		maxWidth = image.getWidth();
		maxHeight = image.getHeight();
		for(int h = 0; h < maxHeight; h++) {
			for(int w = 0; w < maxWidth; w++) {
				int pixel = image.getRGB(w, h);
				Color baseColor = new Color(pixel);
				Color newColor = null;
				if(blackOnWhite) {
					int gradient = getGradient(w , h, false);
					newColor = new Color(Math.min(baseColor.getRed() + gradient, 254),
							Math.min(baseColor.getGreen() + gradient, 254),
							Math.min(baseColor.getBlue() + gradient, 254));
				}
				else {
					int gradient = getGradient(w , h, true);
					if(baseColor.getRed() >= 55)
						newColor = new Color(Math.max(baseColor.getRed() - gradient, 55),
								Math.max(baseColor.getGreen() - gradient, 55),
								Math.max(baseColor.getBlue() - gradient, 55));
					else
						newColor = new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue());
						
				}
				image.setRGB(w,  h, newColor.getRGB());
			}
		}
		
		return image;
	}
}
