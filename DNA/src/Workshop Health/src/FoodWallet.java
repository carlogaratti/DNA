import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodWallet {

	private Map<String, FoodComponent> _map = new HashMap<> ( );

	public void addFoodComponent( FoodComponent aFoodComponent ) {

		int preexistingServing = 0;
		if ( _map.containsKey ( aFoodComponent._name ) ) {
			FoodComponent preexistingComponent = _map.get ( aFoodComponent._name );
			preexistingServing = preexistingComponent._serving;
		}

		FoodComponent newFoodComponent = aFoodComponent.addServing ( preexistingServing );

		_map.put ( newFoodComponent._name, newFoodComponent );
	}

	public List<FoodComponent> asList() {

		List<FoodComponent> result = new ArrayList<> ( );
		for ( FoodComponent each : _map.values ( ) ) {
			result.add ( each );
		}
		return result;
	}

	public String asString() {

		StringBuilder result = new StringBuilder ( );
		for ( FoodComponent each : asList ( ) ) {
			result.append ( each.asString ( ) );
			result.append ( "," );
		}
		return result.toString ( );
	}

	public boolean contains( FoodComponent aFoodComponent ) {

		boolean containsKey = _map.containsKey ( aFoodComponent._name );

		if ( ! containsKey ) return false;
		FoodComponent foodComponent = _map.get ( aFoodComponent._name );
		if ( foodComponent.equals ( aFoodComponent ) ) return true;
		return false;
	}

	@Override
	public boolean equals( Object anObject ) {

		if ( ! ( anObject instanceof FoodWallet ) ) return false;
		FoodWallet anotherFoodWallet = (FoodWallet) anObject;

		if ( ! ( anotherFoodWallet.size ( ) == size ( ) ) ) return false;

		for ( FoodComponent eachPart : asList ( ) ) {
			if ( ! anotherFoodWallet.contains ( eachPart ) ) return false;
		}
		return true;
	}

	public FoodComponent first() {

		if ( _map.isEmpty ( ) ) return null;
		return asList ( ).get ( 0 );
	}

	public FoodWallet intersects( FoodWallet anotherWallet ) {

		FoodWallet result = new FoodWallet ( );
		for ( FoodComponent each : _map.values ( ) ) {
			String nameNeeded = each._name;
			int quantityNeeded = each._serving;

			int quantityAvailable = anotherWallet.servingsFor ( nameNeeded );

			int quantityToBuy = Math.min ( quantityNeeded, quantityAvailable );
			if ( quantityToBuy > 0 ) result.addFoodComponent ( new FoodSimple ( quantityToBuy, nameNeeded ) );
		}
		return result;
	}

	public FoodWallet minus( FoodWallet anotherWallet ) {

		FoodWallet result = new FoodWallet ( );
		for ( FoodComponent each : _map.values ( ) ) {
			String nameNeeded = each._name;
			int quantityNeeded = each._serving;

			int quantityAvailable = anotherWallet.servingsFor ( nameNeeded );

			int quantityToBuy = Math.max ( quantityNeeded - quantityAvailable, 0 );
			if ( quantityToBuy == 0 ) continue;
			result.addFoodComponent ( new FoodSimple ( quantityToBuy, nameNeeded ) );
		}
		return result;
	}

	public void reportOn( PrintStream aStream ) {

		for ( FoodComponent foodComponent : asList ( ) ) {
			foodComponent.reportOn ( aStream );
		}
	}

	public int servingsFor( String aName ) {

		if ( ! _map.containsKey ( aName ) ) return 0;

		FoodComponent food = _map.get ( aName );
		return food._serving;
	}

	public FoodWallet servingMultipliedSimpleFoods() {

		FoodWallet result = new FoodWallet ( );

		for ( FoodComponent each : asList ( ) ) {
			each.addServingMultiplySimpleToWallet ( result );
		}
		return result;
	}

	public int size() {

		return _map.size ( );
	}

	public int sizeParts() {

		FoodWallet result = new FoodWallet ( );
		for ( FoodComponent each : asList ( ) ) {
			each.addServingMultiplySimpleToWallet ( result );
		}
		return result.size ( );
	}

	@Override
	public String toString() {

		return asString ( );
	}

	public void writeOn( FoodWallet anotherWallet ) {

		for ( FoodComponent each : asList ( ) ) {
			anotherWallet.addFoodComponent ( each );
		}
	}

}
