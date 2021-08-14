package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
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

	public static void loadData() throws FileNotFoundException {
		readElements();
		readAcidsBases();
		readIons();
		readStdEnthalpies();
		readStdEntropies();
		readStdGibbsFreeEnergy();
		readElectronicConfigurations();
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
	
	private static void readStdEnthalpies() {
		/*
		 enthalpyFile = open("ListOfStdEnthalpies.txt", "r")

nameList = []
formulaList = []
enthalpyList = []

for line in enthalpyFile:
    splitLine = line.split(" ")
    while len(splitLine) > 3:
        splitLine[0] += " " + splitLine[1]
        del splitLine[1]

    nameList.append(splitLine[0].lower())
    formulaList.append(splitLine[1])
    enthalpyList.append(float(splitLine[2].replace("\n", "")))

enthalpyFile.close()
nameToEnthalpy = dict(zip(nameList, enthalpyList))
formulaToEnthalpy = dict(zip(formulaList, enthalpyList))

formulaToEnthalpy["K(+)(aq)"] = -251.2
formulaToEnthalpy["K(s)"] = 0

		 */
	}
	
	private static void readStdEntropies() {
		/*
		 entropyFile = open("ListOfStdEntropies.txt", "r")

nameList = []
formulaList = []
entropyList = []

for line in entropyFile:
    splitLine = line.split(" ")
    while len(splitLine) > 3:
        splitLine[0] += " " + splitLine[1]
        del splitLine[1]

    nameList.append(splitLine[0].lower())
    formulaList.append(splitLine[1])
    entropyList.append(float(splitLine[2].replace("\n", "")))

entropyFile.close()

formulaToEntropy = dict(zip(formulaList, entropyList))


		 */
	}
	
	private static void readStdGibbsFreeEnergy() {
		/*
		 gibbsFile = open("ListOfStdGibbsFreeEnergy.txt", "r")

nameList = []
formulaList = []
gibbsList = []

for line in gibbsFile:
    splitLine = line.split(" ")
    while len(splitLine) > 3:
        splitLine[0] += " " + splitLine[1]
        del splitLine[1]

    nameList.append(splitLine[0].lower())
    formulaList.append(splitLine[1])
    gibbsList.append(float(splitLine[2].replace("\n", "")))

gibbsFile.close()
nameToGibbs = dict(zip(nameList, gibbsList))
formulaToGibbs = dict(zip(formulaList, gibbsList))

 
		 */
	}
	
	private static void readElectronicConfigurations() {
		/*
		 electroConfigFile = open("ListOfElectronicConfig.txt", "r")

atomicNumberList = []
nameList = []
electroConfigList = []

for line in electroConfigFile:
    splitLine = line.split(" ")

    atomicNumberList.append(int(splitLine[0]))
    nameList.append(splitLine[1].lower())
    electroConfigList.append(splitLine[2].replace("\n", ""))

electroConfigFile.close()

atomicNumberToElectroConfig = dict(zip(atomicNumberList, electroConfigList))
nameToElectroConfig = dict(zip(nameList, electroConfigList)) 
		 */
	}

}

