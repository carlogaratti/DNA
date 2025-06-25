import java.io.PrintStream;

public class FoodSimple extends FoodComponent {

	public FoodSimple( int aServing, String aName ) {

		super ( aServing, aName );
	}

	@Override
	public FoodComponent addServing( int aPreexistingServing ) {

		return new FoodSimple ( _serving + aPreexistingServing, _name );
	}

	@Override
	public void addServingMultiplySimpleToWallet( FoodWallet aWallet ) {

		aWallet.addFoodComponent ( this );
	}

	@Override
	public boolean equals( Object anObject ) {

		if ( ! ( anObject instanceof FoodSimple ) ) return false;
		FoodSimple anotherFoodSimple = (FoodSimple) anObject;

		if ( ! anotherFoodSimple._name.equals ( _name ) ) return false;
		if ( ! ( anotherFoodSimple._serving == _serving ) ) return false;
		return true;
	}

	@Override
	public void reportOn( PrintStream aStream ) {

		aStream.print ( "FoodSimple(" + _serving + " " + _name + ")" );

	}

}
