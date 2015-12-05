import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		String path1 = null, path2 = null;
		
		ImageMerger imageMerger = new ImageMerger();
		
		try {
			/*XOR==0, OR==1, AND==2*/
			imageMerger.mergeImages(path1, path2, 0).flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
