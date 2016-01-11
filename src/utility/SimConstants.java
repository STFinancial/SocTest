package utility;

public final class SimConstants {
	/** Represents the standard deviation of the intelligence trait */
	public static final int INTELLIGENCE_STD_DEV = 8;
	/** Represents the magnitude from baseline, in intelligence points, of an intelligence anomaly... either disabled or genius */
	public static final int INTELLIGENCE_ANOMALY_MAGNITUDE = 40;
	/** Represents the percentage of agents with intelligence anomaly (out of 1000) */
	public static final int INTELLIGENCE_ANOMALY_THRESHOLD = 10;
	/** Represents the average value of intelligence, when randomly generated */
	public static final int INTELLIGENCE_DEFAULT_AVERAGE = 100;
	
	/** Represents the standard deviation of physical traits. Assuming similar to intelligence atm */
	public static final int PHYSICAL_STD_DEV = 10;
	/** Represents the percentage of agents with negative physical anomaly (out of 1000) */
	public static final int PHYSICAL_ANOMALY_THRESHOLD = 10;
	/** Represents the average value of physical, when randomly generated */
	public static final int PHYSICAL_DEFAULT_AVERAGE = 100;
}
