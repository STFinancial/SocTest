package world.worldgraph;

import java.util.LinkedList;

import simobject.SimObject;
import world.PositionVector;
import world.WorldConstants;

/**
 * This class contains the underlying structure of the {@link World}
 * in which the simulation will take place. Currently holds pairs of 
 * {@link WorldGraphNode nodes} and their adjacent and diagonal positions.
 * @author Timothy
 */
public class WorldGraph {
	WorldGraphTuple[][][] graph;
	
	public WorldGraph() {
		graph = new WorldGraphTuple[WorldConstants.WORLD_Z][WorldConstants.WORLD_Y][WorldConstants.WORLD_X];
		
		/* Create all of the WorldGraphTuples for this graph */
		for (int z = 0; z < WorldConstants.WORLD_Z; z++) {
			for (int y = 0; y < WorldConstants.WORLD_Y; y++) {
				for (int x = 0; x < WorldConstants.WORLD_X; x++) {
					graph[z][y][x] = new WorldGraphTuple(new WorldGraphNode(PositionVector.getPositionVector(z, y, x)));
				}
			}
		}
		
		/* Assign all of the adjacency pointers for each of the tuples */
		LinkedList<WorldGraphTuple> adjList;
		LinkedList<WorldGraphTuple> diaList;
		for (int z = 0; z < WorldConstants.WORLD_Z; z++) {
			for (int y = 0; y < WorldConstants.WORLD_Y; y++) {
				for (int x = 0; x < WorldConstants.WORLD_X; x++) {
					adjList = new LinkedList<WorldGraphTuple>();
					diaList = new LinkedList<WorldGraphTuple>();
					
					/* Super lazy way to add adjacent tuples without a forest of if statements */
					try { adjList.add(graph[z][y][x + 1]); } catch (IndexOutOfBoundsException e) {}
					try { adjList.add(graph[z][y][x - 1]); } catch (IndexOutOfBoundsException e) {}
					try { adjList.add(graph[z][y + 1][x]); } catch (IndexOutOfBoundsException e) {}
					try { adjList.add(graph[z][y - 1][x]); } catch (IndexOutOfBoundsException e) {}
					try { adjList.add(graph[z + 1][y][x]); } catch (IndexOutOfBoundsException e) {}
					try { adjList.add(graph[z - 1][y][x]); } catch (IndexOutOfBoundsException e) {}
					
					/* Super lazy way to add diagonal tuples without a forest of if statements */
					try { diaList.add(graph[z][y + 1][x + 1]); } catch (IndexOutOfBoundsException e) {}
					try { diaList.add(graph[z][y + 1][x - 1]); } catch (IndexOutOfBoundsException e) {}
					try { diaList.add(graph[z][y - 1][x + 1]); } catch (IndexOutOfBoundsException e) {}
					try { diaList.add(graph[z][y - 1][x - 1]); } catch (IndexOutOfBoundsException e) {}
					
					try { diaList.add(graph[z + 1][y][x + 1]); } catch (IndexOutOfBoundsException e) {}
					try { diaList.add(graph[z + 1][y][x - 1]); } catch (IndexOutOfBoundsException e) {}
					try { diaList.add(graph[z - 1][y][x + 1]); } catch (IndexOutOfBoundsException e) {}
					try { diaList.add(graph[z - 1][y][x - 1]); } catch (IndexOutOfBoundsException e) {}
					
					try { diaList.add(graph[z + 1][y + 1][x]); } catch (IndexOutOfBoundsException e) {}
					try { diaList.add(graph[z + 1][y - 1][x]); } catch (IndexOutOfBoundsException e) {}
					try { diaList.add(graph[z - 1][y + 1][x]); } catch (IndexOutOfBoundsException e) {}
					try { diaList.add(graph[z - 1][y - 1][x]); } catch (IndexOutOfBoundsException e) {}
					
					try { diaList.add(graph[z + 1][y + 1][x + 1]); } catch (IndexOutOfBoundsException e) {}
					try { diaList.add(graph[z + 1][y + 1][x - 1]); } catch (IndexOutOfBoundsException e) {}
					try { diaList.add(graph[z + 1][y - 1][x + 1]); } catch (IndexOutOfBoundsException e) {}
					try { diaList.add(graph[z + 1][y - 1][x - 1]); } catch (IndexOutOfBoundsException e) {}
					try { diaList.add(graph[z - 1][y + 1][x + 1]); } catch (IndexOutOfBoundsException e) {}
					try { diaList.add(graph[z - 1][y + 1][x - 1]); } catch (IndexOutOfBoundsException e) {}
					try { diaList.add(graph[z - 1][y - 1][x + 1]); } catch (IndexOutOfBoundsException e) {}
					try { diaList.add(graph[z - 1][y - 1][x - 1]); } catch (IndexOutOfBoundsException e) {}
					
					graph[z][y][x].adjacentNodes = adjList;
					graph[z][y][x].adjacentNodes = diaList;
				}
			}
		}
	}
	
	
	/**
	 * Clears the {@link SimObject object} from its specified {@link PositionVector position}.
	 * Position is specified to make this operation constant time. Clears its position in both
	 * the world and object state so the object may fall out of scope after this is called.
	 * @param obj - The object we are looking to clear the position of.
	 * @param pos - The position to which the object is currently assigned.
	 */
	void clearObjectPosition(SimObject obj, PositionVector pos) {
		graph[pos.getZ()][pos.getY()][pos.getX()].node.clearNode();
		obj.clearPosition();
	}
	
	/**
	 * Returns the {@link SimObject} at the specified {@link PositionVector}
	 * if one exists.
	 * @param pos - The position for which we are trying to fetch the object.
	 * @return The object at the specified position. If such an object does not exist, we return null.
	 */
	SimObject getObject(PositionVector pos) {
		return graph[pos.getZ()][pos.getY()][pos.getX()].node.getObject();
	}
	
	/**
	 * Checks to see whether the specified position is occupied with an
	 * {@link Agent} or not.
	 * @param pos - The {@link PositionVector position} at which we want to know if there
	 * is an Agent or not.
	 * @return True if the specified position is occupied with an Agent, false otherwise.
	 */
	boolean isOccupied(PositionVector pos) {
		return graph[pos.getZ()][pos.getY()][pos.getX()].node.isOccupied();
	}
	
	/**
	 * Assigns the {@link SimObject object} to the specified {@link PositionVector position}
	 * in both the world and object state.
	 * @param obj - The object which we want to assign the position of.
	 * @param pos - The position to assign the object.
	 */
	void setObjectPosition(SimObject obj, PositionVector pos) {
		graph[pos.getZ()][pos.getY()][pos.getX()].node.addObject(obj);;
		obj.setPosition(pos);
	}
	
	public void update() {
		for (int z = 0; z < WorldConstants.WORLD_Z; z++) {
			for (int y = 0; y < WorldConstants.WORLD_Y; y++) {
				for (int x = 0; x < WorldConstants.WORLD_X; x++) {
					graph[z][y][x].update();
				}
			}
		}
	}
	
	/**
	 * This class holds information about a {@link WorldGraphNode node}
	 * and the nodes that are adjacent and diagonal to it.
	 * @author Timothy
	 */
	private class WorldGraphTuple {
		private WorldGraphNode node;
		private LinkedList<WorldGraphTuple> adjacentNodes;
		private LinkedList<WorldGraphTuple> diagonalNodes;
		
		private WorldGraphTuple(WorldGraphNode node) {
			this.node = node;
		}
		
		private void update() {
			node.update();
		}
	}
}
