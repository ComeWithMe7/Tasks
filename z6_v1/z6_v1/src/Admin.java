import java.io.Serializable;
import java.util.*;
public class Admin implements Serializable {

    private int number;
    private Date date=new Date();

    public void block(Client cl) {
        if(cl.getAccount().getBalance()<0)
            cl.getCard().setIsBlocked(true);
    }

    public Date getDate() {
        return date;
    }

    Admin(int inNumber) {
        this.number = inNumber;
    }

    public String toString() {
        return ("Number: " + number + " | Date: " + date);
    }

}
