package main;

import java.io.FileNotFoundException;

import handleData.DataReader;


public class ResultPrinter {
	
	public static void main(String[] args) throws FileNotFoundException {
		DataReader.loadData();
		
		/*
		print(getMolarMassFromFormula("O"))
		# print(getMassPercentageFromFormula("O", "C17H21O4N"))
		# print(getNameFromFormula("KOH"))
		# print(getFormulaFromName("bromat"))
		# print(getAtomsFromAmountInFormula("Ag", 10, "AgNO3"))
		# print(getMassFromMole("O2", 1))
		# print(getMolesFromMass("C6H12O6", 10))
		# print(getMassFromLiquidReaction("KOH", ["K", "O", "H"], [1, 2], [0.1, 0.005]))
		# print(getMassFromGasReactantsAndMass("16CO2", ["2C8H18", "25O2"], [8536*getMolarMassFromFormula("C8H18"), 106700*getMolarMassFromFormula("O2")]))
		# print(getStdEnthalpyFromFormulaAndPhase("Mg(OH)2", "aq"))
		# print(getStdEnthalpyFromReaction(["C6H12O6", "6O2"], ["s", "g"], ["6CO2", "6H2O"], ["g", "l"]))
		# print(getStdEntropyFromFormulaAndPhase("H2O", "g"))
		# print(getStdEntropyFromReaction(["Na2SO4"], ["s"], ["2Na(+)", "SO4(2-)"], ["aq", "aq"]))
		# print(getStdGibbsFromFormulaAndPhase("H2O", "g"))
		# print(getStdGibbsFromReaction(["Na2SO4"], ["s"], ["2Na(+)", "SO4(2-)"], ["aq", "aq"]))
		# print(getSpontaneousTempFromEnthalpyAndEntropy(5400, 18))
		# print(getStdGibbsFromTempAndEquilConst(273+800, 2.5*(10**(-3))))
		# print(getEquilConstForStdFreeEnergyFromReactionAndTemp(["2SO2", "O2"], ["g", "g"], ["2SO3"], ["g"], 273.15+25))
		# print(getElectronicConfig("Ni(2+)"))
		# print(getAtomNumberFromElement("P"))

		*/
	}
	
	private static void print(String result) {
		System.out.println(result);
	}
	
	private static void print(int result) {
		System.out.println(result);
	}
	
	private static void print(double result) {
		System.out.println(result);
	}
	
}

/*
# Fix electro config for ioner, så 4s tømmes før 3d for ioner
# Eg. for Ni(2+) er det:
# [Ar]3d8
# og ikke
# [Ar]4s23d6


*/