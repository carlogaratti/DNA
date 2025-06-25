import java.io.PrintStream;
import java.util.List;

public class FoodComposite extends FoodComponent {

	private FoodWallet _wallet = new FoodWallet ( );

	public FoodComposite( int aServing, String aName ) {

		super ( aServing, aName );
	}

	@Override
	public FoodComponent addServing( int aPreexistingServing ) {

		FoodComposite newFoodComposite = new FoodComposite ( _serving + aPreexistingServing, _name );
		// note: gli ingredienti non sono cloni
		this.asWallet ( ).writeOn ( newFoodComposite.asWallet ( ) );
		return newFoodComposite;
	}

	@Override
	public void addServingMultiplySimpleToWallet( FoodWallet aWallet ) {

		List<FoodComponent> list = _wallet.asList ( );

		for ( FoodComponent each : list ) {
			for ( int i = 0; i < _serving; i++ ) {
				each.addServingMultiplySimpleToWallet ( aWallet );
			}
		}
	}

	public FoodWallet asWallet() {

		return _wallet;
	}

	@Override
	public boolean equals( Object anObject ) {

		if ( ! ( anObject instanceof FoodComposite ) ) return false;

		FoodComposite anotherFoodComposite = (FoodComposite) anObject;

		if ( ! anotherFoodComposite._name.equals ( _name ) ) return false;
		if ( ! ( anotherFoodComposite._serving == _serving ) ) return false;

		if ( ! anotherFoodComposite.asWallet ( ).equals ( this.asWallet ( ) ) ) return false;

		return true;

	}

	@Override
	public void reportOn( PrintStream aStream ) {

		aStream.print ( "FoodComposite(" + _name + " " + _serving + ": " );
		_wallet.reportOn ( aStream );
		aStream.print ( ")" );
	}

	public int size() {

		return _wallet.size ( );
	}

}
