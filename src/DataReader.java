import java.util.Map;

public class DataReader {
	
	public static Map<String, String> nameToSymbol;
	public static Map<String, Integer> nameToAtomicNumber;

	public static void main(String[] args) {
		
	}
	
	private static void readElements() {
		/*
		elementsFile = open("ListOfElements.txt", "r")

				elementList = []
				symbolList = []
				atomicNumberList = []
				molarMassList = []

				for line in elementsFile:
				    splitLine = line.split(" ")
				    elementList.append(splitLine[0].upper())
				    symbolList.append(splitLine[1])
				    atomicNumberList.append(int(splitLine[2]))
				    molarMassList.append(float(splitLine[3].replace("\n", "")))

				elementsFile.close()

				nameToSymbol = dict(zip(elementList, symbolList))
				nameToAtomicNumber = dict(zip(elementList, atomicNumberList))
				nameToMolarMass = dict(zip(elementList, molarMassList))

				symbolToName = dict(zip(symbolList, elementList))
				symbolToAtomicNumber = dict(zip(symbolList, atomicNumberList))
				symbolToMolarMass = dict(zip(symbolList, molarMassList))

				atomicNumberToName = dict(zip(atomicNumberList, elementList))
				atomicNumberToSymbol = dict(zip(atomicNumberList, symbolList))
				atomicNumberToMolarMass = dict(zip(atomicNumberList, molarMassList))
		*/
	}
	
	private static void readAcidsBases() {
		/*
		acidBaseFile = open("ListOfAcidsBases.txt", "r")

				acidBaseNameList = []
				acidBaseFormulaList = []

				for line in acidBaseFile:
				    splitLine = line.split(" ")
				    acidBaseNameList.append(splitLine[0])
				    acidBaseFormulaList.append(splitLine[1].replace("\n", ""))

				acidBaseFile.close()

				acidBaseFormulaToName = dict(zip(acidBaseFormulaList, acidBaseNameList))
				acidBaseNameToFormula = dict(zip(acidBaseNameList, acidBaseFormulaList))

				# Manual changes
				def addToAcidBase(formula, *names):
				    namesAsString = ""
				    for name in names:
				        namesAsString += name + ", "
				        acidBaseNameToFormula[name] = formula
				    acidBaseFormulaToName[formula] = namesAsString[:-2]


				addToAcidBase("CH3COOH", "ethansyre", "eddikesyre")
				addToAcidBase("NaOH", "natriumhydroxid")
				addToAcidBase("KOH", "kaliumhydroxid")
		*/
	}
	
	private static void readIons() {
		/*
		 ionsFile = open("ListOfIons.txt", "r")

ionsNameList = []
ionsFormulaList = []

for line in ionsFile:
    splitLine = line.split(" ")
    ionsNameList.append(splitLine[0].lower())
    ionsFormulaList.append(splitLine[1].replace("\n", ""))

ionsFile.close()

ionsFormulaToName = dict(zip(ionsFormulaList, ionsNameList))
ionsNameToFormula = dict(zip(ionsNameList, ionsFormulaList))

		 */
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

