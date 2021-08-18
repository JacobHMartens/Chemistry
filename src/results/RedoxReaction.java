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
				if (atom.equals("O") || atom.equals("H"))
					continue;
				if (reactAtoms.containsKey(atom))
					reactAtoms.put(atom, reactAtoms.get(atom) + reactAtomsTmp.get(atom));
				else {
					reactAtoms.put(atom, reactAtomsTmp.get(atom));
				}
			}
		}
		
		Map<String, Integer> prodAtoms = new HashMap<String, Integer>();
		for (String prod : products) {
			prodCoeff.put(prod, 1.);
			Map<String, Integer> prodAtomsTmp = splitFormula(getIonChargeAndRoot(prod).getFormula());
			for (String atom : prodAtomsTmp.keySet()) {
				if (atom.equals("O") || atom.equals("H"))
					continue;
				if (prodAtoms.containsKey(atom))
					prodAtoms.put(atom, prodAtoms.get(atom) + prodAtomsTmp.get(atom));
				else {
					prodAtoms.put(atom, prodAtomsTmp.get(atom));
				}
			}
		}
		
		
		// Equalize number of atoms apart from O and H
		while (!reactAtoms.equals(prodAtoms)) {
			for (String atom : reactAtoms.keySet()) {
				if (reactAtoms.get(atom) != prodAtoms.get(atom)) {
					int tmpReactAtoms = reactAtoms.get(atom);
					for (String react : reactCoeff.keySet()) {
						if (react.contains(atom)) {
							double newCoeff = reactCoeff.get(react) * prodAtoms.get(atom);
							reactCoeff.put(react, newCoeff);
							for (String at : splitFormula(getIonChargeAndRoot(react).getFormula()).keySet()) {
								if (at.equals("O") || at.equals("H"))
									continue;
								reactAtoms.put(at, reactAtoms.get(at) * prodAtoms.get(atom));
							}
						}
					}
					for (String prod : prodCoeff.keySet()) {
						if (prod.contains(atom)) {
							double newCoeff = prodCoeff.get(prod) * tmpReactAtoms;
							prodCoeff.put(prod, newCoeff);
							for (String at : splitFormula(getIonChargeAndRoot(prod).getFormula()).keySet()) {
								if (at.equals("O") || at.equals("H"))
									continue;
								prodAtoms.put(at, prodAtoms.get(at) * tmpReactAtoms);
							}
						}
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
			double multiplier = 1;
			for (String react : reactCoeff.keySet()) {
				if (react.contains(elem)) {
					if (splitFormula(getIonChargeAndRoot(react).getFormula()).size() == 1) {  // If single atom or ion
						multiplier = reactCoeff.get(react);
					}
				}
			}
			double reactOx = oxidationNumbersReactants.get(elem) * multiplier;
			
			if (!oxidationNumbersProducts.containsKey(elem))
				continue;
			
			multiplier = 1;
			for (String prod : prodCoeff.keySet()) {
				if (prod.contains(elem)) {
					if (splitFormula(getIonChargeAndRoot(prod).getFormula()).size() == 1) {  // If single atom or ion
						multiplier = prodCoeff.get(prod);
					}
				}
			}
			double prodOx = oxidationNumbersProducts.get(elem) * multiplier;
			if (reactOx != prodOx) {
				chargeDifference.put(elem, prodOx-reactOx);
			}
		}
		
		// Product of charge differences
		double totalMultiplier = 1;
		double totalChargeSum = 0;
		for (double charge : chargeDifference.values()) {
			totalMultiplier *= Math.abs(charge);
			totalChargeSum += charge;
		}

		// Apply coefficients for reactants and products
		if (totalChargeSum != 0) {
			for (String elem : chargeDifference.keySet()) {
				double chargeDiff = chargeDifference.get(elem);
				if (chargeDiff < 0) {
					chargeDiff *= -1;
					for (String prod : products) {
						if (prod.contains(elem)) {
							if (prodCoeff.get(prod) != totalMultiplier/chargeDiff) {
								prodCoeff.put(prod, prodCoeff.get(prod) * totalMultiplier/chargeDiff);
								for (String react : reactants) {
									if (react.contains(elem)) {
										if (reactCoeff.get(react) != totalMultiplier/chargeDiff) {
											reactCoeff.put(react, reactCoeff.get(react) * totalMultiplier/chargeDiff);
										}
									}
								}
							}						
						}
					}
				}
				else if (chargeDiff > 0) {
					for (String react : reactants) {
						if (react.contains(elem)) {
							if (reactCoeff.get(react) != totalMultiplier/chargeDiff) {
								reactCoeff.put(react, reactCoeff.get(react) * totalMultiplier/chargeDiff);
								for (String prod : products) {
									if (prod.contains(elem)) {
										if (prodCoeff.get(prod) != totalMultiplier/chargeDiff) {
											prodCoeff.put(prod, prodCoeff.get(prod) * totalMultiplier/chargeDiff);
										}
									}
								}
							}						
						}
					}
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
				prodCoeff.put("H(+)", -chargeDiff);
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
			finalReaction += (int) (double) react.getValue() + " " + react.getKey() + " + ";
		}
		finalReaction = finalReaction.substring(0, finalReaction.length()-3) + " --> ";
		for (Entry<String, Double> prod : prodCoeff.entrySet()) {
			finalReaction += (int) (double) prod.getValue() + " " + prod.getKey() + " + ";
		}
		finalReaction = finalReaction.substring(0, finalReaction.length()-3);
		
		return finalReaction + "\t\t(Good idea to check for equal amounts of hydrogen on both sides!)";
	}
	
	
	private static Map<String, Double> getOxidationNumbers(String[] terms) {
		Map<String, Double> oxidationNumbers = new HashMap<String, Double>();
		
		for (String react : terms) {
			Ion ionObj = getIonChargeAndRoot(react);
			int ion = ionObj.getIon();
			String formula = ionObj.getFormula();
			Map<String, Integer> elements = splitFormula(formula);
			if (elements.size() == 1) {
				for (String elem : elements.keySet()) {
					oxidationNumbers.put(elem, (double) ion);
					elements.remove(elem);
				}
			}
			
			double compoundCharge = 0;
			int count = 0;
			while (elements.size() > 0) {
				
				Set<String> toBeRemoved = new HashSet<String>();;
				elemloop: for (String elem : elements.keySet()) {
					if (toBeRemoved.size() > 0)
						break elemloop;
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
					
					else if (count > elements.size()) {
						int negOxNbs = 0;
						for (double oxNb : oxNbs) {
							if (oxNb < 0)
								negOxNbs++;
						}
						if (negOxNbs == 1 && compoundCharge > 0) {
							ox = oxNbs[0];
							oxidationNumbers.put(elem, ox);
							compoundCharge += ox * elements.get(elem);
							toBeRemoved.add(elem);
						}
						
						else if (negOxNbs == 0) {
							for (String e : elements.keySet()) {
								if (e.equals(elem))
									continue;
								int negOxNbs2 = 0;
								double[] oxNbs2 = symbolToOxidationNumbers.get(e);
								for (double oxNb : oxNbs2) {
									if (oxNb < 0)
										negOxNbs2++;
								}
								if (negOxNbs2 == 1) {
									ox = oxNbs2[0];
									oxidationNumbers.put(e, ox);
									compoundCharge += ox * elements.get(e);
									toBeRemoved.add(e);
								}
							}
						}
					}
					
					if (count > 100) {
						System.out.println("Unable to compute redox reaction...");
						System.exit(0);
					}
					
				}
				for (String elem : toBeRemoved) {
					elements.remove(elem);
				}
				count++;
			}
		}

		return oxidationNumbers;
	}
	
	
}
