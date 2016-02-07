package world.worldgraph;

import simobject.EmptyObject;
import simobject.SimObject;
import simobject.SimObjectType;
import world.PositionVector;


/**
 * This class represents a node within the {@link WorldGraph}. Each node
 * is a position that an {@link SimObject} may reside in. Furthermore,
 * each node should be uniquely identified by its {@link PositionVector position}
 * and any deviation from this rule will result in unexpected behavior.
 * @author Timothy
 * @see WorldGraph
 * @see World
 */
final class WorldGraphNode {
	/** The maximum number of {@link SimObject objects} that a graph node may contain at a single time */
	private static final int NODE_CAPACITY = 1;
	private static EmptyObject emptyObject = EmptyObject.getInstance();
	
	/** This is the position of the node within the World */
	private PositionVector position;
	/** The list of objects contained within this node */
	private SimObject object;
	
	
	/**
	 * The constructor for the node class. Note, that parameters are not copied and
	 * thus the object provided to the constructor should not be modified.
	 * @param pos - The position in the {@link WorldGraph} which this node
	 * will occupy.
	 * @see WorldGraph
	 */
	WorldGraphNode(PositionVector pos) {
		object = emptyObject;
		position = pos;
	}
	
	/**
	 * Adds the specified {@link SimObject object} to this node.
	 * This method will fail if the node capacity will be exceeded
	 * or if the provided object is null.
	 * @param object - The object we are attempting to add to this
	 * {@link WorldGraphNode node}. This object should not be of type null.
	 */
	void addObject(SimObject object) {
		if (this.object.getObjectType() == SimObjectType.EMPTY && object != null) { 
			this.object = object;
		}
	}
	
	/**
	 * Attempts to check if we can add an {@link SimObject object} to this {@link WorldGraphNode node}.
	 * This method currently will return false only if the node cannot hold any additional objects,
	 * and note that this may change if changes are made to the number of a specific type of object
	 * that may occupy a given node of the graph.
	 * @return True if this object can be added to this node, false otherwise.
	 */
	boolean canAddObject() {
		return object.getObjectType() == SimObjectType.EMPTY;
	}
	
	/**
	 * Removes any non-empty {@link SimObject objects} from this {@link WorldGraphNode node}.
	 */
	void clearNode() {
		object = emptyObject;
	}
	
	/**
	 * This method checks to see if the specified {@link SimObject}
	 * is present within this {@link WorldGraphNode}
	 * @param object - The object whose presence we are checking for.
	 * @return True if the specified object is present in this node, false otherwise.
	 */
	boolean containsObject(SimObject object) {
		return object.equals(object);
	}
	
	
	/**
	 * Checks to see whether the specified position is occupied with an
	 * {@link Agent} or not.
	 * @return True if the specified position is occupied with an Agent, false otherwise.
	 */
	boolean isOccupied() {
		return object.getObjectType() == SimObjectType.AGENT;
	}
	
	/**
	 * @return The {@link SimObject} currently housed by this {@link WorldGraphNode node} if one exists.
	 * If this node is empty, then an {@link EmptyObject} is returned.
	 */
	SimObject getObject() {
		return object;
	}
	
	void update() {
		object.update();
	}
	
	@Override
	public int hashCode() {
		/* A node's hashcode is simply the hashcode of its position */
		return position.hashCode() + NODE_CAPACITY;
	}
	
	@Override
	public boolean equals(Object o) {
		/* An instance of a node is equal if their positions are equal, currently */
		if (o instanceof WorldGraphNode) {
			WorldGraphNode n = (WorldGraphNode) o;
			return n.position.equals(position);
		}
		return false;
	}
	
//	private final class NodeIterator<E> implements Iterator<E> {
//		private LinkedList<E> list;
//		private int index;
//		
//		private NodeIterator(LinkedList<E> list) {
//			this.list = list;
//			this.index = 0;
//		}
//		
//		@Override
//		public boolean hasNext() {
//			return index < list.size();
//		}
//
//		/* TODO: Getting is slower for LinkedList, consider switching to ArrayList */
//		@Override
//		public E next() {
//			if(hasNext()) {
//	            return list.get(index++);
//	        } else {
//	            throw new NoSuchElementException("There are no elements size = " + list.size());
//	        }
//		}
//		
//		/** Do not use this method */
//		@Override
//		public void remove() {
//			throw new UnsupportedOperationException();
//		}
//	}
}

