package exceptions;

import java.util.Date;

public class Transaction {

    //region Поля
    private static int id;
    private Date date;
    private double transactionAmount;
    private Account accountFrom;
    private Account accountTo;
    //endregion
    public static Transaction createTransaction (Account accountFrom, Account accountTo, double transactionAmount) throws IllegalArgumentException, InsufficientFundsException {
        if (transactionAmount < 0){
            throw new IllegalArgumentException("Попытка создать транзакцию с отрицательноой суммой.\nТранзакция не создана");}
        return new Transaction(accountFrom, accountTo, transactionAmount);
    }

    /**
     * конструктор Транзакции перевод между двумя счетами
     * @param accountFrom - счет откуда идет перевод
     * @param accountTo - счет куда идет зачисление
     * @param transactionAmount - сумма перевода
     * @throws IllegalArgumentException Введено отрицательное значение
     * @throws InsufficientFundsException Недостаточно средств на счете
     */
    private Transaction(Account accountFrom, Account accountTo, double transactionAmount) throws IllegalArgumentException, InsufficientFundsException {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        if (transactionAmount < 0){
            throw new IllegalArgumentException("Введено отрицательное значение.", accountFrom.getClass().getSimpleName(), transactionAmount);}
        this.transactionAmount = transactionAmount;
        id++;
        this.date = new Date();
//        try {
            changeBalance();
//        }catch (InsufficientFundsException e){
//            System.out.printf("%s\nТекущий баланс %s: %.2f\n", e.getMessage(),accountFrom.getClass().getSimpleName(), e.getAmount());
//        }
    }

    /**
     * изменение остатков по счетам: уменшаем от куда перевод, увеличиваем куда пеервод
     * @throws InsufficientFundsException Недостаточно средств на счете
     */
    private void changeBalance() throws InsufficientFundsException {
        if (this.accountFrom.currentBalance - this.transactionAmount < 0){
            throw new InsufficientFundsException("Недостаточно средств.", accountFrom.getClass().getSimpleName(), this.accountFrom.currentBalance);}
        this.accountFrom.currentBalance -= this.transactionAmount;
        this.accountTo.currentBalance += this.transactionAmount;
    }

    @Override
    public String toString() {
        return String.format("id %d %s from %s to %s сумма транзакции: %.2f\n", id, date, accountFrom.getClass().getSimpleName(), accountTo.getClass().getSimpleName(), transactionAmount);
    }
}
