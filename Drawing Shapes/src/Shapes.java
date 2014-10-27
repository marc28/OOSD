import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Shapes {
	
	public Shapes(){
		
		JFrame frame = new JFrame("Drawing");
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		DrawingPanel obj = new DrawingPanel();
		frame.add(obj);
		frame.pack();
		obj.drawing();
		
	}
	
	public static void main(String args []){
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new Shapes();
			}
		});

	
	}

}
