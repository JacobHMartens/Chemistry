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
import static results.AcidsBases.*;


public class ResultPrinter {
	
	public static void main(String[] args) throws FileNotFoundException {
		DataReader.loadData();
		
//		print(getMolarMassFromFormula("C9H8O4"));
//		print(getMassPercentageFromFormula("O", "C17H21O4N"));
//		print(getNameFromFormula("KOH"));
//		print(getNumberOfAtomsFromMassOfFormula("Ag", 10, "AgNO3"));
//		print(getMassFromMole("O2", 51.70691090757702)+621);
//		print(getMolesFromMass("C", 621));
//		print(getMolesFromMass("N2", 807));
//		print(getMassFromLiquidReactantsAndMolarAndVolume("KOH", new String[] {"K", "OH"}, new double[] {1, 2}, new double[] {0.1, 0.005}));
//		print(getMassFromGasOrSolidReactantsAndMass("LiFePO4", new String[] {"Li2CO3", "NH4H2PO4", "Fe3O4"}, new double[] {5, 5, 5})); 
//		print(getStdEnthalpyFromFormulaAndPhase("Mg(OH)2", "aq"));
//		print(getStdEnthalpyFromReaction(new String[] {"C6H12O6", "6O2"}, new String[] {"s", "g"}, new String[] {"6CO2", "6H2O"}, new String[] {"g", "l"}));
//		print(getStdEntropyFromFormulaAndPhase("H2O", "g"));
//		print(getStdEntropyFromReaction(new String[] {"Na2SO4"}, new String[] {"s"}, new String[] {"2Na(+)", "SO4(2-)"}, new String[] {"aq", "aq"}));
//		print(getStdGibbsFreeEnergyFromFormulaAndPhase("H2O", "g"));
//		print(getStdGibbsFreeEnergyFromReaction(new String[] {"Na2SO4"}, new String[] {"s"}, new String[] {"2Na(+)", "SO4(2-)"}, new String[] {"aq", "aq"}));
//		print(getSpontaneousTempFromEnthalpyAndEntropy(5400, 18));
//		print(getStdGibbsFromTempAndEquilConst(25+273, 1.75/0.97));
//		print(getEquilConstForStdFreeEnergyFromReactionAndTemp(new String[] {"2SO2", "O2"}, new String[] {"g", "g"}, new String[] {"2SO3"}, new String[] {"g"}, 273.15+25));
//		print(getElectronicConfig("S"));
//		print(getAtomNumberFromElement("P"));
//		printFunctionalGroups();
//		print(getUnknownValue(1., 12000., 5.871114936468665*25, 273+3000.));
//		print(getStrongWeakAcids());
		
		print(getRedoxReaction(new String[] {"Pb3O4", "Cl(-)"}, new String[] {"PbCl2", "Cl2"}, "Acid"));
//		print(getRedoxReaction(new String[] {"Ba(2+)", "Cr2O7(2-)"}, new String[] {"BaCrO4"}, "Acid"));
//		print(getRedoxReaction(new String[] {"Ca(2+)", "HPO4(2-)"}, new String[] {"Ca5(PO4)3OH"}, "Base"));
//		print(getRedoxReaction(new String[] {"HgCl2", "NH3"}, new String[] {"HgNH2Cl", "Cl(-)"}, "Base"));
//		print(getRedoxReaction(new String[] {"Cu", "NO3(-)"}, new String[] {"NO", "Cu(2+)"}, "Acid"));
//		print(getRedoxReaction(new String[] {"I2", "SO2"}, new String[] {"I(-)", "SO4(2-)"}, "Acid"));
//		print(getRedoxReaction(new String[] {"MnO4(-)", "I(-)"}, new String[] {"I2", "Mn(2+)"}, "Acid"));
//		print(getRedoxReaction(new String[] {"VO4(3-)", "Fe(2+)"}, new String[] {"VO(2+)", "Fe(3+)"}, "Acid"));
//		print(getRedoxReaction(new String[] {"CuSCN", "KIO3", "HCl"}, new String[] {"CuSO4", "KCl", "HCN", "ICI"}, "Base"));

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