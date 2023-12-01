package exceptions;

public class CreditAccount extends Account{

    /**
     * Создание нового счета
     * @param accountOwner - владелец счета
     * @param startBalance - стартовый баланс счета
     * @return создается новый счет
     * @throws IllegalArgumentException Попытка создать счет с отрицательным балансом
     */
    public static CreditAccount createCreditAccount (Client accountOwner, double startBalance) throws IllegalArgumentException {
        if (startBalance < 0){
            throw new IllegalArgumentException("Попытка создать счет с отрицательным балансом.\nСчет не создан");}
        return new CreditAccount(accountOwner, startBalance);
    }
    protected CreditAccount(Client accountOwner, double currentBalance){
        super (accountOwner, currentBalance);
    }
}
