import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DrawingPanel2 extends JPanel {
	// Components
	private TopMenu topMenu;

	private int clickCounter = 0;
	/*
	 * private ArrayList<MyShape> shapes = new ArrayList<MyShape>(); private
	 * ArrayList<Color> colours = new ArrayList<Color>(); private
	 * ArrayList<Integer> brushSizesList = new ArrayList<Integer>();
	 */
	/*
	 * private ArrayList<Shape> shapesIn = new ArrayList<Shape>(); private
	 * ArrayList<Shape> shapesOut = new ArrayList<Shape>();
	 */
	/* private ArrayList<Boolean> fillList = new ArrayList<Boolean>(); */
	private Graphics2D g2d;
	private Point start, end, middle, polyOne, polyTwo, polyThree, polyFour,
			temp;
	private boolean isDrawing = false;

	public DrawingPanel2() {
		this.repaint();
	}

	public DrawingPanel2(TopMenu t) {
		topMenu = t;
		t.setDrawingPanel2(this);

		// TODO Auto-generated constructor stub
		Dimension size = getPreferredSize();
		size.width = 1000;
		size.height = 500;
		setPreferredSize(size);
		// Set the layout of the panel
		setLayout(new BorderLayout()); // border Layout for the Buttons
		setBackground(Color.white); // set the canvas white
		// Connect components to Panel
		// add(topMenu, BorderLayout.PAGE_START);
		// add(topMenu, BorderLayout.PAGE_END);
		repaint();

		addMouseMotionListener(new MouseMotionListener() {
			// TODO Mouse moved
			@Override
			public void mouseMoved(MouseEvent e) {
				if (isDrawing) {
					if (topMenu.buttonChoice == 1) {
						temp = new Point(e.getX(), e.getY());
					} else if (topMenu.buttonChoice == 2) {
						temp = new Point(e.getX(), e.getY());
					} else if (topMenu.buttonChoice == 3) {
						temp = new Point(e.getX(), e.getY());
					} else if (topMenu.buttonChoice == 4) {
						temp = new Point(e.getX(), e.getY());
						if (clickCounter == 2)
							temp = new Point(e.getX(), e.getY());
					} else if (topMenu.buttonChoice == 5) {
						// lineShape = drawLine(start.x, start.y, e.getX(),
						// e.getY());
						temp = new Point(e.getX(), e.getY());
						if (clickCounter == 2)
							temp = new Point(e.getX(), e.getY());
						// lineShape = drawLine(polyOne.x, polyOne.y, e.getX(),
						// e.getY());
						if (clickCounter == 3)
							temp = new Point(e.getX(), e.getY());
						// lineShape = drawLine(polyTwo.x, polyTwo.y, e.getX(),
						// e.getY());
						if (clickCounter == 4)
							temp = new Point(e.getX(), e.getY());
						// lineShape = drawLine(polyThree.x, polyThree.y,
						// e.getX(), e.getY());

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
				// 1. line
				if (topMenu.buttonChoice == 1) {
					if (clickCounter == 0) {
						start = new Point(e.getX(), e.getY());
						clickCounter++;
						isDrawing = true;
					} else if (clickCounter == 1) {
						end = new Point(e.getX(), e.getY());
						topMenu.shapes.add(new Line(start.x, start.y, end.x,end.y));
						topMenu.colours.add(topMenu.color);
						topMenu.fillList.add(true);
						topMenu.brushSizesList.add(topMenu.strokeSize);
						isDrawing = false;
						clickCounter = 0;
						repaint();

					}
				}// end of button choice 1
					// circle
				else if (topMenu.buttonChoice == 2) {
					if (clickCounter == 0) {
						start = new Point(e.getX(), e.getY());
						clickCounter++;
						isDrawing = true;
					} else if (clickCounter == 1) {
						end = new Point(e.getX(), e.getY());
						topMenu.shapes.add(new Circle(start.x, start.y, end.x,end.y));
						topMenu.colours.add(topMenu.color);
						topMenu.fillList.add(topMenu.fillOrOutline);
						topMenu.brushSizesList.add(topMenu.strokeSize);
						isDrawing = false;
						clickCounter = 0;
						repaint();
					}
				} // end of button choice 2
					// 3. square
				else if (topMenu.buttonChoice == 3) {
					if (clickCounter == 0) {
						start = new Point(e.getX(), e.getY());
						clickCounter++;
						isDrawing = true;
					} else if (clickCounter == 1) {
						end = new Point(e.getX(), e.getY());
						topMenu.shapes.add(new Square(start.x, start.y, end.x,
								end.y));
						topMenu.colours.add(topMenu.color);
						topMenu.fillList.add(topMenu.fillOrOutline);
						topMenu.brushSizesList.add(topMenu.strokeSize);
						repaint();
						clickCounter = 0;
						isDrawing = false;
					}
				} // end of button choice 3
					// 4 Triangle
				else if (topMenu.buttonChoice == 4) {
					if (clickCounter == 0) {
						start = new Point(e.getX(), e.getY());
						clickCounter++;
						isDrawing = true;
					} else if (clickCounter == 1) {
						middle = new Point(e.getX(), e.getY());
						clickCounter++;
					} else if (clickCounter == 2) {
						end = new Point(e.getX(), e.getY());
						topMenu.shapes.add(new Triangle(start.x, start.y,
								middle.x, middle.y, end.x, end.y));
						topMenu.colours.add(topMenu.color);
						topMenu.fillList.add(topMenu.fillOrOutline);
						topMenu.brushSizesList.add(topMenu.strokeSize);
						clickCounter = 0;
						isDrawing = false;
						repaint();
					}
				} // end of button choice 4
					// 5. Polygon Shape
				else if (topMenu.buttonChoice == 5) {
					if (clickCounter == 0) {
						start = new Point(e.getX(), e.getY());
						clickCounter++;
						isDrawing = true;
					} else if (clickCounter == 1) {
						polyOne = new Point(e.getX(), e.getY());
						clickCounter++;
					} else if (clickCounter == 2) {
						polyTwo = new Point(e.getX(), e.getY());
						clickCounter++;
					} else if (clickCounter == 3) {
						polyThree = new Point(e.getX(), e.getY());
						clickCounter++;
					} else if (clickCounter == 4) {
						polyFour = new Point(e.getX(), e.getY());
						topMenu.shapes.add(new MyPolygon(start, polyOne,
								polyTwo, polyThree, polyFour, start));
						topMenu.colours.add(topMenu.color);
						topMenu.fillList.add(topMenu.fillOrOutline);
						topMenu.brushSizesList.add(topMenu.strokeSize);
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

	public void paint(Graphics g) {
		super.paint(g);

		g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		if (isDrawing) {
			g2d.setPaint(topMenu.color);
			if (topMenu.buttonChoice == 1) // line
				g2d.drawLine(start.x, start.y, temp.x, temp.y);
			else if (topMenu.buttonChoice == 2) { // circle
				int r = (int) Math.round(start.distance(temp));
				g2d.drawOval(start.x - r, start.y - r, 2 * r, 2 * r);
			} else if (topMenu.buttonChoice == 3) {

				AffineTransform saveAT = g2d.getTransform();
				double angle = Math.toRadians(0)
						+ (Math.atan2((temp.y - start.y), (temp.x - start.x)));
				// Perform transformation

				int width = (int) Math.round(start.distance(temp));
				g2d.rotate(angle, start.x, start.y);

				g2d.drawRect(start.x, start.y, width, width);
				// Restore original transform

				g2d.setTransform(saveAT);

			} else if (topMenu.buttonChoice == 4) {
				g2d.drawLine(start.x, start.y, temp.x, temp.y);
				if (clickCounter == 2)
					g2d.drawLine(middle.x, middle.y, temp.x, temp.y);
			} else if (topMenu.buttonChoice == 5)
				g2d.drawLine(start.x, start.y, temp.x, temp.y);
		}

		int i = 0;

		
			for (MyShape s : topMenu.shapes) {
				g2d.setStroke(new BasicStroke(topMenu.brushSizesList.get(i)));
				s.color = topMenu.colours.get(i);
				
				s.draw(g2d, topMenu.fillList.get(i));
				i++;
			}
		

	} // END of paintComponent()

	/**
	 * The main drawing method called in the Shapes class
	 */
	public void drawing() {
		repaint(); // calls paintComponent()
	}
}