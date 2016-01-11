package application;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class GUI extends JPanel implements MouseListener {
	private Dimension dimensions = new Dimension(GUIConstants.DEFAULT_PANEL_WIDTH, GUIConstants.DEFAULT_PANEL_HEIGHT);
	
	public GUI() {
		setSize(dimensions);
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Click!");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
