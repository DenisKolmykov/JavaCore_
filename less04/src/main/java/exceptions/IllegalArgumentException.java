package exceptions;

public class IllegalArgumentException extends MyException{
    public IllegalArgumentException(String message, String accountName, double amount) {
        super(message, accountName, amount);
    }

    public IllegalArgumentException(String message) {
        super(message);
    }
}
