package coffee.data

import kotlinx.serialization.Serializable


@Serializable
data class BrewingMethod(
    val pourOver: Boolean,
    val frenchPress: Boolean,
    val espresso: Boolean,
    val machine: Boolean,
    val capsule: Boolean,
    val instant: Boolean,
    val beanToCup: Boolean,
    val coldBrew: Boolean,
    val extract: Boolean,
    val otherMethodName: String,
){
    constructor(map: Map<String, String>) : this(
        pourOver = map["How do you brew coffee at home? (Pour over)"].toBoolean(),
        frenchPress = map["How do you brew coffee at home? (French press)"].toBoolean(),
        espresso = map["How do you brew coffee at home? (Espresso)"].toBoolean(),
        machine = map["How do you brew coffee at home? (Coffee brewing machine (e.g. Mr. Coffee))"].toBoolean(),
        capsule = map["How do you brew coffee at home? (Pod/capsule machine (e.g. Keurig/Nespresso))"].toBoolean(),
        instant = map["How do you brew coffee at home? (Instant coffee)"].toBoolean(),
        beanToCup = map["How do you brew coffee at home? (Bean-to-cup machine)"].toBoolean(),
        coldBrew = map["How do you brew coffee at home? (Cold brew)"].toBoolean(),
        extract = map["How do you brew coffee at home? (Coffee extract (e.g. Cometeer))"].toBoolean(),
        otherMethodName = map["How else do you brew coffee at home?"].toString()
    )
}
@Serializable
data class CoffeeBuyingPlace(
    val buyCoffeeOnChain: Boolean,
    val buyCoffeeOnLocalCafe: Boolean,
    val buyCoffeeOnDriveThru: Boolean,
    val buyCoffeeOnSpecialtyShop: Boolean,
    val buyCoffeeOnSupermarket: Boolean,
    val buyCoffeeOnOtherName: String,
) {
    constructor(map: Map<String, String>) : this(
        buyCoffeeOnChain = map["On the go, where do you typically purchase coffee? (National chain (e.g. Starbucks, Dunkin))"].toBoolean(),
        buyCoffeeOnLocalCafe = map["On the go, where do you typically purchase coffee? (Local cafe)"].toBoolean(),
        buyCoffeeOnDriveThru = map["On the go, where do you typically purchase coffee? (Drive-thru)"].toBoolean(),
        buyCoffeeOnSpecialtyShop = map["On the go, where do you typically purchase coffee? (Specialty coffee shop)"].toBoolean(),
        buyCoffeeOnSupermarket = map["On the go, where do you typically purchase coffee? (Deli or supermarket)"].toBoolean(),
        buyCoffeeOnOtherName = map["Where else do you purchase coffee?"].toString(),
    )
}

@Serializable
data class CoffeeAdditions(
    val coffeeAddition: Boolean,
    val coffeeAdditionNo: Boolean,
    val coffeeAdditionCreamer: Boolean,
    val coffeeAdditionSweetener: Boolean,
    val coffeeAdditionSyrup: Boolean,
    val coffeeAdditionOther: String
) {
    constructor(map: Map<String, String>) : this(
        coffeeAddition = map["Do you usually add anything to your coffee? (No - just black)"].toBoolean(),
        coffeeAdditionNo = map["Do you usually add anything to your coffee? (Milk, dairy alternative, or coffee creamer)"].toBoolean(),
        coffeeAdditionCreamer = map["Do you usually add anything to your coffee? (Sugar or sweetener)"].toBoolean(),
        coffeeAdditionSweetener = map["Do you usually add anything to your coffee? (Flavor syrup)"].toBoolean(),
        coffeeAdditionSyrup = map["Do you usually add anything to your coffee? (Other)"].toBoolean(),
        coffeeAdditionOther = map["What else do you add to your coffee?"].toString(),
    )
}

