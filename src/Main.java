import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Main {
	static int cosThing = 1;
	public static void main(String[] args){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeigth = screenSize.height;
		JFrame frame = new JFrame("interactive graphics");
		frame.setSize(screenWidth/2, screenHeigth/2);
		frame.setLocationRelativeTo(null);
		
		MyPanel panel = new MyPanel();
		
		Timer t = new Timer(16,(ActionListener)panel);
		t.start();

		frame.add(panel);
		
		frame.setVisible(true);

	}
	private static class IncC implements ActionListener{
		public void actionPerformed(ActionEvent e){
			cosThing++;
		}
	}
	private static class ChangeC implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider slider = (JSlider) e.getSource();
			int sliderVal= slider.getValue();
			cosThing = sliderVal;
		}
		
	}
	
	private static   class  MyPanel extends JPanel implements ActionListener {
		private double spinny=0;
		int ballSize = 60;

		public MyPanel(){
			super();
			JButton incX = new JButton();
			JSlider changeX = new JSlider(-10,10);
			IncC incListener = new IncC();
			
			ChangeC changeC = new ChangeC();
			changeX.addChangeListener(changeC);
			incX.addActionListener(incListener);
			this.add(changeX);
			this.add(incX);
		}


		public void paintComponent(Graphics g){
			super.paintComponent(g);
			int width = this.getWidth();
			int heigth = this.getHeight();
			//for (int ki = 1; ki<30; ki++){
				for (int i = 1; i<25; i++){
					g.setColor(new Color(100,i*4+100,i*8,255));

					g.fillOval( ((width/2)  + (int)(getX(i*cosThing)*500*heigth/1500) -ballSize/2) ,	 	//x pos
							(heigth/2) + (int)(getY(i)*500*heigth/1500)-ballSize/2,		//y pos
							ballSize*heigth/1500, ballSize*heigth/1500);

				}

			//}
		}


		public double getX(double i){
			return Math.sin(spinny*i);
		}
		public double getY(double i){
			return Math.cos(spinny*i);
		}

		public void actionPerformed(ActionEvent e){
			
			this.repaint();
			spinny=spinny +0.001;
		}
	}

}
