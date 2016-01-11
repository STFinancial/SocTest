package utility;

import java.util.Random;

import sex.Sex;

public final class VariationHandler {
	private static Random random = new Random();
	private static Sex[] sexes = Sex.values();
	
	
	
	/**
	 * @return A randomly generated base value for {@link Intelligence}.
	 * The average of these values corresponds to the {@link SimConstants#INTELLIGENCE_DEFAULT_AVERAGE default average intelligence}
	 * as specified in {@link SimConstants}
	 */
	public static double getRandomIntelligence() {
		return SimConstants.INTELLIGENCE_DEFAULT_AVERAGE + random.nextGaussian() * SimConstants.INTELLIGENCE_STD_DEV;
	}
	
	/**
	 * @return A randomly generated base value for {@link Physical}.
	 * The average of these values corresponds to the {@link SimConstants#PHYSICAL_DEFAULT_AVERAGE default average physical}
	 * as specified in {@link SimConstants}
	 */
	public static double getRandomPhysical() {
		return SimConstants.PHYSICAL_DEFAULT_AVERAGE + random.nextGaussian() * SimConstants.PHYSICAL_STD_DEV;
	}
	
	/**
	 * @return A randomly generated {@link Sex} value.
	 */
	public static Sex getRandomSex() {
		return sexes[random.nextInt(2)];
	}
	
	public static float applyIntelligenceVariation(float baseIntelligence) {
		baseIntelligence += random.nextGaussian() * SimConstants.INTELLIGENCE_STD_DEV;
		return baseIntelligence;
//		if (random.nextInt(1000) > SimConstants.INTELLIGENCE_ANOMALY_THRESHOLD) {
//			return baseIntelligence;
//		} else {
//			return baseIntelligence + ((2 * random.nextInt(2)) - 1) * SimConstants.INTELLIGENCE_ANOMALY_MAGNITUDE;
//		}
	}
	
	//public static float applyPhysicalVariation(float )
}
