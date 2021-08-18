package main;

import java.io.FileNotFoundException;

import data.DataReader;
import static results.AtomsAndMolecules.*;
import static results.Reactions.*;
import static results.Thermodynamics.*;
import static results.ElectronicConfigurations.*;
import static results.OrganicMolecules.*;
import static results.RedoxReaction.*;
import static results.IdealGasEquation.*;


public class ResultPrinter {
	
	public static void main(String[] args) throws FileNotFoundException {
		DataReader.loadData();
		
		// print(getMolarMassFromFormula("KOH"));
		// print(getMassPercentageFromFormula("O", "C17H21O4N"));
		// print(getNameFromFormula("KOH"));
		// print(getNumberOfAtomsFromMassOfFormula("Ag", 10, "AgNO3"));
		print(getMassFromMole("SO2", 0.1559));
		print(getMolesFromMass("S", 5));
		print(getMolesFromMass("C12H26", 1000));
		// print(getMassFromLiquidReactantsAndMolarAndVolume("KOH", new String[] {"K", "OH"}, new double[] {1, 2}, new double[] {0.1, 0.005}));
		print(getMassFromGasOrSolidReactantsAndMass("SO2", new String[] {"S", "O2"}, new double[] {5, 12.5})); 
		// print(getStdEnthalpyFromFormulaAndPhase("Mg(OH)2", "aq"));
		// print(getStdEnthalpyFromReaction(new String[] {"C6H12O6", "6O2"}, new String[] {"s", "g"}, new String[] {"6CO2", "6H2O"}, new String[] {"g", "l"}));
		// print(getStdEntropyFromFormulaAndPhase("H2O", "g"));
		// print(getStdEntropyFromReaction(new String[] {"Na2SO4"}, new String[] {"s"}, new String[] {"2Na(+)", "SO4(2-)"}, new String[] {"aq", "aq"}));
		// print(getStdGibbsFreeEnergyFromFormulaAndPhase("H2O", "g"));
		// print(getStdGibbsFreeEnergyFromReaction(new String[] {"Na2SO4"}, new String[] {"s"}, new String[] {"2Na(+)", "SO4(2-)"}, new String[] {"aq", "aq"}));
		// print(getSpontaneousTempFromEnthalpyAndEntropy(5400, 18));
		// print(getStdGibbsFromTempAndEquilConst(273+800, 2.5*Math.pow(10,(-3))));
		// print(getEquilConstForStdFreeEnergyFromReactionAndTemp(new String[] {"2SO2", "O2"}, new String[] {"g", "g"}, new String[] {"2SO3"}, new String[] {"g"}, 273.15+25));
		print(getElectronicConfig("Br(-)"));
		// print(getAtomNumberFromElement("P"));
		printFunctionalGroups();
		print(getUnknownValue(1., null, 5.871114936468665*25, 273+3000.));
		
		
		// print(getRedoxReaction(new String[] {"Al", "NO3(-)"}, new String[] {"Al(OH)4(-)", "NH3"}, "Base"));
		// print(getRedoxReaction(new String[] {"Ca(2+)", "HPO4(2-)"}, new String[] {"Ca5(PO4)3OH"}, "Base"));
		// print(getRedoxReaction(new String[] {"HgCl2", "NH3"}, new String[] {"HgNH2Cl", "Cl(-)"}, "Base"));
		// print(getRedoxReaction(new String[] {"HgCl2", "NH3"}, new String[] {"HgNH2Cl", "Cl(-)"}, "Base"));
		// print(getRedoxReaction(new String[] {"I2", "SO2"}, new String[] {"I(-)", "SO4(2-)"}, "Acid"));

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