package results;

import data.DataReader;
import static data.DataReader.*;

import static results.BaseOperations.splitFormula;
import static results.Constants.*;

import java.io.FileNotFoundException;
import java.util.Map;


public class AtomsAndMolecules {
	
	private static Map<String, Integer> formulaDict;
		

	public static void main(String[] args) throws FileNotFoundException {
		DataReader.loadData();
		
	}
	
	public static int getAtomNumberFromElement(String formula) {
		return symbolToAtomicNumber.get(formula);
	}
	
	public static String getElementFromAtomNumber(int number) {
		return atomicNumberToSymbol.get(number);
	}
	
	public static double getMolarMassFromFormula(String formula) {
		formulaDict = splitFormula(formula);
		double totalMass = 0;
		for (Map.Entry<String, Integer> entry : formulaDict.entrySet()) {
			totalMass += entry.getValue() * symbolToMolarMass.get(entry.getKey());
		}
		return totalMass;
	}
	
	public static double getMassPercentageFromFormula(String element, String formula) {
		formulaDict = splitFormula(formula);
		return 100 * formulaDict.get(element) * getMolarMassFromFormula(element) / getMolarMassFromFormula(formula);
	}
	
	public static String getAtomsFromAmountInFormula(String element, double amountInGrams, String formula) {
		double grams = amountInGrams * getMassPercentageFromFormula(element, formula) / 100;
		double moles = grams / getMolarMassFromFormula(element);
		double atoms = moles * NAvigadro;
		return String.valueOf(atoms).replace("e+", " x 10^");
	}
	
	public static double getMassFromMole(String formula, double mole) {
	    return getMolarMassFromFormula(formula) * mole;
	}
	
	public static double getMolesFromMass(String formula, double mass) {
	    return mass / getMolarMassFromFormula(formula);
	}
}

