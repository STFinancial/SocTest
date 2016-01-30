package simobject.agent.attributes;

/**
 * Represents the intelligence of an agent.
 * Intelligence relates to income. More later.
 * @author Timothy
 *
 */
class Intelligence extends AgentAttribute {
	
	private double baseIntelligence;
	private double currentIntelligence;
	//TODO: Genetic chance at intelligence anomaly
	
	Intelligence(double baseIntelligence) {
		this.baseIntelligence = baseIntelligence;
		this.currentIntelligence = baseIntelligence;
	}
	
	double getBaseIntelligence() {
		return baseIntelligence;
	}
	
	double getCurrentIntelligence() {
		return currentIntelligence;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Base Intelligence: " + baseIntelligence + "\t Current Intelligence: " + currentIntelligence);
		return sb.toString();
	}
}
