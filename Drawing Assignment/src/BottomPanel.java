import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class BottomPanel extends JPanel{

	 JButton undoBtn, resetBtn, loadBtn, saveBtn,brushSizeBig, brushSizeSmall;
	ArrayList<MyShape> shapes = new ArrayList<MyShape>();
	ArrayList<Color> colours = new ArrayList<Color>();
	ArrayList<Integer> brushSizesList = new ArrayList<Integer>();
	ArrayList<Boolean> fillList = new ArrayList<Boolean>();
	Integer strokeSize = 1;
	
	FileDialog fDialogSave = new FileDialog(new Frame(),"Save", FileDialog.SAVE);
	String pathSave = fDialogSave.getDirectory()+ fDialogSave.getFile();
	FileDialog fDialogLoad = new FileDialog(new Frame(),"Load", FileDialog.LOAD);
	String pathLoad = fDialogLoad.getDirectory()+ fDialogLoad.getFile();
	File f = new File(pathSave);

	// variables
	static int buttonChoice = 1;
	
	public BottomPanel() {
		Dimension size = getPreferredSize();
		size.width = 800;
		size.height = 50;
		setPreferredSize(size);
		// Set the layout of the panel
		setLayout(new FlowLayout()); // border Layout for the Buttons
		addButtonsToMenuPanel();
	}
	
	/**
	 * method to add things to the panel TODO Adding PAnel parts
	 */
	void addButtonsToMenuPanel() {
		
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

		add(undoBtn);
		add(resetBtn);
		add(loadBtn);
		add(saveBtn);
		add(brushSizeBig);
		add(brushSizeSmall);

	} // end of constructor
	
	/**
	 * Action Listener to determine which shape to draw
	 * 
	 */
	private class MyMouseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				if (e.getSource() == undoBtn) { // Undo button
					if (shapes.size() == 0)
						return;
					else {
						// All the ArrayLists must be reset so they stay in sync
						shapes.remove(shapes.size() - 1);
						colours.remove(colours.size() - 1);
						fillList.remove(fillList.size() - 1);
						brushSizesList.remove(brushSizesList.size() - 1);
						repaint();
					}
				} else if (e.getSource() == resetBtn) { // Reset button
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to reset?", "Reset",
							dialogButton);
					if (dialogResult == 0) {
						shapes.clear(); // removes al th shapes from the
										// arrayList
						colours.clear();
						fillList.clear();
						brushSizesList.clear();
						repaint();
					}
				} else if (e.getSource() == brushSizeBig) {
					strokeSize += 2;
					if (strokeSize > 20)
						strokeSize = 20; // dont let the brush get too big

				} else if (e.getSource() == brushSizeSmall) {
					strokeSize -= 2;
					if (strokeSize < 1)
						strokeSize = 1;
				}else if (e.getSource() == saveBtn) {
					// Method to write objects to a file
					ObjectOutputStream fileOut;
					try {
						
						fDialogSave.setVisible(true);
						pathSave += (pathSave.contains(".dat") ? "" : ".dat");
						fileOut = new ObjectOutputStream(new FileOutputStream(f));
						for (int i = 0; i < shapes.size(); i++) {
							fileOut.writeObject(shapes.get(i));
							fileOut.writeObject(colours.get(i));
							fileOut.writeBoolean(fillList.get(i));
							fileOut.writeObject(brushSizesList.get(i));
						}
						fileOut.close();
						// JOptionPane.showMessageDialog(null, "Saved");

					} catch (IOException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}

				} else if (e.getSource() == loadBtn) {
					MyShape aShape;
					Color aColor;
					boolean abool;
					Integer bs;
					int i = 1;
					try {
						shapes.clear(); // removes al th shapes from the
										// arrayList
						colours.clear();
						fillList.clear();
						brushSizesList.clear();
						repaint();
						/*FileDialog fDialog = new FileDialog(new Frame(),"Load", FileDialog.LOAD);
						fDialog.setVisible(true);
						String path = fDialog.getDirectory()+ fDialog.getFile();*/
						File f = new File(pathSave);
						ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
						aShape = (MyShape) in.readObject();
						aColor = (Color) in.readObject();
						abool = in.readBoolean();
						bs = (Integer) in.readObject();
						shapes.add(0, aShape);
						colours.add(0, aColor);
						fillList.add(0, abool);
						brushSizesList.add(0, bs);
						if (aShape != null) {
							revalidate();
							repaint();
							while (aShape != null) {
								aShape = (MyShape) in.readObject();
								aColor = (Color) in.readObject();
								abool = in.readBoolean();
								bs = (Integer) in.readObject();
								shapes.add(i, aShape);
								colours.add(i, aColor);
								fillList.add(i, abool);
								brushSizesList.add(i, bs);
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
						if (i > 1)
							;
						else {
							System.out.println("IOError");
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					} catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

				}

			}
		}
	}// end of MyMouseLsitener

}
