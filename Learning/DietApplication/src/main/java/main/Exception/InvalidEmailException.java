package main.Exception;

public class InvalidEmailException extends Exception{
	public InvalidEmailException (String message) {
		super(message);
	}
}
