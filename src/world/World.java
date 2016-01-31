package world;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JComponent;

import simobject.SimObject;
import simobject.SimObjectType;
import simobject.block.WorldBlock;
import simobject.block.WorldBlockType;

public final class World extends JComponent {
	private static final long serialVersionUID = -6704749150768820789L;

	private WorldPanel worldPanel;
	private Queue<WorldEvent> eventQueue;
	
	/* Arrays in the form Z, Y, X */
	private ObjectLayer objectLayer;
	
	public World() {
		objectLayer = new ObjectLayer();
		eventQueue = new LinkedList<WorldEvent>();
	}
	
	World(ObjectLayer objectLayer) {
		this.objectLayer = objectLayer;
		eventQueue = new LinkedList<WorldEvent>();
	}
	
	public void queueEvent(WorldEvent event) { 
		eventQueue.add(event);
	}
	
	public void runForever() {
		while (true) {
			objectLayer.update();
			
			while (!eventQueue.isEmpty()) {
				processEvent(eventQueue.poll());
			}
			
			worldPanel.repaint();
		}
	}
	
	private void processEvent(WorldEvent event) {
		switch (event.getType()) {
		case OBJECT_MOVE:
			moveObject(event.getObject(), event.getDisplacement());
			break;
		default:
			break;
		}
	}
	
	/* **** Begin Event Processing Methods **** */
	private void moveObject(SimObject obj, DisplacementVector dis) {
		PositionVector oldPosition = obj.getPosition();
		PositionVector newPosition = PositionVector.getDestination(oldPosition, dis);
		objectLayer.clearObjectPosition(obj, oldPosition);
		objectLayer.setObjectPosition(obj, newPosition);
	}
	
	
	
	/* **** End Event Process Methods **** */
	
	
	void setPanel(WorldPanel panel) {
		this.worldPanel = panel;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		/* Get block size and starting position */
		int blockSize = worldPanel.getBlockSize();
		double startX = worldPanel.getStartX();
		double startY = worldPanel.getStartY();
		double endX = worldPanel.getEndX();
		double endY = worldPanel.getEndY();
		
		int partX = 0;
		int partY = 0;
		int partSizeX = 0;
		int partSizeY = 0;
		PositionVector pos;
		for (int y = (int) Math.floor(startY); y < (int) Math.ceil(endY); y++) {
			for (int x = (int) Math.floor(startX); x < (int) Math.ceil(endX); x++) {
				/* If our start and end indexes are non-integer */
				if (x < startX || x + 1 > endX || y < startY || y + 1 > endY) {
					/* Calculate the how much we need to render */
					if (x < startX) {
						partX = 0;
						partSizeX = (int) ((x + 1 - startX) * blockSize);
					} else if (x + 1 > endX) {
						partX = (int) ((x - startX) * blockSize);
						partSizeX = (int) ((endX - x) * blockSize);
					} else {
						partX = (int) ((x - startX) * blockSize);
						partSizeX = blockSize;
					}
					if (y < startY) {
						partY = 0;
						partSizeY = (int) ((y + 1 - startY) * blockSize);
					} else if (y + 1 > endY) {
						partY = (int) ((y - startY) * blockSize);
						partSizeY = (int) ((endY - y) * blockSize);
					} else {
						partY = (int) ((y - startY) * blockSize);
						partSizeY = blockSize;
					}
					
					pos = PositionVector.getPositionVector(0, y, x);
					g.setColor(objectLayer.getObject(pos).getRenderColor());
					g.fillRect(partX, partY, partSizeX, partSizeY);
				} else {
					pos = PositionVector.getPositionVector(0, y, x);
					g.setColor(objectLayer.getObject(pos).getRenderColor());
					g.fillRect((int) ((x - startX) * blockSize), (int) ((y - startY) * blockSize), blockSize, blockSize);
				}
			}
		}
	}
}
