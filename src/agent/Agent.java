package agent;

import sex.Sex;

public class Agent {
	
	/* Independent Attributes */
	private Sex sex;
	private int age; /* In months */
	
	/* Agent Attributes */
	Intelligence intelligence;
	Physical physical;
	Satisfaction satisfaction;
	Knowledge knowledge;
	
	Agent(Sex sex) {
		this.sex = sex;
		this.age = 0;
	}
}
