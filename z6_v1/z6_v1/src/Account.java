import java.io.*;
import java.util.*;
public class Account implements Serializable{

    private Date date = new Date();
    private int balance;

    Account(){
        balance = 0;
    }
    Account(int inBalance) {
        this.balance = inBalance;
    }

    public Date getDate() {
        return date;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int inBalance) {
        this.balance = inBalance;
    }
}
