package my;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyProtocolHandler implements MouseListener{
	String hostName;
	boolean server, client;
	GameField field;
	
	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	
	public MyProtocolHandler (GameField f, String n, boolean s, boolean c){
		this.field = f;
		this.hostName = n;
		this.server = s;
		this.client = c;
		if (c) {
			try {
				createClient();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (s) createServer();
	}

	private void createServer() {
		// TODO Auto-generated method stub
		
	}

	private void createClient() throws IOException{
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

		/*BufferedReader stdIn = new BufferedReader(new InputStreamReader(
				System.in)); // In the future, here will be reader from some
								// game listener
*/		String fromServer;
//		String fromUser;

		while ((fromServer = in.readLine()) != null) {
			if (fromServer.equals("Bye."))
				break;
				
			parse(fromServer);

			/*fromUser = stdIn.readLine(); //here I should somehow connect game listener
			if (fromUser != null) {
				out.println(fromUser);
			}*/
		}

		out.close();
		in.close();
//		stdIn.close();
		socket.close();
		
	}

	private void parse(String fromServer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
