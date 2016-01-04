import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.*;
import java.io.*;
import java.lang.Math;
import javax.imageio.ImageIO;

public class ImageMerger {	
	private int minWidth, minHeight;
	private int maxWidth, maxHeight;
	private int mode;
	private BufferedImage baseImageA, baseImageB;
	public ImageMerger() {}
	public ImageMerger(int _mode) {
		mode = _mode;
	}
	/*TO IMPLEMENT private functions"
	 * setWhiteBackground()
	 * setImageExtension()
	 * getImageExtension()
	 * setBitsPerPixel()
	 */
	private void mergePixels(BufferedImage newImage, int width, int height) {
		if(baseImageA.getWidth() >= baseImageB.getWidth() && baseImageA.getHeight() <= baseImageB.getHeight()) {
			minWidth = (width - baseImageB.getWidth())/2;
			maxWidth = minWidth + baseImageB.getWidth();
			minHeight = (height - baseImageA.getHeight())/2;
			maxHeight = minHeight + baseImageA.getHeight();
			
			for(int w = minWidth, i = 0; w < maxWidth; w++, i++)
				for(int h = minHeight, j = 0; h < maxHeight; h++, j++) {
					int pixel1 = baseImageA.getRGB(w, j);
					int pixel2 = baseImageB.getRGB(i, h);
					switch(mode) {
						case 0 :
							newImage.setRGB(w, h, pixel1 ^ pixel2);
							break;
						case 1 :
							newImage.setRGB(w, h, pixel1 | pixel2);
							break;
						case 2 :
							newImage.setRGB(w, h, pixel1 & pixel2);
					}
					
				}
		}
		else if(baseImageA.getWidth() <= baseImageB.getWidth() && baseImageA.getHeight() >= baseImageB.getHeight()) {
			minWidth = (width - baseImageA.getWidth())/2;
			maxWidth = minWidth + baseImageA.getWidth();
			minHeight = (height - baseImageB.getHeight())/2;
			maxHeight = minHeight + baseImageB.getHeight();
			
			for(int w = minWidth, i = 0; w < maxWidth; w++, i++)
				for(int h = minHeight, j = 0; h < maxHeight; h++, j++) {
					int pixel1 = baseImageB.getRGB(w, j);
					int pixel2 = baseImageA.getRGB(i, h);
					switch(mode) {
						case 0 :
							newImage.setRGB(w, h, pixel1 ^ pixel2);
							break;
						case 1 :
							newImage.setRGB(w, h, pixel1 | pixel2);
							break;
						case 2 :
							newImage.setRGB(w, h, pixel1 & pixel2);	
					}
				}
		}
	}
	
	public BufferedImage mergeImages(String pathA, String pathB) throws IOException{
		baseImageA = ImageIO.read(new File(pathA));
		baseImageB = ImageIO.read(new File(pathB));
		
		int width = Math.max(baseImageA.getWidth(), baseImageB.getWidth());
		int height = Math.max(baseImageA.getHeight(), baseImageB.getHeight());
		
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics graph = newImage.getGraphics();
		graph.drawImage(baseImageA, (width - baseImageA.getWidth())/2, (height - baseImageA.getHeight())/2, null);
		graph.drawImage(baseImageB, (width - baseImageB.getWidth())/2, (height - baseImageB.getHeight())/2, null);
		
		mergePixels(newImage, width, height);
		/*only for tests*/
		ImageIO.write(newImage, "BMP", new File("newImage.bmp"));
		
		graph.dispose();
		baseImageA.flush();
		baseImageB.flush();
		
		return newImage;
	}
	
	/*direction: 0 - from L to R, 1 - from TL to BR, 2 - from T to B, 3 - from TR to BL
	 * 			 4 - from R to L, 5 - from BR to TL, 6 - from B to T, 7 - from BL to TR
	 */
	private int getGradient(int direction, int currentWidth, int currentHeight){
		switch(direction) {
		case 0 :
			float ratio = (float) currentWidth / maxWidth;
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
			currentArea = (maxWidth - (currentWidth - minWidth)) * currentHeight;
			ratio = (float) currentArea / area;
			return (int) (ratio * 255);
		case 4 :
			int width = maxWidth - (currentWidth - minWidth);
			ratio = (float) width / maxWidth;
			return (int) (ratio * 255);
		case 5 :
			area = maxWidth * maxHeight;
			currentArea = (maxWidth - (currentWidth - minWidth)) * (maxHeight - (currentHeight - minHeight));
			ratio = (float) currentArea / area;
			return (int) (ratio * 255);
		case 6 :
			int height = maxHeight - (currentHeight - minHeight);
			ratio = (float) height / maxHeight;
			return (int) (ratio * 255);
		case 7 :
			area = maxWidth * maxHeight;
			currentArea = currentWidth * (maxHeight - (currentHeight - minHeight));
			ratio = (float) currentArea / area;
			return (int) (ratio * 255);
		}
		return -1;
	}
	
	public BufferedImage shadeImage(BufferedImage image, int direction) throws IOException {
		if(direction == 2 || direction == 6) {
			for(int h = minHeight; h < maxHeight; h++) {
				for(int w = minWidth; w < maxWidth; w++) {
					int pixel = image.getRGB(w, h);
					int gradient = getGradient(direction, w ,h);
					Color baseColor = new Color(pixel);
					Color newColor = new Color(Math.max(baseColor.getRed() - gradient, 0),
							Math.max(baseColor.getGreen() - gradient, 0),
							Math.max(baseColor.getBlue() - gradient, 0));
					image.setRGB(w,  h, newColor.getRGB());
				}
			}
		}
		else {
			for(int w = minWidth; w < maxWidth; w++) {
				for(int h = minHeight; h < maxHeight; h++) {
					int pixel = image.getRGB(w, h);
					int gradient = getGradient(direction, w ,h);
					Color baseColor = new Color(pixel);
					Color newColor = new Color(Math.max(baseColor.getRed() - gradient, 0),
							Math.max(baseColor.getGreen() - gradient, 0),
							Math.max(baseColor.getBlue() - gradient, 0));
					image.setRGB(w,  h, newColor.getRGB());	
				}
			}
		}
		// only for tests //
		ImageIO.write(image, "BMP", new File("shadeImage.bmp"));
		return image;
	}
	
	public BufferedImage fadeImage(BufferedImage image, int direction) throws IOException {
		if(direction == 2 || direction == 6) {
			for(int h = minHeight; h < maxHeight; h++) {
				for(int w = minWidth; w < maxWidth; w++) {
					int pixel = image.getRGB(w, h);
					int gradient = getGradient(direction, w ,h);
					Color baseColor = new Color(pixel);
					Color newColor = new Color(Math.min(baseColor.getRed() + gradient, 254),
							Math.min(baseColor.getGreen() + gradient, 254),
							Math.min(baseColor.getBlue() + gradient, 254));
					image.setRGB(w,  h, newColor.getRGB());
				}
			}
		}
		else {
			for(int w = minWidth; w < maxWidth; w++) {
				for(int h = minHeight; h < maxHeight; h++) {
					int pixel = image.getRGB(w, h);
					int gradient = getGradient(direction, w ,h);
					Color baseColor = new Color(pixel);
					Color newColor = new Color(Math.min(baseColor.getRed() + gradient, 254),
							Math.min(baseColor.getGreen() + gradient, 254),
							Math.min(baseColor.getBlue() + gradient, 254));
					image.setRGB(w,  h, newColor.getRGB());	
				}
			}
		}
		// only for tests //
		ImageIO.write(image, "BMP", new File("fadeImage.bmp"));
		return image;
	}
}
