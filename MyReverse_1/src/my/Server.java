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
	String inputLine, outputLine, playerName, oppName;
	GameField gameField;
	Cell lastMove;

	public Server(int portNum, int descriptor, String playerName) {
		this.portNumber = portNum;
		this.descriptor = descriptor;
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

	private void openInOut() throws IOException {
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		
		outputLine = protocol.processStartData(descriptor, playerName);
        out.println(outputLine);

        while ((inputLine = in.readLine()) != null) {
        	if (state == START){
             outputLine = protocol.processInput(inputLine);
             out.println(outputLine);
             state = protocol.getState();
             } else if (state == MOVING){
            	 makeMove();
             } else if (state == GAMEOVER){
            	 protocol.processInput(inputLine);
             }
             if (outputLine.equals("Bye."))
                break;
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
	}

	private void makeMove() {
		Cell move = parser.parseMove(inputLine);
		 gameField.tryMakeMove(move);		 
		 if (gameField.movedSuccess) {
			 lastMove = move;
			 state = WAITING; 
		 }
		 if (gameField.gameOver) {
			 state = GAMEOVER;
			 outputLine = protocol.processGameOver();
			 out.println(outputLine);
		 }
	}

	@Override
	public void makeMove(GameField gameField) {
		this.gameField = gameField;
		state = MOVING;
	}

	@Override
	public int getState() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void stateChanged(GameField gameField, Cell cell) {
		if (state == WAITING){
			if (cell != lastMove){				
				outputLine = serializer.serializeMove(cell);
			    out.println(outputLine);
			    state = SENDMOVE;
			}
		}
	}

}
