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

public class DrawingPanel extends JPanel {
	// Components
	private JPanel topMenu = new JPanel();
	private JPanel bottomMenu = new JPanel();
	private JButton lineBtn, circleBtn, triangleBtn, squareBtn, polygonBtn,
			colorBtn, fillBtn, brushSizeBig, brushSizeSmall;
	private JButton undoBtn, resetBtn, loadBtn, saveBtn;

	private int buttonChoice = 1, clickCounter = 0;
	private ArrayList<MyShape> shapes = new ArrayList<MyShape>();
	private ArrayList<Color> colours = new ArrayList<Color>();
	private ArrayList<Integer> brushSizesList = new ArrayList<Integer>();
	/*
	 * private ArrayList<Shape> shapesIn = new ArrayList<Shape>(); private
	 * ArrayList<Shape> shapesOut = new ArrayList<Shape>();
	 */
	private ArrayList<Boolean> fillList = new ArrayList<Boolean>();
	private Graphics2D g2d;
	private Point start, end, middle, polyOne, polyTwo, polyThree, polyFour,
			temp;
	private boolean isDrawing = false, fillOrOutline = true;
	private Color color = Color.BLUE;
	private File f = new File("shapes.dat");
	private Integer strokeSize = 1;

	public DrawingPanel() {
		// TODO Auto-generated constructor stub
		setBorder(BorderFactory.createLineBorder(color));
		Dimension size = getPreferredSize();
		size.width = 800;
		size.height = 500;
		setPreferredSize(size);
		addButtonsToMenuPanel(); // adding the buttons to the Panel
		// Set the layout of the panel
		setLayout(new BorderLayout()); // border Layout for the Buttons
		setBackground(Color.white); // set the canvas white
		// Connect components to Panel
		add(topMenu, BorderLayout.PAGE_START);
		add(bottomMenu, BorderLayout.PAGE_END);
		repaint();

		addMouseMotionListener(new MouseMotionListener() {
			// TODO Mouse moved
			@Override
			public void mouseMoved(MouseEvent e) {
				if (isDrawing) {
					if (buttonChoice == 1) {
						temp = new Point(e.getX(), e.getY());
					}else if(buttonChoice == 2){
						temp = new Point(e.getX(), e.getY());
					}
					else if(buttonChoice == 3){
						temp = new Point(e.getX(), e.getY());
					}else if(buttonChoice == 4){
						temp = new Point(e.getX(),e.getY());
						if(clickCounter == 2)
							temp = new Point(e.getX(),e.getY());
					}else if(buttonChoice == 5){
						//lineShape = drawLine(start.x, start.y, e.getX(), e.getY());
						temp = new Point(e.getX(), e.getY());
					if(clickCounter == 2)
						temp = new Point(e.getX(), e.getY());
						//lineShape = drawLine(polyOne.x, polyOne.y, e.getX(), e.getY());
					if(clickCounter == 3)
						temp = new Point(e.getX(), e.getY());
						//lineShape = drawLine(polyTwo.x, polyTwo.y, e.getX(), e.getY());
					if(clickCounter == 4)
						temp = new Point(e.getX(), e.getY());
						//lineShape = drawLine(polyThree.x, polyThree.y, e.getX(), e.getY());
			}
				}
				repaint();

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
				if (buttonChoice == 1) {
					if (clickCounter == 0) {
						start = new Point(e.getX(), e.getY());
						clickCounter++;
						isDrawing = true;
					} else if (clickCounter == 1) {
						end = new Point(e.getX(), e.getY());
						isDrawing = false;
						clickCounter = 0;
						shapes.add(new Line(start.x, start.y, end.x, end.y));
						colours.add(color);
						fillList.add(fillOrOutline);
						brushSizesList.add(strokeSize);
						repaint();

					}
				}// end of button choice 1
				//circle
				else if(buttonChoice == 2){ 
					if(clickCounter == 0){
						start = new Point(e.getX(), e.getY());
						clickCounter++;
						isDrawing = true;
					}else if(clickCounter == 1){
						end = new Point(e.getX(),e.getY());
						shapes.add(new Circle(start.x, start.y,end.x, end.y));
						colours.add(color);
						fillList.add(fillOrOutline);
						brushSizesList.add(strokeSize);
						isDrawing = false;
						clickCounter = 0;
						repaint();
					}
				} //end of button choice 2
				//3. square
				else if(buttonChoice == 3){ 
					if(clickCounter == 0){
						start = new Point(e.getX(), e.getY());
						clickCounter++;
						isDrawing = true;
					}else if(clickCounter ==1){
						end = new Point(e.getX(), e.getY());
						shapes.add(new Square(start.x, start.y, end.x, end.y));
						colours.add(color);
						fillList.add(fillOrOutline);
						brushSizesList.add(strokeSize);
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
						shapes.add(new Triangle(start.x, start.y,middle.x,middle.y ,end.x, end.y));
						colours.add(color);
						fillList.add(fillOrOutline);
						brushSizesList.add(strokeSize);
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
						shapes.add(new MyPolygon(start, polyOne, polyTwo, polyThree, polyFour, start));
						colours.add(color);
						fillList.add(fillOrOutline);
						brushSizesList.add(strokeSize);
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
			g2d.setPaint(color);
			if (buttonChoice == 1) // line
				g2d.drawLine(start.x, start.y, temp.x, temp.y);
			else if(buttonChoice ==2){ //circle
				int r = (int)Math.round(start.distance(temp));
				g2d.drawOval(start.x-r, start.y-r, 2 * r, 2 *r);
				}
			else if(buttonChoice == 3){
				int len = (int) start.distance(temp);
				AffineTransform saveAT = g2d.getTransform();
				 double angle = Math.toRadians(0) + (Math.atan2((temp.y - start.y),(temp.x - start.x)));
				// Perform transformation

					int width = (int) Math.round(start.distance(temp));
				g2d.rotate(angle, start.x, start.y);
				
				g2d.drawRect(start.x, start.y, width,width);
				// Restore original transform
				
				g2d.setTransform(saveAT);
				
			}else if(buttonChoice == 4){
				g2d.drawLine(start.x, start.y, temp.x, temp.y);
				if(clickCounter == 2)
					g2d.drawLine(middle.x, middle.y, temp.x, temp.y);
			}else if(buttonChoice == 5)
				g2d.drawLine(start.x, start.y, temp.x, temp.y);
		}

		int i = 0;
		//if (start != null) {
			for (MyShape s : shapes) {
				
				s.color = colours.get(i);

				// g2d.setColor(colours.get(i));
				// g2d.setPaint(colours.get(i));
				s.draw(g2d, fillList.get(i)); // draw is a built in method to draw the saved
								// shape!!
				g2d.setStroke(new BasicStroke(brushSizesList.get(i)));
				// if(fillList.get(i)) g2d.fill((Shape) s);
				// determines whether the shape should be an outline or filled
				i++;
			}
		//}

	} // END of paintComponent()

	/**
	 * method to add things to the panel TODO Adding PAnel parts
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

	} // end of constructor

	/**
	 * The main drawing method called in the Shapes class
	 */
	public void drawing() {
		repaint(); // calls paintComponent()
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
							brushSizesList.remove(brushSizesList.size()-1);
						repaint();
					}
				} else if (e.getSource() == resetBtn) { // Reset button
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null,"Are you sure you want to reset?", "Reset",dialogButton);
					if (dialogResult == 0) {
						shapes.clear(); // removes al th shapes from the arrayList
						colours.clear();
						fillList.clear();
						brushSizesList.clear();
						repaint();
					}
				}else if(e.getSource() == brushSizeBig){
					strokeSize +=2;
					if(strokeSize > 20)
						strokeSize = 20; //dont let the brush get too big
					
				}else if(e.getSource() == brushSizeSmall){
					strokeSize-=2;
				if(strokeSize < 1)
					strokeSize = 1;
				}else if(e.getSource() == colorBtn){
					color = JColorChooser.showDialog(null, "Choose a Colour", color);

				}else if(e.getSource() == saveBtn){
					//Method to write objects to a file
					ObjectOutputStream  fileOut;
						try {
							  FileDialog fDialog = new FileDialog(new Frame(), "Save", FileDialog.SAVE);
						        fDialog.setVisible(true);
						        String path = fDialog.getDirectory() + fDialog.getFile();
						        path+=(path.contains(".dat") ? "" : ".dat");
						        File f = new File(path);
							fileOut = new ObjectOutputStream(new FileOutputStream(f));
							for(int i=0;i<shapes.size();i++){
								fileOut.writeObject(shapes.get(i));
								fileOut.writeObject(colours.get(i));
								fileOut.writeBoolean(fillList.get(i));
								fileOut.writeObject(brushSizesList.get(i));
							}
							fileOut.close();
							//JOptionPane.showMessageDialog(null, "Saved");
						        
						} catch (IOException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
					
					
				}else if(e.getSource() == loadBtn){
					MyShape aShape;
					Color aColor;
					boolean abool;
					Integer bs;
					int i =1;
					try {
						shapes.clear(); // removes al th shapes from the arrayList
						colours.clear();
						fillList.clear();
						brushSizesList.clear();
						repaint();
						FileDialog fDialog = new FileDialog(new Frame(), "Load", FileDialog.LOAD);
				        fDialog.setVisible(true);
				        String path = fDialog.getDirectory() + fDialog.getFile();
				        File f = new File(path);
						ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
						aShape = (MyShape) in.readObject();
						aColor = (Color) in.readObject();
						abool = in.readBoolean();
						bs = (Integer) in.readObject();
						shapes.add(0,aShape);
						colours.add(0, aColor);
						fillList.add(0,abool);
						brushSizesList.add(0,bs);
						if(aShape != null){
							revalidate();
							repaint();
						while(aShape != null){
							aShape = (MyShape)in.readObject();
							aColor = (Color) in.readObject();
							abool = in.readBoolean();
							bs = (Integer) in.readObject();
							shapes.add(i, aShape);
							colours.add(i, aColor);
							fillList.add(i,abool);
							brushSizesList.add(i,bs);
							revalidate();
							repaint();
							i++;
							}
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
}