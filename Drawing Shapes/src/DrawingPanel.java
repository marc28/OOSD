import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * buttonchoice: 
 * line = 1
 * circle = 2
 * square = 3
 * triangle = 4
 */

public class DrawingPanel extends JPanel implements Serializable{

	// Components
	private JPanel topMenu = new JPanel();
	private JPanel bottomMenu = new JPanel();
	private JButton lineBtn, circleBtn, triangleBtn, squareBtn, polygonBtn,
			colorBtn, fillBtn, brushSizeBig, brushSizeSmall;
	private JButton undoBtn, resetBtn, loadBtn, saveBtn;

	// variables
	private int buttonChoice = 1, clickCounter = 0;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Color> colours = new ArrayList<Color>();
	private ArrayList<Shape> shapesIn = new ArrayList<Shape>();
	private ArrayList<Shape> shapesOut = new ArrayList<Shape>();
	private ArrayList<Boolean> fillList = new ArrayList<Boolean>();
	private Graphics2D g2d;
	private Point start, end,middle, polyOne, polyTwo, polyThree, polyFour;
	private boolean isDrawing = false, fillOrOutline = true;
	private Shape lineShape, circleShape, rectangle, triangleLine, triangleLine2;
	private Color color = Color.BLUE;
	private File f = new File("shapes.dat");
	private float strokeSize = 1;

	/**
	 * Constructor TODO constructor
	 */
	public DrawingPanel() {
		setBorder(BorderFactory.createLineBorder(color));
		Dimension size = getPreferredSize();
		size.width = 800;
		size.height = 500;
		setPreferredSize(size);
		addButtonsToMenuPanel(); // adding the buttons to the Panel
		// Set the layout of the panel
		setLayout(new BorderLayout()); //border Layout for the Buttons
		setBackground(Color.white); //set the canvas white
		// Connect components to Panel
		add(topMenu, BorderLayout.PAGE_START);
		add(bottomMenu, BorderLayout.PAGE_END);
	


		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				if (isDrawing) {
					if(buttonChoice == 1){
					lineShape = drawLine(start.x, start.y, e.getX(),e.getY());
					}else if(buttonChoice == 2 ){
						circleShape = drawCircle(start.x, start.y, e.getX(),e.getY());
					}else if(buttonChoice == 3 ){
						rectangle = drawRectangle(start.x, start.y, e.getX(),e.getY());
					}else if(buttonChoice == 4){
						triangleLine = drawLine(start.x, start.y, e.getX(), e.getY());
							if(clickCounter == 2)
								triangleLine2 = drawLine(middle.x, middle.y, e.getX(), e.getY());
					}else if(buttonChoice == 5){
							lineShape = drawLine(start.x, start.y, e.getX(), e.getY());
							if(clickCounter == 2)
								lineShape = drawLine(polyOne.x, polyOne.y, e.getX(), e.getY());
							if(clickCounter == 3)
								lineShape = drawLine(polyTwo.x, polyTwo.y, e.getX(), e.getY());
							if(clickCounter == 4)
								lineShape = drawLine(polyThree.x, polyThree.y, e.getX(), e.getY());
					}
					repaint();
					
				}
				
				
			} // no use

