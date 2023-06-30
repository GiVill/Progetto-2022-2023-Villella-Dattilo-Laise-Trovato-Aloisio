package it.unical.ea.VintedProject.exception;

public class UserException extends Exception{
    //It's called when there's an exception with User
    public UserException(String message) {
        super(message);
    }
}
