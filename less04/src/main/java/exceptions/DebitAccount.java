package exceptions;

public class DebitAccount extends Account{

    /**
     * Создание нового счета
     * @param accountOwner - владелец счета
     * @param startBalance - стартовый баланс счета
     * @return создается новый счет
     * @throws IllegalArgumentException Попытка создать счет с отрицательным балансом
     */
    public static DebitAccount createDebitAccount (Client accountOwner, double startBalance) throws IllegalArgumentException {
        if (startBalance < 0){
            throw new IllegalArgumentException("Попытка создать счет с отрицательным балансом.\nСчет не создан");}
        return new DebitAccount(accountOwner, startBalance);
    }
    protected DebitAccount(Client accountOwner, double currentBalance) {
        super(accountOwner, currentBalance);
    }
}
