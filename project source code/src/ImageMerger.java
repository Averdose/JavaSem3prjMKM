import java.awt.Graphics;
import java.awt.Color;
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
	private static final String[] EXTENSIONS = new String[]{
	        "png", "bmp","jpeg","JPEG","jpg","JPG","BMP", "PNG" // and other formats you need
	};
	private static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

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
	/*this function merges a list of paths to images */
	public BufferedImage Merge(List<String> paths) {
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
	public BufferedImage MergeAndFade(List<String> paths, boolean blackOnWhite, int direction) {
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
	public BufferedImage MergeAndShade(List<String> paths, boolean blackOnWhite, int direction) {
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
					faderShader.fadeImage(baseImageA);
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
	public List<BufferedImage> DirectoryMerge(List<String> directories) {
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
			img = Merge(list.get(i));
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
	public List<BufferedImage> DirectoryMergeAndFade(List<String> directories, boolean blackOnWhite, int direction) {
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
			img = MergeAndFade(list.get(i), blackOnWhite, direction);
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
	public List<BufferedImage> DirectoryMergeAndShade(List<String> directories, boolean blackOnWhite, int direction) {
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
			img = MergeAndShade(list.get(i), blackOnWhite, direction);
			if(img != null)
				images.add(img);
			img.flush();
			list.get(i).clear();
		}
		list.clear();
		return images;
	}
	/*not working atm*/
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
		//System.out.println(baseImageA.getWidth() + " x " + baseImageA.getHeight() + " " 
	//+ baseImageB.getWidth() + " x " + baseImageB.getHeight());
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
	
	public BufferedImage mergeImagesFadeShade() throws IOException{	
		int width = Math.max(baseImageA.getWidth(), baseImageB.getWidth());
		int height = Math.max(baseImageA.getHeight(), baseImageB.getHeight());
		
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
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
	
	public BufferedImage mergeImages() {
		int width = Math.max(baseImageA.getWidth(), baseImageB.getWidth());
		int height = Math.max(baseImageA.getHeight(), baseImageB.getHeight());
		
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
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
