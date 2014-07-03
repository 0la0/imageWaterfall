package imgWaterfall;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



@SuppressWarnings("serial")
public class Controls extends JPanel{

	private String imgPath = "files";		//REL PATH TO IMAGE FILES
	private JPanel btns = new JPanel();
	private JButton start = new JButton(" Start ");
	private JButton stop = new JButton (" Stop  ");
	private ImgWtrfl image = new ImgWtrfl();
	private String[] dir;
	private JSlider gravity = new JSlider(-2, 2);
	private JLabel _gravity = new JLabel("                       -gravity-");
	private JLabel _imageLabel = new JLabel("                      -images-");
	private JComboBox jcb;
	
	public Controls(){
    	dir = new File(imgPath).list();
    	jcb = new JComboBox(dir);
		image.setMinimumSize(new Dimension(705, 700));
		image.setPreferredSize(new Dimension(705, 700));
		btns.setLayout(new GridLayout(2, 3));
		btns.add(start);
		btns.add(_gravity);
		btns.add(_imageLabel);
		btns.add(stop);
		btns.add(gravity);
		btns.add(jcb);
		start.addActionListener(new BtnListener());
	    stop.addActionListener(new BtnListener());
	    jcb.addActionListener(new BtnListener());
	    jcb.setSelectedIndex(1);
		gravity.addChangeListener(new SliderListener());
		gravity.setValue(1);
		this.setMinimumSize(new Dimension(900, 705));
		this.setPreferredSize(new Dimension(900, 705));
		this.setLayout(new BorderLayout());
		this.add(btns, BorderLayout.NORTH);
		this.add(image, BorderLayout.CENTER);
	}
	
	private class BtnListener implements ActionListener{
	    @Override
	    public void actionPerformed(ActionEvent e){
	    	if (e.getSource() == start){
	    		image.start();	
	        }
	    	if (e.getSource() == stop){
	    		image.stop();	
	    	}
	    	if (e.getSource() == jcb){
	    		int k = jcb.getSelectedIndex();
	    		String temp = "files/" + dir[k];
	    		image.setImage(temp);
	    	}
	    }
	}
	
	private class SliderListener implements ChangeListener{
	    @Override
	    public void stateChanged(ChangeEvent ce){
	    	if (ce.getSource() == gravity){
	    		image.setGrav(gravity.getValue());
	    	}
	    }
	}
}
