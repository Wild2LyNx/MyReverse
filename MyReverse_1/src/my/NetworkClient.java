package my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkClient {
	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	String hostName;
	Parser parser;

	public NetworkClient(String hostName) throws IOException {
		this.hostName = hostName;
		runConnection();
	}

	private void runConnection() throws IOException {
		try {
			socket = new Socket(hostName, 4444);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: "
					+ hostName);
			System.exit(1);
		}
		System.out.println("Connection established");

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(
				System.in)); // In the future, here will be reader from some
								// game listener
		String fromServer;
		String fromUser;

		while ((fromServer = in.readLine()) != null) {
			if (fromServer.equals("Bye."))
				break;
				
			parser.parseMove(fromServer);

			fromUser = stdIn.readLine(); //here I should somehow connect game listener
			if (fromUser != null) {
				out.println(fromUser);
			}
		}

		out.close();
		in.close();
		stdIn.close();
		socket.close();
	}	
}
