package exceptions;

public abstract class MyException extends Exception{
    private final double amount;
    private String accountName;

    public double getAmount() {
            return amount;
    }
    public String getAccount() {
        return accountName;
    }

    public MyException(String message, String accountName, double amount) {
        super(message);
        this.amount = amount;
        this.accountName = accountName;
    }

    public MyException(String message) {
        super(message);
        this.amount = 0;
        this.accountName = "";
    }
}

