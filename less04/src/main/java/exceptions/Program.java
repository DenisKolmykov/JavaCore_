package exceptions;

import java.util.Scanner;

    //region Описание задачи
/*
1. Создать программу управления банковским счетом (Account).
Программа должна позволять пользователю вводить начальный баланс счета, сумму депозита и сумму снятия средств.
При этом она должна обрабатывать следующие исключительные ситуации:
Попытка создать счет с отрицательным начальным балансом должна вызывать исключение IllegalArgumentException с соответствующим сообщением.
Попытка внести депозит с отрицательной суммой должна вызывать исключение IllegalArgumentException с соответствующим сообщением.
Попытка снять средства, сумма которых превышает текущий баланс, должна вызывать исключение InsufficientFundsException
с сообщением о недостаточных средствах и текущим балансом.
Продемонстрируйте работу вашего приложения:
Программа должна обрабатывать все исключения с помощью конструкции try-catch, выводя соответствующие сообщения об ошибках.

2*.
Создать несколько типов счетов, унаследованных от Account, например: CreditAcciunt, DebitAccount.
Создать класс (Transaction), позволяющий проводить транзакции между счетами (переводить сумму с одного счета на другой)
Класс Transaction должен возбуждать исключение в случае неудачной попытки перевести деньги с одного счета на другой.
Продемонстрируйте работу вашего приложения:
Программа должна обрабатывать все исключения с помощью конструкции try-catch, выводя соответствующие сообщения об ошибках.
 */
//endregion

public class Program {
    private static final Scanner scanner = new Scanner(System.in);

    //region Метод проверки введенного значения
    private static double checkInput (){
        boolean flag = false;
        String transactionAmountStr = null;
        double transactionAmount = 0;
        do {
            System.out.print("Введите сумму транзакции: ");
            transactionAmountStr = scanner.nextLine();
            try {
                transactionAmount = Double.parseDouble(transactionAmountStr);
                flag = true;
            } catch (NumberFormatException e){
                System.out.println("!Введено некорректное значение (не число)");
            }
        } while (!flag);
        return transactionAmount;
    }
    //endregion

    //region Метод транзакция перевод с одного счета на другой
    /**
     * транзакция перевод с одного счета на другой
     * @param debitAccount
     * @param creditAccount
     * @throws IllegalArgumentException - Введено отрицательное значение
     */
    private static void getTransactionBetweenAccounts (Account debitAccount, Account creditAccount) throws IllegalArgumentException {
        boolean flag = false;
        while (!flag) {
            System.out.println("\nУкажите счет c которого произвести транзакцию:");
            System.out.printf("1. DebitAccount (остаток: %.2f)\n", debitAccount.getCurrentBalance());
            System.out.printf("2. CreditAccount (остаток: %.2f)\n", creditAccount.getCurrentBalance());
            System.out.println("0 - выход");
            System.out.print("--> ");
            String choice = scanner.nextLine();

            Account accountFrom = debitAccount;
            Account accountTo = creditAccount;
            switch (choice) {
                case ("1"):
                    accountFrom = debitAccount;
                    accountTo = creditAccount;
                    break;
                case ("2"):
                    accountFrom = creditAccount;
                    accountTo = debitAccount;
                    break;
                case ("0"):
                    System.out.println("Транзакция отменена.");
                    System.out.printf("Остаток на счете %s: %.2f\n",accountFrom.getClass().getSimpleName(), accountFrom.getCurrentBalance());
                    System.out.printf("Остаток на счете %s: %.2f\n",accountTo.getClass().getSimpleName(), accountTo.getCurrentBalance());
                    flag = true;
                    break;
            }
            if (!flag) {
                double transactionAmount = checkInput();

                try {
                    Transaction.createTransaction(accountFrom, accountTo, transactionAmount);
                } catch (InsufficientFundsException e) {
                    System.out.printf("%s Остаток на счете %s: %.2f\n", e.getMessage(), accountFrom.getClass().getSimpleName(), e.getAmount());
                    System.out.printf("Остаток на счете %s: %.2f\n", accountTo.getClass().getSimpleName(), accountTo.getCurrentBalance());
                    System.out.println("Транзакция отменена.");
                    flag = true;
                }
                if (!flag) {
                    System.out.println("Транзакция выполнена УСПЕШНО.");
                    System.out.printf("Остаток на счете %s: %.2f\n", accountFrom.getClass().getSimpleName(), accountFrom.getCurrentBalance());
                    System.out.printf("Остаток на счете %s: %.2f\n", accountTo.getClass().getSimpleName(), accountTo.getCurrentBalance());
                }
            }
        }
    }
    //endregion

