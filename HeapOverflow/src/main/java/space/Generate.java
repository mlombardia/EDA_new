package space;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Generate {

		public static void main(String[] args) throws IOException {
			
			ArrayList<BufferedImage> cartoons= new ArrayList<BufferedImage>(100000);
			
			for(int rec= 0; rec < 100000; rec++ )
			{
				String fileName= String.format("/%02d.jpg", rec % 12);
				BufferedImage img = ImageIO.read(new File( Generate.class.getClass().getResource(fileName).getFile() ));
				System.out.println(rec);
				cartoons.add(img);
			}
		}
}

//2025	2065	2066	2053	2045	2043