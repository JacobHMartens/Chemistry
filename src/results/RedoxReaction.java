package results;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import objects.Ion;

import static data.DataReader.symbolToOxidationNumbers;
import static results.BaseOperations.splitFormula;
import static results.BaseOperations.getIonChargeAndRoot;

public class RedoxReaction {
	
	public static String getRedoxReaction(String[] reactants, String[] products, String acidOrBase) {
		// Load coefficients = 1 into maps for reactants and products
		Map<String, Double> reactCoeff = new HashMap<String, Double>();
		Map<String, Double> prodCoeff = new HashMap<String, Double>();
		
		Map<String, Integer> reactAtoms = new HashMap<String, Integer>();
		for (String react : reactants) {
			reactCoeff.put(react, 1.);
			Map<String, Integer> reactAtomsTmp = splitFormula(getIonChargeAndRoot(react).getFormula());
			for (String atom : reactAtomsTmp.keySet()) {
				reactAtoms.put(atom, reactAtomsTmp.get(atom));
			}
		}
		
		Map<String, Integer> prodAtoms = new HashMap<String, Integer>();
		for (String prod : products) {
			prodCoeff.put(prod, 1.);
			Map<String, Integer> prodAtomsTmp = splitFormula(getIonChargeAndRoot(prod).getFormula());
			for (String atom : prodAtomsTmp.keySet()) {
				prodAtoms.put(atom, prodAtomsTmp.get(atom));
			}
		}
		
		// Equalize number of atoms apart from O and H
		for (String atom : reactAtoms.keySet()) {
			if (atom.equals("O") || atom.equals("H"))
				continue;
			if (reactAtoms.get(atom) != prodAtoms.get(atom)) {
				loop1 : for (String prod : prodCoeff.keySet()) {
					if (prod.contains(atom)) {
						prodCoeff.put(prod, (double) (int) reactAtoms.get(atom));
						break loop1;
					}
				}
				loop2 : for (String react : reactCoeff.keySet()) {
					if (react.contains(atom)) {
						reactCoeff.put(react, (double) (int) prodAtoms.get(atom));
						break loop2;
					}
				}
			}
		}
		
		// Oxidation numbers for the reactants and the products
		Map<String, Double> oxidationNumbersReactants = getOxidationNumbers(reactants);
		Map<String, Double> oxidationNumbersProducts = getOxidationNumbers(products);
				
		// Difference in charge/oxidation numbers between LHS and RHS 
		Map<String, Double> chargeDifference = new HashMap<String, Double>();
		for (String elem : oxidationNumbersReactants.keySet()) {
			double reactOx = oxidationNumbersReactants.get(elem);
			double prodOx = oxidationNumbersProducts.get(elem);

			if (reactOx != prodOx) {
				chargeDifference.put(elem, Math.abs(prodOx-reactOx));
			}
		}

		// Apply coefficients for reactants and products
		for (String elem : chargeDifference.keySet()) {
			for (String react : reactants) {
				if (!react.contains(elem)) {
					double coeff = chargeDifference.get(elem);
					reactCoeff.put(react, reactCoeff.get(react)*coeff);
				}
			}
			
			for (String prod : products) {
				if (!prod.contains(elem)) {
					double coeff = chargeDifference.get(elem);
					prodCoeff.put(prod, prodCoeff.get(prod)*coeff);
				}
			}
		}

		// Get total charge on both LHS and RHS
		int LHSCharge = 0, RHSCharge = 0;
		for (String react : reactCoeff.keySet()) {
			LHSCharge += reactCoeff.get(react) * getIonChargeAndRoot(react).getIon();
		}
		for (String prod : prodCoeff.keySet()) {
			RHSCharge += prodCoeff.get(prod) * getIonChargeAndRoot(prod).getIon();
		}
		
		// Add H+ or OH- to equalize charge
		double chargeDiff = RHSCharge - LHSCharge;
		if (acidOrBase.toUpperCase().equals("ACID")) {
			if (chargeDiff > 0) {
				reactCoeff.put("H(+)", chargeDiff);
			}
			else if (chargeDiff < 0) {
				prodCoeff.put("H(+)", chargeDiff);
			}
		}
		
		else if (acidOrBase.toUpperCase().equals("BASE")) {
			if (chargeDiff > 0) {
				prodCoeff.put("OH(-)", chargeDiff);
			}
			else if (chargeDiff < 0){
				reactCoeff.put("OH(-)", -chargeDiff);
			}
		}
		
		// Add water
		double LHSOxygen = 0, RHSOxygen = 0;
		for (String react : reactCoeff.keySet()) {
			Integer countOxygen = splitFormula(getIonChargeAndRoot(react).getFormula()).get("O");
			if (countOxygen == null) {
				countOxygen = 0;
			}
			LHSOxygen += reactCoeff.get(react) * countOxygen;
		}
		
		for (String prod : prodCoeff.keySet()) {
			Integer countOxygen = splitFormula(getIonChargeAndRoot(prod).getFormula()).get("O");
			if (countOxygen == null) {
				countOxygen = 0;
			}
			RHSOxygen += prodCoeff.get(prod) * countOxygen;
		}

		double oxygenDiff = RHSOxygen - LHSOxygen;
		if (oxygenDiff > 0) {
			reactCoeff.put("H2O", oxygenDiff);
		}
		else if (oxygenDiff < 0) {
			prodCoeff.put("H2O", -oxygenDiff);
		}

		// Print final reaction
		String finalReaction = "";
		for (Entry<String, Double> react : reactCoeff.entrySet()) {
			finalReaction += react.getValue() + " " + react.getKey() + " + ";
		}
		finalReaction = finalReaction.substring(0, finalReaction.length()-3) + " --> ";
		for (Entry<String, Double> prod : prodCoeff.entrySet()) {
			finalReaction += prod.getValue() + " " + prod.getKey() + " + ";
		}
		finalReaction = finalReaction.substring(0, finalReaction.length()-3);
		
		return finalReaction;
	}
	
