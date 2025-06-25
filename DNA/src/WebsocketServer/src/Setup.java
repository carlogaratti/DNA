import java.io.IOException;
import java.util.List;

import org.eclipse.jetty.websocket.api.WebSocketAdapter;

public class Setup {

	private FoodWallet _fridge;
	private FoodWallet _meal;

	public Setup( FoodWallet fridge, FoodWallet meal, List<WebSocketAdapter> aWebSockets ) {

		_fridge = fridge;
		_meal = meal;

	}

	public void run( WebSocketAdapter aWebSocket, String from ) {

		StringBuilder command1 = new StringBuilder ( );

		// create fridge
		command1.append ( "newCard" );
		command1.append ( ":" );
		String content1 = "FRIDGE: " + _fridge.asString ( );
		command1.append ( "name=fridge|text=" + content1 );

		try {
			aWebSocket.getRemote ( ).sendString ( command1.toString ( ) );

			System.out.println ( "CreateNewCardCommand: sending command: " + command1.toString ( ) );
		} catch (IOException ignored) {
		}

		StringBuilder command2 = new StringBuilder ( );

		// create fridge
		command2.append ( "newCard" );
		command2.append ( ":" );
		String content2 = "MEAL: " + _meal.asString ( );
		command2.append ( "name=meal|text=" + content2 );

		try {
			aWebSocket.getRemote ( ).sendString ( command2.toString ( ) );

			System.out.println ( "CreateNewCardCommand: sending command: " + command2.toString ( ) );
		} catch (IOException ignored) {
		}

		StringBuilder command3 = new StringBuilder ( );

		// create fridge
		command3.append ( "newCard" );
		command3.append ( ":" );
		String content3 = "TODOLIST: " + _meal.minus ( _fridge ).asString ( );
		command3.append ( "name=todolist|text=" + content3 );

		try {
			aWebSocket.getRemote ( ).sendString ( command3.toString ( ) );

			System.out.println ( "CreateNewCardCommand: sending command: " + command3.toString ( ) );
		} catch (IOException ignored) {
		}

		StringBuilder command4 = new StringBuilder ( );

		command4.append ( from );
		command4.append ( ":" );
		command4.append ( "done!" );

		try {
			aWebSocket.getRemote ( ).sendString ( command4.toString ( ) );

			System.out.println ( "CreateNewCardCommand: sending command: " + command4.toString ( ) );
		} catch (IOException ignored) {
		}

	}

}
