package agent;

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
}