			@Override
			public void mouseDragged(MouseEvent e) {
				
			} // no use
		});

		addMouseListener(new MouseAdapter() {
			// TODO Mouse Pressed
			@Override
			public void mousePressed(MouseEvent e) {
				
				//1. line
				if (buttonChoice == 1) { 
					if (clickCounter == 0) {
						start = new Point(e.getX(), e.getY());
						clickCounter++;
						isDrawing = true;
					} else if (clickCounter == 1) {
						end = new Point(e.getX(), e.getY());
						isDrawing = false;
						clickCounter = 0;
						Shape lineShape = drawLine(start.x, start.y, end.x,	end.y);
						shapes.add(lineShape);
						colours.add(color);
						fillList.add(fillOrOutline);
						repaint();

					}
				}//end of button choice 1
				//circle
				else if(buttonChoice == 2){ 
					if(clickCounter == 0){
						start = new Point(e.getX(), e.getY());
						clickCounter++;
						isDrawing = true;
					}else if(clickCounter == 1){
						end = new Point(e.getX(),e.getY());
						Shape circleShape = drawCircle(start.x, start.y, end.x,end.y);
						shapes.add(circleShape);
						colours.add(color);
						fillList.add(fillOrOutline);
						repaint();
						isDrawing = false;
						clickCounter = 0;
					}
				} //end of button choice 2
				//3. square
				else if(buttonChoice == 3){ 
					if(clickCounter == 0){
						start = new Point(e.getX(), e.getY());
						clickCounter++;
						isDrawing = true;
					}else if(clickCounter ==1){
						end = new Point(e.getX(),e.getY());
						rectangle = drawRectangle(start.x, start.y, end.x,end.y);
						AffineTransform saveAT = g2d.getTransform();
						g2d.rotate(20, end.x, end.y);
						g2d.setTransform(saveAT);
						shapes.add(rectangle);
						colours.add(color);
						fillList.add(fillOrOutline);
						repaint();
						clickCounter = 0;
						isDrawing = false;
					}
				} //end of button choice 3
				//4 Triangle
				else if(buttonChoice == 4){ 
					if(clickCounter == 0){
						start = new Point(e.getX(), e.getY());
						clickCounter++;
						isDrawing = true;
					}else if(clickCounter ==1){
						middle = new Point(e.getX(),e.getY());
						clickCounter++;
					}else if(clickCounter == 2){
						end = new Point(e.getX(), e.getY());
						Shape triangle  = new Polygon(new int[] { start.x, middle.x,end.x, start.x },new int[] { start.y, middle.y, end.y,start.y }, 3);
						shapes.add(triangle);
						colours.add(color);
						fillList.add(fillOrOutline);
						clickCounter = 0;
						isDrawing = false;
						repaint();
					}
				} //end of button choice 4
				//5. Polygon Shape
				else if(buttonChoice == 5){
					if(clickCounter == 0){
						start = new Point(e.getX(), e.getY());
						clickCounter++;
						isDrawing = true;
					}else if(clickCounter == 1){
						polyOne = new Point(e.getX(),e.getY());
						clickCounter++;
					}else if(clickCounter == 2){
						polyTwo = new Point(e.getX(),e.getY());
						clickCounter++;
					}else if(clickCounter == 3){
						polyThree = new Point(e.getX(),e.getY());
						clickCounter++;
					}else if(clickCounter == 4){
						polyFour = new Point(e.getX(),e.getY());
						Shape polygonShape  = new Polygon(new int[] { start.x, polyOne.x,polyTwo.x,polyThree.x, polyFour.x,start.x },
								new int[] { start.y, polyOne.y,polyTwo.y,polyThree.y, polyFour.y,start.y }, 5);
						shapes.add(polygonShape);
						colours.add(color);
						fillList.add(fillOrOutline);
						clickCounter = 0;
						isDrawing = false;
						repaint();
					}
			
					
				}
				
				
			} // end of mosePressed

			@Override
			public void mouseExited(MouseEvent e) {
			} // no use

			@Override
			public void mouseEntered(MouseEvent e) {
			} // no use

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}// no use

		});

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// How to draw text
		// g.drawString("Hello from the panel", 10, 20);

		g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		
		g2d.setStroke(new BasicStroke(strokeSize));
		
		
		// Run through the array lst where the shape is stored
		 int i = 0;
		for (Shape s : shapes) {
			g2d.setColor(colours.get(i));
			g2d.setPaint(colours.get(i));
			g2d.draw(s); // draw is a built in method to draw the saved shape!!
			
			if(fillList.get(i)) 
				g2d.fill(s); //determines whether the shape should be an outline or filled
			i++;
		}
		
		for (Shape s : shapesIn) {
			g2d.setColor(colours.get(i));
			g2d.setPaint(colours.get(i));
			g2d.draw(s); // draw is a built in methpd to draw the saved shape!!
			g2d.fill(s);

		}
		
		if(isDrawing){
			g2d.setPaint(color);
			if(buttonChoice == 1) //line
				g2d.draw(lineShape);
			else if(buttonChoice ==2) //circle
				g2d.draw(circleShape);
			else if(buttonChoice ==3) //square
				g2d.draw(rectangle);
			else if(buttonChoice == 4){
				g2d.draw(triangleLine);
				if(clickCounter == 2)
					 g2d.draw(triangleLine2);
			}else if(buttonChoice == 5)
				g2d.draw(lineShape);
		}
		

	} // END of paintComponent()

	/**
	 * Method to draw the Line TODO Draw Line Line2d inherits from shape
	 */
	private Line2D drawLine(int x, int y, int x2, int y2) {
		return new Line2D.Float(x, y, x2, y2);
	}
	
	/**
	 * There is no circle class like Rectangle so you have to use:
	 * http://stackoverflow
	 * .com/questions/13674407/is-there-a-circle-class-in-java
	 * -like-the-rectangle-class
	 */
	private Ellipse2D drawCircle(int x, int y, int x2, int y2) {
		// need to find the smallest to you can't make shapes drag backwards
		int smallX = findSmallestValue(x, x2);
		int SmallY = findSmallestValue(y, y2);
		int width = Math.abs(x2 - x);
		int height = Math.abs(y2 - y);
		return new Ellipse2D.Float(smallX, SmallY, width, height);
		// need .Float cause its a subclass of Ellipse2d which is Abstract
	}
	
	/**
	 * Method to Return the Rectangle as a shape Need to determine the width and
	 * height. Need to subtract EACH starting POINT from EACH ending Point
	 * Math.abs: turning a integer positive:
	 * http://stackoverflow.com/questions/493494
	 * /make-a-negative-number-positive-in-java I need Math.abs to turn the
	 * values into positive numbers so if you drag upwards
	 */
	private Rectangle drawRectangle(int x, int y, int x2, int y2) {
		// need to find the smallest to you can't make shapes drag backwards
		int smallX = findSmallestValue(x, x2);
		int SmallY = findSmallestValue(y, y2);
		int width = Math.abs(x2 - x);
		int height = Math.abs(y2 - y);
		return new Rectangle(smallX, SmallY, width, height);
	}
	
	/**
	 * Method to draw the Brush
	 */
	private Ellipse2D brush(int x, int y, int strokeWidth, int strokeHeight) {
		return new Ellipse2D.Float(x, y, strokeWidth, strokeHeight);
	}
	
	

	
	/**
	 * Method to return the smallest value between two numbers
	 */
	private int findSmallestValue(int x, int x2) {
		if (x < x2)
			return x;
		return x2;
	}
	


	/**
	 * The main drawing method called in the Shapes class
	 */
	public void drawing() {
		repaint(); // calls paintComponent()
	}

	/**
	 * method to add things to the panel
	 * TODO Adding PAnel parts
	 */
	void addButtonsToMenuPanel() {
		lineBtn = new JButton("Line");
		lineBtn.addActionListener(new MyMouseListener());
		circleBtn = new JButton("Circle");
		circleBtn.addActionListener(new MyMouseListener());
		squareBtn = new JButton("Square");
		squareBtn.addActionListener(new MyMouseListener());
		triangleBtn = new JButton("Triangle");
		triangleBtn.addActionListener(new MyMouseListener());
		polygonBtn = new JButton("Polygon");
		polygonBtn.addActionListener(new MyMouseListener());
		colorBtn = new JButton("Colour");
		colorBtn.addActionListener(new MyMouseListener());
		fillBtn = new JButton("Outline");
		fillBtn.addActionListener(new MyMouseListener());
		undoBtn = new JButton("Undo");
		undoBtn.addActionListener(new MyMouseListener());
		resetBtn = new JButton("Reset");
		resetBtn.addActionListener(new MyMouseListener());
		loadBtn = new JButton("Load");
		loadBtn.addActionListener(new MyMouseListener());
		saveBtn = new JButton("Save");
		saveBtn.addActionListener(new MyMouseListener());
		brushSizeBig = new JButton("Bigger");
		brushSizeBig.addActionListener(new MyMouseListener());
		brushSizeSmall = new JButton("Smaller");
		brushSizeSmall.addActionListener(new MyMouseListener());
		
		topMenu.add(lineBtn);
		topMenu.add(circleBtn);
		topMenu.add(squareBtn);
		topMenu.add(triangleBtn);
		topMenu.add(polygonBtn);
		topMenu.add(colorBtn);
		topMenu.add(fillBtn);
		bottomMenu.add(undoBtn);
		bottomMenu.add(resetBtn);
		bottomMenu.add(loadBtn);
		bottomMenu.add(saveBtn);
		bottomMenu.add(brushSizeBig);
		bottomMenu.add(brushSizeSmall);
		

	}

	/**
	 * Action Listener to determine which shape to draw
	 * 
	 */
	private class MyMouseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {

				if (e.getSource() == lineBtn) {
					buttonChoice = 1;
				} else if (e.getSource() == circleBtn) {
					buttonChoice = 2;
				} else if (e.getSource() == squareBtn) {
					buttonChoice = 3;
				} else if (e.getSource() == triangleBtn) {
					buttonChoice = 4;
				} else if (e.getSource() == polygonBtn) {
					buttonChoice = 5;
				} else if (e.getSource() == undoBtn) { // Undo button
					if (shapes.size() == 0)
						return;
					else {
						//All the ArrayLists must be reset so they stay in sync
							shapes.remove(shapes.size() - 1);
							colours.remove(colours.size() - 1);
							fillList.remove(fillList.size()-1);
						repaint();
					}
				} else if (e.getSource() == resetBtn) { // Reset button
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to reset?", "Reset",
							dialogButton);
					if (dialogResult == 0) {
						shapes.clear(); // removes al th shapes from the arrayList
						colours.clear();
						fillList.clear();
						repaint();
					}
				}else if(e.getSource() == brushSizeBig){
					strokeSize ++;
					if(strokeSize > 5)
						strokeSize = 5; //dont let the brush get too big
					
				}else if(e.getSource() == brushSizeSmall){
					strokeSize--;
				if(strokeSize < 1)
					strokeSize = 1;
				}else if(e.getSource() == colorBtn){
					color = JColorChooser.showDialog(null, "Choose a Colour", color);

				}else if(e.getSource() == saveBtn){
					//Method to write objects to a file
					ObjectOutputStream  fileOut;
						try {
							
							if(f.exists()) {
								fileOut = new AppendingObjectOutputStream(new FileOutputStream(f));
							}else{
								fileOut = new ObjectOutputStream(new FileOutputStream(f));
							}
							for(int i=0;i<shapes.size();i++){
								fileOut.writeObject(shapes.get(i));
							}
							fileOut.close();
							System.out.println("Success");
							
						} catch (IOException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
					
					
				}else if(e.getSource() == loadBtn){
					Shape aShape;
					int i =1;
					int numShapes = 0;
					try {
						ObjectInputStream in = new ObjectInputStream(new FileInputStream("shapes.dat"));
						aShape = (Shape) in.readObject();
						shapesIn.add(0,aShape);
						repaint();
						while(aShape != null){
							aShape = (Shape)in.readObject();
							shapesIn.add(i, aShape);
							repaint();
							i++;
						}
						
						in.close();
						
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (IOException e2) {
						if(i > 1)
							;
						else{
							System.out.println("IOError");
						// TODO Auto-generated catch block
						e2.printStackTrace();
						}
					} catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					
				}else if(e.getSource() == fillBtn){
					if(fillOrOutline == true){
						fillOrOutline = false;
						fillBtn.setText("Fill");
					}else{
						fillOrOutline = true;
						fillBtn.setText("Outline");
					}
				}
			}

		}
	}// end of MyMouseLsitener
}// End of Whole Class
