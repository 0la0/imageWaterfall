package imgWaterfall;
import java.awt.Dimension;

import javax.swing.JFrame;


public class ImgWarterfallStart {

	public static void main(String[] args){
	
		int width = 700;
		int height = 750;
		
		Controls c = new Controls();
		JFrame frame01 = new JFrame();
		c.setMinimumSize(new Dimension(width, height));
		c.setPreferredSize(new Dimension(width, height));
		frame01.add(c);
		frame01.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame01.setSize(width, height);
		frame01.pack();
		frame01.setLocation(60, 0);
		frame01.setVisible(true);
		
	}
}
