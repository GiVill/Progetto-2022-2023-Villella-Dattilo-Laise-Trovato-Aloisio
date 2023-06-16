package it.unical.ea.VintedProject.config.interceptor;

public class ManyRequestException extends RuntimeException {

    public ManyRequestException(String message) {
        super(message);
    }
}
