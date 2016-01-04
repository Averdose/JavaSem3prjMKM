import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		String path1 = null,
				path2 = null;
		
		ImageMerger imageMerger = new ImageMerger(0);
		
		try {
			/*XOR==0, OR==1, AND==2*/
			BufferedImage img =	imageMerger.mergeImages(path1, path2);
			imageMerger.fadeImage(img, 7);
			imageMerger.shadeImage(img, 7);
			img.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
