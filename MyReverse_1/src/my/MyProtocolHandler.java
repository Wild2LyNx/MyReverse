package my;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyProtocolHandler implements MouseListener {
	private static final int WAITING = 0;
	private static final int SENTAUTHREQUEST = 1;
	private static final int WAITFORCOLOR = 2;
	private static final int SENDMOVE = 3;
	private static final int GAMEOVER = 4;

	private int state = WAITING;

	String hostName;
	boolean server, client;
	GameField field;

	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;

	Parser parser = new Parser();
	Serializer serializer = new Serializer();

	int playerDescriptor = 3; // index, which is displaying the color of stones
								// of player who playing on this computer.
								// ("...in the house that Jack built", yeah). In
								// my designation 0 means "black" and 1 means
								// "white".

	public MyProtocolHandler(GameField f, String n, boolean s, boolean c) {
		this.field = f;
		this.hostName = n;
		this.server = s;
		this.client = c;
		if (c) {
			try {
				createClient();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (s)
			createServer();
	}

	private void createServer() {
		// TODO Auto-generated method stub

	}

	private void createClient() throws IOException {
		state = SENTAUTHREQUEST;
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
		state = WAITFORCOLOR;

		String fromServer;

		while ((fromServer = in.readLine()) != null) {
			if (fromServer.equals("Bye."))
				break;

			if (state == WAITFORCOLOR) {
				playerDescriptor = parser.parseColor(fromServer);
				if (playerDescriptor == 0)
					state = SENDMOVE; // make first move if our color is black.
				if (playerDescriptor == 1)
					state = WAITING; // wait for first move if our color is
										// white.
			}

			if (state == WAITING) {
				parser.parseMove(fromServer);
				Cell curCell = field.findCellByIJ(parser.getI(), parser.getJ());
				if (!field.canMove(curCell)) out.println("Couldn't move this cell");
				else field.tryMakeMove(curCell);
				
				if (field.passMove) out.println("Pass");				
				else if (field.gameOver) sendGameOver();
				else {
					state = SENDMOVE;					
				}				
			}			
		}

		out.close();
		in.close();
		socket.close();
	}	

	private void sendGameOver() {
		int blackScores = field.blackStones.size();
		int whiteScores = field.whiteStones.size();
		out.println("Game over by scores: " + blackScores + "to " + whiteScores);
		state = GAMEOVER;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (state == SENDMOVE){
			double x = e.getX();
			double y = e.getY();
			Cell cell = field.findCellByXY(x, y);

			if (cell != null)
				System.out.println("Cell: " + cell.i_index + ", " + cell.j_index);

			if (field.canMove(cell)) {
				field.tryMakeMove(cell);
				String move = serializer.serializeMove(cell);
				out.println(move);
				state = WAITING;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
