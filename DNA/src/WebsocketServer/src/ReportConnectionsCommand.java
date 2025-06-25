import java.io.IOException;
import java.util.List;

import org.eclipse.jetty.websocket.api.WebSocketAdapter;

public class ReportConnectionsCommand {

	private List<WebSocketAdapter> _webSockets;

	public ReportConnectionsCommand( List<WebSocketAdapter> aWebSockets ) {

		_webSockets = aWebSockets;

	}

	public void run( WebSocketAdapter aWebSocket, String from ) {

		StringBuilder result = new StringBuilder ( );

		result.append ( from );
		result.append ( ":" );
		result.append ( "\n" );

		result.append ( _webSockets.size ( ) + " connections:" );
		result.append ( "\n" );

		for ( WebSocketAdapter each : _webSockets ) {
			String remoteEndPoint = each.getRemote ( ).toString ( );
			String localEndPoint = each.toString ( );
			result.append ( localEndPoint + " <-> " + remoteEndPoint );
			result.append ( "\n" );
		}

		String text = result.toString ( );
		try {
			aWebSocket.getRemote ( ).sendString ( text );

			System.out.println ( "ReportConnectionsCommand: sending report: " + text );
		} catch (IOException ignored) {
		}
	}

}
