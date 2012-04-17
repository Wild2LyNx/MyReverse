package my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Player {

	int portNumber;
	PrintWriter out = null;
	BufferedReader in = null;
	Socket clientSocket = null;
	ServerSocket serverSocket = null;
	Parser parser = new Parser();
	Protocol protocol = new Protocol();

	public Server(int portNum) {
		this.portNumber = portNum;

		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + portNumber + ".");
			System.exit(1);
		}

		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}

		try {
			openInOut();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void openInOut() throws IOException {
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
	}

	@Override
	public void makeMove(GameField gameField) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getState() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void stateChanged(GameField gameField, Cell cell) {
		// TODO Auto-generated method stub

	}

}
