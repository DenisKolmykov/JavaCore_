package exceptions;


public abstract class Account {

    //region Поля
    protected Client accountOwner; // владелец счета
    protected double currentBalance; // текущий баланс счета

    protected Account(Client accountOwner, double currentBalance){
        this.accountOwner = accountOwner;
        this.currentBalance = currentBalance;
    }
    //endregion

    //region Свойства
    public double getCurrentBalance(){
        return currentBalance;
    }

    public Client getAccountOwner(){
        return accountOwner;
    }
    //endregion

    /**
     * пополнение счета
     * @param topUpAmount - сумма пополнения счета
     * @throws IllegalArgumentException Введено отрицательное значение
     */
    public void setTopUpAmount(double topUpAmount) throws IllegalArgumentException {
        if (topUpAmount < 0){
            throw new IllegalArgumentException("Введено отрицательное значение.", this.getClass().getSimpleName(), currentBalance);}
        this.currentBalance += topUpAmount;
    }

    /**
     * снятите со счета
     * @param withdrawAmount - сумма снятия
     * @throws IllegalArgumentException Введено отрицательное значение
     * @throws InsufficientFundsException Недостаточно средств на счете

     */
    public void setWithdrawAmount(double withdrawAmount) throws IllegalArgumentException, InsufficientFundsException {
        if (withdrawAmount < 0){
            throw new IllegalArgumentException("Введено отрицательное значение.", this.getClass().getSimpleName(), currentBalance);}
        if (this.currentBalance - withdrawAmount < 0){
            throw new InsufficientFundsException("Недостаточно средств на счете.",this.getClass().getSimpleName(), currentBalance);}
        this.currentBalance -= withdrawAmount;
    }

    @Override
    public String toString() {
        return String.format("Клиент: %s, текущий баланс счета %s: %.2f\n", accountOwner.getName(), this.getClass().getSimpleName(), currentBalance);
    }
}
