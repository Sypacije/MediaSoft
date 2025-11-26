import java.time.LocalDateTime;

class BankAccount {
    private String ownerName;
    private int balance;
    private LocalDateTime openingDate;
    private boolean isBlocked;

    BankAccount(String name) {
        ownerName = name;
        balance = 0;
        openingDate = LocalDateTime.now();
        isBlocked = false;
    }

    public boolean deposit(int amount) { // пополнение счета
        if (amount > 0) {
            balance += amount;
            System.out.println("Счет успешно пополнен на: " + amount);
            System.out.println("Текущий баланс: " + balance + "\n");
            return true;
        }

        else {
            System.out.println("Неверно введена сумма. Ваш баланс: " + balance + "\n");
            return false;
        }
    }

    public boolean withdraw(int amount) { // снятие денег со счета
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Успешно списано " + amount);
            System.out.println("Текущий баланс: " + balance + "\n");
            return true;
        }

        else {
            System.out.println("Недостаточно средств или неверно введена сумма. Ваш баланс: " + balance + "\n");
            return false;
        }

    }

    public boolean transfer(BankAccount otherAccount, int amount) {
        if (amount <= balance) {
            this.balance -= amount;
            otherAccount.balance += amount;
            System.out.println("Успешно переведено " + amount + " на счет " + otherAccount.ownerName);
            System.out.println("Текущий баланс: " + balance + "\n");
            return true;
        }

        else {
            System.out.println("Недостаточно средств или неверно введена сумма. Ваш баланс: " + balance + "\n");
            return false;
        }
    }

}

public class task1 {
    public static void main(String[] args) {
        BankAccount testAcc1 = new BankAccount("Test1");
        BankAccount testAcc2 = new BankAccount("Test2");

        testAcc1.deposit(1000);
        testAcc1.withdraw(1100);
        testAcc1.transfer(testAcc2, 1100);
        testAcc1.withdraw(200);
        testAcc1.transfer(testAcc2, 200);
    }
}

