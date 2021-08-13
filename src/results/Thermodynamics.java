package results;

public class Thermodynamics {
/*
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

 */
}
