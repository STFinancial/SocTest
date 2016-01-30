package simobject.agent.attributes;

import simobject.agent.Agent;

public final class AttributeBuilder {
	private static final AttributeBuilder INSTANCE = new AttributeBuilder();
	
	private AgentAttributeHolder currentHolder = null;
	private AttributeBuilder() { }
	
	/** @return The singleton instance of this class that allows building an {@link AgentAttributeHolder} for an {@link Agent}. */
	public static AttributeBuilder getInstance() {
		return INSTANCE;
	}
	
	
	
	
	/* ***** Set Methods ***** */
	
	/**
	 * Initializes the {@link Intelligence} of this set of attributes.
	 * @param baseIntelligence - The base value for this attribute set's intelligence.
	 */
	public void setIntelligence(double baseIntelligence) { currentHolder.setIntelligence(baseIntelligence); }
	/**
	 * Initializes the {@link Physical} of this set of attributes.
	 * @param basePhysical - The base value for this attribute set's intelligence
	 */
	public void setPhysical(double basePhysical) { currentHolder.setPhysical(basePhysical); }
	
	/* ***** End of Set Methods ***** */
	
	
	/** 
	 * This method is called to begin the building process. 
	 * Builds which are not begun will throw null pointer exceptions 
	 * Building generates a {@link AgentAttributeHolder} which will then
	 * be assigned to a specific {@link Agent}. 
	 */
	public void begin() {
		currentHolder = new AgentAttributeHolder();
	}
	
	/**
	 * This method should be called to end of the building process
	 * for the returned {@link AgentAttributeHolder}. No calls to
	 * "set" methods should be made after this method is called until
	 * {@link build} is called again. All changes to the base values
	 * of the returned AgentAttributeHolder are finalized upon calling
	 * this method.
	 * @return An AgentAttributeHolder with values assigned as provided by the
	 * "set" methods or with their default values.
	 * @see {@link build} 
	 * @see {@link setIntelligence}
	 * @see {@link setPhysical}
	 */
	public AgentAttributeHolder finishBuild() {
		AgentAttributeHolder toReturn = currentHolder;
		currentHolder = null;
		return toReturn;
	}
}