@Serializable
data class Dairy(
    val dairyWhole: Boolean,
    val dairySkim: Boolean,
    val dairyHalfHalf: Boolean,
    val dairyCreamer: Boolean,
    val dairyFlavouredCreamer: Boolean,
    val dairyOat: Boolean,
    val dairyAlmond: Boolean,
    val dairySoy: Boolean,
    val dairyOther: Boolean,
) {
    constructor(map: Map<String, String>) : this(
        dairyWhole = map["What kind of dairy do you add? (Whole milk)"].toBoolean(),
        dairySkim = map["What kind of dairy do you add? (Skim milk)"].toBoolean(),
        dairyHalfHalf = map["What kind of dairy do you add? (Half and half)"].toBoolean(),
        dairyCreamer = map["What kind of dairy do you add? (Coffee creamer)"].toBoolean(),
        dairyFlavouredCreamer = map["What kind of dairy do you add? (Flavored coffee creamer)"].toBoolean(),
        dairyOat = map["What kind of dairy do you add? (Oat milk)"].toBoolean(),
        dairyAlmond = map["What kind of dairy do you add? (Almond milk)"].toBoolean(),
        dairySoy = map["What kind of dairy do you add? (Soy milk)"].toBoolean(),
        dairyOther = map["What kind of dairy do you add? (Other)"].toBoolean(),
    )
}

@Serializable
data class Sweetener(
    val granulatedSugar: Boolean,
    val artificialSweetener: Boolean,
    val honey: Boolean,
    val mapleSyrup: Boolean,
    val stevia: Boolean,
    val agave: Boolean,
    val brownSugar: Boolean,
    val rawSugar: Boolean,
) {
    constructor(map: Map<String, String>) : this(
        granulatedSugar = map["What kind of sugar or sweetener do you add? (Granulated Sugar)"].toBoolean(),
        artificialSweetener = map["What kind of sugar or sweetener do you add? (Artificial Sweeteners (e.g., Splenda))"].toBoolean(),
        honey = map["What kind of sugar or sweetener do you add? (Honey)"].toBoolean(),
        mapleSyrup = map["What kind of sugar or sweetener do you add? (Maple Syrup)"].toBoolean(),
        stevia = map["What kind of sugar or sweetener do you add? (Stevia)"].toBoolean(),
        agave = map["What kind of sugar or sweetener do you add? (Agave Nectar)"].toBoolean(),
        brownSugar = map["What kind of sugar or sweetener do you add? (Brown Sugar)"].toBoolean(),
        rawSugar = map["What kind of sugar or sweetener do you add? (Raw Sugar (Turbinado))"].toBoolean(),
    )
}

@Serializable
data class Flavouring(
    val flavouringsVanilla: Boolean,
    val flavouringsCaramel: Boolean,
    val flavouringsHazelnut: Boolean,
    val flavouringsCinnamon: Boolean,
    val flavouringsPeppermint: Boolean,
    val flavouringsOtherName: String,
) {
    constructor(map: Map<String, String>) : this (
        flavouringsVanilla = map["What kind of flavorings do you add? (Vanilla Syrup)"].toBoolean(),
        flavouringsCaramel = map["What kind of flavorings do you add? (Caramel Syrup)"].toBoolean(),
        flavouringsHazelnut = map["What kind of flavorings do you add? (Hazelnut Syrup)"].toBoolean(),
        flavouringsCinnamon = map["What kind of flavorings do you add? (Cinnamon (Ground or Stick))"].toBoolean(),
        flavouringsPeppermint = map["What kind of flavorings do you add? (Peppermint Syrup)"].toBoolean(),
        flavouringsOtherName = map["What other flavoring do you use?"].toString(),
    )
}

@Serializable
data class ReasonToDrink(
    val itTastesGood: Boolean,
    val iNeedCaffeine: Boolean,
    val ritual: Boolean,
    val makesMePoop: Boolean,
    val otherReasonName: String,
) {
    constructor(map: Map<String, String>) : this(
        itTastesGood = map["Why do you drink coffee? (It tastes good)"].toBoolean(),
        iNeedCaffeine = map["Why do you drink coffee? (I need the caffeine)"].toBoolean(),
        ritual = map["Why do you drink coffee? (I need the ritual)"].toBoolean(),
        makesMePoop = map["Why do you drink coffee? (It makes me go to the bathroom)"].toBoolean(),
        otherReasonName = map["Other reason for drinking coffee"].toString(),
    )
}

