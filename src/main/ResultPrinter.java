package main;

import java.io.FileNotFoundException;

import data.DataReader;
import static results.AtomsAndMolecules.*;
import static results.Reactions.*;
import static results.Thermodynamics.*;
import static results.ElectronicConfigurations.*;
import static results.OrganicMolecules.*;


public class ResultPrinter {
	
	public static void main(String[] args) throws FileNotFoundException {
		DataReader.loadData();
		
		print(getMolarMassFromFormula("KOH"));
		// print(getMassPercentageFromFormula("O", "C17H21O4N"));
		print(getNameFromFormula("KOH"));
		print(getNumberOfAtomsFromMassOfFormula("Ag", 10, "AgNO3"));
		// print(getMassFromMole("O2", 1));
		// print(getMolesFromMass("C6H12O6", 10));
		// print(getMassFromLiquidReactantsAndMolarAndVolume("KOH", new String[] {"K", "OH"}, new double[] {1, 2}, new double[] {0.1, 0.005}));
		// print(getMassFromGasReactantsAndMass("16CO2", new String[] {"2C8H18", "25O2"}, new double[] {8536*getMolarMassFromFormula("C8H18"), 106700*getMolarMassFromFormula("O2")})); 
		// print(getStdEnthalpyFromFormulaAndPhase("Mg(OH)2", "aq"));
		// print(getStdEnthalpyFromReaction(new String[] {"C6H12O6", "6O2"}, new String[] {"s", "g"}, new String[] {"6CO2", "6H2O"}, new String[] {"g", "l"}));
		// print(getStdEntropyFromFormulaAndPhase("H2O", "g"));
		// print(getStdEntropyFromReaction(new String[] {"Na2SO4"}, new String[] {"s"}, new String[] {"2Na(+)", "SO4(2-)"}, new String[] {"aq", "aq"}));
		// print(getStdGibbsFreeEnergyFromFormulaAndPhase("H2O", "g"));
		// print(getStdGibbsFreeEnergyFromReaction(new String[] {"Na2SO4"}, new String[] {"s"}, new String[] {"2Na(+)", "SO4(2-)"}, new String[] {"aq", "aq"}));
		// print(getSpontaneousTempFromEnthalpyAndEntropy(5400, 18));
		// print(getStdGibbsFromTempAndEquilConst(273+800, 2.5*Math.pow(10,(-3))));
		// print(getEquilConstForStdFreeEnergyFromReactionAndTemp(new String[] {"2SO2", "O2"}, new String[] {"g", "g"}, new String[] {"2SO3"}, new String[] {"g"}, 273.15+25));
		// print(getElectronicConfig("Ni(2+)"));
		// print(getAtomNumberFromElement("P"));

	}
	
	private static void print(String result) {
		System.out.println(result);
	}
	
	private static void print(Integer result) {
		System.out.println(result);
	}
	
	private static void print(Double result) {
		System.out.println(result);
	}
	
}

/*
# Fix electro config for ioner, s� 4s t�mmes f�r 3d for ioner
# Eg. for Ni(2+) er det:
# [Ar]3d8
# og ikke
# [Ar]4s23d6


*/