package com.example.drew.test1;
/**
 * SPECIES
 * @author Mathieu
 *
 * All available species with their corresponding equation.
 */

public enum Species {

	BALSAM_FIR(new MiscAbpe(-2.2304,2.3263)),
	WHITE_SPRUCE(new MiscAbpe(-1.8322,2.2413)),
	BLACK_SPRUCE(new MiscAbpe(-1.3371,2.0707)),
	RED_SPRUCE(new MiscAbpe(-1.7957,2.2417)),
	EASTERN_WHITE_CEDAR(new MiscAbpe(-1.9615,2.1063)),
	EASTERN_LARCH(new MiscAbpe(-2.3012,2.3853)),
	WHITE_PINE(new MiscAbpe(-2.6177,2.4638)),
	RED_PINE(new MiscAbpe(-2.6177,2.4638)),
	JACK_PINE(new MiscAbpe(-2.6177,2.4638)),
	EASTERN_HEMLOCK(new MiscAbpe(-2.3480,2.3876)),
	RED_MAPLE(new MiscAbpe(-1.9702,2.3405)),
	SUGAR_MAPLE(new MiscAbpe(-1.8760,2.3924)),
	YELLOW_BIRCH(new MiscAbpe(-2.1306,2.4510)),
	WHITE_BIRCH(new MiscAbpe(-2.0045,2.3634)),
	GREY_BIRCH(new MiscAbpe(-2.2271,2.4513)),
	LARGE_TOOTHED_ASPEN(new MiscAbpe(-2.3200,2.3773)),
	TREMBLING_ASPEN(new MiscAbpe(-2.3778,2.4085)),
	IRON_WOOD(new MiscAbpe(-2.2652,2.5349)),
	BLACK_CHERRY(new MiscAbpe(-2.2118,2.4133)),
	AMERICAN_BEECH(new MiscAbpe(-2.0705,2.4410)),
	RED_OAK(new MiscAbpe(-2.0705,2.4410)),
	WHITE_ASH(new MiscAbpe(-2.0314,2.3524)),
	GENERIC_HARD_WOOD(new GenericAbpe(-2.46,1.01)),
	GENERIC_SOFT_WOOD(new GenericAbpe(-2.41,1.01));

	private AbpEquation equation;

	private Species(AbpEquation equation){
		this.equation = equation;
	}

	/**
	 * Get corresponding Allometric Biomass Prediction Equation
	 * @return abpEquation
	 */
	public AbpEquation getAbpEquation(){
		return this.equation;
	}
}
