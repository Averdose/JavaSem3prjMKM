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
	
	int setColorDepth() {
		int depthA = baseImageA.getType(), depthB = baseImageB.getType();
		if(depthA == BufferedImage.TYPE_4BYTE_ABGR || depthA == BufferedImage.TYPE_INT_ARGB
				|| depthB == BufferedImage.TYPE_4BYTE_ABGR || depthB == BufferedImage.TYPE_INT_ARGB)
			return BufferedImage.TYPE_4BYTE_ABGR;
		else if(depthA == BufferedImage.TYPE_3BYTE_BGR || depthA == BufferedImage.TYPE_INT_BGR ||
				depthA == BufferedImage.TYPE_INT_RGB || depthB == BufferedImage.TYPE_3BYTE_BGR ||
				depthB == BufferedImage.TYPE_INT_BGR || depthB == BufferedImage.TYPE_INT_RGB)
			return BufferedImage.TYPE_INT_RGB;
		else if(depthA == BufferedImage.TYPE_USHORT_565_RGB || depthA == BufferedImage.TYPE_USHORT_GRAY ||
				depthB == BufferedImage.TYPE_USHORT_565_RGB || depthB == BufferedImage.TYPE_USHORT_GRAY)
			return BufferedImage.TYPE_USHORT_GRAY;
		else if(depthA == BufferedImage.TYPE_BYTE_GRAY || depthB == BufferedImage.TYPE_BYTE_GRAY)
			return BufferedImage.TYPE_BYTE_GRAY;
		else if(depthA == BufferedImage.TYPE_BYTE_BINARY || depthB == BufferedImage.TYPE_BYTE_BINARY)
			return BufferedImage.TYPE_BYTE_BINARY;
		return BufferedImage.TYPE_INT_RGB;
	}
	private void setWhiteBackground(BufferedImage image) {
		maxWidth = image.getWidth();
		maxHeight = image.getHeight();
		for(int w = 0; w < maxWidth; w++) {
			for(int h = 0; h < maxHeight; h++) {
				image.setRGB(w, h, 0xFFFFFF);
			}
		}
	}
	private void mergePixels(BufferedImage newImage, int width, int height) {
		System.out.println(baseImageA.getWidth() + " x " + baseImageA.getHeight() + " " 
	+ baseImageB.getWidth() + " x " + baseImageB.getHeight());
		if(baseImageA.getWidth() >= baseImageB.getWidth() && baseImageA.getHeight() <= baseImageB.getHeight()) {
			minWidth = (width - baseImageB.getWidth())/2;
			maxWidth = minWidth + baseImageB.getWidth();
			minHeight = (height - baseImageA.getHeight())/2;
			maxHeight = minHeight + baseImageA.getHeight();
			
			for(int w = minWidth, i = 0; w < maxWidth; w++, i++) {
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
		}
		else if(baseImageA.getWidth() <= baseImageB.getWidth() && baseImageA.getHeight() >= baseImageB.getHeight()) {
			minWidth = (width - baseImageA.getWidth())/2;
			maxWidth = minWidth + baseImageA.getWidth();
			minHeight = (height - baseImageB.getHeight())/2;
			maxHeight = minHeight + baseImageB.getHeight();
			
			for(int w = minWidth, i = 0; w < maxWidth; w++, i++) {
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
		else if(baseImageA.getWidth() > baseImageB.getWidth() && baseImageA.getHeight() > baseImageB.getHeight()) {
			minWidth = (width - baseImageB.getWidth())/2;
			maxWidth = minWidth + baseImageB.getWidth();
			minHeight = (height - baseImageB.getHeight())/2;
			maxHeight = minHeight + baseImageB.getHeight();
			
			for(int w = minWidth, i = 0; w < maxWidth; w++, i++) {
				for(int h = minHeight, j = 0; h < maxHeight; h++, j++) {
					int pixel1 = baseImageB.getRGB(i, j);
					int pixel2 = baseImageA.getRGB(w, h);
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
		else if(baseImageA.getWidth() < baseImageB.getWidth() && baseImageA.getHeight() < baseImageB.getHeight()) {
			minWidth = (width - baseImageA.getWidth())/2;
			maxWidth = minWidth + baseImageA.getWidth();
			minHeight = (height - baseImageA.getHeight())/2;
			maxHeight = minHeight + baseImageA.getHeight();
			
			for(int w = minWidth, i = 0; w < maxWidth; w++, i++) {
				for(int h = minHeight, j = 0; h < maxHeight; h++, j++) {
					int pixel1 = baseImageB.getRGB(w, h);
					int pixel2 = baseImageA.getRGB(i, j);
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
	}
	
	public BufferedImage mergeImages(String pathA, String pathB) throws IOException{
		baseImageA = ImageIO.read(new File(pathA));
		baseImageB = ImageIO.read(new File(pathB));
		
		int width = Math.max(baseImageA.getWidth(), baseImageB.getWidth());
		int height = Math.max(baseImageA.getHeight(), baseImageB.getHeight());
		
		BufferedImage newImage = new BufferedImage(width, height, setColorDepth());
		setWhiteBackground(newImage);
		
		Graphics graph = newImage.getGraphics();
		graph.drawImage(baseImageA, (width - baseImageA.getWidth())/2, (height - baseImageA.getHeight())/2, null);
		graph.drawImage(baseImageB, (width - baseImageB.getWidth())/2, (height - baseImageB.getHeight())/2, null);
		
		mergePixels(newImage, width, height);
		/*only for tests*/
		//ImageIO.write(newImage, "BMP", new File("newImage.bmp"));
		
		graph.dispose();
		baseImageA.flush();
		baseImageB.flush();
		
		return newImage;
	}
}
