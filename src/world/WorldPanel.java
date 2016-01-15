package world;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

/**
 * This is the panel upon which the {@link World} is drawn.
 * This class provides its own mouse listeners.
 * @author Timothy
 *
 */
public class WorldPanel extends JPanel {
	private static final long serialVersionUID = -6792162069008824164L;
	private static final int MIN_PIXEL_SIZE = 1;
	private static final int MAX_PIXEL_SIZE = 64;
	
	private World world;
	private int pixelSize;
	
	public WorldPanel(World world) {
		this.world = world;
		this.pixelSize = 8;
		world.setPanel(this);
		
		PanelMouseHandler handler = new PanelMouseHandler();
		addMouseListener(handler);
		addMouseMotionListener(handler);
		addMouseWheelListener(handler);
	}
	
	/**
	 * @return The pixel size for the current paint.
	 */
	int getPixelSize() {
		return pixelSize;
	}
	
	public void runForever() {
		world.runForever();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		world.paintComponent(g);
	}
	
	
	
	/**
	 * This class is intended to handle all of the mouse based events for the {@link WorldPanel}.
	 * @author Timothy
	 */
	private class PanelMouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {
//		private static final PanelMouseHandler INSTANCE = new PanelMouseHandler();
//		
//		private PanelMouseHandler() {}
//		static PanelMouseHandler getInstance() { return INSTANCE; }
		
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			int rotations = e.getWheelRotation();
			if (rotations > 0) {
				/* Rotations > 0 implies wheel scrolled down. Zoom out. */
				pixelSize = pixelSize >>> rotations;
			} else if (rotations < 0) {
				/* Rotations < 0 implies wheel scrolled up. Zoom in */
				pixelSize = pixelSize << rotations;
			}
			
			/* Check that the pixel size does not exceed bounds.
			 * This code lies outside the if to avoid potential overflow,
			 * which could cause unexpected negative numbers.
			 */
			if (pixelSize > MAX_PIXEL_SIZE) {
				pixelSize = MAX_PIXEL_SIZE;
			} else if (pixelSize < MIN_PIXEL_SIZE) {
				pixelSize = MIN_PIXEL_SIZE;
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("Clicked");
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) { return; }

		@Override
		public void mouseEntered(MouseEvent e) { return; }

		@Override
		public void mouseExited(MouseEvent e) { return; }
	}
}
