
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class WSServer {

	public static List<WebSocketAdapter> webSockets = new ArrayList<> ( );
	private static FoodWallet _wallet1;
	private static FoodWallet _wallet2;
	private static FoodWallet _wallet3;

	public static void main( String[] args ) throws Exception {
		///// TASK Decido che porta usare per il server,default e' 8080

		_wallet1 = new FoodWallet ( );
		_wallet1.addFoodComponent ( new FoodSimple ( 1, "Sugar" ) );
		_wallet1.addFoodComponent ( new FoodSimple ( 1, "Rice" ) );
		_wallet1.addFoodComponent ( new FoodSimple ( 1, "Chicken" ) );
		_wallet1.addFoodComponent ( new FoodSimple ( 2, "Onion" ) );
		_wallet1.addFoodComponent ( new FoodSimple ( 3, "Carrot" ) );

		_wallet2 = new FoodWallet ( );
		_wallet2.addFoodComponent ( new FoodSimple ( 1, "Chicken" ) );
		_wallet2.addFoodComponent ( new FoodSimple ( 1, "Onion" ) );
		_wallet2.addFoodComponent ( new FoodSimple ( 1, "Garlic" ) );
		_wallet2.addFoodComponent ( new FoodSimple ( 1, "OliveOil" ) );

		int port = args.length == 0 ? 8080 : Integer.valueOf ( args[0] );

		/////

		///// TASK Servlet che sa gestire la migrazione di una connessione HTTP a una connessione WebSocket per output

		Servlet websocketServlet = new WebSocketServlet ( ) {

			@Override
			public void configure( WebSocketServletFactory aFactory ) {

				///// TASK Registriamo la classe da cui verranno istanziati i WebSocket di output

				aFactory.register ( MyWebSocket.class );

				/////
			}
		};

		/////

		///// TASK Creo l'handler che gestisce le richieste che arrivano al server

		ServletHandler handler = new ServletHandler ( );

		/////

		///// TASK Configuro l'handler associando la servlet al suo path

		handler.addServletWithMapping ( new ServletHolder ( websocketServlet ), "/" );

		/////

		///// TASK Creo il server configurando porta e handler

		Server server = new Server ( port );
		server.setHandler ( handler );

		/////

		//// TASK Faccio partire il server

		server.start ( );

		/////
	}

	// note ogni volta che un cliente chiama la servlet mappata su "/" viene creato un oggetto MyWebSocket e la
	// note comunicazione viene 'migrata' dal protocollo http al protocollo websocket
	// note Un oggetto MyWebSocket gestisce la comunicazione con un singolo cliente.
	// note Se abbiamo 3 browsers collegati avremo 3 oggetti MyWebSocket
	public static class MyWebSocket extends WebSocketAdapter {

		///// TASK Rimuovo questo websocket dalla lista di websocket attivi

		@Override
		public void onWebSocketClose( int aStatusCode, String aReason ) {

			System.out.println ( "OnWebSocketClose: " + this.toString ( ) );
			webSockets.remove ( this );
		}

		/////

		///// TASK Aggiungo questo websocket alla lista di websocket attivi

		@Override
		public void onWebSocketConnect( Session aSession ) {

			super.onWebSocketConnect ( aSession );
			// toremove
			System.out.println ( "OnWebSocketConnect:" + aSession.getRemote ( ).toString ( ) );

			webSockets.add ( this );
		}

		/////

		///// TASK Scrivo su tutti websocket attivi il messaggio ricevuto da questo websocket

		@Override
		public void onWebSocketText( String aMessage ) {

			int messagePos = aMessage.indexOf ( ":" );
			String from = aMessage.substring ( 0, messagePos );
			String message = aMessage.substring ( messagePos + 1 ).trim ( );

			// toremove
			System.out.println ( "OnWebSocketText from: " + this.getRemote ( ).toString ( ) + "::" + from + " message: " + message );

			if ( message.equals ( "report connections" ) ) {
				new ReportConnectionsCommand ( webSockets ).run ( this, from );
				return;
			}

			if ( message.equals ( "setup" ) ) {
				new Setup ( _wallet1, _wallet2, webSockets ).run ( this, from );
				return;
			}

			for ( WebSocketAdapter each : webSockets ) {

				// if ( each == this ) continue;
				// note non facciamo echo

				///// TASK Scrivo sull'output

				try {
					String response = "Unknown Request";
					each.getRemote ( ).sendString ( from + ": " + response );
					System.out.println ( "OnWebSocketText to: " + each.getRemote ( ).toString ( ) + "::" + from + " message: " + response );

				} catch (IOException e) {
					System.out.println ( e.getMessage ( ) );
				}

				//////
			}
		}
	}

}
