package agent;

/**
 * Represents the intelligence of an agent.
 * Intelligence relates to income. More later.
 * @author Timothy
 *
 */
class Intelligence extends AgentAttribute {
	
	private float baseIntelligence;
	private float currentIntelligence;
	//TODO: Genetic chance at intelligence anomaly
	
	Intelligence(float baseIntelligence) {
		this.baseIntelligence = baseIntelligence;
		this.currentIntelligence = baseIntelligence;
	}
	
	float getBaseIntelligence() {
		return baseIntelligence;
	}
	
	float getCurrentIntelligence() {
		return currentIntelligence;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Base Intelligence: " + baseIntelligence + "\t Current Intelligence: " + currentIntelligence);
		return sb.toString();
	}
}
