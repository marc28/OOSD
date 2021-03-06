import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author marc UseFul Links:
 *         http://docs.oracle.com/javase/tutorial/uiswing/painting/step3.html
 *         http://www.corewebprogramming.com/PDF/ch10.pdf
 * 
 */

// Just for drawing
public class Draw extends JPanel {

	// Components
	private JPanel topMenu = new JPanel();
	private JPanel canvas = new JPanel();
	private JButton lineBtn;
	private JButton circleBtn;
	private JButton triangleBtn;
	JButton squareBtn;
	private JButton myShape;
	private JButton colorBtn;
	private JButton undoBtn;
	private JButton resetBtn;
	private JButton loadBtn;
	private JButton saveBtn;

	// class Variables
	private Point start = null, end = null, triangleEnd = null;
	private int buttonChoice = 2;
	Graphics2D g2d;
	Polygon triangle;
	private int clickCounter = 0;
	Shape shape = null;
	boolean isDrawing = false;

	// ArrayList used to store the shape just drawn, otherwise it disappears!!
	ArrayList<Shape> shapes = new ArrayList<Shape>();

	public Draw() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Dimension size = getPreferredSize();
		size.width = 800;
		size.height = 100;
		setPreferredSize(size);
		addButtonsToMenuPanel(); // adding the buttons to the Panel
		// Set the layout of the panel
		setLayout(new FlowLayout());
		// Connect components to Panel
		add(topMenu, BorderLayout.NORTH);
		add(canvas, BorderLayout.CENTER);
		topMenu.setSize(800, 200);
		canvas.setSize(800, 200);

	
		// AddListeners
		addMouseListener(new MouseAdapter() {
		//Mouse Pressed Listener
			public void mousePressed(MouseEvent e) {
				Shape whichShape = null;
				if(clickCounter == 0){
					start = new Point(e.getX(), e.getY());
					//end = start;
					clickCounter++;
				}else if(clickCounter == 1){
					end = new Point(e.getX(),e.getY());
					clickCounter++;
					isDrawing = true;
				}
				if(clickCounter == 2){
					whichShape = drawLine(start.x, start.y, end.x,end.y);
					shapes.add(whichShape);
					// these need to be null for the purpose of the outline
					// while moving
					start = null;
					end = null;
					repaint();
					clickCounter = 0;
					isDrawing = false;
				}
					
			}
	
		//Mouse Release Listener
			public void mouseReleased(MouseEvent e) {
				// The shape needs to be stored somewhere or else it vanishes
				
				/*if (buttonChoice != 5) {
					
					if (buttonChoice == 1) {
						whichShape = drawLine(start.x, start.y, end.x,end.y);
					} else if (buttonChoice == 2) {
						whichShape = drawCircle(start.x, start.y, e.getX(),
								e.getY());
					} else if (buttonChoice == 3) {
						whichShape = drawRectangle(start.x, start.y, e.getX(),
								e.getY());
					} else if (buttonChoice == 4) {
						whichShape = new Polygon(new int[] { start.x, e.getX(),
								e.getX() }, new int[] { start.y, e.getY(),
								start.y }, 3);

					} else if (buttonChoice == 5) {
						whichShape = drawCircle(start.x, start.y, e.getX(),
								e.getY());
					}
				}*/
				Shape whichShape = null;
				if(isDrawing){
					
					whichShape = drawLine(start.x, start.y, e.getX(),e.getY());
					shapes.add(whichShape);
					repaint();
				}
					/*shapes.add(whichShape);
					// these need to be null for the purpose of the outline
					// while moving
					start = null;
					end = null;
					repaint();*/
		
				
			}
		
		//Mouse Dragged Listener
			public void mouseDragged(MouseEvent e) {

				if (isDrawing) {
					Shape brushShape = brush(e.getX(), e.getY(), 6, 6);
					shapes.add(brushShape);
				}
				end = new Point(e.getX(), e.getY());
				repaint();

			}
		});
	}

	// This stops us from having to set the actual size of the frame is pixels
	public Dimension getPreferredSize() {
		return new Dimension(800, 500);
	}

	// The main drawing method called in the Shapes class
	public void drawing() {
		repaint(); // calls paintComponent()
	}

	/**
	 * This method is key to drawing the graphics To see all the Graphics2D
	 * object see: http://www.corewebprogramming.com/PDF/ch10.pdf
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// How to draw text
		// g.drawString("Hello from the panel", 10, 20);

		g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		// Run through the array lst where the shape is stored

		for (Shape s : shapes) {
			g2d.setColor(Color.BLUE);
			g2d.draw(s); // draw is a built in methpd to draw the saved shape!!
			g2d.setPaint(Color.BLUE);
			g2d.fill(s);

		}

		// Below deals with the outline while your drawing a shape on the screen
		// TODO Shoadow
		if (/*start != null && end != null*/ isDrawing) {
			// g2d.setPaint(new
			// GradientPaint(0,0,Color.MAGENTA,175,175,Color.YELLOW, true));
			Shape shape = null;
			g2d.setPaint(new Color(0, 0, 0, 0.5f)); // black out line
													// (r,g,b,opacity)
			if (buttonChoice == 1) { // line
				shape = drawLine(start.x, start.y, end.x, end.y);
				g2d.draw(shape);
			} else if (buttonChoice == 2) { // circle
				shape = drawCircle(start.x, start.y, end.x, end.y);
				g2d.draw(shape);
			} else if (buttonChoice == 3) { // rectangle
				shape = drawRectangle(start.x, start.y, end.x, end.y);
				g2d.draw(shape);
			} else if (buttonChoice == 4) { // triangle
				shape = new Polygon(new int[] { start.x, end.x, end.x },
						new int[] { start.y, end.y, start.y }, 3);
			} else if (buttonChoice == 5) {
				/*
				 * shape = brush(start.x, start.y, end.x, end.y);
				 * g2d.draw(shape);
				 */
			}
			
			repaint();
		}

	} // END of paintComponent()

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
	 * Method to draw the Line
	 * 
	 */
	private Line2D drawLine(int x, int y, int x2, int y2) {
		return new Line2D.Float(x, y, x2, y2);
	}

	/**
	 * Method to draw the triangle
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
	 * method to add things to the panel
	 * 
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
		myShape = new JButton("My Shape");
		myShape.addActionListener(new MyMouseListener());
		colorBtn = new JButton("Colour");
		colorBtn.addActionListener(new MyMouseListener());
		undoBtn = new JButton("Undo");
		undoBtn.addActionListener(new MyMouseListener());
		resetBtn = new JButton("Reset");
		resetBtn.addActionListener(new MyMouseListener());
		loadBtn = new JButton("Load");
		loadBtn.addActionListener(new MyMouseListener());
		saveBtn = new JButton("Save");
		saveBtn.addActionListener(new MyMouseListener());

		topMenu.add(lineBtn);
		topMenu.add(circleBtn);
		topMenu.add(squareBtn);
		topMenu.add(triangleBtn);
		topMenu.add(myShape);
		topMenu.add(colorBtn);
		topMenu.add(undoBtn);
		topMenu.add(resetBtn);
		topMenu.add(loadBtn);
		topMenu.add(saveBtn);

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
				} else if (e.getSource() == myShape) {
					buttonChoice = 5;
				} else if (e.getSource() == undoBtn) { //Undo button
					if (shapes.size() == 0)
						return;
					else {
						if ((shapes.get(shapes.size() - 1)) instanceof Ellipse2D.Float) {
							//I need this loop to remove all the brush shapes in the array
							//because each brush stroke is a separate object.
							while (true) { 
								int i = shapes.size() - 1;
								shapes.remove(i);
								i--;
								if (i < 0 || !((shapes.get(i) instanceof Ellipse2D.Float))) {
									break;
								}

							}
						} else {
							shapes.remove(shapes.size() - 1);
						}
						repaint();
					}	
				} else if (e.getSource() == resetBtn){ //Reset button
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset?", "Reset",dialogButton);
					if(dialogResult==0){
						shapes.clear(); //removes al th shapes from the arrayList
						repaint();
					}	
				}
			}

		}
	}// end of MyMouseLsitener

} // End of entire class

/*
 * ...
 * 
 * 
 * // register mouse listener with appropriate component  // within the main
 * program component.addMouseListener(new MyMouseListener()); ...
 */

