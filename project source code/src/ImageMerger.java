import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.*;
import java.io.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


/* in constructor mode = 0 -> XOR, mode = 1 -> OR, mode = 2 -> AND*/
public class ImageMerger {	
	private int minWidth, minHeight;
	private int maxWidth, maxHeight;
	private int mode;
	private BufferedImage baseImageA, baseImageB;
	private final String[] EXTENSIONS = new String[]{
	       "TIF", "TIFF", "tif", "tiff", "png", "bmp","jpeg","JPEG","jpg","JPG","BMP", "PNG" // and other formats you need
	};
	private final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
	};
	public ImageMerger() {}
	public ImageMerger(int _mode) {
		mode = _mode;
	}
	/* takes formats: .bmp .jpg .jpeg .png .tiff .tif, bitsPerPixel: 1, 8, 16, 24, 32(32 not for bmp)*/
	public void saveImage(BufferedImage image, String fileName, String format, int bitsPerPixel, String destination) {
		int width = image.getWidth();
		int height = image.getHeight();
		int bpp = 24;
		if(bitsPerPixel == 32)
			bpp = BufferedImage.TYPE_4BYTE_ABGR;
		else if(bitsPerPixel == 24)
			bpp = BufferedImage.TYPE_INT_RGB;
		else if(bitsPerPixel == 16)
			bpp = BufferedImage.TYPE_USHORT_565_RGB;
		else if(bitsPerPixel == 8)
			bpp = BufferedImage.TYPE_BYTE_GRAY;
		else if(bitsPerPixel == 1)
			bpp = BufferedImage.TYPE_BYTE_BINARY;
		
		BufferedImage newImage = new BufferedImage(width, height, bpp);
		Graphics graph = newImage.getGraphics();
		graph.drawImage(image, 0, 0, null);
		graph.dispose();
		image.flush();
		
		try {
			ImageIO.write(newImage, format.toUpperCase(), new File(destination+"\\" + fileName + "." + format));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*this function merges a list of paths to images */
	public BufferedImage merge(List<String> paths) {
		System.out.println("Merging");
		//System.out.println(paths.size());
		if(paths.size() == 1) {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(paths.get(0)));
				return img;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return null;
			}
		}
		boolean success = false;
		for(int i = 0, a = 0; i < paths.size() - 1; i++) {
			try {
			//	System.out.println(paths.get(i));
				if(a == 0)
					baseImageA = ImageIO.read(new File(paths.get(i)));
				a++;
				baseImageB = ImageIO.read(new File(paths.get(i + 1)));
				baseImageA = mergeImages();
				success = true;
			} catch(IOException e) {
				continue;
			}
		}
		if(success == true)
		{
			baseImageB.flush();
			return baseImageA;
		}
		if(baseImageA != null && baseImageB == null)
			return baseImageA;
		if(baseImageA == null && baseImageB != null)
			return baseImageB;
		return null;
	}
	/*merges and fades a list of paths to images, blackOnWhite - true if image is black on white background
	 * direction 0 - from left to right, 1 - from top left to bottom right, and so on clockwise from 0 to 7
	 */
	public BufferedImage mergeAndFade(List<String> paths, boolean blackOnWhite, int direction) {
		FaderShader faderShader = new FaderShader(blackOnWhite, direction);
		if(paths.size() == 1) {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(paths.get(0)));
				faderShader.fadeImage(img);
				return img;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return null;
			}
		}
		boolean success = false;
		for(int i = 0, a = 0; i < paths.size() - 1; i++) {
			try {
				if(a == 0)
				{
					baseImageA = ImageIO.read(new File(paths.get(i)));
					faderShader.fadeImage(baseImageA);
				}
				a++;
				baseImageB = ImageIO.read(new File(paths.get(i + 1)));
				faderShader.fadeImage(baseImageB);
				baseImageA = mergeImages();
				success = true;
			} catch(IOException e) {
				continue;
			}
		}
		if(success == true)
		{
			baseImageB.flush();
			return baseImageA;
		}
		if(baseImageA != null && baseImageB == null)
			return baseImageA;
		if(baseImageA == null && baseImageB != null)
			return baseImageB;
		return null;
	}
	/*merges and shades a list of paths to images, blackOnWhite - true if image is black on white background
	 * direction 0 - from left to right, 1 - from top left to bottom right, and so on clockwise from 0 to 7
	 */
	public BufferedImage mergeAndShade(List<String> paths, boolean blackOnWhite, int direction) {
		FaderShader faderShader = new FaderShader(blackOnWhite, direction);
		if(paths.size() == 1) {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(paths.get(0)));
				faderShader.shadeImage(img);
				return img;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return null;
			}
		}
		boolean success = false;
		for(int i = 0, a = 0; i < paths.size() - 1; i++) {
			try {
				if(a == 0)
				{
					baseImageA = ImageIO.read(new File(paths.get(i)));
					faderShader.shadeImage(baseImageA);
				}
				a++;
				baseImageB = ImageIO.read(new File(paths.get(i + 1)));
				faderShader.shadeImage(baseImageB);
				baseImageA = mergeImages();
				success = true;
			} catch(IOException e) {
				continue;
			}
		}
		if(success == true)
		{
			baseImageB.flush();
			return baseImageA;
		}
		if(baseImageA != null && baseImageB == null)
			return baseImageA;
		if(baseImageA == null && baseImageB != null)
			return baseImageB;
		return null;
	}
	/*this function merges a list of paths to directories, merging image1 from dir1, dir2, dir3, image2 from
	 * dir1, dir2, dir3 and so on. returns list of merged images */
	public List<BufferedImage> directoryMerge(List<String> directories) {
		List<List<String>> list = new ArrayList<List<String>>();
		int maxNumberOfFiles = -1;
		for(int i = 0; i < directories.size(); i++) {
			File dir = new File(directories.get(i));
			//System.out.println(dir.getName());
			if (dir.isDirectory()) {
				int j = 0;
				for(final File file : dir.listFiles(IMAGE_FILTER)) {
				//	System.out.println("\t" + file.getName() + " " + j + " " + maxNumberOfFiles);
					if(j > maxNumberOfFiles)
					{
						list.add(new ArrayList<String>());
						maxNumberOfFiles = j;
					}
					list.get(j).add(file.getAbsolutePath());
					j++;
				}
			}	
		}
	//	System.out.println(list.size());
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		BufferedImage img = null;
		for(int i = 0; i < list.size(); i++)  {
			img = merge(list.get(i));
			if(img != null)
			{
				images.add(img);
				img.flush();
			}
			list.get(i).clear();
		}
		list.clear();
		//System.out.println(images.size());
		return images;		
	}
	/*this function merges and fades a list of paths to directories, merging image1 from dir1, dir2, dir3,
	 *  image2 from dir1, dir2, dir3 and so on. returns list of merged images */
	public List<BufferedImage> directoryMergeAndFade(List<String> directories, boolean blackOnWhite, int direction) {
		List<List<String>> list = new ArrayList<List<String>>();
		int maxNumberOfFiles = -1;
		for(int i = 0; i < directories.size(); i++) {
			File dir = new File(directories.get(i));
			if (dir.isDirectory()) {
				int j = 0;
				for(final File file : dir.listFiles(IMAGE_FILTER)) {
					if(j > maxNumberOfFiles)
					{
						list.add(new ArrayList<String>());
						maxNumberOfFiles = j;
					}
					list.get(j).add(file.getAbsolutePath());
					j++;
				}
			}	
		}
		
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		BufferedImage img = null;
		for(int i = 0; i < list.size(); i++)  {
			img = mergeAndFade(list.get(i), blackOnWhite, direction);
			if(img != null)
				images.add(img);
			img.flush();
			list.get(i).clear();
		}
		list.clear();
		return images;
	}
	/*this function merges and shades a list of paths to directories, merging image1 from dir1, dir2, dir3,
	 *  image2 from dir1, dir2, dir3 and so on. returns list of merged images */
	public List<BufferedImage> directoryMergeAndShade(List<String> directories, boolean blackOnWhite, int direction) {
		List<List<String>> list = new ArrayList<List<String>>();
		int maxNumberOfFiles = -1;
		for(int i = 0; i < directories.size(); i++) {
			File dir = new File(directories.get(i));
			if (dir.isDirectory()) {
				int j = 0;
				for(final File file : dir.listFiles(IMAGE_FILTER)) {
					if(j > maxNumberOfFiles)
					{
						list.add(new ArrayList<String>());
						maxNumberOfFiles = j;
					}
					list.get(j).add(file.getAbsolutePath());
					j++;
				}
			}	
		}
		
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		BufferedImage img = null;
		for(int i = 0; i < list.size(); i++)  {
			img = mergeAndShade(list.get(i), blackOnWhite, direction);
			if(img != null)
				images.add(img);
			img.flush();
			list.get(i).clear();
		}
		list.clear();
		return images;
	}

	int setColorDepth() {
		int depthA = baseImageA.getType(), depthB = baseImageB.getType();
		if(depthA == BufferedImage.TYPE_3BYTE_BGR || depthA == BufferedImage.TYPE_INT_BGR ||
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
		int width = image.getWidth();
		int height = image.getHeight();
		
		Graphics g = image.getGraphics();
		g.setColor(new Color(0xFF, 0xFF, 0xFF));
		g.fillRect(0, 0, width, height);
		g.dispose();
	}
	private void mergePixels(BufferedImage newImage, int width, int height) {
		if(baseImageA.getWidth() >= baseImageB.getWidth() && baseImageA.getHeight() <= baseImageB.getHeight()) {
			minWidth = (width - baseImageB.getWidth())/2;
			maxWidth = minWidth + baseImageB.getWidth();
			minHeight = (height - baseImageA.getHeight())/2;
			maxHeight = minHeight + baseImageA.getHeight();
			
			for(int w = minWidth, i = 0; w < maxWidth; w++, i++) {
				for(int h = minHeight, j = 0; h < maxHeight; h++, j++) {
					int pixelA = baseImageA.getRGB(w, j);
					int pixelB = baseImageB.getRGB(i, h);
					switch(mode) {
						case 0 :
							newImage.setRGB(w, h, pixelA ^ pixelB);
							break;
						case 1 :
							newImage.setRGB(w, h, pixelA | pixelB);
							break;
						case 2 :
							newImage.setRGB(w, h, pixelA & pixelB);
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
					int pixelA = baseImageB.getRGB(w, j);
					int pixelB = baseImageA.getRGB(i, h);
					switch(mode) {
						case 0 :
							newImage.setRGB(w, h, pixelA ^ pixelB);
							break;
						case 1 :
							newImage.setRGB(w, h, pixelA | pixelB);
							break;
						case 2 :
							newImage.setRGB(w, h, pixelA & pixelB);
					}		
				}
			}
		}
		else if(baseImageA.getWidth() >= baseImageB.getWidth() && baseImageA.getHeight() >= baseImageB.getHeight()) {
			minWidth = (width - baseImageB.getWidth())/2;
			maxWidth = minWidth + baseImageB.getWidth();
			minHeight = (height - baseImageB.getHeight())/2;
			maxHeight = minHeight + baseImageB.getHeight();
			
			for(int w = minWidth, i = 0; w < maxWidth; w++, i++) {
				for(int h = minHeight, j = 0; h < maxHeight; h++, j++) {
					int pixelA = baseImageB.getRGB(i, j);
					int pixelB = baseImageA.getRGB(w, h);
					switch(mode) {
						case 0 :
							newImage.setRGB(w, h, pixelA ^ pixelB);
							break;
						case 1 :
							newImage.setRGB(w, h, pixelA | pixelB);
							break;
						case 2 :
							newImage.setRGB(w, h, pixelA & pixelB);
					}		
				}
			}
		}
		else if(baseImageA.getWidth() <= baseImageB.getWidth() && baseImageA.getHeight() <= baseImageB.getHeight()) {
			minWidth = (width - baseImageA.getWidth())/2;
			maxWidth = minWidth + baseImageA.getWidth();
			minHeight = (height - baseImageA.getHeight())/2;
			maxHeight = minHeight + baseImageA.getHeight();
			
			for(int w = minWidth, i = 0; w < maxWidth; w++, i++) {
				for(int h = minHeight, j = 0; h < maxHeight; h++, j++) {
					int pixelA = baseImageB.getRGB(w, h);
					int pixelB = baseImageA.getRGB(i, j);
					switch(mode) {
						case 0 :
							newImage.setRGB(w, h, pixelA ^ pixelB);
							break;
						case 1 :
							newImage.setRGB(w, h, pixelA | pixelB);
							break;
						case 2 :
							newImage.setRGB(w, h, pixelA & pixelB);
					}		
				}
			}
		}
	}
	
	public BufferedImage mergeImages() {
		int width = Math.max(baseImageA.getWidth(), baseImageB.getWidth());
		int height = Math.max(baseImageA.getHeight(), baseImageB.getHeight());
		
		BufferedImage newImage = new BufferedImage(width, height, setColorDepth());
		setWhiteBackground(newImage);
		
		Graphics graph = newImage.getGraphics();
		graph.drawImage(baseImageA, (width - baseImageA.getWidth())/2, (height - baseImageA.getHeight())/2, null);
		graph.drawImage(baseImageB, (width - baseImageB.getWidth())/2, (height - baseImageB.getHeight())/2, null);
		
		mergePixels(newImage, width, height);
		
		graph.dispose();
		baseImageA.flush();
		baseImageB.flush();
		
		return newImage;
	}
}
