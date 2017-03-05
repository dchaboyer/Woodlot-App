package com.example.drew.test1;
/**
 * SPECIES
 * @author Mathieu
 *
 * All available species with their corresponding equation.
 */

public enum Species {

	BALSAM_FIR("Balsam Fur", new MiscAbpe(-2.2304,2.3263)),
	WHITE_SPRUCE("White Spruce", new MiscAbpe(-1.8322,2.2413)),
	BLACK_SPRUCE("Black Spruce", new MiscAbpe(-1.3371,2.0707)),
	RED_SPRUCE("Red Spruce", new MiscAbpe(-1.7957,2.2417)),
	EASTERN_WHITE_CEDAR("Eastern White Cedar", new MiscAbpe(-1.9615,2.1063)),
	EASTERN_LARCH("Eastern Larch", new MiscAbpe(-2.3012,2.3853)),
	WHITE_PINE("White Pine", new MiscAbpe(-2.6177,2.4638)),
	RED_PINE("Red Pine", new MiscAbpe(-2.6177,2.4638)),
	JACK_PINE("Jack Pine", new MiscAbpe(-2.6177,2.4638)),
	EASTERN_HEMLOCK("Eastern Hemlock", new MiscAbpe(-2.3480,2.3876)),
	RED_MAPLE("Red Maple", new MiscAbpe(-1.9702,2.3405)),
	SUGAR_MAPLE("Sugar Maple", new MiscAbpe(-1.8760,2.3924)),
	YELLOW_BIRCH("Yellow Birch", new MiscAbpe(-2.1306,2.4510)),
	WHITE_BIRCH("White Birch", new MiscAbpe(-2.0045,2.3634)),
	GREY_BIRCH("Grey Birch", new MiscAbpe(-2.2271,2.4513)),
	LARGE_TOOTHED_ASPEN("Large Toothed Aspen", new MiscAbpe(-2.3200,2.3773)),
	TREMBLING_ASPEN("Trembling Aspen", new MiscAbpe(-2.3778,2.4085)),
	IRON_WOOD("Iron Wood", new MiscAbpe(-2.2652,2.5349)),
	BLACK_CHERRY("Black Cherry", new MiscAbpe(-2.2118,2.4133)),
	AMERICAN_BEECH("American Beech", new MiscAbpe(-2.0705,2.4410)),
	RED_OAK("Red Oak", new MiscAbpe(-2.0705,2.4410)),
	WHITE_ASH("White Ash", new MiscAbpe(-2.0314,2.3524)),
	GENERIC_HARD_WOOD("Generic Hard Wood", new GenericAbpe(-2.46,1.01)),
	GENERIC_SOFT_WOOD("Generic Soft Wood", new GenericAbpe(-2.41,1.01));

	private AbpEquation equation;
	private String name;

	private Species(String name, AbpEquation equation){
		this.name = name;
		this.equation = equation;
	}

	/**
	 * Get corresponding Allometric Biomass Prediction Equation
	 * @return abpEquation
	 */
	public AbpEquation getAbpEquation(){
		return this.equation;
	}

	/**
	 * Get corresponding name
	 * @return name
	 */
	public String getName(){
		return this.name;
	}
}
