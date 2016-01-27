package world;

import java.awt.Dimension;
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
	
	/** Maximum render size, in pixels, of a single block of the world */
	private static final int MIN_BLOCK_SIZE = 1;
	/** Minimum render size, in pixels, of a single block of the world */
	private static final int MAX_BLOCK_SIZE = 64;
	
	/** 
	 * This is the minimum length of a velocity vector before it is set to 0.
	 * This minimum must be in place to ensure that camera pans do not go (almost) infinitely.
	 */
	private static final double MIN_VELOCITY_VECTOR_LENGTH = 0.1;
	
	/**
	 * Divide by this constant when calculating the friction.
	 * We essentially slow by (total speed) / FRICTION_COEFFICIENT.
	 */
	private static final double FRICTION_COEFFICIENT = 8;
	
	private World world;
	
	
	private int width;
	private int height;
	
	/* Temporary attributes for rendering */
	private int blockSize;
	private double startX; /* The "actual" values are useful for proper zooming */
	private double startY;
	private double endX;
	private double endY;
	private double velocityX;
	private double velocityY;
	
	private double xBound;
	private double yBound;
	
	public WorldPanel(World world, int defaultWidth, int defaultHeight) {
		setSize(new Dimension(defaultWidth, defaultHeight));
		setPreferredSize(new Dimension(defaultWidth, defaultHeight));
		
		this.world = world;
		this.blockSize = 8;
		this.startX = 0;
		this.startY = 0;
		this.endX = defaultWidth / blockSize;
		this.endY = defaultHeight / blockSize;
		this.xBound = WorldConstants.WORLD_X - ((double)defaultWidth / blockSize) - 1;
		this.yBound = WorldConstants.WORLD_Y - ((double)defaultHeight / blockSize) - 1;
		world.setPanel(this);
		
		PanelMouseHandler handler = new PanelMouseHandler();
		addMouseListener(handler);
		addMouseMotionListener(handler);
		addMouseWheelListener(handler);
	}
	
	/**
	 * @return The ending index (exclusive), in world blocks,
	 * of the bottom right x position of the render.
	 */
	double getEndX() {
		return endX;
	}
	
	/**
	 * @return The ending index (exclusive), in world blocks,
	 * of the bottom right y position of the render.
	 */
	double getEndY() {
		return endY;
	}
	
	/**
	 * @return The pixel size for the current paint.
	 */
	int getBlockSize() {
		return blockSize;
	}
	
	/**
	 * @return The starting index, in world blocks,
	 * of the top left x position of the render.
	 */
	double getStartX() {
		return startX;
	}
	
	/**
	 * @return The starting index, in world blocks,
	 * of the top left y position of the render.
	 */
	double getStartY() {
		return startY;
	}
	