@Serializable
data class PersonalInfo(
    val ageRange: String,
    val gender: String,
    val genderOther: String,
    val educationLevel: String,
    val ethnicity: String,
    val ethnicityOther: String,
    val employmentStatus: String,
    val children: String
) {
    constructor(map: Map<String, String>) : this(
        ageRange = map["What is your age?"].toString(),
        gender = map["Gender"].toString(),
        genderOther = map["Gender (please specify)"].toString(),
        educationLevel = map["Education Level"].toString(),
        ethnicity = map["Ethnicity/Race"].toString(),
        ethnicityOther = map["Ethnicity/Race (please specify)"].toString(),
        employmentStatus = map["Employment Status"].toString(),
        children = map["Number of Children"].toString()
    )
}

@Serializable
data class ResearchLine(
    val id: String,
    val coffeeADay: String,
    val favouriteDrink: String,
    val favouriteDrinkOther: String,
    val coffeeYouLike: String,
    val coffeeStrong: String,
    val roastLevel: String,
    val caffeineLevel: String,
    val coffeeExpertise: String,
    val workFromHome: String,
    val moneySpendOnCoffeeMonthly: String,
    val doYouLikeTheTaste: Boolean,
    val doYouKnowWhereYourCoffeeComesFrom: Boolean,
    val mostYouPaid: String,
    val mostWillingToPay: String,
    val valueForMoneyAtCafe: String,
    val valueSpendInEquipment: String,
    val valueForMoneyEquipment: String,
    val personalInfo: PersonalInfo,
    val coffeeDrinkingPlace: CoffeeDrinkingPlace,
    val brewingMethod: BrewingMethod,
    val coffeeBuyingPlace: CoffeeBuyingPlace,
    val coffeeAdditions: CoffeeAdditions,
    val dairy: Dairy,
    val sweetener: Sweetener,
    val flavouring: Flavouring,
    val reasonToDrink: ReasonToDrink,
) {
    constructor(map: Map<String, String>) : this (
        id = map["Submission ID"].toString(),
        personalInfo = PersonalInfo(map),
        coffeeADay = map["How many cups of coffee do you typically drink per day?"].toString(),
        coffeeDrinkingPlace = CoffeeDrinkingPlace(map),
        brewingMethod = BrewingMethod(map),
        coffeeBuyingPlace = CoffeeBuyingPlace(map),
        favouriteDrink = map["What is your favorite coffee drink?"].toString(),
        favouriteDrinkOther = map["Please specify what your favorite coffee drink is"].toString(),
        coffeeAdditions = CoffeeAdditions(map),
        dairy = Dairy(map),
        sweetener = Sweetener(map),
        flavouring = Flavouring(map),
        coffeeYouLike = map["Before today's tasting, which of the following best described what kind of coffee you like?"].toString(),
        coffeeStrong = map["How strong do you like your coffee?"].toString(),
        roastLevel = map["What roast level of coffee do you prefer?"].toString(),
        caffeineLevel = map["How much caffeine do you like in your coffee?"].toString(),
        coffeeExpertise = map["Lastly, how would you rate your own coffee expertise?"].toString(),
        workFromHome = map["Do you work from home or in person?"].toString(),
        moneySpendOnCoffeeMonthly = map["In total, much money do you typically spend on coffee in a month?"].toString(),
        reasonToDrink = ReasonToDrink(map),
        doYouLikeTheTaste = map["Do you like the taste of coffee?"].toBoolean(),
        doYouKnowWhereYourCoffeeComesFrom = map["Do you know where your coffee comes from?"].toBoolean(),
        mostYouPaid = map["What is the most you've ever paid for a cup of coffee?"].toString(),
        mostWillingToPay = map["What is the most you'd ever be willing to pay for a cup of coffee?"].toString(),
        valueForMoneyAtCafe = map["Do you feel like you’re getting good value for your money when you buy coffee at a cafe?"].toString(),
        valueSpendInEquipment = map["Approximately how much have you spent on coffee equipment in the past 5 years?"].toString(),
        valueForMoneyEquipment = map["Do you feel like you’re getting good value for your money with regards to your coffee equipment?"].toString()
    )
}