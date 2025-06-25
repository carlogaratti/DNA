import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class Starter {

	@Test
	public void test_FoodComposite_AddSimpleToWalletMoreTimes() throws Exception {

		FoodComposite onechocolatecake = new FoodComposite ( 1, "chocolatecake" );
		FoodSimple onechocolate = new FoodSimple ( 1, "chocolate" );
		onechocolatecake.asWallet ( ).addFoodComponent ( onechocolate );

		FoodWallet wallet = new FoodWallet ( );

		onechocolatecake.addServingMultiplySimpleToWallet ( wallet );
		onechocolatecake.addServingMultiplySimpleToWallet ( wallet );

		wallet.reportOn ( System.out );

	}

	@Test
	public void test_FoodComposite_equals_false() throws Exception {

		FoodComposite composite1 = new FoodComposite ( 1, "sameName" );

		FoodComposite composite2 = new FoodComposite ( 1, "sameName" );

		composite1.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "bread" ) );
		composite2.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "bread" ) );
		composite2.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "somethingDifferent" ) );

		assertFalse ( ( (FoodComponent) composite1 ).equals ( composite2 ) );

	}

	@Test
	public void test_FoodComposite_equals_true() throws Exception {

		FoodComposite composite1 = new FoodComposite ( 1, "sameName" );

		FoodComposite composite2 = new FoodComposite ( 1, "sameName" );

		composite1.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "bread" ) );
		composite2.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "bread" ) );

		assertTrue ( composite1.equals ( composite2 ) );

	}

	@Test
	public void test_FoodComposite_simpleParts() throws Exception {

		FoodComposite chocolateCake = new FoodComposite ( 1, "chocolate cake" );
		chocolateCake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "chocolate" ) );
		chocolateCake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		chocolateCake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "egg" ) );

		FoodWallet wallet = new FoodWallet ( );
		chocolateCake.addServingMultiplySimpleToWallet ( wallet );
		assertEquals ( "should be not empty", 3, wallet.size ( ) );
	}

	@Test
	public void test_FoodSimple_AddToWalletMoreTimes() throws Exception {

		FoodSimple onechocolate = new FoodSimple ( 1, "chocolate" );
		FoodWallet wallet = new FoodWallet ( );
		onechocolate.addServingMultiplySimpleToWallet ( wallet );
		onechocolate.addServingMultiplySimpleToWallet ( wallet );
		onechocolate.addServingMultiplySimpleToWallet ( wallet );
		wallet.reportOn ( System.out );

	}

	@Test
	public void test_FoodSimple_equals() throws Exception {

		assertTrue ( new FoodSimple ( 1, "chocolate" ).equals ( new FoodSimple ( 1, "chocolate" ) ) );

	}

	@Test
	public void test_FoodSimple_equals_false() throws Exception {

		assertFalse ( new FoodSimple ( 1, "chocolate" ).equals ( new FoodSimple ( 1, "bread" ) ) );

	}

	@Test
	public void test_FoodWallet_AddMoreFoodComponents() throws Exception {

		FoodComposite chocolateCake = new FoodComposite ( 2, "chocolate cake" );
		chocolateCake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "chocolate" ) );

		FoodWallet wallet = new FoodWallet ( );
		chocolateCake.addServingMultiplySimpleToWallet ( wallet );

		assertEquals ( "should simple parts size be", 2, wallet.servingsFor ( "chocolate" ) );

	}

	@Test
	public void test_FoodWallet_contains() throws Exception {

		FoodWallet wallet = new FoodWallet ( );
		wallet.addFoodComponent ( new FoodSimple ( 1, "chocolate" ) );
		List<FoodComponent> components = wallet.asList ( );

		assertEquals ( "should size be 1", 1, wallet.size ( ) );
		assertEquals ( "should dump contain chocolate", "1 serving chocolate,", wallet.asString ( ) );
		assertTrue ( "should type ok", wallet.first ( ) instanceof FoodSimple );
		FoodComponent first = wallet.first ( );

		assertEquals ( "should first be chocolate", new FoodSimple ( 1, "chocolate" ), first );
		assertTrue ( "should list contain chocolate", components.contains ( new FoodSimple ( 1, "chocolate" ) ) );
		assertTrue ( "should wallet contain chocolate", wallet.contains ( new FoodSimple ( 1, "chocolate" ) ) );

	}

	@Test
	public void test_FoodWallet_contains_simple() throws Exception {

		FoodWallet wallet = new FoodWallet ( );
		wallet.addFoodComponent ( new FoodSimple ( 1, "chocolate" ) );
		assertTrue ( wallet.contains ( new FoodSimple ( 1, "chocolate" ) ) );

	}

	@Test
	public void test_Foodwallet_firstFull_secondEmpty() throws Exception {

		FoodWallet first = new FoodWallet ( );
		first.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		FoodWallet second = new FoodWallet ( );

		assertEquals ( "second shoud be empty", 0, second.size ( ) );

		first.writeOn ( second );

		assertEquals ( "second shoud not be empty", 1, second.size ( ) );
	}

	@Test
	public void test_Foodwallet_firstFull_secondfull() throws Exception {

		FoodWallet first = new FoodWallet ( );
		first.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		FoodWallet second = new FoodWallet ( );
		second.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );

		assertEquals ( "second shoud be empty", 1, second.size ( ) );

		first.writeOn ( second );

		assertEquals ( "second shoud not be empty", 1, second.size ( ) );
		assertEquals ( "second shoud not be empty", 2, second.servingsFor ( "sugar" ) );
	}

	@Test
	public void test_Foodwallet_firstFull_secondfull_complex() throws Exception {

		FoodWallet first = new FoodWallet ( );
		first.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		first.addFoodComponent ( new FoodSimple ( 1, "chocolate" ) );
		FoodWallet second = new FoodWallet ( );
		second.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		second.addFoodComponent ( new FoodSimple ( 1, "bread" ) );

		assertEquals ( "second shoud be empty", 2, second.size ( ) );

		first.writeOn ( second );

		assertEquals ( "second shoud not be empty", 3, second.size ( ) );
	}

	@Test
	public void test_FoodWallet_Intersects() throws Exception {

		FoodWallet wallet1 = new FoodWallet ( );
		wallet1.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		wallet1.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		wallet1.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );

		FoodWallet wallet2 = new FoodWallet ( );
		wallet2.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );

		FoodWallet wallet3 = wallet1.intersects ( wallet2 );

		assertEquals ( 1, wallet3.servingsFor ( "sugar" ) );

	}

	@Test
	public void test_FoodWallet_Intersects_MoreInSet2() throws Exception {

		FoodWallet wallet1 = new FoodWallet ( );
		wallet1.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );

		FoodWallet wallet2 = new FoodWallet ( );
		wallet2.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		wallet2.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		wallet2.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );

		FoodWallet wallet3 = wallet1.intersects ( wallet2 );

		assertEquals ( "should be not empty", 1, wallet3.size ( ) );

		assertEquals ( "should have 1 sugar", 1, wallet3.servingsFor ( "sugar" ) );

	}

	@Test
	public void test_FoodWallet_Intersects_None() throws Exception {

		FoodWallet wallet1 = new FoodWallet ( );
		wallet1.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		wallet1.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		wallet1.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );

		FoodWallet wallet2 = new FoodWallet ( );
		wallet2.addFoodComponent ( new FoodSimple ( 1, "chocolate" ) );

		FoodWallet wallet3 = wallet1.intersects ( wallet2 );

		assertEquals ( "should be empty", 0, wallet3.size ( ) );

		assertEquals ( "should have 0 sugar", 0, wallet3.servingsFor ( "sugar" ) );
		assertEquals ( "should have 0 chocolate", 0, wallet3.servingsFor ( "chocolate" ) );

	}

	@Test
	public void test_FoodWallet_MinusWallet() throws Exception {

		FoodWallet wallet1 = new FoodWallet ( );
		wallet1.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		wallet1.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		wallet1.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );

		FoodWallet wallet2 = new FoodWallet ( );
		wallet2.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );

		FoodWallet wallet3 = wallet1.minus ( wallet2 );

		assertEquals ( 2, wallet3.servingsFor ( "sugar" ) );
	}

	@Test
	public void test_FoodWallet_MinusWallet2() throws Exception {

		FoodWallet wallet1 = new FoodWallet ( );
		wallet1.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		wallet1.addFoodComponent ( new FoodSimple ( 1, "carrots" ) );
		wallet1.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );

		FoodWallet wallet2 = new FoodWallet ( );
		wallet2.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );

		FoodWallet wallet3 = wallet1.minus ( wallet2 );

		assertEquals ( 1, wallet3.servingsFor ( "sugar" ) );
		assertEquals ( 1, wallet3.servingsFor ( "carrots" ) );
	}

	@Test
	public void test_FoodWallet_MinusWallet3() throws Exception {

		FoodWallet wallet1 = new FoodWallet ( );
		wallet1.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		wallet1.addFoodComponent ( new FoodSimple ( 1, "carrots" ) );
		wallet1.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );

		FoodWallet wallet2 = new FoodWallet ( );
		wallet2.addFoodComponent ( new FoodSimple ( 3, "sugar" ) );

		FoodWallet wallet3 = wallet1.minus ( wallet2 );

		assertEquals ( 0, wallet3.servingsFor ( "sugar" ) );
		assertEquals ( 1, wallet3.servingsFor ( "carrots" ) );
	}

	@Test
	public void test_FoodWallet_NoKey() throws Exception {

		FoodWallet wallet = new FoodWallet ( );

		assertEquals ( 0, wallet.servingsFor ( "sugar" ) );
	}

	@Test
	public void test_FoodWallet_putSameIngredientSeveralTimes() throws Exception {

		FoodWallet wallet = new FoodWallet ( );
		wallet.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		wallet.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		wallet.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );

		assertEquals ( 3, wallet.servingsFor ( "sugar" ) );
	}

	@Test
	public void test_FoodWallet_putUnexistant() throws Exception {

		FoodWallet wallet = new FoodWallet ( );
		wallet.addFoodComponent ( new FoodSimple ( 1, "sugar" ) );

		assertEquals ( 1, wallet.servingsFor ( "sugar" ) );
	}

	@Test
	public void test_FoodWallet_simpleParts() throws Exception {

		FoodComposite chocolateCake = new FoodComposite ( 2, "chocolate cake" );
		chocolateCake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "chocolate" ) );
		chocolateCake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		chocolateCake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "egg" ) );

		FoodWallet wallet = new FoodWallet ( );
		wallet.addFoodComponent ( new FoodSimple ( 1, "carrot" ) );
		wallet.addFoodComponent ( chocolateCake );

		assertEquals ( "should composite size be 2", 2, wallet.size ( ) );
		assertTrue ( "should be a chocolatecake", wallet.contains ( chocolateCake ) );

		FoodWallet simpleFoods = wallet.servingMultipliedSimpleFoods ( );
		simpleFoods.reportOn ( System.out );
		assertEquals ( "should simple parts size be", 4, simpleFoods.size ( ) );
		assertEquals ( "should simple parts size be", 2, simpleFoods.servingsFor ( "chocolate" ) );

	}

	@Test
	public void test_Fridge_two_times_same_item() throws Exception {

		Fridge fridge = new Fridge ( );
		fridge.put ( new FoodSimple ( 1, "chocolate" ) );
		fridge.put ( new FoodSimple ( 1, "chocolate" ) );
		assertEquals ( "fridge should have 2 servings chocolate", 2, fridge.servingsFor ( "chocolate" ) );

	}

	@Test
	public void test_Fridge_WhatDoIHave() throws Exception {

		FoodWallet existingListForMeal = new FoodWallet ( );

		Fridge fridge = new Fridge ( );
		fridge.put ( new FoodSimple ( 1, "chocolate" ) );
		fridge.put ( new FoodSimple ( 1, "bread" ) );

		FoodComposite cake = new FoodComposite ( 1, "chocolateCake" );
		cake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		cake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "chocolate" ) );
		cake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "egg" ) );

		Meal meal = new Meal ( "breakfast" );
		meal.add ( cake );

		fridge.whatDoIHaveFor ( meal, existingListForMeal );
		assertEquals ( "should shopping list be one thing", 1, existingListForMeal.size ( ) );
		assertEquals ( "should be one thing", 1, existingListForMeal.servingsFor ( "chocolate" ) );
	}

	@Test
	public void test_Meal() throws Exception {

		Meal breakfast = new Meal ( "breakfast" );
		FoodComposite chocolateCake = new FoodComposite ( 1, "cake" );
		chocolateCake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		chocolateCake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "chocolate" ) );
		chocolateCake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "egg" ) );
		breakfast.add ( chocolateCake );

		breakfast.add ( new FoodSimple ( 1, "carrot" ) );

		assertEquals ( 2, breakfast._wallet.asList ( ).size ( ) );
		assertEquals ( 4, breakfast._wallet.servingMultipliedSimpleFoods ( ).size ( ) );

	}

	@Test
	public void test_Meal_mixedIngredient() throws Exception {

		Meal breakfast = new Meal ( "breakfast" );
		FoodComposite chocolateCake = new FoodComposite ( 1, "cake" );
		chocolateCake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		chocolateCake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "chocolate" ) );
		chocolateCake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "egg" ) );
		breakfast.add ( chocolateCake );

		breakfast.add ( new FoodSimple ( 1, "sugar" ) );

		breakfast.reportOn ( System.out );
		assertEquals ( 2, breakfast._wallet.asList ( ).size ( ) );
		assertEquals ( 3, breakfast._wallet.servingMultipliedSimpleFoods ( ).size ( ) );

	}

	@Test
	public void test_S001_no_need_fridge_empth() throws Exception {

		FoodWallet shoppingList = new FoodWallet ( );
		Fridge fridge = new Fridge ( );

		Meal breakfast = new Meal ( "breakfast" );

		fridge.whatAreTheIngredientsNeededToMake ( breakfast, shoppingList );

		assertEquals ( "fridge should have chocolate", 0, fridge.servingsFor ( "chocolate" ) );
		assertEquals ( "should be one thing", 0, shoppingList.size ( ) );
	}

	@Test
	public void test_S002_2chocolate_bf_fridge_has_one_chocolate() throws Exception {

		FoodWallet shoppingList = new FoodWallet ( );
		Fridge fridge = new Fridge ( );
		fridge.put ( new FoodSimple ( 1, "chocolate" ) );

		Meal breakfast = new Meal ( "breakfast" );
		breakfast.add ( new FoodSimple ( 2, "chocolate" ) );
		breakfast.add ( new FoodSimple ( 1, "sugar" ) );

		fridge.whatAreTheIngredientsNeededToMake ( breakfast, shoppingList );

		assertEquals ( "fridge should have chocolate", 1, fridge.servingsFor ( "chocolate" ) );
		assertEquals ( "should be one thing", 2, shoppingList.size ( ) );
		assertEquals ( "should have 1serving", 1, shoppingList.servingsFor ( "chocolate" ) );
		assertTrue ( "should have 1s chocolate", shoppingList.contains ( new FoodSimple ( 1, "chocolate" ) ) );
		assertTrue ( "should have 1s sugare", shoppingList.contains ( new FoodSimple ( 1, "sugar" ) ) );

	}

	@Test
	public void test_S003_1chocolate_bf_fridge_empty() throws Exception {

		FoodWallet shoppingList = new FoodWallet ( );
		Fridge fridge = new Fridge ( );

		Meal breakfast = new Meal ( "breakfast" );
		breakfast.add ( new FoodSimple ( 1, "chocolate" ) );

		fridge.whatAreTheIngredientsNeededToMake ( breakfast, shoppingList );

		assertEquals ( "fridge should have chocolate", 0, fridge.servingsFor ( "chocolate" ) );
		assertEquals ( "should be one thing", 1, shoppingList.size ( ) );
		assertEquals ( "should write this", 1, shoppingList.servingsFor ( "chocolate" ) );
	}

	@Test
	public void test_S004_1chocolate_bf_fridge_1carrot() throws Exception {

		FoodWallet shoppingList = new FoodWallet ( );
		Fridge fridge = new Fridge ( );
		fridge.put ( new FoodSimple ( 1, "carrot" ) );

		Meal breakfast = new Meal ( "breakfast" );
		breakfast.add ( new FoodSimple ( 1, "chocolate" ) );

		fridge.whatAreTheIngredientsNeededToMake ( breakfast, shoppingList );

		assertEquals ( "fridge should have carrot", 1, fridge.servingsFor ( "carrot" ) );
		assertEquals ( "should be one thing", 1, shoppingList.size ( ) );
		assertEquals ( "should write this", 1, shoppingList.servingsFor ( "chocolate" ) );
	}

	@Test
	public void test_S005_1chocolate_bf_fridge_1carrot_and_1chocolate() throws Exception {

		FoodWallet shoppingList = new FoodWallet ( );
		Fridge fridge = new Fridge ( );
		fridge.put ( new FoodSimple ( 1, "carrot" ) );
		fridge.put ( new FoodSimple ( 1, "chocolate" ) );

		Meal breakfast = new Meal ( "breakfast" );
		breakfast.add ( new FoodSimple ( 1, "chocolate" ) );

		fridge.whatAreTheIngredientsNeededToMake ( breakfast, shoppingList );

		assertEquals ( "fridge should have carrot", 1, fridge.servingsFor ( "carrot" ) );
		assertEquals ( "fridge should have chocolate", 1, fridge.servingsFor ( "chocolate" ) );
		assertEquals ( "should be one thing", 0, shoppingList.size ( ) );
	}

	@Test
	public void test_S006_1cake_bf_fridge_1chocolate() throws Exception {

		// 1 cacke: 1 sugar, 1 chocolate, 1 egg
		FoodWallet shoppingList = new FoodWallet ( );

		Fridge fridge = new Fridge ( );
		fridge.put ( new FoodSimple ( 1, "chocolate" ) );

		Meal breakfast = new Meal ( "breakfast" );
		FoodComposite chocolateCake = new FoodComposite ( 2, "cake" );
		chocolateCake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "sugar" ) );
		chocolateCake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "chocolate" ) );
		chocolateCake.asWallet ( ).addFoodComponent ( new FoodSimple ( 1, "egg" ) );
		breakfast.add ( chocolateCake );

		fridge.whatAreTheIngredientsNeededToMake ( breakfast, shoppingList );

		assertEquals ( "fridge should have chocolate", 1, fridge.servingsFor ( "chocolate" ) );

		shoppingList.reportOn ( System.out );
		assertEquals ( "should be three ingredients", 3, shoppingList.size ( ) );
		assertEquals ( "should be 1 chocolate", 1, shoppingList.servingsFor ( "chocolate" ) );
		assertEquals ( "should be 2 sugar", 2, shoppingList.servingsFor ( "sugar" ) );

	}

}