//	/**
//	 * @return The current height, in pixels, of this panel.
//	 */
//	int getPanelHeight() {
//		return getHeight();
//	}
//	
//	/**
//	 * @return The current width, in pixels, of this panel.
//	 */
//	int getPanelWidth() {
//		return getWidth();
//	}
	
	@Override
	public void paintComponent(Graphics g) {
		/* This should be called every time the world is updated */
		super.paintComponent(g);
		applyVelocity();
		world.paintComponent(g);
	}
	
	private void applyVelocity() {
		double velVectorLength = Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2));
		if (velVectorLength < MIN_VELOCITY_VECTOR_LENGTH) {
			velocityX = 0;
			velocityY = 0;
			return;
		}
		
		/* Apply the velocity, but ensure that it is in bounds */
		double newStartX = Math.max(0, Math.min(xBound, startX + velocityX));
		double newStartY = Math.max(0, Math.min(yBound, startY + velocityY));
		
		/* Apply friction after performing movement */
		velocityX += -(velocityX / velVectorLength) * (velVectorLength / FRICTION_COEFFICIENT);
		velocityY += -(velocityY / velVectorLength) * (velVectorLength / FRICTION_COEFFICIENT);
		
		if (newStartX == startX) {
			/* If we hit a bound, then we do't want to preserve any velocity ***maybe*** */
			velocityX = 0;
		} else {
			/* Did not hit a bound, update start and end indexes */
			endX += newStartX - startX;
			startX = newStartX;
		}
		if (newStartY == startY) {
			velocityY = 0;
		} else {
			endY += newStartY - startY;
			startY = newStartY;
		}
	}
	
	/**
	 * This class is intended to handle all of the mouse based events for the {@link WorldPanel}.
	 * @author Timothy
	 */
	private class PanelMouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {
		/* TODO: Most of this work should be moved into WorldCamera */
		
//		private static final PanelMouseHandler INSTANCE = new PanelMouseHandler();
//		
//		private PanelMouseHandler() {}
//		static PanelMouseHandler getInstance() { return INSTANCE; }
		
		/** When we press down the mouse, we assume that the user is 
		 * trying to initiate a drag, and this value is set to find
		 * the beginning X position of the drag to calculate velocity.
		 * Is updated with the previous finish position throughout the
		 * course of the drag motion.
		 * @see dragFinishX
		 */
		private double dragStartX;
		/** When we press down the mouse, we assume that the user is 
		 * trying to initiate a drag, and this value is set to find
		 * the beginning Y position of the drag to calculate velocity.
		 * Is updated with the previous finish position throughout the
		 * course of the drag motion. 
		 * @see dragFinishY
		 */
		private double dragStartY;
		
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			/* Variables used frequently */
			int width = getWidth();
			int height = getHeight();
			
			/* Store the old blockSize for calculations, then update it */
			int prevBlockSize = blockSize;
			blockSize += e.getWheelRotation();
			
			/* Check that the pixel size does not exceed bounds.
			 * This code lies outside the if to avoid potential overflow,
			 * which could cause unexpected negative numbers.
			 */
			if (blockSize < MIN_BLOCK_SIZE) {
				blockSize = MIN_BLOCK_SIZE;
			} else if (blockSize > MAX_BLOCK_SIZE) {
				blockSize = MAX_BLOCK_SIZE;
			}
			
			/* Recalculate bounds, which represent the farthest right that the camera can be */
			xBound = Math.max(0, WorldConstants.WORLD_X - ((double) width / blockSize) - 1); // Bounds cannot be lower than zero.
			yBound = Math.max(0, WorldConstants.WORLD_Y - ((double) height / blockSize) - 1);
			
			/* Recalculate starting indexes, assuming that
			 * we zoom to and from the middle of the currently
			 * visible portion of the world.
			 */
			double xBlocks = width / (double) blockSize; // getWidth() / blockSize is the number of indexes we can display
			double yBlocks = height / (double) blockSize;
			double prevXBlocks = width / (double) prevBlockSize;
			double prevYBlocks = height / (double) prevBlockSize;
			double difXBlocks = prevXBlocks - xBlocks;
			double difYBlocks = prevYBlocks - yBlocks;
			
			/* Ensure that the new startX and startY fit within rendering bounds */
			startX = Math.max(0, Math.min(xBound, startX + (difXBlocks / 2)));
			startY = Math.max(0, Math.min(yBound, startY + (difYBlocks / 2)));
			endX = startX + xBlocks;
			endY = startY + yBlocks;
			
			// TODO: Recalculate bounds based on camera orientation. Rather than assume downward view.
			
			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			/* Dragging down and right gives positive delta values,
			 * but we want to move in the negative direction for both x and y
			 * when we drag down and right. This means that we want the sign
			 * of the velocity to be opposite of the delta values.
			 */
			
			/* Get the mouse position of the drag event */
			double dragFinishX = e.getXOnScreen();
			double dragFinishY = e.getYOnScreen();
			
			/* Calculate the delta values of the drag */
			double deltaX = dragFinishX - dragStartX;
			double deltaY = dragFinishY - dragStartY;
			
			/* Pan by the delta values of the drag.
			 * But we need to make sure we don't pan
			 * past the edges of the world.
			 */
			if (deltaX < 0) {
				velocityX += -deltaX / blockSize;
			} else {
				velocityX -= deltaX / blockSize;
			}
			if (deltaY < 0) {
				velocityY += -deltaY / blockSize;
			} else {
				velocityY -= deltaY / blockSize;
			}
			dragStartX = dragFinishX;
			dragStartY = dragFinishY;
			//System.out.println("Delta X: " + deltaX + "  Delta Y: " + deltaY);
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) { return; }

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("Clicked : " + e.getX() + ", " + e.getY());
		}

		@Override
		public void mousePressed(MouseEvent e) {
			dragStartX = e.getXOnScreen();
			dragStartY = e.getYOnScreen();
		}

		@Override
		public void mouseReleased(MouseEvent e) { return; }

		@Override
		public void mouseEntered(MouseEvent e) { return; }

		@Override
		public void mouseExited(MouseEvent e) { return; }
	}
}
