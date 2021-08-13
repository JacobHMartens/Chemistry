package results;

public class Reactions {
/*
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

 */
}
