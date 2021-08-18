package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DataReader {
	
	private static Scanner dataReader;
	
	/*
	 * Data maps
	 */
	
	// Chemical elements
	public static Map<String, String> nameToSymbol = new HashMap<String, String>();
	public static Map<String, Integer> nameToAtomicNumber = new HashMap<String, Integer>();
	public static Map<String, Double> nameToMolarMass = new HashMap<String, Double>();
	
	public static Map<String, String> symbolToName = new HashMap<String, String>();
	public static Map<String, Integer> symbolToAtomicNumber = new HashMap<String, Integer>();
	public static Map<String, Double> symbolToMolarMass = new HashMap<String, Double>();
	                       
	public static Map<Integer, String> atomicNumberToName = new HashMap<Integer, String>();
	public static Map<Integer, String> atomicNumberToSymbol = new HashMap<Integer, String>();
	public static Map<Integer, Double> atomicNumberToMolarMass = new HashMap<Integer, Double>();
	
	// Acids and bases
	public static Map<String, String> acidBaseNameToFormula = new HashMap<String, String>();
	public static Map<String, String> acidBaseFormulaToName = new HashMap<String, String>();
	
	// Ions
	public static Map<String, String> ionsFormulaToName = new HashMap<String, String>();
	public static Map<String, String> ionsNameToFormula = new HashMap<String, String>();
	
	// Std. enthalpies, std. entropies and std. Gibbs free energy
	public static Map<String, Double> formulaToEnthalpy = new HashMap<String, Double>();
	public static Map<String, Double> formulaToEntropy = new HashMap<String, Double>();
	public static Map<String, Double> formulaToGibbsFreeEnergy = new HashMap<String, Double>();
	
	// Electronic configurations
	public static Map<Integer, String> atomicNumberToElectroConfig = new HashMap<Integer, String>();
	public static Map<String, String> nameToElectroConfig = new HashMap<String, String>();
	
	public static Map<String, double[]> symbolToOxidationNumbers = new HashMap<String, double[]>();

	/*
	 * Data for functional groups (Organic chemistry)
	 */
	public static List<String> functionalGroups = new ArrayList<String>();
	
	/*
	 * Data for strong and weak acids and conjugate bases
	 */
	public static List<String> strongWeakAcids = new ArrayList<String>();
	
	/*
	 * Data for molal boiling point and freezing point of common liquids
	 */
	public static List<String> boilingFreezingPoints = new ArrayList<String>();
	
	
	public static void loadData() throws FileNotFoundException {
		readElements();
		readAcidsBases();
		readIons();
		readStdEnthalpies();
		readStdEntropies();
		readStdGibbsFreeEnergy();
		readElectronicConfigurations();
		readFunctionalGroups();
		readOxidationNumbers();
		readStrongWeakAcids();
		readFreezingBoilingPoint();
	}

	private static void readElements() throws FileNotFoundException {
		File file = new File("Data files/ListOfElements.txt");
		dataReader = new Scanner(file);
		
		String name, symbol;
		int atomicNumber;
		double molarMass;
		
		while (dataReader.hasNextLine()) {
			// Split data
			String[] data = dataReader.nextLine().split(" ");
			
			// Read data fields
			name = data[0].toUpperCase();
			symbol = data[1];
			atomicNumber = Integer.valueOf(data[2]);
			molarMass = Double.valueOf(data[3]);
			
			// Load data into maps
			nameToSymbol.put(name, symbol);
			nameToAtomicNumber.put(name, atomicNumber);
			nameToMolarMass.put(name, molarMass);
			
			symbolToName.put(symbol, name);
			symbolToAtomicNumber.put(symbol, atomicNumber);
			symbolToMolarMass.put(symbol, molarMass);
					                        
			atomicNumberToName.put(atomicNumber, name);
			atomicNumberToSymbol.put(atomicNumber, symbol);
			atomicNumberToMolarMass.put(atomicNumber, molarMass);
		}
		dataReader.close();
	}
	
	private static void readAcidsBases() throws FileNotFoundException {
		File file = new File("Data files/ListOfAcidsBases.txt");
		dataReader = new Scanner(file);
		
		String name, formula;
		
		while (dataReader.hasNextLine()) {
			// Split data
			String[] data = dataReader.nextLine().split(" ");
			
			// Read data fields
			name = data[0].toUpperCase();
			formula = data[1];

			// Load data into maps
			acidBaseNameToFormula.put(name, formula);
			acidBaseFormulaToName.put(formula, name);
			
		}
		dataReader.close();
	}
	
	private static void readIons() throws FileNotFoundException {
		File file = new File("Data files/ListOfIons.txt");
		dataReader = new Scanner(file);
		
		String name, formula;
		
		while (dataReader.hasNextLine()) {
			// Split data
			String[] data = dataReader.nextLine().split(" ");
			
			// Read data fields
			name = data[0].toUpperCase();
			formula = data[1];

			// Load data into maps
			ionsNameToFormula.put(name, formula);
			ionsFormulaToName.put(formula, name);
			
		}
		dataReader.close();
	}
	
	private static void readStdEnthalpies() throws FileNotFoundException {
		File file = new File("Data files/ListOfStdEnthalpies.txt");
		dataReader = new Scanner(file);
		
		// String name;
		String formula;
		double enthalpy;
		
		while (dataReader.hasNextLine()) {
			// Split data
			String[] data = dataReader.nextLine().split(" ");
			
			// Read data fields
			while (data.length > 3) {
				String[] tmpData = new String[data.length-1]; 
				tmpData[0] = data[0] + " " + data[1];
				for (int i=2; i < data.length; i++) {
					tmpData[i-1] = data[i];
				}
				data = tmpData;
			}
			// name = data[0].toUpperCase();
			formula = data[1];
			enthalpy = Double.valueOf(data[2]);
			
			// Load data into maps
			formulaToEnthalpy.put(formula, enthalpy);
			
		}
		dataReader.close();
		// Manual appendage
		formulaToEnthalpy.put("K(+)(aq)", -251.2);
		formulaToEnthalpy.put("K(s)", 0.);
	}
	
	private static void readStdEntropies() throws FileNotFoundException {
		File file = new File("Data files/ListOfStdEntropies.txt");
		dataReader = new Scanner(file);
		
		// String name;
		String formula;
		double entropy;
		
		while (dataReader.hasNextLine()) {
			// Split data
			String[] data = dataReader.nextLine().split(" ");
			
			// Read data fields
			while (data.length > 3) {
				String[] tmpData = new String[data.length-1]; 
				tmpData[0] = data[0] + " " + data[1];
				for (int i=2; i < data.length; i++) {
					tmpData[i-1] = data[i];
				}
				data = tmpData;
			}
			// name = data[0].toUpperCase();
			formula = data[1];
			entropy = Double.valueOf(data[2]);
			
			// Load data into maps
			formulaToEntropy.put(formula, entropy);
			
		}
		dataReader.close();
	}
	
	private static void readStdGibbsFreeEnergy() throws FileNotFoundException {
		File file = new File("Data files/ListOfStdGibbsFreeEnergy.txt");
		dataReader = new Scanner(file);
		
		// String name;
		String formula;
		double gibbs;
		
		while (dataReader.hasNextLine()) {
			// Split data
			String[] data = dataReader.nextLine().split(" ");
			
			// Read data fields
			while (data.length > 3) {
				String[] tmpData = new String[data.length-1]; 
				tmpData[0] = data[0] + " " + data[1];
				for (int i=2; i < data.length; i++) {
					tmpData[i-1] = data[i];
				}
				data = tmpData;
			}
			// name = data[0].toUpperCase();
			formula = data[1];
			gibbs = Double.valueOf(data[2]);
			
			// Load data into maps
			formulaToGibbsFreeEnergy.put(formula, gibbs);
			
		}
		dataReader.close();

	}
	
	private static void readElectronicConfigurations() throws FileNotFoundException {
		File file = new File("Data files/ListOfElectronicConfig.txt");
		dataReader = new Scanner(file);
		
		int atomicNumber;
		String name, config;
		
		while (dataReader.hasNextLine()) {
			// Split data
			String[] data = dataReader.nextLine().split(" ");
			
			// Read data fields			
			atomicNumber = Integer.valueOf(data[0]);
			name = data[1].toUpperCase();
			config = data[2];
			
			// Load data into maps
			atomicNumberToElectroConfig.put(atomicNumber, config);
			nameToElectroConfig.put(name, config);
			
		}
		dataReader.close();
	}
	
	private static void readFunctionalGroups() throws FileNotFoundException {
		File file = new File("Data files/ListOfFunctionalGroups.txt");
		dataReader = new Scanner(file);
		
		while (dataReader.hasNextLine()) {
			// Load data into array
			functionalGroups.add(dataReader.nextLine());
		}
		dataReader.close();
	}
	
	private static void readOxidationNumbers() throws FileNotFoundException {
		File file = new File("Data files/ListOfOxidationNumbers.txt");
		dataReader = new Scanner(file);
		
		while (dataReader.hasNextLine()) {
			// Split data
			String[] data = dataReader.nextLine().split(" ");
						
			// Read data fields
			String symbol = data[2];
			double[] oxidationNumbers = new double[data.length-3];
			for (int i=3; i < data.length; i++) {
				oxidationNumbers[i-3] = Double.valueOf(data[i]);
			}
			
			// Load data into map
			symbolToOxidationNumbers.put(symbol, oxidationNumbers);
		}
		dataReader.close();
	}
	
	private static void readStrongWeakAcids() throws FileNotFoundException {
		File file = new File("Data files/ListOfStrongWeakAcids.txt");
		dataReader = new Scanner(file);
		
		while (dataReader.hasNextLine()) {
			// Load data into array
			strongWeakAcids.add(dataReader.nextLine());
		}
		dataReader.close();
	}
	
	private static void readFreezingBoilingPoint() throws FileNotFoundException {
		File file = new File("Data files/ListOfFreezingAndBoilingPoints.txt");
		dataReader = new Scanner(file);
		
		while (dataReader.hasNextLine()) {
			// Load data into array
			boilingFreezingPoints.add(dataReader.nextLine());
			
		}
		dataReader.close();

	}
}

