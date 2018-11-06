import java.util.Date;
import java.io.*;
import java.text.SimpleDateFormat;


//enum Currency{ USD, EUR, CHF, GBP, RUB, BYN };

public class BankAccount implements Serializable {
    private static int nextAccNum = 1;
    // Поля.
    private final int accNumber;
    private int accountCode;
    private String ownerSurname;
    private String accountCurrency;
    private double sum;
    private Date openingDay;
    private double percentPerYear;
    private boolean deleted;

    boolean isRightInput( int accountCode, String ownerSurname, String accountCurrency, double sum,
                         double percentPerYear) {
        if(accountCode <= 0) return false;
        if(ownerSurname == null) return false;
        if(!isRightCurrency(accountCurrency)) return false;
        if(sum < 0) return false;
        if(percentPerYear < 0 || percentPerYear >100) return false;
        return true;
    }

    boolean isRightCurrency(String accountCurrency) {
        if(     accountCurrency.compareTo("USD") != 0 &&
                accountCurrency.compareTo("EUR") != 0 &&
                accountCurrency.compareTo("CHF") != 0 &&
                accountCurrency.compareTo("GBP") != 0 &&
                accountCurrency.compareTo("RUB") != 0 &&
                accountCurrency.compareTo("BYN") != 0 &&
                accountCurrency.isEmpty())
        {
            return false;
        }
        return true;
    }

    public BankAccount(){
        this.accNumber = nextAccNum++;
        this.accountCode = -1;
        this.ownerSurname = null;
        this.accountCurrency = null;
        this.sum = -1;
        this.openingDay = new Date();
        this.percentPerYear = -1;
        this.deleted = false;
    }

    // Конструктор c параметрами.
    public BankAccount( int accountCode, String ownerSurname, String accountCurrency, double sum,
                       double percentPerYear) throws IllegalArgumentException
    {
        if(!isRightInput( accountCode, ownerSurname, accountCurrency, sum, percentPerYear)) {
            throw new IllegalArgumentException();
        }

        this.accNumber = nextAccNum++;
        this.accountCode = accountCode;
        this.ownerSurname = ownerSurname;
        this.accountCurrency = accountCurrency;
        this.sum = sum;
        this.openingDay = new Date();
        this.percentPerYear = percentPerYear;
        this.deleted = false;
    }


    public byte[] toByteArray() {
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();          // создание потока
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(this);
            objectStream.flush();
            objectStream.close();
            return byteStream.toByteArray();
        }
        catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    static public BankAccount fromByteArray(byte[] inputByteArray) {
        try {
            ObjectInputStream objectStream = new ObjectInputStream(new ByteArrayInputStream(inputByteArray));
            return (BankAccount) objectStream.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    public int getAccNumber() {
        return accNumber;
    }

    public int getAccountCode() {
        return accountCode;
    }

    public String getOwnerSurname() {
        return ownerSurname;
    }

    public String getAccountCurrency() {
        return accountCurrency;
    }

    public double getSum() {
        return sum;
    }

    public Date getOpeningDay() {
        return openingDay;
    }

    public double getPercentPerYear() {
        return percentPerYear;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return ("| " + accNumber + " | " + accountCode + " | " + ownerSurname + " | " + accountCurrency + " | " + sum +
                " | " + openingDay + " | " + percentPerYear + "%|");
    }
}

