package my;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyProtocolHandler implements MouseListener{
	String hostName;
	boolean server, client;
	
	public MyProtocolHandler (String n, boolean s, boolean c){
		this.hostName = n;
		this.server = s;
		this.client = c;
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
