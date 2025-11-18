package Теория;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.ArrayList;
import java.util.Objects;

class BankAccount {
    private String ownerName;
    
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    private int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    private LocalDateTime openingDate;
    
    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDateTime openingDate) {
        this.openingDate = openingDate;
    }

    private boolean isBlocked;

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    private ArrayList<Integer> number;

    public ArrayList<Integer> getNumber() {
        return number;
    }

    public void setNumber(ArrayList<Integer> number) {
        this.number = number;
    }

    BankAccount(String name) {
        ownerName = name;
        balance = 0;
        openingDate = LocalDateTime.now();
        isBlocked = false;
        number = new ArrayList<Integer>();
        Random dij = new Random();
        for (int i = 0; i < 8; i++) {
            number.add(dij.nextInt(10));
        }
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

    public String toString() {
        String numString = "";
        for (int i = 0; i < 8; i++) {
            numString += Integer.toString(getNumber().get(i));
        }
         return "Информация о счете:\n" + "Владелец счета: " + getOwnerName() + "\n" + "Номер счета: " + numString + "\n" + "Баланс счета: "
                 + getBalance() + "\n" + "Дата открытия счета: " + getOpeningDate() + "\n" + "Счет заблокирован? "
                 + (isBlocked() ? "Да" : "Нет") + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true; // сравнение по ссылке
        if (o == null || getClass() != o.getClass())
            return false;

        BankAccount that = (BankAccount) o;

        // сравнение по номеру счёта
        return Objects.equals(this.number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
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

        System.out.println(testAcc1.toString());

        System.out.println(testAcc1.equals(testAcc2));
        System.out.println(testAcc1.equals(testAcc1));
    }
}

