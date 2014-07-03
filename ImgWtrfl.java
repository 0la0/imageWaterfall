package imgWaterfall;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class ImgWtrfl extends JPanel{

	private int width = 700;
	private int height = 700;
	private BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private Color pixelColor;
	private int matrixWidth = 140;
	private int matrixHeight = 140;
	private int[][][] points = new int[matrixWidth][matrixHeight][2];
	private int frameRate = 60;
	private Timer timer = new Timer(frameRate, new TimerListener());
	private int buf = (width / matrixWidth) / 2;
	private int wf = 1;
	private int cnt = 0;
	private int grav = 1;
	private int gravSwitch = 1;
	
	public ImgWtrfl(){
		initPoints();
	}
	
	private void iterate(){
		if ((cnt % 8) == 0)
			if (grav != 0)
				wf += grav;
		printImage();
		repaint();
		cnt++;
	}
	
	private void printImage(){
		Graphics2D g2d = bi.createGraphics();
		int height;
		int width;
		int xBuffer;
		
		if (gravSwitch > 0){
			for (int i = 0; i < matrixWidth; i++){
				for (int j = 0; j < matrixHeight; j++){
					int c = bi.getRGB(points[i][j][0], points[i][j][1]);
					pixelColor = new Color(c);
					g2d.setColor(pixelColor);
				
					height = (int) (wf * Math.random());
					width = (int) ((wf * 1.25) * Math.random());
					
					xBuffer = width / 2;
					g2d.fillRect(points[i][j][0] - xBuffer, points[i][j][1], width, height);
				}
			}
		} else if (gravSwitch < 0){
			for (int i = matrixWidth - 1; i >= 0; i--){
				for (int j = 0; j < matrixHeight; j++){
					height = (int) (wf * Math.random());
					width = (int) ((wf * 1.25) * Math.random());
					
					int c = bi.getRGB(points[i][j][0], points[i][j][1]);
					pixelColor = new Color(c);
					g2d.setColor(pixelColor);
				
					xBuffer = width / 2;
					g2d.fillRect(points[i][j][0] - xBuffer, points[i][j][1] - height, width, height);
				}
			}
		} else if (gravSwitch == 0){
			for (int i = matrixWidth - 1; i >= 0; i--){
				for (int j = 0; j < matrixHeight; j++){
					if (Math.random() < 0.5){
						wf = 12;
						height = (int) (wf * Math.random());
						width = (int) (wf * Math.random());
					
						int c = bi.getRGB(points[i][j][0], points[i][j][1]);
						pixelColor = new Color(c);
						g2d.setColor(pixelColor);
						
						xBuffer = width / 2;
						g2d.fillRect(points[i][j][0] - (xBuffer / 2), points[i][j][1] - (height / 2), width, height);
					}
				}
			}
		} 
		g2d.dispose();  
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, 0, 0, null);
		g2d.dispose();
	}
	
	private void initPoints(){
		int x = 0;
		int xIt = width / matrixWidth;
		int yIt = height / matrixHeight;
		for (int i = 0; i < matrixWidth; i++){
			int y = 0;
			for (int j = 0; j < matrixHeight; j++){
				points[i][j][0] = x + buf;
				points[i][j][1] = y;
				y += yIt;
			}
			x += xIt;
		}
	}
	
	public void setImage(String fileName){
		if (timer.isRunning())
			stop();
		try {
		    bi = ImageIO.read(new File(fileName));
		} catch (IOException e) {
		}
		wf = 1;
		cnt = 0;
		repaint();
	}
	
	public void start(){
		timer.start();
	}
	
	public void stop(){
		timer.stop();
	}
	
	public void setGrav(int grav){
		gravSwitch = grav;
		this.grav = Math.abs(grav);
	}

	private class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			iterate();
		} 	  
	}
}
