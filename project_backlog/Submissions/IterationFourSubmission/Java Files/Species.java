package com.example.drew.test1;
/**
 * SPECIES
 * @author Mathieu
 *
 * All available species with their corresponding equation.
 */

public enum Species {


	AMERICAN_BEECH("American Beech", InputParser.HARDWOOD, new MiscAbpe(-2.0705,2.4410)),
	BALSAM_FIR("Balsam Fir", InputParser.SOFTWOOD, new MiscAbpe(-2.2304,2.3263)),
	BLACK_CHERRY("Black Cherry", InputParser.HARDWOOD, new MiscAbpe(-2.2118,2.4133)),
	BLACK_SPRUCE("Black Spruce", InputParser.SOFTWOOD, new MiscAbpe(-1.3371,2.0707)),
	EASTERN_HEMLOCK("Eastern Hemlock", InputParser.SOFTWOOD, new MiscAbpe(-2.3480,2.3876)),
	EASTERN_LARCH("Eastern Larch", InputParser.SOFTWOOD, new MiscAbpe(-2.3012,2.3853)),
	EASTERN_WHITE_CEDAR("Eastern White Cedar", InputParser.SOFTWOOD, new MiscAbpe(-1.9615,2.1063)),
	GENERIC_HARD_WOOD("Generic Hard Wood", InputParser.HARDWOOD, new GenericAbpe(2.46,1.01)),
	GENERIC_SOFT_WOOD("Generic Soft Wood", InputParser.SOFTWOOD, new GenericAbpe(2.41,1.01)),
	GREY_BIRCH("Grey Birch",InputParser.HARDWOOD, new MiscAbpe(-2.2271,2.4513)),
	IRON_WOOD("Iron Wood", InputParser.HARDWOOD, new MiscAbpe(-2.2652,2.5349)),
	JACK_PINE("Jack Pine", InputParser.SOFTWOOD, new MiscAbpe(-2.6177,2.4638)),
	LARGE_TOOTHED_ASPEN("Large Toothed Aspen", InputParser.HARDWOOD, new MiscAbpe(-2.3200,2.3773)),
	RED_MAPLE("Red Maple", InputParser.HARDWOOD, new MiscAbpe(-1.9702,2.3405)),
	RED_OAK("Red Oak", InputParser.HARDWOOD, new MiscAbpe(-2.0705,2.4410)),
	RED_PINE("Red Pine", InputParser.SOFTWOOD, new MiscAbpe(-2.6177,2.4638)),
	RED_SPRUCE("Red Spruce", InputParser.SOFTWOOD, new MiscAbpe(-1.7957,2.2417)),
	SUGAR_MAPLE("Sugar Maple", InputParser.HARDWOOD, new MiscAbpe(-1.8760,2.3924)),
	TREMBLING_ASPEN("Trembling Aspen", InputParser.HARDWOOD, new MiscAbpe(-2.3778,2.4085)),
	WHITE_ASH("White Ash", InputParser.HARDWOOD, new MiscAbpe(-2.0314,2.3524)),
	WHITE_BIRCH("White Birch", InputParser.HARDWOOD, new MiscAbpe(-2.0045,2.3634)),
	WHITE_PINE("White Pine", InputParser.SOFTWOOD, new MiscAbpe(-2.6177,2.4638)),
	WHITE_SPRUCE("White Spruce", InputParser.SOFTWOOD, new MiscAbpe(-1.8322,2.2413)),
	YELLOW_BIRCH("Yellow Birch", InputParser.HARDWOOD, new MiscAbpe(-2.1306,2.4510));

	private AbpEquation equation;
	private String name;
	private boolean isHardwood;

	private Species(String name, boolean isHardwood, AbpEquation equation){
		this.name = name;
		this.isHardwood = isHardwood;
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

	public boolean isHardwood() {return this.isHardwood;}
}
