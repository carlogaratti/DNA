import java.util.ArrayList;

public class Fridge {

	private ArrayList<FoodComponent> _foodComponentList = new ArrayList<> ( );
	private FoodWallet _wallet = new FoodWallet ( );

	public void put( FoodComponent anIngredient ) {

		_foodComponentList.add ( anIngredient );
		anIngredient.addServingMultiplySimpleToWallet ( _wallet );
	}

	public int servingsFor( String aName ) {

		return _wallet.servingsFor ( aName );
	}

	public void whatAreTheIngredientsNeededToMake( Meal aMeal, FoodWallet aShoppingList ) {

		aMeal._wallet.servingMultipliedSimpleFoods ( ).minus ( _wallet.servingMultipliedSimpleFoods ( ) ).writeOn ( aShoppingList );

	}

	public void whatDoIHaveFor( Meal aMeal, FoodWallet aShoppingList ) {

		FoodWallet mealWallet = aMeal._wallet;
		FoodWallet result = _wallet.servingMultipliedSimpleFoods ( ).intersects ( mealWallet.servingMultipliedSimpleFoods ( ) );
		result.writeOn ( aShoppingList );

	}

}
