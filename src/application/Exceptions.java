package application;

class InvalidMoveException extends Exception {
	public InvalidMoveException(String message) {
		super(message);
	}
}

class EndGameException extends Exception {
	public EndGameException(String message) {
		super(message);
	}
}