	private static Map<String, Double> getOxidationNumbers(String[] terms) {
		Map<String, Double> oxidationNumbers = new HashMap<String, Double>();
		
		for (String react : terms) {
			Ion ionObj = getIonChargeAndRoot(react);
			int ion = ionObj.getIon();
			String formula = ionObj.getFormula();
			Map<String, Integer> elements = splitFormula(formula);
			System.out.println(elements.toString());
			if (elements.size() == 1) {
				for (String elem : elements.keySet()) {
					oxidationNumbers.put(elem, (double) ion);
					elements.remove(elem);
				}
			}
			
			double compoundCharge = 0;
			while (elements.size() > 0) {
				Set<String> toBeRemoved = new HashSet<String>();;
				for (String elem : elements.keySet()) {
					double ox;
					double[] oxNbs = symbolToOxidationNumbers.get(elem);
					
					if (oxNbs.length == 1) {
						ox = oxNbs[0];
						oxidationNumbers.put(elem, ox);
						compoundCharge += ox * elements.get(elem);
						toBeRemoved.add(elem);
					}
					
					else if (elements.size() == 1) {
						ox = (-compoundCharge+ion) / elements.get(elem);
						oxidationNumbers.put(elem, ox);
						toBeRemoved.add(elem);
					}
					
					else if (elem.equals("O")) {
						ox = -2;
						oxidationNumbers.put(elem, ox);
						compoundCharge += ox * elements.get(elem);
						toBeRemoved.add(elem);
					}
					
					else if (elem.equals("H")) {
						ox = 1;
						oxidationNumbers.put(elem, ox);
						compoundCharge += ox * elements.get(elem);
						toBeRemoved.add(elem);
					}
					
					else {
					}
					
				}
				for (String elem : toBeRemoved) {
					elements.remove(elem);
				}
			}			
		}

		return oxidationNumbers;
	}
	
	
}
