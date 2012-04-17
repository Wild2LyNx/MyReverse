package my;

public interface Player {
	void makeMove(GameField gameField);

	int getState();

	void stateChanged(GameField gameField, Cell cell);
}
