import java.util.*;
import java.io.*;
public class Client implements Serializable{

    private int clientNumber;
    private Date date= new Date();
    private Account account;
    private Card card;

    Client(Account acc,int inClientNumber) {
        this.clientNumber = inClientNumber;
        account = acc;
        card = new Card(acc);
    }

    // Оплата заказа.
    public void makePay(int pay) {
        account.setBalance(account.getBalance()-pay);
    }

    // Перевод.
    public void transfer(Account acc, int count) {
        acc.setBalance(acc.getBalance()+count);
        account.setBalance(account.getBalance()-count);
    }
    public void blockCard() {
        card.setIsBlocked(true);
    }

    public void annulateAccount() {
        account.setBalance(0);
    }

    public Account getAccount() {
        return this.account;
    }

    public Card getCard() {
        return this.card;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Date getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return ("Client Number: " + clientNumber + " | " + date +" |  " + account.getBalance() + " | Blocking card status: " + card.getIsBlocked());
    }
}
