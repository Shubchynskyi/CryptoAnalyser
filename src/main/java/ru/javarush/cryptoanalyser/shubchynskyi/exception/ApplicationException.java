package ru.javarush.cryptoanalyser.shubchynskyi.exception;

public class ApplicationException extends RuntimeException {


    public ApplicationException() {
    }
    @SuppressWarnings("unused")
    public ApplicationException(String message) {
        super(message);
    }
    @SuppressWarnings("unused")
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
    @SuppressWarnings("unused")
    public ApplicationException(Throwable cause) {
        super(cause);
    }
}
