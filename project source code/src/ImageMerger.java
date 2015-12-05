import java.awt.Graphics;
import java.awt.image.*;
import java.io.*;
import java.lang.Math;
import javax.imageio.ImageIO;

public class ImageMerger {	
	
	/*TO IMPLEMENT private functions"
	 * setWhiteBackground()
	 * setImageExtension()
	 * getImageExtension()
	 * setBitsPerPixel()
	 */
	private void mergePixels(BufferedImage baseImageA, BufferedImage baseImageB, BufferedImage newImage, int width, int height, int mode) {
		if(baseImageA.getWidth() >= baseImageB.getWidth() && baseImageA.getHeight() <= baseImageB.getHeight()) {
			int minWidth = (width - baseImageB.getWidth())/2;
			int maxWidth = minWidth + baseImageB.getWidth();
			int minHeight = (height - baseImageA.getHeight())/2;
			int maxHeight = minHeight + baseImageA.getHeight();
			
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
			int minWidth = (width - baseImageA.getWidth())/2;
			int maxWidth = minWidth + baseImageA.getWidth();
			int minHeight = (height - baseImageB.getHeight())/2;
			int maxHeight = minHeight + baseImageB.getHeight();
			
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
	public BufferedImage mergeImages(String pathA, String pathB, int mode) throws IOException{
		BufferedImage baseImageA = ImageIO.read(new File(pathA));
		BufferedImage baseImageB = ImageIO.read(new File(pathB));
		
		int width = Math.max(baseImageA.getWidth(), baseImageB.getWidth());
		int height = Math.max(baseImageA.getHeight(), baseImageB.getHeight());
		
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics graph = newImage.getGraphics();
		graph.drawImage(baseImageA, (width - baseImageA.getWidth())/2, (height - baseImageA.getHeight())/2, null);
		graph.drawImage(baseImageB, (width - baseImageB.getWidth())/2, (height - baseImageB.getHeight())/2, null);
		
		mergePixels(baseImageA, baseImageB, newImage, width, height, mode);
		/*only for tests*/
		ImageIO.write(newImage, "BMP", new File("newImage.bmp"));
		
		graph.dispose();
		baseImageA.flush();
		baseImageB.flush();
		
		return newImage;
	}
}
