import java.io.PrintStream;

public abstract class FoodComponent {

	public String _name;
	public int _serving;

	public FoodComponent( int serving, String name ) {

		_name = name;
		_serving = serving;
	}

	abstract public FoodComponent addServing( int aPreexistingServing );

	abstract public void addServingMultiplySimpleToWallet( FoodWallet aWallet );

	public String asString() {

		return _serving + " serving " + _name;
	}

	abstract public void reportOn( PrintStream aStream );

	@Override
	public String toString() {

		return asString ( );
	}

}
