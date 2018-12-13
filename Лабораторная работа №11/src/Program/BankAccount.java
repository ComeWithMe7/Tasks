package Program;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;


public class BankAccount implements Serializable {
    // Поля.
    private static String fieldName = "accNumber";
    private static int nextAccNum = 1;
    private int accNumber;
    private int accountCode;
    private String ownerSurname;
    private String accountCurrency;
    private double sum;
    private Date openingDay;
    private double percentPerYear;
    private boolean isDeleted;


    boolean isNotMainForeignCurrency(String accountCurrency) {
        if(     accountCurrency.compareTo("USD") != 0 &&
                accountCurrency.compareTo("EUR") != 0 &&
                accountCurrency.compareTo("CHF") != 0 &&
                accountCurrency.compareTo("GBP") != 0 &&
                accountCurrency.compareTo("RUB") != 0 ||
                accountCurrency.isEmpty()) {
            return true;
        }
        return false;
    }

    boolean isUnknownOwner(String surname){
        if(surname.isEmpty())
            return true;
        return false;
    }

    boolean isRightPercent(double percent){
        return(percent >= 0);
    }

    public BankAccount(){
        this.accNumber = nextAccNum++;
        this.accountCode = -1;
        this.ownerSurname = "Unknown";
        this.accountCurrency = null;
        this.sum = -1;
        this.openingDay = new Date();
        this.percentPerYear = -1;
        this.isDeleted = false;
    }

    // Конструктор c параметрами.
    public BankAccount( int accountCode, String ownerSurname, String accountCurrency, double sum, Date openingDay,
                        double inPercentPerYear) throws IllegalArgumentException
    {
        if(!isRightPercent(inPercentPerYear))
            inPercentPerYear = 7.5;


        this.accNumber = nextAccNum++;
        this.accountCode = accountCode;

        if(isUnknownOwner(ownerSurname))
            ownerSurname = "Unknown";
        this.ownerSurname = ownerSurname;

        if(isNotMainForeignCurrency(accountCurrency))
            accountCurrency = "BYN";
        this.accountCurrency = accountCurrency;
        this.sum = sum;
        this.openingDay = openingDay;
        this.percentPerYear = inPercentPerYear;
        this.isDeleted = false;
    }

    public BankAccount(String s) throws ParseException {
        StringTokenizer str = new StringTokenizer(s, " ,");
        if (str.hasMoreTokens()) {
            this.accNumber = Integer.parseInt(str.nextToken());
            this.accountCode = Integer.parseInt(str.nextToken());
            this.ownerSurname = str.nextToken();

            this.accountCurrency = str.nextToken();
            if(isNotMainForeignCurrency(accountCurrency))
                this.accountCurrency = "BYN";

            this.sum = Double.parseDouble(str.nextToken());

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            try {
                this.openingDay = sdf.parse(str.nextToken());
            }
            catch(Exception excp) {
                openingDay = sdf.parse("01.01.2018");
            }

            this.percentPerYear = Double.parseDouble(str.nextToken());
            this.isDeleted = false;
        }
    }

    public Object getFieldName() throws NoSuchFieldException, IllegalAccessException {
        Field field = this.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(this);
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
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    @Override
    public String toString() {
        return ("| " + accNumber + " | " + accountCode + " | " + ownerSurname + " | " + accountCurrency + " | " + sum +
                " | " + openingDay + " | " + percentPerYear + "%|");
    }
}

