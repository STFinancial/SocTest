package agent;

class Satisfaction extends AgentAttribute {
	
	
	
	private double satisfaction;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Satisfaction: " + satisfaction);
		return sb.toString();
	}
}
