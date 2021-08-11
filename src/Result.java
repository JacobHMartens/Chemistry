
public class Result {
	
	public static void main(String[] args) {
		
	}
}

/*
import math

import Maps

numbers = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"]
NAvigadro = 6.022 * (10**23)
KBolzmann = 1.38 * (10**(-23))
stdTemp = 273.15 + 25  # Kelvin
stdPres = 1  # bar
RIdealGas = 0.082057  # L*atm / K*mol
RThermo = 8.314  # J / K*mol
hPlanck = 6.63 * (10**(-34))  # J*s
c = 3.00 * 10**8  # m/s


def splitFormula(formula):
    formulaDict = {}
    tmpAtom = ""
    tmpNumber = "1"
    multiplier = 1
    while len(formula) > 0:
        if formula[0] == "(":
            i = 0
            while formula[i] != ")":
                i += 1
            j = i+1
            while formula[j] in numbers and j < len(formula)-1:
                j += 1
            multiplier = int(formula[i+1:j+1])
        elif formula[0] == ")":
            multiplier = 1

        elif formula[0] in numbers:
            tmpNumber += formula[0]

        else:
            if formula[0].isupper() and tmpAtom != "":
                if len(tmpNumber) > 1:
                    formulaDict[tmpAtom] = int(tmpNumber[1:])*multiplier
                else:
                    formulaDict[tmpAtom] = int(tmpNumber)*multiplier
                tmpAtom = ""
                tmpNumber = "1"
            tmpAtom += formula[0]
        formula = formula[1:]

    tmpAtom = tmpAtom[0].upper() + tmpAtom[1:].lower()
    if len(tmpNumber) > 1:
        formulaDict[tmpAtom] = int(tmpNumber[1:])
    else:
        formulaDict[tmpAtom] = int(tmpNumber)

    return formulaDict


def getPrecedingNumbers(string):
    precedingNumbers = ""
    for i in string:
        if i in numbers:
            precedingNumbers += i
        else:
            break
    return precedingNumbers


def getAtomNumberFromElement(formula):
    return Maps.symbolToAtomicNumber[formula]


def getElementFromAtomNumber(number):
    return Maps.atomicNumberToSymbol[number]


def getMolarMassFromFormula(formula):
    formulaDict = splitFormula(formula)
    totalMass = 0
    for elem, numb in formulaDict.items():
        totalMass += numb*Maps.symbolToMolarMass[elem]
    return totalMass


def getMassPercentageFromFormula(element, formula):
    formulaDict = splitFormula(formula)
    if element.upper() in Maps.elementList:
        element = Maps.nameToSymbol[element.upper()]
    return 100*formulaDict[element]*getMolarMassFromFormula(element) / getMolarMassFromFormula(formula)


def getNameFromFormula(formula):  # Acids, bases and ions
    try:
        return Maps.acidBaseFormulaToName[formula]
    except KeyError:
        try:
            return Maps.ionsFormulaToName[formula]
        except KeyError:
            print("Formula: %s not in data base" % formula)


def getFormulaFromName(name):
    try:
        return Maps.acidBaseNameToFormula[name.lower()]
    except KeyError:
        try:
            return Maps.ionsNameToFormula[name.lower()]
        except KeyError:
            print("Name: %s not in data base" % name)


def getAtomsFromAmountInFormula(element, amountInGram, formula):
    grams = amountInGram * getMassPercentageFromFormula(element, formula) / 100
    moles = grams / getMolarMassFromFormula(element)
    atoms = moles * NAvigadro
    atoms = str(atoms).replace("e+", " x 10^")
    return atoms


def getMassFromMole(formula, mole):
    return getMolarMassFromFormula(formula) * mole


def getMolesFromMass(formula, mass):
    return mass / getMolarMassFromFormula(formula)


def getMassFromLiquidReactantsAndMolarAndVolume(targetProduct, reactants, molar, amount):
    limReactant = None
    for i in range(len(reactants)):
        tmpMole = molar[i]*amount[i]
        if limReactant is None:
            limReactant = tmpMole
        elif limReactant > tmpMole:
            limReactant = tmpMole
    return getMassFromMole(targetProduct, limReactant)


def getMassFromGasReactantsAndMass(targetProduct, reactants, mass):
    limReactant = None
    targetProductMole = getPrecedingNumbers(targetProduct)
    if targetProductMole == "":
        targetProductMole = "1"
    for i in range(len(reactants)):
        curMole = "1"
        potMole = getPrecedingNumbers(reactants[i])
        if potMole != "":
            curMole = potMole

        pureReactant = reactants[i][len(getPrecedingNumbers(reactants[i])):]
        tmpMole = (int(targetProductMole) / int(curMole)) * getMolesFromMass(pureReactant, mass[i])
        if limReactant is None:
            limReactant = tmpMole
        elif limReactant > tmpMole:
            limReactant = tmpMole
    pureTargetProduct = targetProduct[len(getPrecedingNumbers(targetProduct)):]
    return limReactant * getMolarMassFromFormula(pureTargetProduct)


def getStdEnthalpyFromNameAndPhase(name, phase):
    if phase.lower() in ("gas", "g"):
        name += "(g)"
    elif phase.lower() in ("liquid", "l"):
        name += "(l)"
    elif phase.lower() in ("solid", "s"):
        name += "(s)"
    elif phase.lower() in ("aqua", "aqueous", "aq"):
        name += "(aq)"
    try:
        return Maps.nameToEnthalpy[name]
    except KeyError:
        print("Name: %s not in data base. Try with the chemical formula instead." % name)


def getStdEnthalpyFromFormulaAndPhase(formula, phase):
    if phase.lower() in ("gas", "g"):
        formula += "(g)"
    elif phase.lower() in ("liquid", "l"):
        formula += "(l)"
    elif phase.lower() in ("solid", "s"):
        formula += "(s)"
    elif phase.lower() in ("aqua", "aqueous", "aq"):
        formula += "(aq)"
    try:
        return Maps.formulaToEnthalpy[formula]
    except KeyError:
        print("Formula: %s not in data base." % formula)


def getStdEnthalpyFromReaction(reactants, reactantPhases, products, productPhases):
    reactantsSum = 0
    productsSum = 0
    for i in range(len(reactants)):
        reactant = reactants[i]
        phase = reactantPhases[i]
        coefficient = 1
        k = 0
        while reactant[k] in numbers:
            k += 1
        if k != 0:
            coefficient = int(reactant[:k])
            reactant = reactant[len(str(coefficient)):]
        reactantsSum += coefficient * getStdEnthalpyFromFormulaAndPhase(reactant, phase)

    for j in range(len(products)):
        product = products[j]
        phase = productPhases[j]
        coefficient = 1
        k = 0
        while product[k] in numbers:
            k += 1
        if k != 0:
            coefficient = int(product[:k])
            product = product[len(str(coefficient)):]
        productsSum += coefficient * getStdEnthalpyFromFormulaAndPhase(product, phase)

    return productsSum - reactantsSum


def getStdEntropyFromFormulaAndPhase(formula, phase):
    if phase.lower() in ("gas", "g"):
        formula += "(g)"
    elif phase.lower() in ("liquid", "l"):
        formula += "(l)"
    elif phase.lower() in ("solid", "s"):
        formula += "(s)"
    elif phase.lower() in ("aqua", "aqueous", "aq"):
        formula += "(aq)"
    try:
        return Maps.formulaToEntropy[formula]
    except KeyError:
        print("Formula: %s not in data base." % formula)


def getStdEntropyFromReaction(reactants, reactantPhases, products, productPhases):
    reactantsSum = 0
    productsSum = 0
    for i in range(len(reactants)):
        reactant = reactants[i]
        phase = reactantPhases[i]
        coefficient = 1
        k = 0
        while reactant[k] in numbers:
            k += 1
        if k != 0:
            coefficient = int(reactant[:k])
            reactant = reactant[len(str(coefficient)):]
        reactantsSum += coefficient * getStdEntropyFromFormulaAndPhase(reactant, phase)

    for j in range(len(products)):
        product = products[j]
        phase = productPhases[j]
        coefficient = 1
        k = 0
        while product[k] in numbers:
            k += 1
        if k != 0:
            coefficient = int(product[:k])
            product = product[len(str(coefficient)):]
        productsSum += coefficient * getStdEntropyFromFormulaAndPhase(product, phase)

    return productsSum - reactantsSum


def getStdGibbsFromNameAndPhase(name, phase):
    if phase.lower() in ("gas", "g"):
        name += "(g)"
    elif phase.lower() in ("liquid", "l"):
        name += "(l)"
    elif phase.lower() in ("solid", "s"):
        name += "(s)"
    elif phase.lower() in ("aqua", "aqueous", "aq"):
        name += "(aq)"
    try:
        return Maps.nameToGibbs[name]
    except KeyError:
        print("Name: %s not in data base. Try with the chemical formula instead." % name)


def getStdGibbsFromFormulaAndPhase(formula, phase):
    if phase.lower() in ("gas", "g"):
        formula += "(g)"
    elif phase.lower() in ("liquid", "l"):
        formula += "(l)"
    elif phase.lower() in ("solid", "s"):
        formula += "(s)"
    elif phase.lower() in ("aqua", "aqueous", "aq"):
        formula += "(aq)"
    try:
        return Maps.formulaToGibbs[formula]
    except KeyError:
        print("Formula: %s not in data base." % formula)


def getStdGibbsFromReaction(reactants, reactantPhases, products, productPhases):
    reactantsSum = 0
    productsSum = 0
    for i in range(len(reactants)):
        reactant = reactants[i]
        phase = reactantPhases[i]
        coefficient = 1
        k = 0
        while reactant[k] in numbers:
            k += 1
        if k != 0:
            coefficient = int(reactant[:k])
            reactant = reactant[len(str(coefficient)):]
        reactantsSum += coefficient * getStdGibbsFromFormulaAndPhase(reactant, phase)

    for j in range(len(products)):
        product = products[j]
        phase = productPhases[j]
        coefficient = 1
        k = 0
        while product[k] in numbers:
            k += 1
        if k != 0:
            coefficient = int(product[:k])
            product = product[len(str(coefficient)):]
        productsSum += coefficient * getStdGibbsFromFormulaAndPhase(product, phase)

    return productsSum - reactantsSum


def getGibbsFromEnthalpyAndEntropyAndTemp(enthalpy, entropy, temp):
    return enthalpy - temp * entropy


def getSpontaneousTempFromEnthalpyAndEntropy(enthalpy, entropy):
    return "Greater than: " + str(enthalpy/entropy) + " Kelvin\nAssuming you used correct units!\n"


def getStdGibbsFromTempAndEquilConst(temp, K):
    return str(-RThermo*temp*math.log(K)) + " J/mol"


def getEquilConstForStdFreeEnergyFromReactionAndTemp(reactants, reactantPhases, products, productPhases, temp):
    # Std Gibbs in Joule
    stdGibbs = 1000 * getStdGibbsFromReaction(reactants, reactantPhases, products, productPhases)
    K = str(math.exp(stdGibbs / (-RThermo*temp)))
    if "e" in K:
        K = K.replace("e+", " x 10^")
    return K


def getElectronicConfig(element):
    ion = 0
    startIdx = element.find("(")
    if startIdx != -1:
        endIdx = element.find(")")
        ion = element[startIdx+1: endIdx]
        if ion[-1] == "-":
            ion = int(ion[:-1])
        else:
            ion = -int(ion[:-1])
        element = element[:startIdx]

    atomNumber = getAtomNumberFromElement(element) + ion

    electroConfigShort = Maps.atomicNumberToElectroConfig[atomNumber]
    electroConfigFull = electroConfigShort
    while "[" in electroConfigFull:
        nobleGasAtomicNumber = getAtomNumberFromElement(electroConfigFull[1:3])
        electroConfigFull = Maps.atomicNumberToElectroConfig[nobleGasAtomicNumber] + electroConfigFull[4:]

    return electroConfigShort, electroConfigFull


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


# Fix electro config for ioner, så 4s tømmes før 3d for ioner
# Eg. for Ni(2+) er det:
# [Ar]3d8
# og ikke
# [Ar]4s23d6


*/