package exceptions;

public class InsufficientFundsException extends MyException{
    public InsufficientFundsException(String message, String accountName, double amount) {
        super(message, accountName, amount);
    }
}
