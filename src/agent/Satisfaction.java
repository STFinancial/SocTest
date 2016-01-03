package agent;

class Satisfaction extends AgentAttribute {
	
	
	
	private float satisfaction;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Satisfaction: " + satisfaction);
		return sb.toString();
	}
}
