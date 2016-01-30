package world;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

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
	private SimObject[][][] objectLayer;
	
	public World() {
		objectLayer = new SimObject[WorldConstants.WORLD_Z][WorldConstants.WORLD_Y][WorldConstants.WORLD_X];
		eventQueue = new LinkedList<WorldEvent>();
//		blockLayer = new WorldBlock[WorldConstants.WORLD_Z][WorldConstants.WORLD_Y][WorldConstants.WORLD_X];
//		newObjectLayer = new SimObject[WorldConstants.WORLD_Z][WorldConstants.WORLD_Y][WorldConstants.WORLD_X];
	}
	
	World(SimObject[][][] objectLayer) {
		this.objectLayer = objectLayer;
		eventQueue = new LinkedList<WorldEvent>();
//		this.blockLayer = blockLayer;
//		newObjectLayer = new SimObject[WorldConstants.WORLD_Z][WorldConstants.WORLD_Y][WorldConstants.WORLD_X];
	}
	
	public void queueEvent(WorldEvent event) { 
		eventQueue.add(event);
	}
	
	public void runForever() {
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for (int z = 0; z < WorldConstants.WORLD_Z; z++) {
				for (int y = 0; y < WorldConstants.WORLD_Y; y++) {
					for (int x = 0; x < WorldConstants.WORLD_X; x++) {
						objectLayer[z][y][x].update();
					}
				}
			}
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
		//TODO
	}
	
	
	
	/* **** End Event Process Methods **** */
	
	/* This method is for hacking, not permanent use */
	void setObject(SimObject obj, PositionVector pv) {
		objectLayer[pv.getZ()][pv.getY()][pv.getX()] = obj;
	}
	
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
//		System.out.println("Painting X: " + Math.floor(startX) + ", " + Math.ceil(endX) + " Y: " + Math.floor(startY) + ", " + Math.ceil(endY));
		for (int y = (int) Math.floor(startY); y < (int) Math.ceil(endY); y++) {
			for (int x = (int) Math.floor(startX); x < (int) Math.ceil(endX); x++) {
				if (x < startX || x + 1 > endX || y < startY || y + 1 > endY) {
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
					//System.out.println("Getting here: " + partX + ", " + partY + ", " + partSizeX + ", " + partSizeY);
					if (objectLayer[0][y][x].getObjectType() == SimObjectType.BLOCK && ((WorldBlock) objectLayer[0][y][x]).getType() != WorldBlockType.EMPTY) {
						
						g.setColor(Color.BLACK);
						g.fillRect(partX, partY, partSizeX, partSizeY);
					}
					if (objectLayer[0][y][x] != null && objectLayer[0][y][x].getObjectType() == SimObjectType.AGENT) {
						g.setColor(Color.RED);
						g.fillRect(partX, partY, partSizeX, partSizeY);
					}
				} else {
					if (objectLayer[0][y][x].getObjectType() == SimObjectType.BLOCK && ((WorldBlock) objectLayer[0][y][x]).getType() != WorldBlockType.EMPTY) {
						g.setColor(Color.BLACK);
						g.fillRect((int) ((x - startX) * blockSize), (int) ((y - startY) * blockSize), blockSize, blockSize);	
					}
					if (objectLayer[0][y][x] != null && objectLayer[0][y][x].getObjectType() == SimObjectType.AGENT) {
						g.setColor(Color.RED);
						g.fillRect((int) ((x - startX) * blockSize), (int) ((y - startY) * blockSize), blockSize, blockSize);	
					}
				}
				
			}
		}
	}
}