    //region Метод перевод со счета / пополнение счета
    /**
     * перевод со счета / пополнение счета
     * @param account - счет по которому проводятся операции
     * @throws IllegalArgumentException Введено отрицательное значение
     * @throws InsufficientFundsException Недостаточно средств на счете
     */
    private static void getAccountTransactions(Account account) throws InsufficientFundsException, IllegalArgumentException {
        boolean flag = false;
        while (!flag) {
            System.out.printf("\nУкажите тип транзакции по счету %s (остаток: %.2f):\n", account.getClass().getSimpleName(), account.getCurrentBalance());
            System.out.println("1. Пополнение счета");
            System.out.println("2. Снятие со счета");
            System.out.println("0 - выход");
            System.out.print("--> ");

            String choice = scanner.nextLine();

            switch (choice) {
                case ("1"):
                    // пополнение  счета
                    account.setTopUpAmount(checkInput());
                    System.out.println(account);
                    break;
                case ("2"):
                    // снятие со счета
                    account.setWithdrawAmount(checkInput());
                    System.out.println(account);
                    break;
                case("0"):
                    System.out.printf("Операции по счету %s завершены\nОстаток на счете %s: %.2f\n", account.getClass().getSimpleName(), account.getClass().getSimpleName(), account.getCurrentBalance());
                    flag = true;
                    break;
            }
        }
    }
    //endregion

    //region Методы создания Debit и Credit счетов (с вводом начального баланса)
    /**
     * создание Debit счета (с вводом начального баланса)
     * @param client - клиент
     * @return - объект класса DebitAccount
     * @throws IllegalArgumentException Введено отрицательное значение
     */
    private static Account createDebitAccountMethod(Client client) throws IllegalArgumentException {
        System.out.println("\nСоздание счета DebitAccount. Введите начальный баланс счета:");
        double startBalanceDebit = checkInput();
        DebitAccount debitAccountClient1 = DebitAccount.createDebitAccount(client, startBalanceDebit);
        if (debitAccountClient1 != null) {
            System.out.println(debitAccountClient1);
        }
        return debitAccountClient1;
    }

    /**
     * создание Credit счета (с вводом начального баланса
     * @param client - клиент
     * @return - объект класса CreditAccount
     * @throws IllegalArgumentException Введено отрицательное значение
     */
    private static Account createCreditAccountMethod(Client client) throws IllegalArgumentException {
        // создание Credit счета
        System.out.println("\nСоздание счета CreditAccount. Введите начальный баланс счета:");
        double startBalanceCredit = checkInput();
        CreditAccount creditAccountClient1 = CreditAccount.createCreditAccount(client, startBalanceCredit);
        if (creditAccountClient1 != null) {
            System.out.println(creditAccountClient1);
        }
        return creditAccountClient1;
    }
    //endregion


    /**
     * точка вхоода (для запуска приложения)
     * @param args - основные парменты
     */
    public static void main(String[] args) {
        Client client1 = new Client("Client1");
        Account debitAccountClient1 = null;
        String balanceDebitAccount = "";

        Account creditAccountClient1 = null;
        String balanceCreditAccount = "";

        boolean flag = false;
        while (!flag) {
            if (debitAccountClient1 != null){
                balanceDebitAccount = String.format("(остаток %.2f)", debitAccountClient1.getCurrentBalance());
            }
            if (creditAccountClient1 != null){
                balanceCreditAccount = String.format("(остаток %.2f)", creditAccountClient1.getCurrentBalance());
            }
            System.out.printf("\nТранзакции по счетам Клиента %s:\n",client1.getName());
            System.out.printf("1. Cчет DebitAccount %s\n", balanceDebitAccount);
            System.out.printf("2. Cчет CreditAccount %s\n", balanceCreditAccount);
            System.out.println("3. Транзакции между счетами");
            System.out.println("0 - выход");
            System.out.print("--> ");
            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case ("1"):
                        if (debitAccountClient1 == null) {
                            debitAccountClient1 = createDebitAccountMethod(client1);
                        } else {
                            getAccountTransactions(debitAccountClient1);
                        }
                        break;
                    case ("2"):
                        if (creditAccountClient1 == null) {
                            creditAccountClient1 = createCreditAccountMethod(client1);
                        } else {
                            getAccountTransactions(creditAccountClient1);
                        }
                        break;
                    case ("3"):
                        if (debitAccountClient1 != null && creditAccountClient1 != null) {
                            getTransactionBetweenAccounts(debitAccountClient1, creditAccountClient1);
                            break;
                        } else {
                            if (debitAccountClient1 == null) System.out.println("У клиента еще НЕ ОТКРЫТ DebitAccount");
                            if (creditAccountClient1 == null) System.out.println("У клиента еще НЕ ОТКРЫТ CreditAccount");
                            break;
                        }

                    case ("0"):
                        flag = true;
                        break;
                }

            } catch (IllegalArgumentException | InsufficientFundsException e) {
                System.out.printf("%s\n", e.getMessage(), e.getAccount(), e.getAmount());
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

