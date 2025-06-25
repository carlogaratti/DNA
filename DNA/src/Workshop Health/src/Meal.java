import java.io.PrintStream;

public class Meal {

	public FoodWallet _wallet;

	private String _name;

	public Meal( String name ) {

		_name = name;
		_wallet = new FoodWallet ( );
	}

	public void add( FoodComponent aFoodItem ) {

		_wallet.addFoodComponent ( aFoodItem );
	}

	public void reportOn( PrintStream stream ) {

		stream.print ( "Hi I'm a " + _name + " " + this.getClass ( ).getName ( ) + ": " );
		_wallet.reportOn ( stream );
	}

}
