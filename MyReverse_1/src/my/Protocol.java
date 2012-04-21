package my;

public class Protocol {
	private static final int WAITING = States.WAITING;
	private static final int MOVING = States.MOVING;
	private static final int SENTauthANSWER = States.SENTauthANSWER;
	private static final int GAMEOVER = States.GAMEOVER;
	private static final int WAITforDATA = States.WAITforDATA;
	private int state = SENTauthANSWER;
	private int descriptor = 3; // this index represents the color of player on
								// this computer. 0 means "black", 1 - "white".

	Parser parser;
	Serializer serializer;
	public String oppName = null;

	public Protocol(Parser p, Serializer s) {
		this.parser = p;
		this.serializer = s;
	}

	public String processInput(String inputLine) {
		String outputLine = null;
		if (state == WAITforDATA) {
			oppName = parser.parseName(inputLine);
			outputLine = serializer.serializeString("Start");
			if (descriptor == 0)
				state = WAITING; // if color of player on this computer is black
									// than wait for his move.
			else if (descriptor == 1)
				state = MOVING; // if color of player on this computer is white
								// than listen for move of the opponent and make
								// it.
		} else if (state == GAMEOVER) {
			if (inputLine.equalsIgnoreCase("y")) {
				if (descriptor == 0)
					state = WAITING;
				else if (descriptor == 1)
					state = MOVING;
			} else {
				outputLine = "Bye.";
				state = SENTauthANSWER;
			}

		}
		return outputLine;
	}

	public String processStartData(int descriptor, String playerName) {
		String outputLine = null;
		this.descriptor = descriptor;
		if (state == SENTauthANSWER) {
			outputLine = serializer.serializeStartData(descriptor, playerName);
			state = WAITforDATA;
		}
		return outputLine;
	}

	public int getState() {
		return state;
	}

	public String processGameOver() {
		String outputLine = null;
		state = GAMEOVER;
		outputLine = serializer.serializeGameOver();
		return outputLine;
	}

	public boolean processGOanswer(String answer) {
		// TODO Auto-generated method stub
		return false;
	}

	public String processOppName(String inputLine) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
