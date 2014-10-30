import java.awt.Color;
import java.awt.FileDialog;
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

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

	/**
	 * Action Listener to determine which shape to draw
	 * 
	 */
	public class MyMouseListener implements ActionListener {
		/*int buttonChoice = 1;*/
		TopMenu m;
		public MyMouseListener(TopMenu m){
			this.m = m;
		}
		public MyMouseListener(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {

				if (e.getSource() == m.lineBtn) {
					m.buttonChoice = 1;
				} else if (e.getSource() == m.circleBtn) {
					m.buttonChoice = 2;
				} else if (e.getSource() == m.squareBtn) {
					m.buttonChoice = 3;
				} else if (e.getSource() == m.triangleBtn) {
					m.buttonChoice = 4;
				} else if (e.getSource() == m.polygonBtn) {
					m.buttonChoice = 5;
				}/* else if (e.getSource() == undoBtn) { // Undo button
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
				}*/else if(e.getSource() == m.colorBtn){
					m.color = JColorChooser.showDialog(null, "Choose a Colour", m.color);

				}/*else if(e.getSource() == saveBtn){
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
					
					
				}*/else if(e.getSource() == m.fillBtn){
					if(m.fillOrOutline == true){
						m.fillOrOutline = false;
						m.fillBtn.setText("Fill");
					}else{
						m.fillOrOutline = true;
						m.fillBtn.setText("Outline");
					}
				}
			}

		}
	}// end of MyMouseLsitener