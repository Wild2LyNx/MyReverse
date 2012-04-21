package my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Player {
	private static final int WAITING = States.WAITING;
	private static final int MOVING = States.MOVING;
	private static final int SENDMOVE = States.SENDMOVE;
	private static final int START = States.START;
	private static final int GAMEOVER = States.GAMEOVER;
	private int state = START;

	int portNumber, descriptor;
	PrintWriter out = null;
	BufferedReader in = null;
	Socket clientSocket = null;
	ServerSocket serverSocket = null;
	Parser parser = new Parser();
	Serializer serializer = new Serializer();
	Protocol protocol = new Protocol(parser, serializer);
	String outputLine, playerName, oppName;
	GameField gameField;
	Cell lastMove;

	public Server(int portNum, int descriptor, String playerName) {
		this.portNumber = portNum;
		this.descriptor = inverseDescriptor(descriptor);
		this.playerName = playerName;

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

	private int inverseDescriptor(int d) {
		if (d == 0)
			return 1;
		else if (d == 1)
			return 0;
		else
			return 3;
	}

	private void openInOut() throws IOException {
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));

		outputLine = protocol.processStartData(descriptor, playerName);
		out.println(outputLine);

		getOppName(in); // get opponent name

		/*
		 * Now, the game is started and we should to decide on the order of
		 * operation: should we wait for the move of player on this comp or we
		 * should read move of the opponent
		 */

		if (descriptor == 0)
			state = WAITING;
		else if (descriptor == 1) {
			state = MOVING;
			listenNewMove();
		}	
	}

	//this method sends request to the opponent would he like start new game or not.
	private void processGameOver() {
		outputLine = protocol.processGameOver();  
		out.println(outputLine);
		handleGOanswer();
	}

	private void handleGOanswer() {
		String answer = parser.parseNewLine(in);
		boolean newGame = protocol.processGOanswer(answer);
		if (newGame)newGame();
		else
			try {
				breakConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void newGame() {
		// TODO Auto-generated method stub
		
	}

	private void breakConnection() throws IOException {
		out.println("Bye.");
		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();		
	}

	private void getOppName(BufferedReader in) {
		String inputLine = parser.parseNewLine(in);
		oppName = protocol.processOppName(inputLine);
	}
	
	//this method returns opponent move to the game field.
	private void makeMove(Cell move) {
		gameField.tryMakeMove(move);
		if (gameField.movedSuccess) {
			lastMove = move;
			state = WAITING;
		}
		if (gameField.gameOver) {
			state = GAMEOVER;
			processGameOver();
		}
	}

	//This method calling for the opponent move
	@Override
	public void makeMove(GameField gameField) {
		this.gameField = gameField;
		state = MOVING;
		listenNewMove();
	}

	@Override
	public int getState() {
		return state;
	}

	@Override
	public void stateChanged(GameField gameField, Cell cell) {
		if (state == WAITING) {
			if (cell != lastMove) {
				outputLine = serializer.serializeMove(cell);
				out.println(outputLine);
				state = SENDMOVE;
			}
		}
	}

	private void listenNewMove() {
		String inputLine = null;
		try {
			if (in.ready()) {
				inputLine = parser.parseNewLine(in);
				if (inputLine == "Game over")
					processGameOver();
				else {
					Cell move = parser.parseMove(inputLine);
					makeMove(move);
				}
			}
		} catch (IOException e) {
			System.err.println("Could not listen input stream.");
			System.exit(1);
		}
	}

}
