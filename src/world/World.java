package world;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JComponent;

import utility.SimObject;

public final class World extends JComponent {
	private static final long serialVersionUID = -6704749150768820789L;

	private WorldPanel worldPanel;
	
	/* Arrays in the form Z, Y, X */
	private WorldBlock[][][] blockLayer;
	private SimObject[][][] objectLayer;
	private SimObject[][][] newObjectLayer;
	
	public World() {
		objectLayer = new SimObject[WorldConstants.WORLD_Z][WorldConstants.WORLD_Y][WorldConstants.WORLD_X];
		blockLayer = new WorldBlock[WorldConstants.WORLD_Z][WorldConstants.WORLD_Y][WorldConstants.WORLD_X];
		newObjectLayer = new SimObject[WorldConstants.WORLD_Z][WorldConstants.WORLD_Y][WorldConstants.WORLD_X];
	}
	
	World(SimObject[][][] objectLayer, WorldBlock[][][] blockLayer) {
		this.objectLayer = objectLayer;
		this.blockLayer = blockLayer;
		newObjectLayer = new SimObject[WorldConstants.WORLD_Z][WorldConstants.WORLD_Y][WorldConstants.WORLD_X];
	}
	
	public void runForever() {
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int y = 0; y < WorldConstants.WORLD_Y; y++) {
				for (int x = 0; x < WorldConstants.WORLD_X; x++) {
					if (objectLayer[0][y][x] != null) {
						Random r = new Random();
						Direction d = Direction.values()[r.nextInt(4)];
						switch (d) {
						case SOUTH:
							if (y < WorldConstants.WORLD_Y - 1) {
								newObjectLayer[0][y + 1][x] = objectLayer[0][y][x];
								newObjectLayer[0][y][x] = null;
							} else {
								newObjectLayer[0][y][x] = objectLayer[0][y][x];
							}
							break;
						case WEST:
							if (x > 0) {
								newObjectLayer[0][y][x - 1] = objectLayer[0][y][x];
								newObjectLayer[0][y][x] = null;
							} else {
								newObjectLayer[0][y][x] = objectLayer[0][y][x];
							}
							break;
						case EAST:
							if (x < WorldConstants.WORLD_X - 1) {
								newObjectLayer[0][y][x + 1] = objectLayer[0][y][x];
								newObjectLayer[0][y][x] = null;
							} else {
								newObjectLayer[0][y][x] = objectLayer[0][y][x];
							}
							break;	
						case NORTH:
							if (y > 0) {
								newObjectLayer[0][y - 1][x] = objectLayer[0][y][x];
								newObjectLayer[0][y][x] = null;
							} else {
								newObjectLayer[0][y][x] = objectLayer[0][y][x];
							}
							break;
						}
					}
				}
			}
			for (int y = 0; y < WorldConstants.WORLD_Y; y++) {
				for (int x = 0; x < WorldConstants.WORLD_X; x++) {
					objectLayer[0][y][x] = newObjectLayer[0][y][x];
				}
			}
			
			worldPanel.repaint();
		}
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
					if (((WorldBlock) blockLayer[0][y][x]).getType() != WorldBlockType.EMPTY) {
						
						g.setColor(Color.BLACK);
						g.fillRect(partX, partY, partSizeX, partSizeY);
					}
					if (objectLayer[0][y][x] != null) {
						g.setColor(Color.RED);
						g.fillRect(partX, partY, partSizeX, partSizeY);
					}
				} else {
					if (((WorldBlock) blockLayer[0][y][x]).getType() != WorldBlockType.EMPTY) {
						g.setColor(Color.BLACK);
						g.fillRect((int) ((x - startX) * blockSize), (int) ((y - startY) * blockSize), blockSize, blockSize);	
					}
					if (objectLayer[0][y][x] != null) {
						g.setColor(Color.RED);
						g.fillRect((int) ((x - startX) * blockSize), (int) ((y - startY) * blockSize), blockSize, blockSize);	
					}
				}
				
			}
		}
	}
}
